package com.netsuite.model;


import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportInvoiceRequest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String action;
    public List<Datum> data;
}
