package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class ArtistHistory {

    private int artistId;
    private int month;
    private int year;
    private int subscribers;
}
