package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class ServiceAccount {

    private int transactionId;
    private String date;
    private double amount;
    private String type;

}
