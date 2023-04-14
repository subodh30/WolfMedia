package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.models.*;
import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationProcessingService {

    private final GenericDAO genericDAO;

    public InformationProcessingService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    /**
     * This method is used get the list of tables in the database
     * @return list of tables
     * @throws Exception if the query fails
     */
    public List<String> getTables()  throws Exception{
        ResultSet data = genericDAO.executeQuery("SHOW TABLES");
        List<String> tables = new ArrayList<>();
        try {
            while (data.next()) {
                tables.add(data.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    /**
     * This method is used to get the list of attributes of a table
     * @return list of attributes
     * @throws Exception if the query fails
     */
    public List<String> getTableAttributes(String tableName)  throws Exception{
        ResultSet data = genericDAO.executeQuery("SHOW COLUMNS FROM " + tableName);
        List<String> attributes = new ArrayList<>();
        try {
            while (data.next()) {
                attributes.add(data.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    /**
     * This method is used to get the songs
     * @return list of Songs 
     * @throws Exception if the query fails
     */
    public List<Songs> getSongs(int id)  throws Exception{
        String query = "SELECT * FROM songs";
        if (id > 0) {
            query += " WHERE songId = " + id;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Songs> songs = new ArrayList<>();
        try {
            while (data.next()) {
                songs.add(new Songs(data.getString("songId"), data.getDouble("royaltyRate"), data.getString("title"),
                        data.getString("royaltyStatus"), data.getInt("playCount"), data.getString("country"),
                        data.getString("language"), data.getDouble("duration"), data.getInt("primaryArtist"),
                        data.getInt("albumId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    /**
     * This method is used to add song
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addSong(Songs song)  throws Exception{

        return genericDAO.executeUpdate(
                "INSERT INTO songs (songId, royaltyRate, title, royaltyStatus, playCount, country, language, duration, primaryArtist, albumId) "
                        +
                        "VALUES (" + song.getSongId() + ", " + song.getRoyaltyRate() + ", '" + song.getTitle() + "', '"
                        + song.getRoyaltyStatus() + "', "
                        + song.getPlayCount() + ", '" + song.getCountry() + "', '" + song.getLanguage() + "', "
                        + song.getDuration() + ", " + song.getPrimaryArtist()
                        + ", " + song.getAlbumId() + ")");
    }

    /**
     * This method is used to update song
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updateSong(Songs song)  throws Exception{
        return genericDAO.executeUpdate(
                "UPDATE songs SET songId = " + song.getSongId() + ", royaltyRate = " + song.getRoyaltyRate()
                        + ", title = '" + song.getTitle() + "', royaltyStatus = '" + song.getRoyaltyStatus()
                        + "', playCount = "
                        + song.getPlayCount() + ", country = '" + song.getCountry() + "', language = '"
                        + song.getLanguage() + "', duration = " + song.getDuration() + ", primaryArtist = "
                        + song.getPrimaryArtist()
                        + ", albumId = " + song.getAlbumId() + " WHERE songId = " + song.getSongId());
    }

    /**
     * This method is used to delete song
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deleteSong(int songId) throws Exception {
        return genericDAO.executeUpdate("DELETE FROM songs WHERE songId = " + songId);
    }

    /**
     * This method is used to get the list of albums
     * @return list of Artists
     * @throws Exception if the query fails
     */
    public List<Albums> getAlbums(int i) throws Exception {
        String query = "SELECT * FROM albums";
        if (i > 0) {
            query += " WHERE albumId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Albums> albums = new ArrayList<>();
        try {
            while (data.next()) {
                albums.add(new Albums(data.getInt("albumId"), data.getString("name"), data.getInt("releaseYear"),
                        data.getString("edition")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    /**
     * This method is used to add album
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addAlbum(Albums album)  throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO albums (albumId, name, releaseYear, edition) "
                        +
                        "VALUES (" + album.getAlbumId() + ", '" + album.getName() + "', " + album.getReleaseYear()
                        + ", '" + album.getEdition() + "')");
    }

    /**
     * This method is used to update album
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updateAlbum(Albums album) throws Exception {
        return genericDAO.executeUpdate(
                "UPDATE albums SET albumId = " + album.getAlbumId() + ", name = '" + album.getName()
                        + "', releaseYear = " + album.getReleaseYear() + ", edition = '" + album.getEdition()
                        + "' WHERE albumId = " + album.getAlbumId());
    }

    /**
     * This method is used to delete album
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deleteAlbum(int albumId) throws Exception {
        return genericDAO.executeUpdate("DELETE FROM albums WHERE albumId = " + albumId);
    }

    /**
     * This method is used to get the list of artists
     * @return list of Artists
     * @throws Exception if the query fails
     */
    public List<Artists> getArtists(int i)  throws Exception{
        // write code for fetching artists
        String query = "SELECT * FROM artists";
        if (i > 0) {
            query += " WHERE artistId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Artists> artists = new ArrayList<>();
        try {
            while (data.next()) {
                artists.add(new Artists(data.getInt("artistId"), data.getString("name"), data.getString("status"),
                        data.getString("country"), data.getString("primaryGenre"), data.getInt("monthlyListeners"),
                        data.getInt("recordId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    /**
     * This method is used to add artist
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addArtist(Artists artist)  throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO artists (artistId, name, status, country, primaryGenre, monthlyListeners, recordId) "
                        +
                        "VALUES (" + artist.getArtistId() + ", '" + artist.getName() + "', '" + artist.getStatus()
                        + "', '" + artist.getCountry() + "', '" + artist.getPrimaryGenre() + "', "
                        + artist.getMonthlyListeners() + ", " + artist.getRecordId() + ")");
    }

    /**
     * This method is used to update artist
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updateArtist(Artists artist) throws Exception {
        return genericDAO.executeUpdate(
                "UPDATE artists SET artistId = " + artist.getArtistId() + ", name = '" + artist.getName()
                        + "', status = '" + artist.getStatus() + "', country = '" + artist.getCountry()
                        + "', primaryGenre = '" + artist.getPrimaryGenre() + "', monthlyListeners = "
                        + artist.getMonthlyListeners() + ", recordId = " + artist.getRecordId() + " WHERE artistId = "
                        + artist.getArtistId());
    }

    /**
     * This method is used to delete artist
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deleteArtist(int artistId)  throws Exception{
        return genericDAO.executeUpdate("DELETE FROM artists WHERE artistId = " + artistId);
    }

    /**
     * This method is used to get the list of podcasts
     * @return list of Podcasts
     * @throws Exception if the query fails
     */
    public List<Podcasts> getPodcasts(int i)  throws Exception{
        String query = "SELECT * FROM podcasts";
        if (i > 0) {
            query += " WHERE podcastId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Podcasts> podcasts = new ArrayList<>();
        try {
            while (data.next()) {
                podcasts.add(new Podcasts(data.getInt("podcastId"), data.getString("name"), data.getString("country"),
                        data.getString("language"), data.getInt("rating"), data.getInt("episodeCount"),
                        data.getDouble("flatFee"), data.getInt("totalSubscribers")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcasts;
    }

    /**
     * This method is used to add podcast
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addPodcast(Podcasts podcast)  throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO podcasts (podcastId, name, country, language, rating, episodeCount, flatFee, totalSubscribers) "
                        +
                        "VALUES (" + podcast.getPodcastId() + ", '" + podcast.getName() + "', '" + podcast.getCountry()
                        + "', '" + podcast.getLanguage() + "', " + podcast.getRating() + ", "
                        + podcast.getEpisodeCount() + ", " + podcast.getFlatFee() + ", " + podcast.getTotalSubscribers()
                        + ")");
    }

    /**
     * This method is used to update podcast
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updatePodcast(Podcasts podcast) throws Exception {
        return genericDAO.executeUpdate(
                "UPDATE podcasts SET podcastId = " + podcast.getPodcastId() + ", name = '" + podcast.getName()
                        + "', country = '" + podcast.getCountry() + "', language = '" + podcast.getLanguage()
                        + "', rating = " + podcast.getRating() + ", episodeCount = " + podcast.getEpisodeCount()
                        + ", flatFee = " + podcast.getFlatFee() + ", totalSubscribers = "
                        + podcast.getTotalSubscribers() + " WHERE podcastId = " + podcast.getPodcastId());
    }

    /**
     * This method is used to delete podcast
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deletePodcast(int podcastId)  throws Exception{
        return genericDAO.executeUpdate("DELETE FROM podcasts WHERE podcastId = " + podcastId);
    }

    /**
     * This method is used to get the list of podcast hosts
     * @return list of PodcastHosts
     * @throws Exception if the query fails
     */
    public List<PodcastHosts> getPodcastHosts(int id)  throws Exception{
        String query = "SELECT * FROM podcastHosts";
        if (id > 0) {
            query += " WHERE hostId = " + id;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<PodcastHosts> podcastHosts = new ArrayList<>();
        try {
            while (data.next()) {
                podcastHosts.add(
                        new PodcastHosts(data.getInt("hostId"), data.getString("firstName"), data.getString("lastName"),
                                data.getString("contact"), data.getString("email"), data.getString("city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcastHosts;
    }

    /**
     * This method is used to add podcast host
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addPodcastHost(PodcastHosts podcastHost) throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO podcastHosts (hostId, firstName, lastName, contact, email, city) "
                        +
                        "VALUES (" + podcastHost.getHostId() + ", '" + podcastHost.getFirstName() + "', '"
                        + podcastHost.getLastName() + "', '" + podcastHost.getContact() + "', '"
                        + podcastHost.getEmail() + "', '" + podcastHost.getCity() + "')");
    }

    /**
     * This method is used to update podcast host
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updatePodcastHost(PodcastHosts podcastHost)  throws Exception{
        return genericDAO.executeUpdate(
                "UPDATE podcastHosts SET firstName = '" + podcastHost.getFirstName() + "', lastName = '"
                        + podcastHost.getLastName() + "', contact = '" + podcastHost.getContact() + "', email = '"
                        + podcastHost.getEmail() + "', city = '" + podcastHost.getCity() + "' WHERE hostId = "
                        + podcastHost.getHostId());
    }

    /**
     * This method is used to delete podcast host
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deletePodcastHost(int hostId)  throws Exception{
        return genericDAO.executeUpdate("DELETE FROM podcastHosts WHERE hostId = " + hostId);
    }

    /**
     * This method is used to get the list of podcast episodes
     * @return list of Episodes
     * @throws Exception if the query fails
     */
    public List<Episodes> getPodcastEpisodes(int i) throws Exception {
        String query = "SELECT * FROM episodes";
        if (i > 0) {
            query += " WHERE podcastId = " + i;
        }

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

    /**
     * This method is used to add podcast episode
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean addPodcastEpisode(Episodes episode)  throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO episodes (podcastId, number, title, duration, releaseDate, ListeningCount, AdvertisementCount) "
                        +
                        "VALUES (" + episode.getPodcastId() + ", " + episode.getNumber() + ", '" + episode.getTitle()
                        + "', " + episode.getDuration() + ", '" + episode.getReleaseDate() + "', "
                        + episode.getListeningCount() + ", " + episode.getAdvertisementCount() + ")");
    }

    /**
     * This method is used to update podcast episode
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean updatePodcastEpisode(Episodes episode)  throws Exception{
        return genericDAO.executeUpdate(
                "UPDATE episodes SET title = '" + episode.getTitle() + "', duration = " + episode.getDuration()
                        + ", releaseDate = '" + episode.getReleaseDate() + "', ListeningCount = "
                        + episode.getListeningCount() + ", AdvertisementCount = " + episode.getAdvertisementCount()
                        + " WHERE podcastId = " + episode.getPodcastId() + " AND number = " + episode.getNumber());
    }

    /**
     * This method is used to delete podcast episode
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean deletePodcastEpisode(int podcastId, int number)  throws Exception{
        return genericDAO.executeUpdate("DELETE FROM episodes WHERE number = " + number + " AND podcastId = " + podcastId);
    }

    /**
     * This method is used to assign artist to album
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean assignArtistToAlbum(int artistId, int albumId) throws Exception {
        return genericDAO.executeUpdate(
                "INSERT INTO artistHas(artistId, albumId) VALUES (" + artistId + ", " + albumId + ")");
    }

    /**
     * This method is used to assign song to album
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean assignSongToAlbum(int songId, int albumId)  throws Exception{
        return genericDAO.executeUpdate("UPDATE songs SET albumId = " + albumId + " WHERE songId = " + songId);
    }

    /** This method is used to assign artist to record label
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean assignArtistToRecordLabel(int artistId, int recordLabelId) throws Exception {
        return genericDAO.executeUpdate(
                "UPDATE artists SET recordId=" + recordLabelId + " WHERE artistId=" + artistId);
    }

    /**
     * This method is used to assign episode to podcast
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean assignEpisodeToPodcast(int number, int podcastId)  throws Exception{
        return genericDAO.executeUpdate(
                "UPDATE episodes SET podcastId=" + podcastId + " WHERE number=" + number);
    }

    /**
     * This method is used to assign podcast host to podcast
     * @return Boolean value
     * @throws Exception if the query fails
     */
    public Boolean assignPodcastHostToPodcast(int podcastId, int hostId)  throws Exception{
        return genericDAO.executeUpdate(
                "INSERT INTO createdBy (podcastId, hostId) VALUES (" + podcastId + ", " + hostId + ")");
    }

}
