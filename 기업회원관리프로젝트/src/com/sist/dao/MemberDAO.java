package com.sist.dao;
import java.sql.*;
import java.util.*;
/*
 *   ***아이디 중복 체크 
 *   ***우편번호 검색 
 *   ***회원가입 
 *   ***회원정보 
 *   회원수정 
 *   ***회원탈퇴 
 */
import com.sist.vo.*;
public class MemberDAO {
	// 전체적으로 사용 
	  private Connection conn; // Socket => 연결 담당 
	  private PreparedStatement ps; // BufferedReader , OutputStream 
	  // 송(SQL문장) 수신(오라클에서 결과값 받기)
	  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	  
	  // 1. 드라이버 등록 
	  public MemberDAO()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
	  }
	  // 2. 오라클 연결 
	  public void getConnection()
	  {
		  try
		  {
			  conn=DriverManager.getConnection(URL,"hr",
					         "happy");
		  }catch(Exception ex) {}
	  }
	  // 3. 오라클 연결 해제 
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
	  // 기능 => 우편번호 검색 
	  public List<ZipcodeVO> postFind(String dong)
	  {
		  List<ZipcodeVO> list=
				  new ArrayList<ZipcodeVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
					    +"FROM zipcode "
					    +"WHERE dong LIKE '%'||?||'%'";
			  //       +"WHERE dong LIKE '%"+dong+"%'" 
			  //       => SQL Injection
			  //       CONCAT('%',?,'%')
			  // 전송 
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, dong);
			  
			  // 결과값 받기 
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  ZipcodeVO vo=new ZipcodeVO();
				  vo.setZipcode(rs.getString(1));
				  vo.setSido(rs.getString(2));
				  vo.setGugun(rs.getString(3));
				  vo.setDong(rs.getString(4));
				  vo.setBunji(rs.getString(5));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public int postFindCount(String dong)
	  {
		  int count=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM zipcode "
					    +"WHERE dong LIKE '%'||?||'%'";
			  //       +"WHERE dong LIKE '%"+dong+"%'" 
			  //       => SQL Injection
			  //       CONCAT('%',?,'%')
			  // 전송 
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, dong);
			  
			  // 결과값 받기 
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  count=rs.getInt(1);
			  rs.close();
			  // 검색 결과 
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return count;
	  }
	  // 아이디 중복 
	  public int memberIdCheck(String id)
	  {
		  int count=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM member "
					    +"WHERE id=?";
			  // 0 , 1 
			  ps=conn.prepareStatement(sql);
			  // ?에 값을 채운다 
			  ps.setString(1, id);
			  // 결과값 
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  count=rs.getInt(1);
			  rs.close();
			  
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return count;
	  }
	  // 회원 가입 
	  // 로그인 => Admin  : 관리자 / user : 마이페이지 
	  // => 메인 출력 
}
