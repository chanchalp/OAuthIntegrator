package com.netsuite.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billingaddress {
    private String address_line_1;
    private String address_line_2;
    private String state;
    private String zip;
    private String country;
    private String phone;
}
