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

    public Boolean addPlayCountSong(Integer songId, Integer playCount) {
            return genericDAO.executeUpdate("UPDATE songs SET playCount = "+playCount+"WHERE songId = "+songId);
    }

    public Boolean addMonthlyListeners(Integer artistId, Integer monthlyListeners) {
            String query = "UPDATE artists SET monthlyListeners = "+monthlyListeners+"WHERE artistId = "+artistId;
            return genericDAO.executeUpdate(query);

    }

    public Boolean addTotalSubscribers(Integer podcastId, Integer totalSubscribers) {
            String query = "UPDATE podcasts SET totalSubscribers = "+totalSubscribers+"WHERE podcastId = "+podcastId;
          return genericDAO.executeUpdate(query);
    }

    public Boolean addPodcastRatings(Integer podcastId, Integer rating) {
            String query = "UPDATE podcasts SET rating = "+rating+"WHERE podcastId = "+podcastId;
            return genericDAO.executeUpdate(query);

    }

    public Boolean addPodcastEpisodeListenerCount(Integer podcastId, Integer episodeId, Integer listenerCount) {

            String query = "UPDATE episodes SET ListenerCount = "+listenerCount+"WHERE podcastId = "+podcastId+" AND number = "+episodeId;
            return genericDAO.executeUpdate(query);
    }

    public Boolean updatePlayCountSong(Integer songId, Integer playCount) {
            String query = "UPDATE songs SET playCount = "+playCount+"WHERE songId = "+songId;
            return genericDAO.executeUpdate(query);
    }

    public Boolean updateMonthlyListeners(Integer artistId, Integer monthlyListeners) {
            String query = "UPDATE artists SET monthlyListeners = "+monthlyListeners+"WHERE artistId = "+artistId;
            return genericDAO.executeUpdate(query);
    }

    public Boolean updateTotalSubscribers(Integer podcastId, Integer totalSubscribers) {
            String query = "UPDATE podcasts SET totalSubscribers = "+totalSubscribers+"WHERE podcastId = "+podcastId;
            return genericDAO.executeUpdate(query);
    }

    public Boolean updatePodcastRatings(Integer podcastId, Integer rating) {


            String query = "UPDATE podcasts SET rating = "+rating+"WHERE podcastId = "+podcastId;
            return genericDAO.executeUpdate(query);
    }

    public Boolean updatePodcastEpisodeListenerCount(Integer podcastId, Integer episodeId, Integer listenerCount) {
            String query = "UPDATE episodes SET ListenerCount = "+listenerCount+"WHERE podcastId = "+podcastId+" AND number = "+episodeId;
            return genericDAO.executeUpdate(query);
    }

    public List<Map<String, Object>> findSongsByArtist(Integer artistId) {
            String query = "SELECT distinct(songs.songId), title FROM songs LEFT JOIN creates ON songs.songId = creates.songId WHERE artistId = "+artistId+" OR primaryArtist = "+artistId;
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

    public List<Songs> findSongsByAlbum(Integer albumId) {
            String query = "SELECT * FROM songs WHERE albumId = " + albumId;
            ResultSet data = genericDAO.executeQuery(query);
            List<Songs> result = new ArrayList<>();
            try {
                while (data.next()) {
                    result.add(new Songs(data.getString("songId"), data.getDouble("royaltyRate"), data.getString("title"), data.getString("royaltyStatus"), data.getInt("playCount"), data.getString("country"), data.getString("language"), data.getDouble("duration"), data.getInt("primaryArtist"), data.getInt("albumId")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
    }

    public  List<Episodes> findPodcastEpisodes(Integer podcastId) {
            String query = "SELECT * FROM episodes WHERE podcastId = "+podcastId;
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
}
