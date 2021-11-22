package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.comm.Command;

public class HomeCommand implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 단순히 처음 들어오는 홈 페이지를 열어줌
		return "home/home"; // home 밑에 home.jsp
	}

}
