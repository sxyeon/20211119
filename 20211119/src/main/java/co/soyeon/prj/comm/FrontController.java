package co.soyeon.prj.comm;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.command.AjaxAuthorUpdate;
import co.soyeon.prj.command.AjaxFileDownLoad;
import co.soyeon.prj.command.HomeCommand;
import co.soyeon.prj.command.Logout;
import co.soyeon.prj.command.MemberDelete;
import co.soyeon.prj.command.MemberEditSave;
import co.soyeon.prj.command.MemberIdCheck;
import co.soyeon.prj.command.MemberInfo;
import co.soyeon.prj.command.MemberJoin;
import co.soyeon.prj.command.MemberJoinForm;
import co.soyeon.prj.command.MemberList;
import co.soyeon.prj.command.MemberLogin;
import co.soyeon.prj.command.MemberLoginForm;
import co.soyeon.prj.command.MemberUpdate;
import co.soyeon.prj.command.NoticeForm;
import co.soyeon.prj.command.NoticeList;
import co.soyeon.prj.command.NoticeRead;
import co.soyeon.prj.command.NoticeRegister;
import co.soyeon.prj.command.ServletApiUpload;


@WebServlet("*.do") // *.do로 호출해주세요(매핑과 같은 역할) / *.do면 다 이 컨트롤러를 타야함
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>(); // key값은 String, Value값은 Command
    
    public FrontController() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		// Command들을 요청에 따라 처리할 수 있도록 메모리에 구성 / 초기화하니까 메모리에 바로 올라감
		map.put("/home.do", new HomeCommand()); // 홈 페이지(처음 페이지)
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/logout.do", new Logout()); // 로그아웃
		map.put("/memberList.do", new MemberList()); // 멤버 목록
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 회원가입
		map.put("/ajaxIdCheck.do", new MemberIdCheck()); // 아이디 중복 체크
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입처리
		map.put("/memberInfo.do", new MemberInfo()); // 회원정보
		map.put("/memberUpdate.do", new MemberUpdate()); // 회원정보수정
		map.put("/memberEditSave.do", new MemberEditSave()); // 회원 수정 정보 저장
		map.put("/memberDelete.do", new MemberDelete()); // 회원정보 삭제
		map.put("/ajaxAuthorUpdate.do", new AjaxAuthorUpdate()); // 회원권한 변경
		map.put("/noticeForm.do", new NoticeForm()); // 공지사항 폼 호출
		map.put("/noticeList.do", new NoticeList()); // 공지사항 목록보기
//		map.put("/noticeRegister.do", new ServletApiUpload()); // 공지사항 저장 Servlet Part
		map.put("/noticeRegister.do", new NoticeRegister()); // 공지사항 저장 cos.jar
//		map.put("/noticeRegister.do", new CommonFileUpload()); // 공지사항 저장 common-fileupload
		map.put("/noticeRead.do", new NoticeRead()); // 공지사항 상세보기
		map.put("/ajaxfileDownLoad.do", new AjaxFileDownLoad()); // 파일 다운로드
	}

	// 요청이 들어오면 request, response 객체를 생성해줌
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청을 분석하고 실행할 명령(command)을 찾아 수행하고 결과를 돌려주는 메소드
		request.setCharacterEncoding("UTF-8"); // 한글 처리
		String uri = request.getRequestURI(); // 도메인 네임을 뺀 나머지 구하기
		String contextPath = request.getContextPath(); // contextPath구하기
		String page = uri.substring(contextPath.length()); // 실제 요청 페이지
		
		Command command = map.get(page); // 키값을 보내주면 밸류에 담겨있는 걸 보내줌 / 실제 요청 페이지 찾기
		String viewPage = command.run(request, response); // viewPage는 보여줄 결과페이지 / 매개변수로 전달
		// map은 new HomeCommand(); 이런 식으로 대체됨
		
		if(!viewPage.endsWith(".do")) {
			if(viewPage.startsWith("ajax:")) { // ajax 처리
				// 이렇게 시작하면
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5)); // 5번째부터 값을 추가
				return;
			} else {				
				viewPage = "WEB-INF/views/" + viewPage + ".jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
