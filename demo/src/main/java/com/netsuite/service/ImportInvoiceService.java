package com.netsuite.service;

import org.springframework.stereotype.Service;

import com.netsuite.model.ImportInvoiceRequest;
import com.netsuite.model.ImportInvoiceResponse;

@Service
public interface ImportInvoiceService {
	
	public ImportInvoiceResponse callImportInvoiceAPI(ImportInvoiceRequest importInvoiceReq) throws Exception;

}
