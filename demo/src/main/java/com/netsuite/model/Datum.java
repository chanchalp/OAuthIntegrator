package com.netsuite.model;


import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Datum implements Serializable {

    private String date;
    private String customer;
    private String ext_id;
    private String service_line;
    private String subsidiary;
    private String currency;
    private List<Item> item;
    private Shippingaddress shippingaddress;
    private Billingaddress billingaddress;
}
