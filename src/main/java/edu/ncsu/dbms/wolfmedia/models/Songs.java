package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class Songs {

    private String songId;
    private Double royaltyRate;
    private String title;
    private String royaltyStatus;
    private Integer playCount;
    private String country;
    private String language;
    private Double duration;
    private String primaryArtist;
    private String albumId;

    public Songs(String songId, Double royaltyRate, String title, String royaltyStatus, Integer playCount, String country, String language, Double duration, String primaryArtist, String albumId) {
        this.songId = songId;
        this.royaltyRate = royaltyRate;
        this.title = title;
        this.royaltyStatus = royaltyStatus;
        this.playCount = playCount;
        this.country = country;
        this.language = language;
        this.duration = duration;
        this.primaryArtist = primaryArtist;
        this.albumId = albumId;
    }


    public String getSongAttribute(String attribute) {
        switch (attribute) {
            case "songId":
                return songId;
            case "royaltyRate":
                return royaltyRate.toString();
            case "title":
                return title;
            case "royaltyStatus":
                return royaltyStatus;
            case "playCount":
                return playCount.toString();
            case "country":
                return country;
            case "language":
                return language;
            case "duration":
                return duration.toString();
            case "primaryArtist":
                return primaryArtist;
            case "albumId":
                return albumId;
            default:
                return null;
        }
    }
}
