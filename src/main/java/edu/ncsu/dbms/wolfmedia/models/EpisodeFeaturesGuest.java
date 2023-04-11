package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class EpisodeFeaturesGuest {
    private int podcastId;
    private int number;
    private int guestId;

}
