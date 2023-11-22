package com.netsuite.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item_name;
	private String description;
	private double rate;
	private double amount;
	private String taxcode;
	private String service_line;
	private String ext_line_id;
	private String order_id;
	private String statementType;
}
