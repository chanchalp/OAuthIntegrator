package com.netsuite.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportInvoiceResponse {

	   @SerializedName("status")
	   String status;

	   @SerializedName("api_version")
	   String apiVersion;

	   @SerializedName("data")
	   List<Object> data;
}
