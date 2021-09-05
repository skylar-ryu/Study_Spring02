package service;

import java.util.List;

import vo.MemberVO;

public interface MemberService {
	
	// ** OracleTest 추가 
	public List<MemberVO> selectListOracle();
		
	List<MemberVO> selectList();//selectList()

	MemberVO selectOne(MemberVO vo);

	int insert(MemberVO vo);

	int update(MemberVO vo);

	int delete(MemberVO vo);

}