package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import util.MemberDAO;
import vo.MemberVO;

// @Component
@Service
public class MemberServiceimpl implements MemberService {
	@Autowired  //new 대신에 
	MemberDAO dao;
	
	// ** OracleTest 추가 
	@Override 
	public List<MemberVO> selectListOracle() {
		return dao.selectListOracle();
	} //selectListOracle()
	
	@Override
	public List<MemberVO> selectList() {
		return dao.selectList();
	}//selectList()
	
	@Override
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	}
	
	@Override
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	}
	@Override
	public int update(MemberVO vo) {
		return dao.update(vo);
	}
	@Override
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	}

}//class
