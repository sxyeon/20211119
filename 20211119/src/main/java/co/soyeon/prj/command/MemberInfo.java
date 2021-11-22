package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;
import co.soyeon.prj.member.serviceImpl.MemberServiceImpl;

public class MemberInfo implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 보기
		HttpSession session = request.getSession(); // 인터페이스는 자기 자신으로 초기화하지 못함
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId((String) session.getAttribute("id")); // session에 담아놓은 id가 object타입이어서 String타입으로 만들기
		
		request.setAttribute("member", memberDao.memberSelect(vo));
		return "member/memberInfo";
	}

}
