package com.sist.dao;
import java.util.*;

import com.sist.commons.DBUtil;
import com.sist.vo.BoardVO;

import java.sql.*;
public class BoardDAO {
   private DBUtil db=new DBUtil();
   private Connection conn;
   private PreparedStatement ps;
   private static BoardDAO dao; // 싱글턴 
   public static BoardDAO newInstance()
   {
	   if(dao==null)
		   dao=new BoardDAO();
	   return dao;
   }
   // 기능 => 목록 
   // 1. 정렬 => ORDER BY 
   // 2. 페이지 => OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY
   //                   --- 0번부터 시작 
   public List<BoardVO> board_list(int page)
   {
	   List<BoardVO> list=
			   new ArrayList<BoardVO>();
	   try
	   {
		   conn=db.getConnection();
		   // SQL문장을 오라클 전송 
		   String sql="SELECT /*+ INDEX_DESC(board board_no_pk)*/ no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
				     +"FROM board "
				     +"OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
		   int start=(page*10)-10;
		   // 전송 
		   ps=conn.prepareStatement(sql);
		   // ? 값을 채운다 
		   ps.setInt(1, start);
		   // 실행 => 결과값
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()) // 출력된 첫번째 위치로 커서 이동 
		   {
			   BoardVO vo=new BoardVO();
			   vo.setNo(rs.getInt(1));
			   // vo.setNo(rs.getInt("no"))
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setDbday(rs.getString(4));
			   vo.setHit(rs.getInt(5));
			   list.add(vo);
		   }
		   rs.close();     
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();
	   }
	   finally {
		  db.disConnection(conn, ps);
	   }
	   return list;
   }
   // 총페이지 ==> CEIL
   public int boardTotalPage()
   {
	   int total=0;
	   try
	   {
		   // 1. 연결 
		   conn=db.getConnection();
		   // 2. SQL문장을 만든다 
		   String sql="SELECT CEIL(COUNT(*)/10.0) as total FROM board";
		   // 3. 오라클로 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. ?가 있는 경우 => 값을 채운다 
		   ResultSet rs=ps.executeQuery();
		   // 5. 출력된 메모리 위치 커서를 이동 
		   rs.next();
		   // 6. 해당 데이터형을 이용해서 데이터를 가지고 온다 
		   // NUMBER , VRACHAR2 , CLOB , DATE 
		   //                            getDate()
		   //          --------------- getString()
		   // | => 정수 (getInt()) , 실수 (getDouble())
		   total=rs.getInt("total");
		   //              -------- 컬럼 인덱스번호 , 컬럼명 
		   // MyBatis는 컬럼명으로 읽는다 (함수=>반드시 별칭)
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return total;
   }
   
//   public static void main(String[] args) {
//	   BoardDAO dao=BoardDAO.newInstance();
//	   List<BoardVO> list=dao.board_list(1);
//	   for(BoardVO vo:list)
//	   {
//		   System.out.println(
//			 vo.getNo()+" "
//			 +vo.getSubject()+" "
//			 +vo.getName()+" "
//			 +vo.getDbday()+" "
//			 +vo.getHit()
//		   );
//		   
//	   }
//   }
}
