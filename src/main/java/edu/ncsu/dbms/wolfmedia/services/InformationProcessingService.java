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

    public List<String> getTables() {
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

    public List<String> getTableAttributes(String tableName) {
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

    public List<Songs> getSongs(int id) {
        String query = "SELECT * FROM songs";
        if(id > 0){
            query += " WHERE songId = " + id;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Songs> songs = new ArrayList<>();
        try {
            while (data.next()) {
                   songs.add(new Songs(data.getString("songId"), data.getDouble("royaltyRate"), data.getString("title"), data.getString("royaltyStatus"), data.getInt("playCount"), data.getString("country"), data.getString("language"), data.getDouble("duration"), data.getInt("primaryArtist"), data.getInt("albumId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public void addSong(Songs song) {

        genericDAO.executeUpdate(
                    "INSERT INTO songs (songId, royaltyRate, title, royaltyStatus, playCount, country, language, duration, primaryArtist, albumId) "
                            +
                            "VALUES (" + song.getSongId() + ", " + song.getRoyaltyRate() + ", '" + song.getTitle() + "', '" + song.getRoyaltyStatus() + "', "
                            + song.getPlayCount() + ", '" + song.getCountry() + "', '" + song.getLanguage() + "', " + song.getDuration() + ", " + song.getPrimaryArtist()
                            + ", " + song.getAlbumId() + ")");
    }


    public void updateSong(Songs song) {
        genericDAO.executeUpdate(
                    "UPDATE songs SET songId = " + song.getSongId() + ", royaltyRate = " + song.getRoyaltyRate() + ", title = '" + song.getTitle() + "', royaltyStatus = '" + song.getRoyaltyStatus() + "', playCount = "
                            + song.getPlayCount() + ", country = '" + song.getCountry() + "', language = '" + song.getLanguage() + "', duration = " + song.getDuration() + ", primaryArtist = " + song.getPrimaryArtist()
                            + ", albumId = " + song.getAlbumId() + " WHERE songId = " + song.getSongId());
    }

    public void deleteSong(int songId) {
        genericDAO.executeUpdate("DELETE FROM songs WHERE songId = " + songId);
    }


    //Albums
    public List<Albums> getAlbums(int i) {
        String query = "SELECT * FROM albums";
        if(i > 0){
            query += " WHERE albumId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Albums> albums = new ArrayList<>();
        try {
            while (data.next()) {
                albums.add(new Albums(data.getInt("albumId"), data.getString("name"), data.getInt("releaseYear"), data.getString("edition")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public void addAlbum(Albums album) {
        genericDAO.executeUpdate(
                    "INSERT INTO albums (albumId, name, releaseYear, edition) "
                            +
                            "VALUES (" + album.getAlbumId() + ", '" + album.getName() + "', " + album.getReleaseYear() + ", '" + album.getEdition() + "')");
    }

    public void updateAlbum(Albums album) {
        genericDAO.executeUpdate(
                    "UPDATE albums SET albumId = " + album.getAlbumId() + ", name = '" + album.getName() + "', releaseYear = " + album.getReleaseYear() + ", edition = '" + album.getEdition() + "' WHERE albumId = " + album.getAlbumId());
    }

    public void deleteAlbum(int albumId) {
        genericDAO.executeUpdate("DELETE FROM albums WHERE albumId = " + albumId);
    }

    public List<Artists> getArtists(int i) {
       //write code for fetching artists
        String query = "SELECT * FROM artists";
        if(i > 0){
            query += " WHERE artistId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Artists> artists = new ArrayList<>();
        try {
            while (data.next()) {
                artists.add(new Artists(data.getInt("artistId"), data.getString("name"), data.getString("status"), data.getString("country"), data.getString("primaryGenre"), data.getInt("monthlyListeners"), data.getInt("recordId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    public void addArtist(Artists artist) {
        genericDAO.executeUpdate(
                    "INSERT INTO artists (artistId, name, status, country, primaryGenre, monthlyListeners, recordId) "
                            +
                            "VALUES (" + artist.getArtistId() + ", '" + artist.getName() + "', '" + artist.getStatus() + "', '" + artist.getCountry() + "', '" + artist.getPrimaryGenre() + "', " + artist.getMonthlyListeners() + ", " + artist.getRecordId() + ")");
    }

    public void updateArtist(Artists artist) {
        genericDAO.executeUpdate(
                    "UPDATE artists SET artistId = " + artist.getArtistId() + ", name = '" + artist.getName() + "', status = '" + artist.getStatus() + "', country = '" + artist.getCountry() + "', primaryGenre = '" + artist.getPrimaryGenre() + "', monthlyListeners = " + artist.getMonthlyListeners() + ", recordId = " + artist.getRecordId() + " WHERE artistId = " + artist.getArtistId());
    }

    public void deleteArtist(int artistId) {
        genericDAO.executeUpdate("DELETE FROM artists WHERE artistId = " + artistId);
    }

    //Podcasts
    public List<Podcasts> getPodcasts(int i) {
        String query = "SELECT * FROM podcasts";
        if(i > 0){
            query += " WHERE podcastId = " + i;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Podcasts> podcasts = new ArrayList<>();
        try {
            while (data.next()) {
                podcasts.add(new Podcasts(data.getInt("podcastId"), data.getString("name"), data.getString("country"), data.getString("language"), data.getInt("rating"), data.getInt("episodeCount"), data.getDouble("flatFee"), data.getInt("totalSubscribers")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcasts;
    }

    public void addPodcast(Podcasts podcast) {
        genericDAO.executeUpdate(
                    "INSERT INTO podcasts (podcastId, name, country, language, rating, episodeCount, flatFee, totalSubscribers) "
                            +
                            "VALUES (" + podcast.getPodcastId() + ", '" + podcast.getName() + "', '" + podcast.getCountry() + "', '" + podcast.getLanguage() + "', " + podcast.getRating() + ", " + podcast.getEpisodeCount() + ", " + podcast.getFlatFee() + ", " + podcast.getTotalSubscribers() + ")");
    }

    public void updatePodcast(Podcasts podcast) {
        genericDAO.executeUpdate(
                    "UPDATE podcasts SET podcastId = " + podcast.getPodcastId() + ", name = '" + podcast.getName() + "', country = '" + podcast.getCountry() + "', language = '" + podcast.getLanguage() + "', rating = " + podcast.getRating() + ", episodeCount = " + podcast.getEpisodeCount() + ", flatFee = " + podcast.getFlatFee() + ", totalSubscribers = " + podcast.getTotalSubscribers() + " WHERE podcastId = " + podcast.getPodcastId());
    }

    public void deletePodcast(int podcastId) {
        genericDAO.executeUpdate("DELETE FROM podcasts WHERE podcastId = " + podcastId);
    }


    //Podcast Hosts
    public List<PodcastHosts> getPodcastHosts(int id) {
        String query = "SELECT * FROM podcastHosts";
        if(id > 0){
            query += " WHERE podcastId = " + id;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<PodcastHosts> podcastHosts = new ArrayList<>();
        try {
            while (data.next()) {
                podcastHosts.add(new PodcastHosts(data.getInt("hostId"), data.getString("firstName"), data.getString("lastName"), data.getString("contact"), data.getString("email"), data.getString("city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcastHosts;
    }

    public void addPodcastHost(PodcastHosts podcastHost) {
        genericDAO.executeUpdate(
                    "INSERT INTO podcastHosts (podcastId, firstName, lastName, contact, email, city) "
                            +
                            "VALUES (" + podcastHost.getHostId() + ", '" + podcastHost.getFirstName() + "', '" + podcastHost.getLastName() + "', '" + podcastHost.getContact() + "', '" + podcastHost.getEmail() + "', '" + podcastHost.getCity() + "')");
    }

    public void updatePodcastHost(PodcastHosts podcastHost) {
        genericDAO.executeUpdate(
                    "UPDATE podcastHosts SET firstName = '" + podcastHost.getFirstName() + "', lastName = '" + podcastHost.getLastName() + "', contact = '" + podcastHost.getContact() + "', email = '" + podcastHost.getEmail() + "', city = '" + podcastHost.getCity() + "' WHERE podcastId = " + podcastHost.getHostId());
    }

    public void deletePodcastHost(int hostId) {
        genericDAO.executeUpdate("DELETE FROM podcastHosts WHERE hostId = " + hostId);
    }

    //Episodes
    public List<Episodes> getPodcastEpisodes(int i) {
        String query = "SELECT * FROM episodes";
        if(i > 0){
            query += " WHERE episodeId = " + i;
        }

        ResultSet data = genericDAO.executeQuery(query);
        List<Episodes> episodes = new ArrayList<>();

        try {
            while (data.next()) {
                episodes.add(new Episodes(data.getInt("episodeId"), data.getInt("number"), data.getString("title"), data.getDouble("duration"), data.getString("releaseDate"), data.getInt("ListeningCount"), data.getInt("AdvertisementCount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }

    public void addPodcastEpisode(Episodes episode) {
        genericDAO.executeUpdate(
                    "INSERT INTO episodes (podcastId, number, title, duration, releaseDate, ListeningCount, AdvertisementCount) "
                            +
                            "VALUES (" + episode.getPodcastId() + ", " + episode.getNumber() + ", '" + episode.getTitle() + "', " + episode.getDuration() + ", '" + episode.getReleaseDate() + "', " + episode.getListeningCount() + ", " + episode.getAdvertisementCount() + ")");
    }

    public void updatePodcastEpisode(Episodes episode) {
        genericDAO.executeUpdate(
                    "UPDATE episodes SET title = '" + episode.getTitle() + "', duration = " + episode.getDuration() + ", releaseDate = '" + episode.getReleaseDate() + "', ListeningCount = " + episode.getListeningCount() + ", AdvertisementCount = " + episode.getAdvertisementCount() + " WHERE podcastId = " + episode.getPodcastId() + " AND number = " + episode.getNumber());
    }

    public void deletePodcastEpisode(int podcastId, int number) {
        genericDAO.executeUpdate("DELETE FROM episodes WHERE number = " + number + " AND podcastId = " + podcastId);
    }

}
