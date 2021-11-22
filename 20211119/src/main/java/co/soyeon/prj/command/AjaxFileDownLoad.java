package co.soyeon.prj.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.comm.Command;

public class AjaxFileDownLoad implements Command { // 파일 다운로드

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		String pfileName = request.getParameter("pfileName");
		
		System.out.println(fileName + "======");
		// 파일다운로드 로직 작성
		InputStream in = null;
		OutputStream out = null;
		File file = null;
		try {
			file = new File(pfileName); // 물리위치에서 파일을 선택하고
			in = new FileInputStream(file); // 읽어들임
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); // 한글 파일명 처리
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			out = response.getOutputStream(); // response 객체로 초기화
			byte b[] = new byte[(int)file.length()]; // 메모리에 파일을 담음
			int leng = 0;
			while((leng = in.read(b)) > 0) { // 실제 다운로드 하기
				out.write(b,0,leng);
			}
			response.reset();
			in.close(); // 반드시 닫아주기
			out.close(); // 반드시 닫아주기
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		return "noticeList.do";
	}

}
