package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;
import co.soyeon.prj.member.serviceImpl.MemberServiceImpl;

public class MemberLogin implements Command {
	private String message;
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 모델을 호출해서 결과를 처리함
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl(); // 자기 자신을 초기화하지 못하고 구현체로 초기화
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id")); // id는 memberLoginForm에서 제공하는 name속성
		member.setPassword(request.getParameter("password"));
		member = memberDao.memberSelect(member);
		
		if(member != null) {
			session.setAttribute("id", member.getId());
			session.setAttribute("author", member.getAuthor());
			session.setAttribute("name", member.getName());
			message = member.getName() + "님 환영합니다.";
		} else {
			message = "아이디 또는 패스워드가 틀렸습니다.";
		}
		
		request.setAttribute("message", message); // message라는 변수 만들어서 message 담기
		return "member/memberLogin";
	}

}
