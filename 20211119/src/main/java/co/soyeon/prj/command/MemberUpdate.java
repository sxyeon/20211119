package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;
import co.soyeon.prj.member.serviceImpl.MemberServiceImpl;

public class MemberUpdate implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 수정 / 회원정보 가지고 가서 수정할 수 있는 폼에 넣어줘야함 
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId((String)request.getAttribute("id"));
		
		request.setAttribute("member", memberDao.memberSelect(vo));
		return "member/memberUpdate";
	}

}
