package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;
import co.soyeon.prj.member.serviceImpl.MemberServiceImpl;

public class MemberDelete implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 삭제
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		int n = memberDao.memberDelete(vo);
		if(n != 0) {
			session.invalidate();
		}
		
		return "home.do";
	}

}
