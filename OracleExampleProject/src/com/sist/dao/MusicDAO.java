package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class MusicDAO {
	//1. 연결 객체 
	   private Connection conn;
	   //2. 송수신 
	   private PreparedStatement ps;
	   // ResultSet -> SQL문장따라 저장되는 데이터가 틀리다 => 지역변수
	   //3. URL 
	   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	   // MySQL / MariaDB => 3306
	   // MSSQL => 1433 => pub 
	   // 단점 : 포트가 다를 수 있다 
	   
	   // 드라이버 등록 1 => 한번만 설정 
	   public MusicDAO()
	   {
		   // 연결만 : thin드라이버 
		   // 오라클에 있는 데이터를 드라이버에 설정 : OCI
		   try
		   {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
	   }
	   
	   // 2. 오라클 연동 => SQLPlus 
	   public void getConnection()
	   {
		   try
		   {
			   conn=
				DriverManager.getConnection(URL,"hr","happy");
		   }catch(Exception ex) {}
	   }
	   // 3. 오라클 닫기
	   public void disConnection()
	   {
		   try
		   {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   // 기능
	   public List<MusicVO> musicListData(int cno)
	   {
		   List<MusicVO> list=
				   new ArrayList<MusicVO>();
		   try
		   {
			   // 1. 연결 
			   getConnection();
			   // 2. SQL문장 
			   String sql="SELECT no,state,idcrement,"
					     +"title,singer,album "
					     +"FROM genie_music "
					     +"WHERE cno=? "
					     +"ORDER BY no ASC";
			   // 3. 오라클 전송 
			   ps=conn.prepareStatement(sql);
			   // 4. ? 에 값을 채운다 
			   ps.setInt(1, cno);
			   
			   // 5. 실행 => 결과값을 가지고 온다 
			   ResultSet rs=ps.executeQuery();
			   // 6. ROW => VO에 추가 => 전체 => List
			   // rs.next() => MoveFirst() => 
			   // 출력한 ROW의 첫번째 위치에 커서
			   // 아래로 내려가면 ROW단위 읽기 
			   // 데이터 없는 경우에 false가 종료 
			   while(rs.next())
			   {
				   MusicVO vo=new MusicVO();
				   vo.setNo(rs.getInt(1));
				   vo.setState(rs.getString(2));
				   vo.setIdcrement(rs.getInt(3));
				   vo.setTitle(rs.getString(4));
				   vo.setSinger(rs.getString(5));
				   vo.setAlbum(rs.getString(6));
				   
				   list.add(vo); // 가변 
			   }
			   rs.close();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
			   // 오류 출력 
		   }
		   finally
		   {
			   disConnection(); // 닫기
		   }
		   return list;
	   }
	   // => API키 이용 => JSON
	   // Youtube / Kakao : 지도 / 네이버 : 뉴스 , Data
	   // AWS / 제미나이 => 
	   // 공공데이터 / 관광공사 
}




