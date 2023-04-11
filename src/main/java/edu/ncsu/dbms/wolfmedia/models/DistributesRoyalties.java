package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class DistributesRoyalties {

    private int artistId;
    private int recordId;
    private int songId;
    private String date;
    private double amount;

}
