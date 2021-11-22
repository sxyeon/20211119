package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.soyeon.prj.comm.Command;

public class Logout implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃 처리 - Session을 remove 시키기
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 전체 삭제
		return "home.do"; // 보낼 페이지
	}

}
