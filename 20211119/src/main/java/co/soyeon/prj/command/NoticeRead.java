package co.soyeon.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.soyeon.prj.comm.Command;
import co.soyeon.prj.notice.service.NoticeService;
import co.soyeon.prj.notice.service.NoticeVO;
import co.soyeon.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeRead implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNo(Integer.valueOf(request.getParameter("no"))); // 폼에서 넘어오는 게 no니까 / 문자를 integer객체로 바꾸기
		request.setAttribute("notice", noticeDao.noticeSelect(vo));
		return "notice/noticeRead";
		
	}

}
