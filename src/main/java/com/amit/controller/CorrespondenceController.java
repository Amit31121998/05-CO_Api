package com.amit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amit.service.CorrespondenceService;

@RestController
public class CorrespondenceController {
	
	@Autowired
	private CorrespondenceService CorrService;

	@GetMapping("/generatecorr/{caseNo}")
	public String generateCoor(HttpServletResponse response ,@PathVariable Integer caseNo) throws Exception {
		
        response.setContentType("application/pdf");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=Citizen.pdf";
		response.addHeader(headerKey,headerValue);
		CorrService.exportPdf(response,caseNo);
		return "success";
	}
}
