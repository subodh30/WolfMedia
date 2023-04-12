package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class ServiceAccount {

    private int transactionId;
    private String date;
    private double amount;
    private String type;

}
