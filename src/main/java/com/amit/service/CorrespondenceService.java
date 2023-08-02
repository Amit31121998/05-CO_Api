package com.amit.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface CorrespondenceService {
	
	public String generateNotice(Integer caseNo);
	
	public boolean exportPdf(HttpServletResponse response, Integer caseNo) throws Exception;


}
