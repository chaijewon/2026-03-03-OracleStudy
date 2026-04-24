package com.sist.dao;
/*
 *   자바 
 *    => 변수 (VO) , 필요시 => 매개변수 / 지역변수 
 *                          | => 사용자 요청값 
 *    => 연산자 : 산술연산자 , 대입연산자 
 *    => 제어문 : if / for / while 
 *    => 배열 / List
 *             ---- VO를 모아서 전송 
 *    => 객체지향 프로그램 
 *       => 캡슐화 : VO 
 *       => 포함 =>  Connction / PreparedStatement
 *       => 오버라이딩
 *       => class 클래스 / 메소드
 *                       => 리턴형 / 매개변수 
 *    => 예외처리 
 *       try ~ catch 
 *    => 라이브러리 
 *       String / Math(ceil) / StringTokenizer
 *       Date / FileInputStream / FileOutputStream
 *       BufferedReader 
 *       Connection / PreparedStatement / ResultSet 
 *       *** List / Map 
 *    ----------------------------------------------
 *    J2EE : 브라우저에서 값 받기 / 브라우저로 값 전송 
 *    
 */
import java.util.*; // List

import com.sist.vo.MovieVO;

import java.sql.*; // 오라클 (데이터베이스) 
/*
 *   1. 드라이버 등록 
 *      연결할 준비 
 *      Class.forName("oracle.jdbc.driver.OracleDriver")
 *                     com.mysql.cj.driver.Driver
 *                     => mysql , mariadb 
 *   2. 오라클 연결 
 *      Connection conn=
 *        DriverManager.getConnection(URL,username,password)
 *        => conn hr/happy
 *        URL 
 *         jdbc:업체명:드라이버종류:@IP:PORT:데이터베이스명
 *              oracle thin     localhost 1521 XE 
 *                              => IP
 *                              => GIT
 *                              localhost 
 *                              => 127.0.0.1
 *   3. SQL문장 제작 (*****)
 *      String sql="SELECT/INSERT/UPDATE/DELETE"
 *                => mybatis : XML 
 *                => jpa : 메소드로 만든다 
 *   4. SQL문장을 오라클 전송 
 *      PreparedStatement ps=conn.preparedStatement(sql)
 *   5. 오라클 실행 => 결과값 받기 (*****)
 *      ResultSet rs=ps.executeQuery() // SELECT
 *      int a=ps.executeUpdate() // INSERT ,UPDATE, DELETE
 *   6. List/VO에 값을 채운다 (*****) list.add()
 *   7. 닫기  ps.close() / conn.close()
 *   
 *   == 기능 
 *   목록 : 사용자가 페이지 요청 
 *         ---------------- 매개변수
 *         20개 
 *         1 ROW => VO => while 
 *                 객체 저장 => List 
 *         리턴형 List<MovieVO>
 *         매개변수 (int page)
 *   상세보기 : 사용자가 영화번호
 *                   ------ 중복없는 데이터 => PRIMARY KEY
 *            리턴형 : MovieVO
 *            매개변수 : 영화번호 =>  int mno
 *   검색 : 리턴형 : List<MovieVO>
 *         매개변수 : 2개 
 *                  String fd , String column
 */
public class MovieDAO {
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
   public MovieDAO()
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
   // 공통 사항 => 오라클 반드시 열고 닫기
   // 기능 
   // 1. 목록
   public List<MovieVO> movieListData(int page)
   {
	   List<MovieVO> list=new ArrayList<MovieVO>();
	   try
	   {
		   
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
   // 2. 상세보기 
   public MovieVO movieDetailData(int mno)
   {
	   MovieVO vo=new MovieVO();
	   try
	   {
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return vo;
   }
   // 3. 검색 
   /*
    *   검색이 안됨 
    *   String sql="SELECT * FROM movie "
    *             +"WHERE "+col+" LIKE '%'||?||'%'" 
    *   
    *   #{} ${}
    *   ps.setString(1,col); => ''
    *   ps.setString(2,fd);
    *   ---------------------- 실제값만 ?
    *   table명 / 컬럼명은 문자열 결합 
    *   
    */
   public List<MovieVO> movieFindData(String col,String fd)
   {
	   List<MovieVO> list=new ArrayList<MovieVO>();
	   try
	   {
		   
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
   // => 댓글 : CRUD 
}



