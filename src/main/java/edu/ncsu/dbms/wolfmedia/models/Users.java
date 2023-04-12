package edu.ncsu.dbms.wolfmedia.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
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
