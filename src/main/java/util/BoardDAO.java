package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;
import vo.BoardVO;

// ** Board CRUD
// => selectList, selectOne, insert, update, delete 

@Log4j
@Repository
public class BoardDAO {
	// ** 전역변수 정의
	Connection cn = DBConnection.getConnection();
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	String sql; 
	
	// ** 답글등록
	// => stepUpdate
	public int stepUpdate(BoardVO vo) {
		sql="update board set step=step+1 where root=? and step>=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1,vo.getRoot());
			pst.setInt(2,vo.getStep());
			return pst.executeUpdate();
		} catch (Exception e) {
			//System.out.println("** stepUpdate => "+e.toString());
			Log.info("** stepUpdate => "+e.toString());
			return 0;
		}
	} // stepUpdate
	
	public int replyInsert(BoardVO vo) {
		
		// System.out.println("** Step_Update Count => "+stepUpdate(vo));
		Log.info("** Step_Update Count => "+stepUpdate(vo));
		
		sql="insert into board(title,id,content,regdate,cnt,root,step,indent) "
				+ "values (?,?,?,CURRENT_TIMESTAMP,0,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1,vo.getTitle());
			pst.setString(2,vo.getId());
			pst.setString(3,vo.getContent());
			pst.setInt(4,vo.getRoot());
			pst.setInt(5,vo.getStep());
			pst.setInt(6,vo.getIndent());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** replyInsert => "+e.toString());
			return 0;
		}
	} //replyInsert
	
	// ** selectList
	// => 답글 기능 추가 ( order by 등등 변경 )
	public List<BoardVO> selectList() {
		sql = "select seq,title,id,regdate,cnt,root,step,indent from board order by root desc, step asc";
		List<BoardVO> list = new ArrayList<>();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					BoardVO vo = new BoardVO();
					vo.setSeq(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setId(rs.getString(3));
					vo.setRegdate(rs.getString(4));
					vo.setCnt(rs.getInt(5));
					vo.setRoot(rs.getInt(6));
					vo.setStep(rs.getInt(7));
					vo.setIndent(rs.getInt(8));
					list.add(vo);
				}while(rs.next());
			}else {
				System.out.println("** 출력할 자료가 1건도 없습니다 ~ **");
				list=null;
			}
		} catch (Exception e) {
			Log.info("** selectList => "+e.toString());
			list = null;
		}
		return list;
	} //selectList
	
	// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		sql = "select * from board where seq=?" ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			rs=pst.executeQuery();
			if (rs.next()) {
				vo.setTitle(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
				vo.setRoot(rs.getInt(7));
				vo.setStep(rs.getInt(8));
				vo.setIndent(rs.getInt(9));
				return vo;
			}else {
				System.out.println("** 글번호에 해당하는 글을 찾을 수 없습니다 ~ **");
			}
		} catch (Exception e) {
			Log.info("** selectOne => "+e.toString());
		}
		return null;
	} //selectOne 
	
	// ** 조회수 증가
	public int countUp(BoardVO vo) {
		sql = "update board set cnt=cnt+1 where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** countUp => "+e.toString());
		}
		return 0;
	} //countUp
	
	// ** insert (원글)
	public int insert(BoardVO vo) {
		// ** MySQL
		// => seq : AUTO Increment 해결 -> LAST_INSERT_ID()
		sql="insert into board(title,id,content,regdate,cnt,root,step,indent) values "
				+ "(?,?,?,CURRENT_TIMESTAMP,0,(LAST_INSERT_ID()+1),0,0)" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getId());
			pst.setString(3, vo.getContent());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board insert => "+e.toString());
		}
		return 0;
	} //insert
	
	// ** update
	public int update(BoardVO vo) {
		sql="update board set title=?, content=? where seq=?" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContent());
			pst.setInt(3, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board update => "+e.toString());
		}
		return 0;
	} //update
	
	// ** delete
	public int delete(BoardVO vo) {
		sql="delete from board where seq=?" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board update => "+e.toString());
		}
		return 0;
	} //delete

} //class
