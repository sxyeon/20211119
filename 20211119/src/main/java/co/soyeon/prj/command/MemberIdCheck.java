package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;
import co.soyeon.prj.member.serviceImpl.MemberServiceImpl;

public class MemberIdCheck implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 아이디 중복체크(Ajax이용)
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("chkid"));
		boolean b = memberDao.memberIdCheck(vo); // true는 존재, false는 사용가능
		String chk = "0";
		if(b) { // b가 true이면(MemberMapper 참고해서 언제 참이 돌아오고 거짓이 돌아오는지 확인 가능)
			chk = "1";
		} 
		return "ajax:"+chk; // chk값이 필요한 거니까
	}

}
