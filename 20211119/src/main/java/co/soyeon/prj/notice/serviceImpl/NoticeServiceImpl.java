package co.soyeon.prj.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.soyeon.prj.comm.DataSource;
import co.soyeon.prj.notice.service.NoticeMapper;
import co.soyeon.prj.notice.service.NoticeService;
import co.soyeon.prj.notice.service.NoticeVO;

public class NoticeServiceImpl implements NoticeService{
	private SqlSession session = DataSource.getDataSource().openSession(true);
	private NoticeMapper map = session.getMapper(NoticeMapper.class);
	@Override
	public List<NoticeVO> noticeSelectList() {
		return map.noticeSelectList();
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		return map.noticeSelect(vo);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		return map.noticeInsert(vo);
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		return map.noticeUpdate(vo);
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		return map.noticeDelete(vo);
	}

	@Override
	public List<NoticeVO> noticeSearchList(String searchKey) {
		return map.noticeSearchList(searchKey);
	}

}
