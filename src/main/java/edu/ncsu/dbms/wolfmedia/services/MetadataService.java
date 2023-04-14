package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.models.Episodes;
import edu.ncsu.dbms.wolfmedia.models.Songs;
import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MetadataService {

    private final GenericDAO genericDAO;

    public MetadataService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    /**
     * This method is used to add the play count of a song
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean addPlayCountSong(Integer songId, Integer playCount) throws Exception {
        return genericDAO.executeUpdate("UPDATE songs SET playCount = " + playCount + " WHERE songId = " + songId);
    }

    /**
     * This method is used to add the monthly listeners of an artist
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean addMonthlyListeners(Integer artistId, Integer monthlyListeners) throws Exception {
        String query = "UPDATE artists SET monthlyListeners = " + monthlyListeners + " WHERE artistId = " + artistId;
        return genericDAO.executeUpdate(query);

    }

    /**
     * This method is used to add the total subscribers of a podcast
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean addTotalSubscribers(Integer podcastId, Integer totalSubscribers) throws Exception {
        String query = "UPDATE podcasts SET totalSubscribers = " + totalSubscribers + " WHERE podcastId = " + podcastId;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to add the ratings of a podcast
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean addPodcastRatings(Integer podcastId, Double rating) throws Exception {
        String query = "UPDATE podcasts SET rating = " + rating + " WHERE podcastId = " + podcastId;
        return genericDAO.executeUpdate(query);

    }

    /**
     * This method is used to add the listener count of a podcast episode
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean addPodcastEpisodeListenerCount(Integer podcastId, Integer number, Integer ListeningCount)
            throws Exception {

        String query = "UPDATE episodes SET ListeningCount = " + ListeningCount + " WHERE podcastId = " + podcastId
                + " AND number = " + number;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to update the play count of a song
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean updatePlayCountSong(Integer songId, Integer playCount) throws Exception {
        String query = "UPDATE songs SET playCount = " + playCount + " WHERE songId = " + songId;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to update the monthly listeners of an artist
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean updateMonthlyListeners(Integer artistId, Integer monthlyListeners) throws Exception {
        String query = "UPDATE artists SET monthlyListeners = " + monthlyListeners + " WHERE artistId = " + artistId;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to update the total subscribers of a podcast
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean updateTotalSubscribers(Integer podcastId, Integer totalSubscribers) throws Exception {
        String query = "UPDATE podcasts SET totalSubscribers = " + totalSubscribers + " WHERE podcastId = " + podcastId;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to update the ratings of a podcast
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean updatePodcastRatings(Integer podcastId, Double rating) throws Exception {

        String query = "UPDATE podcasts SET rating = " + rating + " WHERE podcastId = " + podcastId;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to update the listener count of a podcast episode
     * @return true if the query is executed successfully
     * @throws Exception if the query fails
     */
    public Boolean updatePodcastEpisodeListenerCount(Integer podcastId, Integer number, Integer ListeningCount)
            throws Exception {
        String query = "UPDATE episodes SET ListeningCount = " + ListeningCount + " WHERE podcastId = " + podcastId
                + " AND number = " + number;
        return genericDAO.executeUpdate(query);
    }

    /**
     * This method is used to find Songs by Artist
     * @return play count of a song
     * @throws Exception if the query fails
     */
    public List<Map<String, Object>> findSongsByArtist(Integer artistId) throws Exception {
        String query = "SELECT distinct(songs.songId), title FROM songs LEFT JOIN creates ON songs.songId = creates.songId WHERE artistId = "
                + artistId + " OR primaryArtist = " + artistId;
        ResultSet rs = genericDAO.executeQuery(query);
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            while (rs.next()) {
                result.add(Map.of("songId", rs.getString(1), "title", rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to find Songs by Album
     * @return play count of a song
     * @throws Exception if the query fails
     */
    public List<Songs> findSongsByAlbum(Integer albumId) throws Exception {
        String query = "SELECT * FROM songs WHERE albumId = " + albumId;
        ResultSet data = genericDAO.executeQuery(query);
        List<Songs> result = new ArrayList<>();
        try {
            while (data.next()) {
                result.add(new Songs(data.getString("songId"), data.getDouble("royaltyRate"), data.getString("title"),
                        data.getString("royaltyStatus"), data.getInt("playCount"), data.getString("country"),
                        data.getString("language"), data.getDouble("duration"), data.getInt("primaryArtist"),
                        data.getInt("albumId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to find Podcasts Episode
     * @return play count of a song
     * @throws Exception if the query fails
     */
    public List<Episodes> findPodcastEpisodes(Integer podcastId) throws Exception {
        String query = "SELECT * FROM episodes WHERE podcastId = " + podcastId;
        ResultSet data = genericDAO.executeQuery(query);
        List<Episodes> episodes = new ArrayList<>();
        try {
            while (data.next()) {
                episodes.add(new Episodes(data.getInt("podcastId"), data.getInt("number"), data.getString("title"),
                        data.getDouble("duration"), data.getString("releaseDate"), data.getInt("ListeningCount"),
                        data.getInt("AdvertisementCount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }
}
