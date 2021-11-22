package co.soyeon.prj.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public String run(HttpServletRequest request, HttpServletResponse response); // 뷰 페이지 돌려주기 때문에 String
}
