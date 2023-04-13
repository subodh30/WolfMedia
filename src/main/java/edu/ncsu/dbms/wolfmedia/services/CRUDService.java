package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.models.*;
import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CRUDService {
    private final GenericDAO genericDAO;

    public CRUDService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public List<ArtistHistory> getArtistHistory(Integer artistId) throws Exception {
        String query = "SELECT * FROM artistHistory";
        if(artistId > 0) {
            query += " WHERE artistId = " + artistId;
        }
        ResultSet rs = genericDAO.executeQuery(query);
        List<ArtistHistory> artistHistoryList = new ArrayList<>();
        while(rs.next()) {
           artistHistoryList.add(new ArtistHistory(rs.getInt("artistId"), rs.getInt("month"), rs.getInt("year"), rs.getInt("subscribers")));
        }
        return artistHistoryList;
    }

    public Boolean addArtistHistory(ArtistHistory artistHistory) throws Exception {
        String query = "INSERT INTO artistHistory (artistId, month, year, subscribers) VALUES (" + artistHistory.getArtistId() + ", " + artistHistory.getMonth() + ", " + artistHistory.getYear() + ", " + artistHistory.getSubscribers() + ")";
        return genericDAO.executeUpdate(query);
    }
    public Boolean updateArtistHistory(ArtistHistory artistHistory) throws Exception {
        String query = "UPDATE artistHistory SET subscribers = " + artistHistory.getSubscribers() + " WHERE artistId = " + artistHistory.getArtistId() + " AND month = " + artistHistory.getMonth() + " AND year = " + artistHistory.getYear();
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteArtistHistory(Integer artistId, Integer month, Integer year) throws Exception {
        String query = "DELETE FROM artistHistory WHERE artistId = " + artistId + " AND month = " + month + " AND year = " + year;
        return genericDAO.executeUpdate(query);
    }

    public List<ArtistType> getArtistType(Integer artistTypeId) throws Exception {
        String query = "SELECT * FROM artistType";
        if(artistTypeId > 0) {
            query += " WHERE artistTypeId = " + artistTypeId;
        }
        ResultSet rs = genericDAO.executeQuery(query);
        List<ArtistType> artistTypeList = new ArrayList<>();
        while(rs.next()) {
            artistTypeList.add(new ArtistType(rs.getInt("artistTypeId"), rs.getString("type")));
        }
        return artistTypeList;
    }

    public Boolean addArtistType(ArtistType artistType) throws Exception {
        String query = "INSERT INTO artistType (type) VALUES (" + artistType.getType() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean updateArtistType(ArtistType artistType) throws Exception {
        String query = "UPDATE artistType SET type = " + artistType.getType() + " WHERE artistTypeId = " + artistType.getArtistTypeId();
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteArtistType(Integer artistTypeId) throws Exception {
        String query = "DELETE FROM artistType WHERE artistTypeId = " + artistTypeId;
        return genericDAO.executeUpdate(query);
    }

    public List<BelongsTo> getBelongsTo() throws Exception{
        String query = "SELECT * FROM belongsTo";
        ResultSet rs = genericDAO.executeQuery(query);
        List<BelongsTo> belongsToList = new ArrayList<>();
        while(rs.next()) {
            belongsToList.add(new BelongsTo(rs.getInt("artistTypeId"), rs.getInt("artistId")));
        }
        return belongsToList;
    }

    public Boolean addBelongsTo(BelongsTo belongsTo) throws Exception {
        String query = "INSERT INTO belongsTo (artistTypeId, artistId) VALUES (" + belongsTo.getArtistTypeId() + ", " + belongsTo.getArtistId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteBelongsTo(Integer artistTypeId, Integer artistId) throws Exception {
        String query = "DELETE FROM belongsTo WHERE artistTypeId = " + artistTypeId + " AND artistId = " + artistId;
        return genericDAO.executeUpdate(query);
    }

    public List<CollaboratedWith> getCollaboratedWith() throws Exception {
        String query = "SELECT * FROM collaboratedWith";
        ResultSet rs = genericDAO.executeQuery(query);
        List<CollaboratedWith> collaboratedWithList = new ArrayList<>();
        while(rs.next()) {
           collaboratedWithList.add(new CollaboratedWith(rs.getInt("artistId1"), rs.getInt("artistId2")));
        }
        return collaboratedWithList;
    }

    public Boolean addCollaboratedWith(CollaboratedWith collaboratedWith) throws Exception {
        String query = "INSERT INTO collaboratedWith (artistId1, artistId2) VALUES (" + collaboratedWith.getArtistId1() + ", " + collaboratedWith.getArtistId2() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteCollaboratedWith(Integer artistId1, Integer artistId2) throws Exception {
        String query = "DELETE FROM collaboratedWith WHERE artistId1 = " + artistId1 + " AND artistId2 = " + artistId2;
        return genericDAO.executeUpdate(query);
    }

    public List<CreatedBy> getCreatedBy() throws Exception {
        String query = "SELECT * FROM createdBy";
        ResultSet rs = genericDAO.executeQuery(query);
        List<CreatedBy> createdByList = new ArrayList<>();
        while(rs.next()) {
           createdByList.add( new CreatedBy(rs.getInt("podcastId"), rs.getInt("hostId")));
        }
        return createdByList;
    }

    public Boolean addCreatedBy(CreatedBy createdBy) throws Exception {
        String query = "INSERT INTO createdBy (podcastId, hostId) VALUES (" + createdBy.getPodcastId() + ", " + createdBy.getHostId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteCreatedBy(Integer podcastId, Integer hostId) throws Exception {
        String query = "DELETE FROM createdBy WHERE podcastId = " + podcastId + " AND hostId = " + hostId;
        return genericDAO.executeUpdate(query);
    }

    public List<Creates> getCreates() throws Exception {
        String query = "SELECT * FROM creates";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Creates> createsList = new ArrayList<>();
        while(rs.next()) {
            createsList.add( new Creates(rs.getInt("artistId"), rs.getInt("songId"), rs.getString("type")));
        }
        return createsList;
    }

    public Boolean addCreates(Creates creates) throws Exception {
        String query = "INSERT INTO creates (artistId, songId, type) VALUES (" + creates.getArtistId() + ", " + creates.getSongId() + ", " + creates.getType() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteCreates(Integer artistId, Integer songId) throws Exception {
        String query = "DELETE FROM creates WHERE artistId = " + artistId + " AND songId = " + songId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<DistributesRoyalties> getDistributesRoyalties() throws Exception {
        String query = "SELECT * FROM distributesRoyalties";
        ResultSet rs = genericDAO.executeQuery(query);
        List<DistributesRoyalties> distributesRoyaltiesList = new ArrayList<>();
        while(rs.next()) {
            distributesRoyaltiesList.add( new DistributesRoyalties(rs.getInt("artistId"), rs.getInt("recordId"), rs.getInt("songId"), rs.getString("date"), rs.getDouble("amount")));
        }
        return distributesRoyaltiesList;
    }
    
    public Boolean addDistributesRoyalties(DistributesRoyalties distributesRoyalties) throws Exception {
        String query = "INSERT INTO distributesRoyalties (artistId, recordId, songId, date, amount) VALUES (" + distributesRoyalties.getArtistId() + ", " + distributesRoyalties.getRecordId() + ", " + distributesRoyalties.getSongId() + ", " + distributesRoyalties.getDate() + ", " + distributesRoyalties.getAmount() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deleteDistributesRoyalties(Integer artistId, Integer recordId, Integer songId) throws Exception {
        String query = "DELETE FROM distributesRoyalties WHERE artistId = " + artistId + " AND recordId = " + recordId + " AND songId = " + songId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<EpisodeFeaturesGuest> getEpisodeFeaturesGuest() throws Exception {
        String query = "SELECT * FROM episodeFeaturesGuest";
        ResultSet rs = genericDAO.executeQuery(query);
        List<EpisodeFeaturesGuest> episodeFeaturesGuestList = new ArrayList<>();
        while(rs.next()) {
            episodeFeaturesGuestList.add(new EpisodeFeaturesGuest(rs.getInt("podcastId"), rs.getInt("number"), rs.getInt("guestId")) );
        }
        return episodeFeaturesGuestList;
    }
    
    public Boolean addEpisodeFeaturesGuest(EpisodeFeaturesGuest episodeFeaturesGuest) throws Exception {
        String query = "INSERT INTO episodeFeaturesGuest (podcastId, number, guestId) VALUES (" + episodeFeaturesGuest.getPodcastId() + ", " + episodeFeaturesGuest.getNumber() + ", " + episodeFeaturesGuest.getGuestId() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deleteEpisodeFeaturesGuest(Integer podcastId, Integer number, Integer guestId) throws Exception {
        String query = "DELETE FROM episodeFeaturesGuest WHERE podcastId = " + podcastId + " AND number = " + number + " AND guestId = " + guestId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<Follow> getFollow() throws Exception {
        String query = "SELECT * FROM follow";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Follow> followList = new ArrayList<>();
        while(rs.next()) {
            followList.add( new Follow(rs.getInt("artistId"), rs.getInt("userId")));
        }
        return followList;
    }
    
    public Boolean addFollow(Follow follow) throws Exception {
        String query = "INSERT INTO follow (artistId, userId) VALUES (" + follow.getArtistId() + ", " + follow.getUserId() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deleteFollow(Integer artistId, Integer userId) throws Exception {
        String query = "DELETE FROM follow WHERE artistId = " + artistId + " AND userId = " + userId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<Genres> getGenres() throws Exception {
        String query = "SELECT * FROM genres";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Genres> genresList = new ArrayList<>();
        while(rs.next()) {
            genresList.add(new Genres(rs.getInt("genreId"), rs.getString("name")));
        }
        return genresList;
    }
    
    public Boolean addGenres(Genres genres) throws Exception {
        String query = "INSERT INTO genres (genreId, name) VALUES (" + genres.getGenreId() + ", " + genres.getName() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deleteGenres(Integer genreId) throws Exception {
        String query = "DELETE FROM genres WHERE genreId = " + genreId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<GivesPaymentTo> getGivesPaymentTo() throws Exception {
        String query = "SELECT * FROM givesPaymentTo";
        ResultSet rs = genericDAO.executeQuery(query);
        List<GivesPaymentTo> givesPaymentToList = new ArrayList<>();
        while(rs.next()) {
            givesPaymentToList.add(new GivesPaymentTo( rs.getInt("transactionId"), rs.getInt("hostId")));
        }
        return givesPaymentToList;
    }
    
    public Boolean addGivesPaymentTo(GivesPaymentTo givesPaymentTo) throws Exception {
        String query = "INSERT INTO givesPaymentTo (transactionId, hostId) VALUES (" + givesPaymentTo.getTransactionId() + ", " + givesPaymentTo.getHostId() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deleteGivesPaymentTo(Integer transactionId, Integer hostId) throws Exception {
        String query = "DELETE FROM givesPaymentTo WHERE transactionId = " + transactionId + " AND hostId = " + hostId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<Pays> getPays() throws Exception {
        String query = "SELECT * FROM pays";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Pays> paysList = new ArrayList<>();
        while(rs.next()) {
            paysList.add( new Pays(rs.getInt("userId"), rs.getInt("transactionId")));
        }
        return paysList;
    }
    
    public Boolean addPays(Pays pays) throws Exception {
        String query = "INSERT INTO pays (userId, transactionId) VALUES (" + pays.getUserId() + ", " + pays.getTransactionId() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deletePays(Integer userId, Integer transactionId) throws Exception {
        String query = "DELETE FROM pays WHERE userId = " + userId + " AND transactionId = " + transactionId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<PodcastHas> getPodcastHas() throws Exception {
        String query = "SELECT * FROM podcastHas";
        ResultSet rs = genericDAO.executeQuery(query);
        List<PodcastHas> podcastHasList = new ArrayList<>();
        while(rs.next()) {
            podcastHasList.add( new PodcastHas(rs.getInt("podcastId"), rs.getInt("genreId")));
        }
        return podcastHasList;
    }
    
    public Boolean addPodcastHas(PodcastHas podcastHas) throws Exception {
        String query = "INSERT INTO podcastHas (podcastId, genreId) VALUES (" + podcastHas.getPodcastId() + ", " + podcastHas.getGenreId() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deletePodcastHas(Integer podcastId, Integer genreId) throws Exception {
        String query = "DELETE FROM podcastHas WHERE podcastId = " + podcastId + " AND genreId = " + genreId;
        return genericDAO.executeUpdate(query);
    }
    
    public List<PodcastHistory> getPodcastHistory() throws Exception {
        String query = "SELECT * FROM podcastHistory";
        ResultSet rs = genericDAO.executeQuery(query);
        List<PodcastHistory> podcastHistoryList = new ArrayList<>();
        while(rs.next()) {
            podcastHistoryList.add( new PodcastHistory(rs.getInt("podcastId"), rs.getInt("month"), rs.getInt("year"), rs.getInt("subscribers"), rs.getDouble("rating")));
        }
        return podcastHistoryList;
    }
    
    public Boolean addPodcastHistory(PodcastHistory podcastHistory) throws Exception {
        String query = "INSERT INTO podcastHistory (podcastId, month, year, subscribers, rating) VALUES (" + podcastHistory.getPodcastId() + ", " + podcastHistory.getMonth() + ", " + podcastHistory.getYear() + ", " + podcastHistory.getSubscribers() + ", " + podcastHistory.getRating() + ")";
        return genericDAO.executeUpdate(query);
    }
    
    public Boolean deletePodcastHistory(Integer podcastId, Integer month, Integer year) throws Exception {
        String query = "DELETE FROM podcastHistory WHERE podcastId = " + podcastId + " AND month = " + month + " AND year = " + year;
        return genericDAO.executeUpdate(query);
    }

    public List<Receives> getReceives() throws Exception {
        String query = "SELECT * FROM receives";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Receives> receivesList = new ArrayList<>();
        while(rs.next()) {
            receivesList.add( new Receives(rs.getInt("transactionId"), rs.getInt("recordId")));
        }
        return receivesList;
    }

    public Boolean addReceives(Receives receives) throws Exception {
        String query = "INSERT INTO receives (transactionId, recordId) VALUES (" + receives.getTransactionId() + ", " + receives.getRecordId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteReceives(Integer transactionId, Integer recordId) throws Exception {
        String query = "DELETE FROM receives WHERE transactionId = " + transactionId + " AND recordId = " + recordId;
        return genericDAO.executeUpdate(query);
    }

    public List<RecordLabels> getRecordLabels() throws Exception {
        String query = "SELECT * FROM recordLabels";
        ResultSet rs = genericDAO.executeQuery(query);
        List<RecordLabels> recordLabelsList = new ArrayList<>();
        while(rs.next()) {
            recordLabelsList.add( new RecordLabels(rs.getInt("recordId"), rs.getString("name")));
        }
        return recordLabelsList;
    }

    public Boolean addRecordLabels(RecordLabels recordLabels) throws Exception {
        String query = "INSERT INTO recordLabels (recordId, name) VALUES (" + recordLabels.getRecordId() + ", " + recordLabels.getName() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteRecordLabels(Integer recordId) throws Exception {
        String query = "DELETE FROM recordLabels WHERE recordId = " + recordId;
        return genericDAO.executeUpdate(query);
    }

    public List<ServiceAccount> getServiceAccount() throws Exception {
        String query = "SELECT * FROM serviceAccount";
        ResultSet rs = genericDAO.executeQuery(query);
        List<ServiceAccount> serviceAccountList = new ArrayList<>();
        while(rs.next()) {
            serviceAccountList.add( new ServiceAccount(rs.getInt("transactionId"), rs.getString("date"), rs.getDouble("amount"), rs.getString("type")));
        }
        return serviceAccountList;
    }

    public Boolean addServiceAccount(ServiceAccount serviceAccount) throws Exception {
        String query = "INSERT INTO serviceAccount (transactionId, date, amount, type) VALUES (" + serviceAccount.getTransactionId() + ", " + serviceAccount.getDate() + ", " + serviceAccount.getAmount() + ", " + serviceAccount.getType() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteServiceAccount(Integer transactionId) throws Exception {
        String query = "DELETE FROM serviceAccount WHERE transactionId = " + transactionId;
        return genericDAO.executeUpdate(query);
    }

    public List<SongHas> getSongHas() throws Exception {
        String query = "SELECT * FROM songHas";
        ResultSet rs = genericDAO.executeQuery(query);
        List<SongHas> songHasList = new ArrayList<>();
        while(rs.next()) {
            songHasList.add( new SongHas(rs.getInt("songId"), rs.getInt("genreId")));
        }
        return songHasList;
    }

    public Boolean addSongHas(SongHas songHas) throws Exception {
        String query = "INSERT INTO songHas (songId, genreId) VALUES (" + songHas.getSongId() + ", " + songHas.getGenreId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSongHas(Integer songId, Integer genreId) throws Exception {
        String query = "DELETE FROM songHas WHERE songId = " + songId + " AND genreId = " + genreId;
        return genericDAO.executeUpdate(query);
    }

    public List<SongHistory> getSongHistory() throws Exception {
        String query = "SELECT * FROM songHistory";
        ResultSet rs = genericDAO.executeQuery(query);
        List<SongHistory> songHistoryList = new ArrayList<>();
        while(rs.next()) {
            songHistoryList.add( new SongHistory(rs.getInt("songId"), rs.getInt("month"), rs.getInt("year"), rs.getInt("playCount")));
        }
        return songHistoryList;
    }

    public Boolean addSongHistory(SongHistory songHistory) throws Exception {
        String query = "INSERT INTO songHistory (songId, month, year, playCount) VALUES (" + songHistory.getSongId() + ", " + songHistory.getMonth() + ", " + songHistory.getYear() + ", " + songHistory.getPlayCount() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSongHistory(Integer songId, Integer month, Integer year) throws Exception {
        String query = "DELETE FROM songHistory WHERE songId = " + songId + " AND month = " + month + " AND year = " + year;
        return genericDAO.executeUpdate(query);
    }

    public List<SpecialGuests> getSpecialGuests() throws Exception {
        String query = "SELECT * FROM specialGuests";
        ResultSet rs = genericDAO.executeQuery(query);
        List<SpecialGuests> specialGuestsList = new ArrayList<>();
        while(rs.next()) {
            specialGuestsList.add( new SpecialGuests(rs.getInt("guestId"), rs.getString("name")));
        }
        return specialGuestsList;
    }

    public Boolean addSpecialGuests(SpecialGuests specialGuests) throws Exception {
        String query = "INSERT INTO specialGuests (guestId, name) VALUES (" + specialGuests.getGuestId() + ", " + specialGuests.getName() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSpecialGuests(Integer guestId) throws Exception {
        String query = "DELETE FROM specialGuests WHERE guestId = " + guestId;
        return genericDAO.executeUpdate(query);
    }

    public List<SponsoredBy> getSponsoredBy() throws Exception {
        String query = "SELECT * FROM sponsoredBy";
        ResultSet rs = genericDAO.executeQuery(query);
        List<SponsoredBy> sponsoredByList = new ArrayList<>();
        while(rs.next()) {
            sponsoredByList.add( new SponsoredBy(rs.getInt("sponsorId"), rs.getInt("podcastId")));
        }
        return sponsoredByList;
    }

    public Boolean addSponsoredBy(SponsoredBy sponsoredBy) throws Exception {
        String query = "INSERT INTO sponsoredBy (sponsorId, podcastId) VALUES (" + sponsoredBy.getSponsorId() + ", " + sponsoredBy.getPodcastId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSponsoredBy(Integer sponsorId, Integer podcastId) throws Exception {
        String query = "DELETE FROM sponsoredBy WHERE sponsorId = " + sponsorId + " AND podcastId = " + podcastId;
        return genericDAO.executeUpdate(query);
    }

    public List<Sponsors> getSponsors() throws Exception {
        String query = "SELECT * FROM sponsors";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Sponsors> sponsorsList = new ArrayList<>();
        while(rs.next()) {
            sponsorsList.add( new Sponsors(rs.getInt("sponsorId"), rs.getString("name"), rs.getDouble("amount")));
        }
        return sponsorsList;
    }

    public Boolean addSponsors(Sponsors sponsors) throws Exception {
        String query = "INSERT INTO sponsors (sponsorId, name, amount) VALUES (" + sponsors.getSponsorId() + ", " + sponsors.getName() + ", " + sponsors.getAmount() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSponsors(Integer sponsorId) throws Exception {
        String query = "DELETE FROM sponsors WHERE sponsorId = " + sponsorId;
        return genericDAO.executeUpdate(query);
    }

    public List<Subscribes> getSubscribes() throws Exception {
        String query = "SELECT * FROM subscribes";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Subscribes> subscribesList = new ArrayList<>();
        while(rs.next()) {
            subscribesList.add( new Subscribes(rs.getInt("podcastId"), rs.getInt("userId")));
        }
        return subscribesList;
    }

    public Boolean addSubscribes(Subscribes subscribes) throws Exception {
        String query = "INSERT INTO subscribes (podcastId, userId) VALUES (" + subscribes.getPodcastId() + ", " + subscribes.getUserId() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteSubscribes(Integer podcastId, Integer userId) throws Exception {
        String query = "DELETE FROM subscribes WHERE podcastId = " + podcastId + " AND userId = " + userId;
        return genericDAO.executeUpdate(query);
    }

    public List<TrackNumbers> getTrackNumbers() throws Exception {
        String query = "SELECT * FROM trackNumbers";
        ResultSet rs = genericDAO.executeQuery(query);
        List<TrackNumbers> trackNumbersList = new ArrayList<>();
        while(rs.next()) {
            trackNumbersList.add( new TrackNumbers(rs.getInt("albumId"), rs.getInt("number")));
        }
        return trackNumbersList;
    }

    public Boolean addTrackNumbers(TrackNumbers trackNumbers) throws Exception {
        String query = "INSERT INTO trackNumbers (albumId, number) VALUES (" + trackNumbers.getAlbumId() + ", " + trackNumbers.getNumber() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean deleteTrackNumbers(Integer albumId, Integer number) throws Exception {
        String query = "DELETE FROM trackNumbers WHERE albumId = " + albumId + " AND number = " + number;
        return genericDAO.executeUpdate(query);
    }

    public List<Users> getUsers() throws Exception {
        String query = "SELECT * FROM users";
        ResultSet rs = genericDAO.executeQuery(query);
        List<Users> usersList = new ArrayList<>();
        while(rs.next()) {
            usersList.add( new Users(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("subscriptionStatus"), rs.getDouble("subscriptionFee"), rs.getString("phone"), rs.getString("registrationDate")));
        }
        return usersList;
    }

    public Boolean addUsers(Users users) throws Exception {
        String query = "INSERT INTO users (userId, firstName, lastName, email, subscriptionStatus, subscriptionFee, phone, registrationDate) VALUES (" + users.getUserId() + ", " + users.getFirstName() + ", " + users.getLastName() + ", " + users.getEmail() + ", " + users.getSubscriptionStatus() + ", " + users.getSubscriptionFee() + ", " + users.getPhone() + ", " + users.getRegistrationDate() + ")";
        return genericDAO.executeUpdate(query);
    }

    public Boolean updateUsers(Users user) throws Exception {
        String query = "UPDATE users SET firstName = " + user.getFirstName() + ", lastName = " + user.getLastName() + ", email = " + user.getEmail() + ", subscriptionStatus = " + user.getSubscriptionStatus() + ", subscriptionFee = " + user.getSubscriptionFee() + ", phone = " + user.getPhone() + ", registrationDate = " + user.getRegistrationDate() + " WHERE userId = " + user.getUserId();
        return genericDAO.executeUpdate(query);
    }
    public Boolean deleteUsers(Integer userId) throws Exception {
        String query = "DELETE FROM users WHERE userId = " + userId;
        return genericDAO.executeUpdate(query);
    }

}
