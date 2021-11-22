package co.soyeon.prj.command;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.notice.service.NoticeService;
import co.soyeon.prj.notice.service.NoticeVO;
import co.soyeon.prj.notice.serviceImpl.NoticeServiceImpl;

public class CommonFileUpload implements Command {
	// Apache Common-fileUpload
	private String  fileSave ="c:\\FileTest"; // 개발 시 업로드 파일 저장공간
//	private String fileSave = "fileUpload"; // 운영서버에 실제 동작환경을 꾸밀 때
	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = new HashMap<String, String>();
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 아이디 값
		vo.setName((String) session.getAttribute("name"));
		String fileName = null; // 파일명
		String pfileName = null; // 물리 파일명
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); // 파일저장소 관련 정보
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); // request객체 parse
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			//FileItem 객체는 폼에서 넘어온 multipart 객체형식을 다루는 객체
			for(FileItem item : items) {
				if(item.isFormField()) { // isFormField? 
					map.put(item.getFieldName(), item.getString("UTF-8")); // 필드명과 데이터를 저장
				} else if(!item.isFormField() && item.getSize() >0 ) {
					// 폼에서 넘어온 타입이 일반필드가 아니고 file 타입이고 사이즈가 0보다 크면
					int index = item.getName().lastIndexOf(File.separator); // 마지막 \의 위치
					fileName = item.getName().substring(index+1); // 실 파일명만 추출
					String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length()); // 파일확장자
					UUID uuid = UUID.randomUUID(); // 고유한 UUID 생성
					String newFileName = uuid.toString() + extension;
					pfileName = fileSave + File.separator + fileName;
//					map.put("fileName", fileName); // 원본명 담기
//					map.put("pfileName", pfileName); // 물리 파일명
					File uploadFile = new File(pfileName); // 파일 열어서 읽고
					item.write(uploadFile); // 파일 업로드가 일어남
					System.out.println(item.getFieldName() + "===file===" + item.getString("utf-8"));
				
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//이곳에 DB처리할 값을 넣어주는 곳
		vo.setFileName(fileName); // 원본
		vo.setPfileName(pfileName); // 물리파일명
		vo.setWdate(Date.valueOf(map.get("wdate"))); // 문자를 날짜로 변경
		vo.setTitle(map.get("title"));
		vo.setSubject(map.get("subject"));
		noticeDao.noticeInsert(vo);
		return "noticeList.do";
	}

}
