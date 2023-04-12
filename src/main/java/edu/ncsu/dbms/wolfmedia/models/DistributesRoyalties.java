package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class DistributesRoyalties {

    private int artistId;
    private int recordId;
    private int songId;
    private String date;
    private double amount;

}
