package service;

import java.util.List;

import vo.BoardVO;

public interface BoardService {
	
	int replyInsert(BoardVO vo); // 답글등록

	List<BoardVO> selectList(); //selectList
	BoardVO selectOne(BoardVO vo); //selectList

	// ** 조회수 증가
	int countUp(BoardVO vo); //countUp

	int insert(BoardVO vo); //insert
	int update(BoardVO vo); //update
	int delete(BoardVO vo); //delete

}