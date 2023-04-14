package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentsService {

    private final GenericDAO genericDAO;

    public PaymentsService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    /**
     * Makes a royalty payment to the record label and the artists associated with the song
     * @param songId song for which the royalty payment is to be made
     * @param month month for which the royalty payment is to be made
     * @param year year for which the royalty payment is to be made
     * @return String containing the status of the payment
     */
    public String makeRoyaltyPayment(int songId, int month, int year) {
        Connection connection = genericDAO.createConnection();
        try {

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet songHistoryMonth = statement.executeQuery("SELECT * FROM songHistory WHERE month = "+month+" AND songId = "+songId+";");
            if(songHistoryMonth.next()) {
                return "Royalty payment for this song was already paid in the given month";
            }
            ResultSet royaltiesResultSet = statement.executeQuery("SELECT playCount * royaltyRate AS amount FROM songs");
            statement.executeQuery("INSERT INTO songHistory (songId, month, year, playCount) VALUES ("+songId+", "+month+", "+year+", (SELECT playCount FROM songs WHERE songId = "+songId+"))");
            statement.execute("UPDATE songs SET playCount = 0 WHERE songId = "+songId);
            //get the royalty generated for the mentioned song in the month specified
            royaltiesResultSet.next();
            double royaltiesGenerated = Double.parseDouble(new DecimalFormat("#.##").format(royaltiesResultSet.getDouble(1)));
            //find the record label of the primary artist of the given song
            ResultSet recordLabelResultSet = statement.executeQuery("SELECT recordId FROM artists WHERE artistId = (SELECT primaryArtist FROM songs WHERE songId = " + songId + ")");
            recordLabelResultSet.next();
            int recordLabelId = recordLabelResultSet.getInt(1);
            //count the collaborators of the song
            ResultSet countArtistsResultSet = statement.executeQuery("select count(*) from creates where songId = " + songId + ";");
            countArtistsResultSet.next();
            int artistCount = countArtistsResultSet.getInt(1) + 1;
            //make payment to record label
            statement.executeQuery("INSERT INTO serviceAccount (date, amount, type) VALUES (curdate(), "+royaltiesGenerated + ", 'Debit');");
            ResultSet lastInsertIdResultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            lastInsertIdResultSet.next();
            int transactionId = lastInsertIdResultSet.getInt(1);
            //keep a track of the Record Label payment
            statement.executeQuery("INSERT INTO receives (transactionId, recordId) VALUES (" + transactionId + ", " + recordLabelId + ");");
            //get artists associated to the song
            ResultSet artistsResultSet = statement.executeQuery("SELECT artistId FROM creates WHERE songId = " + songId + " UNION " +
                    " SELECT primaryArtist FROM songs WHERE songId = " + songId);
            List<Integer> artists = new ArrayList<>();
            while (artistsResultSet.next()) {
                artists.add(artistsResultSet.getInt(1));
            }
            double artistPayment = (royaltiesGenerated * 0.7) / artistCount;
            System.out.println(artists);
            //making payments to each artist
            for (int i = 0; i < artistCount; i++) {
                statement.executeQuery("INSERT INTO distributesRoyalties (artistId, recordId, songId, date, amount) " +
                        " VALUES (" + artists.get(i) + ", " + recordLabelId + ", " + songId + ", curdate(), " + artistPayment + ")");
            }
            //update song royalty paid status to yes
            statement.executeQuery("UPDATE songs SET royaltyStatus = 'yes' WHERE songId = "+songId);
            connection.commit();
            return "Payment Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null){
                try{
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep){
                    excep.printStackTrace();
                }
            }
            return "Payment Failed. Transaction is rolled back";
        }finally {
            closeConnection(connection);
        }
    }

    /**
     * Generates the royalty for the given month
     * @param month month for which the royalty is to be generated
     * @return List of maps containing the songId and the royalty generated for the song
     */
    public List<Map<String, Object>> generateMonthlyRoyalty(int month) {
        List<Map<String, Object>> result = new ArrayList<>();
        Connection connection = genericDAO.createConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT s.songId,  " +
                    "  (sh.playCount * s.royaltyRate) AS royaltyGenAmount" +
                    "  FROM songs s" +
                    "  JOIN songHistory sh ON s.songId = sh.songId" +
                    "  WHERE sh.month = "+month+";");
            while (resultSet.next()) {
                result.add(Map.of("songId", resultSet.getInt(1), "Royalty generated in month "+month, new DecimalFormat().format(resultSet.getDouble(2))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.clear();
            result.add(Map.of("Error Occurred", e.getMessage()));
        }finally {
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Makes a payment to the podcast host for the given month
     * @param month month for which the payment is to be made
     * @return String containing the status of the payment
     */
    public String  makePaymentToPodcastHost(int month) {
        Connection connection = genericDAO.createConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet bonusResultSet = statement.executeQuery("SELECT createdBy.hostId, SUM(episodes.AdvertisementCount * 10) as bonus\n" +
                    " FROM podcasts JOIN createdBy ON podcasts.podcastId = createdBy.podcastId " +
                    " JOIN episodes ON episodes.podcastId = podcasts.podcastId " +
                    " WHERE MONTH(episodes.releaseDate) = "+month +
                    " GROUP BY createdBy.hostId;");
            Map<Integer, Double> hostPayments = new HashMap<>();
            while(bonusResultSet.next()) {
                hostPayments.put(bonusResultSet.getInt(1), Double.parseDouble(new DecimalFormat("#.##").format(bonusResultSet.getDouble(2))));
            }
            ResultSet flatFeeResultSet = statement.executeQuery("SELECT\n" +
                    "createdBy.hostId, podcasts.flatFee * COUNT(episodes.number) AS FlatFee\n" +
                    "FROM\n" +
                    "createdBy\n" +
                    "JOIN podcasts ON createdBy.podcastId = podcasts.podcastId\n" +
                    "JOIN episodes ON podcasts.podcastId = episodes.podcastId\n" +
                    "WHERE MONTH(episodes.releaseDate) = "+month+
                    "GROUP BY createdBy.hostId;");
            while(flatFeeResultSet.next()) {
                int hostId = flatFeeResultSet.getInt(1);
                double payment = hostPayments.get(hostId) + Double.parseDouble(new DecimalFormat("#.##").format(flatFeeResultSet.getDouble(2)));
                hostPayments.put(hostId, payment);
            }
            for (Map.Entry<Integer, Double> entry : hostPayments.entrySet()) {
                ResultSet insertIntoServiceAccount = statement.executeQuery("INSERT INTO serviceAccount (date, amount, type)\n" +
                        "VALUES (curdate(), "+entry.getValue()+", 'Debit');");
                ResultSet lastInsertIdResultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
                lastInsertIdResultSet.next();
                int transactionId = lastInsertIdResultSet.getInt(1);
                ResultSet insertIntoGivesPaymentTo = statement.executeQuery("INSERT INTO givesPaymentTo (transactionId, hostId)\n" +
                        "VALUES ("+transactionId+", "+entry.getKey()+");");
                connection.commit();
            }
            return "Payment to Podcast Host is Successful";
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null){
                try{
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                }catch(SQLException excep){
                    excep.printStackTrace();
                }
            }
            return "Payment to Podcast Host has Failed. Transaction is rolled back";
        }finally {
            closeConnection(connection);
        }
    }

    /**
     * receives payment from the subscribers for the given month
     * @return String containing the status of the payment
     */
    public String receivePaymentFromSubscribers() {
        Connection connection = genericDAO.createConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet subscribedUser = statement.executeQuery("SELECT userId, subscriptionFee FROM users WHERE subscriptionStatus='active';");

            Map<Integer, Double> users = new HashMap<>();
            while (subscribedUser.next()) {
                users.put(subscribedUser.getInt(1), subscribedUser.getDouble(2));
            }
            for (Map.Entry<Integer, Double> user : users.entrySet()) {
                statement.executeQuery("INSERT INTO serviceAccount (date, amount, type) VALUES (curdate(),"+user.getValue()+",'Credit');");
                ResultSet lastInsertIdResultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
                lastInsertIdResultSet.next();
                int transactionId = lastInsertIdResultSet.getInt(1);
                statement.executeQuery("INSERT pays (userId, transactionId) VALUES ("+user.getKey()+", "+transactionId+");");
            }

            System.out.println("Payment received from subscribers");
            connection.commit();
            return "Payment received from subscribers";
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null){
                try{
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                }catch(SQLException excep){
                    excep.printStackTrace();
                }
            }
            return "Payment from subscribers failed. Transaction is rolled back";
        }finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            System.out.println("Closing connection");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
