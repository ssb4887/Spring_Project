package com.bbs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.dao.BbsDAO;
import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

@Service
public class BbsServiceImpl implements BbsService {

	@Inject
	BbsDAO dao;
	
	static final String PATH = "F:\\eclipse\\workspace\\Spring_Project\\src\\main\\webapp\\resources\\upload\\";

	@Override
	public void writeAction(Boarder boarder, MultipartFile file) throws Exception {
		
		// 게시글 작성 기능
		boarder = dao.write(boarder);
		
		// 파일 업로드 기능
		// 파일 객체가 비었을 때 (파일 입력하지 않았을 때)
		if(file.isEmpty()) return;
		
		// 작성자가 올린 파일의 원본 이름
		String file_name = file.getOriginalFilename();
		// 파일의 확장자를 구함
		String suffix	 = FilenameUtils.getExtension(file_name);
		// 랜덤한 중복되지 않는 ID 값을 받아옴
		UUID uuid 		 = UUID.randomUUID(); // 기존에 있던 함수
		// 파일이 저장될 때 이름
		String file_realName = uuid + "." + suffix;
		// 파일 업로드(객체를 생성하자마자 바로 보내줌)
		file.transferTo(new File(PATH + file_realName));
		
		UploadFile uploadFile = new UploadFile();
		uploadFile.setBoarder_id(boarder.getBoarder_id());
		uploadFile.setFile_name(file_name);
		uploadFile.setFile_realName(file_realName);
		
		dao.fileUpload(uploadFile);
	}

	@Override
	public HashMap<String, Object> view(Integer boarder_id) throws Exception {

		Boarder 	  boarder = dao.getBoarder(boarder_id);
		UploadFile uploadFile = dao.getUploadFile(boarder_id);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 구글검색시 이러한 HashMap이 많으니 HashMap에 알고 있자.
		map.put("boarder", boarder);
		map.put("uploadFile", uploadFile);
		
		return map; 
		// return boarder, uploadFile 2개이상 반환은 좋지않기도하며 실행되지도 않는다
	}

	@Override
	public void downloadAction(HttpServletRequest request, HttpServletResponse response, UploadFile uploadFile) throws Exception {
		
		uploadFile = dao.getUploadFile(uploadFile.getFile_realName());
		
		String browser = request.getHeader("User-Agent");
		
		// 파일의 인코딩 설정
		if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
			uploadFile.setFile_realName( URLEncoder.encode(uploadFile.getFile_realName(), "UTF-8").replaceAll("\\+", "%20"));
			uploadFile.setFile_name(URLEncoder.encode(uploadFile.getFile_name(), "UTF-8").replaceAll("\\+", "%20"));
		}
		else {
			uploadFile.setFile_realName(new String(uploadFile.getFile_realName().getBytes("UTF-8"), "ISO-8859-1"));
			uploadFile.setFile_name(new String(uploadFile.getFile_name().getBytes("UTF-8"), "ISO-8859-1"));
		}
		
		String file_name = PATH + uploadFile.getFile_realName();
		// File(file_name)은 객체로 받아오고 exists 존재한다면 true값 반환 
		if(!new File(file_name).exists()) return;
		
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + uploadFile.getFile_name() + "\"" );
		
		// output에 대한 통로
		OutputStream     os = response.getOutputStream();
		// input에 대한 통로
		FileInputStream fis = new FileInputStream(file_name);

		int ncount = 0;
		byte[] bytes = new byte[512];
		
		// input 통로를 통해서 512byte로 읽으면서 더 이상 읽을게 없다면 -1 반환
		while((ncount = fis.read(bytes)) != -1) {
			// bytes에 ncount가 담긴다
			os.write(bytes, 0, ncount);
		}
		
		fis.close();
		os.close();
	}
	
	
	
}
















