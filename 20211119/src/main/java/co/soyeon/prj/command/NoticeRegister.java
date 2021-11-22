package co.soyeon.prj.command;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.notice.service.NoticeService;
import co.soyeon.prj.notice.service.NoticeVO;
import co.soyeon.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeRegister implements Command {
	private String filePath = "c:\\FileTest"; // 파일이 저장되는 절대 경로
	private int fileSize = 1024*1024*100; // 파일 최대 사이즈(100M)
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 저장
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 아이디 값
		vo.setName((String) session.getAttribute("name")); // 세션에 저장된 이름 값
		
		try {
			MultipartRequest multi = // 객체 생성 시 파일이 전송됨
					new MultipartRequest(request, filePath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			// 파일 저장
			// filename은 중복이름이 들어올 경우 자동으로 index가 있는 물리 파일명
			String fileName = multi.getFilesystemName("fileName");
			// index 되기 전의 원본명
			String original = multi.getOriginalFileName("fileName");
			fileName = filePath + File.separator + fileName; // 저장경로를 포함해서 만듦 / File.separator는 슬래시
			
				vo.setFileName(original); // 원본 파일명 넣기
				vo.setPfileName(fileName); // 물리 파일명 넣기
				vo.setWdate(Date.valueOf(multi.getParameter("wdate"))); // 문자를 날짜로 변경
				vo.setTitle(multi.getParameter("title"));
				vo.setSubject(multi.getParameter("subject"));
				
				noticeDao.noticeInsert(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "noticeList.do";
		
	}

}
