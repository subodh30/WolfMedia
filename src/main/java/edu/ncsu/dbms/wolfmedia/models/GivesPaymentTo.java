package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class GivesPaymentTo {

    private int transactionId;
    private int hostId;

}
