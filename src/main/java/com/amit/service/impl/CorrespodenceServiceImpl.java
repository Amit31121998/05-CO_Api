package com.amit.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amit.entity.CorrespondenceEntity;
import com.amit.entity.EligDetailsEntity;
import com.amit.entity.User;
import com.amit.repo.CorrespondenceEntityRepo;
import com.amit.repo.UserRepo;
import com.amit.service.CorrespondenceService;
import com.amit.utils.PdfUtils;

@Service
public class CorrespodenceServiceImpl implements CorrespondenceService {

	@Autowired
	private CorrespondenceEntityRepo corrRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PdfUtils pdfUtils;

	@Override
	public String generateNotice(Integer caseNo) {

		User user = userRepo.findById(caseNo).get();
		EligDetailsEntity elig = user.getElig();

		CorrespondenceEntity entity = new CorrespondenceEntity();

		return "success";
	}

	@Override
	public boolean exportPdf(HttpServletResponse response, Integer caseNo) throws Exception {

		User user = userRepo.findById(caseNo).get();
		EligDetailsEntity elig = user.getElig();

		File f = new File("Citizen.pdf");
		pdfUtils.generate(response, elig, f);

		FileInputStream fl = new FileInputStream(f);
		byte[] arr = new byte[(int) f.length()];
		fl.read(arr);

		System.out.print(Arrays.toString(arr));

		CorrespondenceEntity entity = new CorrespondenceEntity();
		entity.setUrl("url");
		entity.setNoticeStatus("Pending");
		entity.setEligFk(elig);
		entity.setCreatedDate(user.getCreateDate());
		entity.setPrintDate(LocalDate.now());
		entity.setUser(user);
		corrRepo.save(entity);

		fl.close();
		f.delete();
		return true;
	}

	
}
