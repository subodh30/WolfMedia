package edu.ncsu.dbms.wolfmedia.models;

import lombok.Data;

@Data
public class Users {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String subscriptionStatus;
    private double subscriptionFee;
    private String phone;
    private String registrationDate;

}
