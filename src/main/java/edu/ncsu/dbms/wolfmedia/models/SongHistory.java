package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class SongHistory {


    private int songId;
    private int month;
    private int year;
    private int playCount;

}
