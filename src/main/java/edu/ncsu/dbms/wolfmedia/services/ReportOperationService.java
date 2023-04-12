package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.models.SongHistory;
import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ReportOperationService {
    private final GenericDAO genericDAO;

    public ReportOperationService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public List<SongHistory> getPlayCountPerSongPerMonth() {
        ResultSet data = genericDAO.executeQuery("SELECT * FROM songHistory;");
        List<SongHistory> songsHistory = new ArrayList<>();
        try {
            while (data.next()) {
                songsHistory.add(new SongHistory(data.getInt("songId"), data.getInt("month"), data.getInt("year"), data.getInt("playCount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songsHistory;
    }

    public List<Map<String, Object>> getPlayCountPerAlbumPerMonth() {
        ResultSet data = genericDAO.executeQuery("SELECT albums.albumId, albums.name, month, year, SUM(songHistory.playCount) AS 'Play Count' FROM songs INNER JOIN songHistory ON songs.songId = songHistory.songId INNER JOIN albums ON songs.albumId = albums.albumId GROUP BY albums.albumId, albums.name, month, year;");
        List<Map<String, Object>> albumList = new ArrayList<>();
        try {
            while (data.next()) {
                albumList.add(new HashMap<>());
                Map<String, Object> map = albumList.get(albumList.size() - 1);
                map.put("albumId", data.getInt(1));
                map.put("albumName", data.getString(2));
                map.put("month", data.getInt(3));
                map.put("year", data.getInt(4));
                map.put("PlayCount", new DecimalFormat("#.##").format(data.getDouble(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumList;
    }

    public List<Map<String, Object>> getPlayCountPerArtistPerMonth() {
        ResultSet data = genericDAO.executeQuery("SELECT combined.artistId, combined.name, month, year, SUM(playCount) FROM((SELECT artists.artistId, artists.name, month, year, SUM(songHistory.playCount) AS playCount FROM songs INNER JOIN songHistory ON songs.songId = songHistory.songId INNER JOIN artists ON songs.primaryArtist = artists.artistId GROUP BY artists.artistId, month, year) UNION ALL (SELECT artists.artistId, artists.name, month, year, SUM(songHistory.playCount) AS playCount FROM songHistory INNER JOIN creates ON creates.songId = songHistory.songId INNER JOIN artists ON creates.artistId = artists.artistId GROUP BY artists.artistId, month, year)) AS combined GROUP BY combined.artistId, combined.name, month, year;");
        List<Map<String, Object>> artistList = new ArrayList<>();
        try {
            while (data.next()) {
                artistList.add(new HashMap<>());
                Map<String, Object> map = artistList.get(artistList.size() - 1);
                map.put("artistId", data.getInt(1));
                map.put("artistName", data.getString(2));
                map.put("month", data.getInt(3));
                map.put("year", data.getInt(4));
                map.put("PlayCount", new DecimalFormat("#.##").format(data.getDouble(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    public Double calculateHostPayments(String startDate, String endDate) {
        ResultSet data = genericDAO.executeQuery("SELECT SUM(amount) FROM givesPaymentTo INNER JOIN serviceAccount ON givesPaymentTo.transactionId = serviceAccount.transactionId WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "'");
        Double totalPayment = 0.0;
        try {
            while (data.next()) {
                totalPayment = Double.valueOf(new DecimalFormat("#.##").format(data.getDouble(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPayment;
    }

    public Double calculateArtistPayments(String startDate, String endDate) {
        ResultSet data = genericDAO.executeQuery("SELECT SUM(amount) FROM distributesRoyalties WHERE date BETWEEN '" + startDate + "' and '" + endDate + "'");
        Double totalPayment = 0.0;
        try {
            while (data.next()) {
                totalPayment = Double.valueOf(new DecimalFormat("#.##").format(data.getDouble(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPayment;
    }

    public Double calculateRecordLabelPayments(String startDate, String endDate) {
        ResultSet data = genericDAO.executeQuery("SELECT 0.3*SUM(amount) AS 'Record Label Payments' FROM receives INNER JOIN serviceAccount ON receives.transactionId = serviceAccount.transactionId WHERE date BETWEEN '" + startDate + "' and '" + endDate + "'");
        Double totalPayment = 0.0;
        try {
            while (data.next()) {
                totalPayment = Double.valueOf(new DecimalFormat("#.##").format(data.getDouble(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPayment;
    }

    public List<Map<String, Object>> calculateMonthlyRevenue() {
        ResultSet data = genericDAO.executeQuery("SELECT YEAR(date) AS year, MONTH(date) AS month, SUM(CASE WHEN type = 'Credit' THEN amount ELSE 0 END) AS revenue FROM serviceAccount GROUP BY YEAR(date), MONTH(date);");

        List<Map<String, Object>> revenueList = new ArrayList<>();
        try {
            while (data.next()) {
                revenueList.add(new HashMap<>());
                Map<String, Object> map = revenueList.get(revenueList.size() - 1);
                map.put("year", data.getInt(1));
                map.put("month", data.getInt(2));
                map.put("Monthly Revenue", new DecimalFormat("#.##").format(data.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueList;
    }

    public List<Map<String, Object>> calculateYearlyRevenue() {
        ResultSet data = genericDAO.executeQuery("SELECT YEAR(date) AS year, SUM(CASE WHEN type = 'Credit' THEN amount ELSE 0 END) AS revenue FROM serviceAccount GROUP BY YEAR(date);");

        List<Map<String, Object>> revenueList = new ArrayList<>();
        try {
            while (data.next()) {
                revenueList.add(new HashMap<>());
                Map<String, Object> map = revenueList.get(revenueList.size() - 1);
                map.put("year", data.getInt(1));
                map.put("Yearly Revenue", new DecimalFormat("#.##").format(data.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueList;
    }

    public List<Map<String, Object>> getSongsByArtist(int artistId) {
        ResultSet data = genericDAO.executeQuery("SELECT distinct(songs.songId), title FROM songs LEFT JOIN creates ON songs.songId = creates.songId WHERE artistId = " + artistId + " OR primaryArtist = " + artistId + ";");

        List<Map<String, Object>> songs = new ArrayList<>();
        try {
            while (data.next()) {
                songs.add(new HashMap<>());
                Map<String, Object> map = songs.get(songs.size() - 1);
                map.put("songId", data.getInt(1));
                map.put("songTitle", data.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public List<Map<String, Object>> getSongsByAlbum(int albumId) {
        ResultSet data = genericDAO.executeQuery("SELECT songId, title from songs WHERE albumId = " + albumId + ";");

        List<Map<String, Object>> songs = new ArrayList<>();
        try {
            while (data.next()) {
                songs.add(new HashMap<>());
                Map<String, Object> map = songs.get(songs.size() - 1);
                map.put("songId", data.getInt(1));
                map.put("songTitle", data.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public List<Map<String, Object>> getPodcastEpisodesByPodcast(int podcastId) {
        ResultSet data = genericDAO.executeQuery("SELECT * FROM episodes WHERE podcastId = " + podcastId + ";");

        List<Map<String, Object>> episodes = new ArrayList<>();
        try {
            while (data.next()) {
                episodes.add(new HashMap<>());
                Map<String, Object> map = episodes.get(episodes.size() - 1);
                map.put("podcastId", data.getInt(1));
                map.put("number", data.getInt(2));
                map.put("title", data.getString(3));
                map.put("duration", data.getDouble(4));
                map.put("releaseDate", data.getString(5));
                map.put("listeningCount", data.getInt(6));
                map.put("advertisementCount", data.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }

}
