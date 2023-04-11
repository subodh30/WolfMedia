package edu.ncsu.dbms.wolfmedia.services;

import edu.ncsu.dbms.wolfmedia.models.Songs;
import edu.ncsu.dbms.wolfmedia.utilities.GenericDAO;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationProcessingService {

    private final GenericDAO genericDAO;

    //list of song attributes
    private final List<String> songAttributes = new ArrayList<>();


    public InformationProcessingService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
        populateSongAttributes();
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
    private void populateSongAttributes(){
        songAttributes.add("songId");
        songAttributes.add("royaltyRate");
        songAttributes.add("title");
        songAttributes.add("royaltyStatus");
        songAttributes.add("playCount");
        songAttributes.add("country");
        songAttributes.add("language");
        songAttributes.add("duration");
        songAttributes.add("primaryArtist");
        songAttributes.add("albumId");
    }
    public List<Songs> getSongs(int id) {
        String query = "SELECT * FROM songs";
        if(id > 0){
            query += " WHERE songId = " + id;
        }
        ResultSet data = genericDAO.executeQuery(query);
        List<Songs> songs = new ArrayList<>();
        System.out.println(data);
        try {
            while (data.next()) {
                   songs.add(new Songs(data.getString("songId"), data.getDouble("royaltyRate"), data.getString("title"), data.getString("royaltyStatus"), data.getInt("playCount"), data.getString("country"), data.getString("language"), data.getDouble("duration"), data.getString("primaryArtist"), data.getString("albumId")));
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
        String query = "UPDATE songs SET ";
        for (String attribute : songAttributes) {
            if (attribute.equals("songId")) {
                continue;
            }
            query += attribute + " = ";
            if (attribute.equals("title") || attribute.equals("royaltyStatus") || attribute.equals("country") || attribute.equals("language") || attribute.equals("primaryArtist") || attribute.equals("albumId")) {
                query += "'" + song.getSongAttribute(attribute) + "', ";
            } else {
                query += song.getSongAttribute(attribute) + ", ";
            }
        }
        query = query.substring(0, query.length() - 2);
        query += " WHERE songId = " + song.getSongId();
        genericDAO.executeUpdate(query);
    }

    public void deleteSong(int songId) {
        genericDAO.executeUpdate("DELETE FROM songs WHERE songId = " + songId);
    }
}
