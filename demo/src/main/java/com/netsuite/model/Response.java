package com.netsuite.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer responseCode;
	private Object data;
}
