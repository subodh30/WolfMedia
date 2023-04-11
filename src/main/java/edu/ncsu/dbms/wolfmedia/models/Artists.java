package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class Artists {
    private int artistId;
    private String name;
    private String status;
    private String country;
    private String primaryGenre;
    private int monthlyListeners;
    private int recordId;

}
