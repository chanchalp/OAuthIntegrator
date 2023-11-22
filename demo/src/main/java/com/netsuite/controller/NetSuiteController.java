package com.netsuite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netsuite.model.ImportInvoiceRequest;
import com.netsuite.model.ImportInvoiceResponse;
import com.netsuite.model.Response;
import com.netsuite.service.ImportInvoiceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/netSuite")
public class NetSuiteController {
	
	@Autowired
	ImportInvoiceService importInvoiceService;
	
	@PostMapping(path="/v1/importInvoice")
	public ResponseEntity<Response> importInvoice(HttpServletRequest request,
			@RequestBody ImportInvoiceRequest invoiceDisputeRequest) throws Exception {
		Response response = new Response();
		try {
			ImportInvoiceResponse result = importInvoiceService.callImportInvoiceAPI(invoiceDisputeRequest);
			response.setResponseCode(HttpStatus.OK.value());
			response.setData(result);
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
