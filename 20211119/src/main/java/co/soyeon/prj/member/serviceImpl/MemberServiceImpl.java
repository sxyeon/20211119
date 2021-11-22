package co.soyeon.prj.member.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.soyeon.prj.comm.DataSource;
import co.soyeon.prj.member.service.MemberMapper;
import co.soyeon.prj.member.service.MemberService;
import co.soyeon.prj.member.service.MemberVO;

public class MemberServiceImpl implements MemberService {
	private SqlSession sqlSession = DataSource.getDataSource().openSession(true);
	private MemberMapper map = sqlSession.getMapper(MemberMapper.class); //mapper 연결
	@Override
	public List<MemberVO> memberSlectList() {
		return map.memberSelectList();
	}
	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return map.memberSelect(vo);
	}
	@Override
	public int memberInsert(MemberVO vo) {
		return map.memberInsert(vo);
	}
	@Override
	public int memberDelete(MemberVO vo) {
		return map.memberDelete(vo);
	}
	@Override
	public int memberUpdate(MemberVO vo) {
		return map.memberUpdate(vo);
	}
	@Override
	public boolean memberIdCheck(MemberVO vo) {
		return map.memberIdCheck(vo);
	}
	@Override
	public int memberAuthorUpdate(MemberVO vo) {
		return map.memberAuthorUpdate(vo);
	}

}
