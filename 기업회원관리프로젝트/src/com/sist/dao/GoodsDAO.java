package com.sist.dao;
import java.sql.*;
import java.util.*;

import com.sist.vo.GoodsVO;
public class GoodsDAO {
  // 전체적으로 사용 
  private Connection conn; // Socket => 연결 담당 
  private PreparedStatement ps; // BufferedReader , OutputStream 
  // 송(SQL문장) 수신(오라클에서 결과값 받기)
  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
  private String[] tables={
		  "",
		  "goods_all",
		  "goods_best",
		  "goods_new",
		  "goods_special"
  };
  // 1. 드라이버 등록 
  public GoodsDAO()
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
  // 기능 설정 => 회원 가입 / 회원 탈퇴 / 로그인 
  // 상품 구매를 할 수 있다 
  // 구매 테이블을 만든다 
  // 관리 => 상품 구매가 많은 회원 => 등급 지정 => 관리자 
  // 구매 현황 , 회원 관리 
  // 1. 상품 목록 
  public List<GoodsVO> goodsListData(int type,int page)
  {
	  List<GoodsVO> list=
			     new ArrayList<GoodsVO>();
	  try
	  {
		  // 1. 연결
		  getConnection();
		  // 2. SQL문장 
		  String sql="SELECT no,goods_poster,goods_name,goods_price "
				    +"FROM "+tables[type] 
				    +" ORDER BY no ASC "
				    +"OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
		            // 12c => 페이지 나누기 => 인라인뷰 
		  // 3. 전송 
		  ps=conn.prepareStatement(sql);
		  // 4. ?에 값을 채운다 => ?가 없는 경우 통과 
		  ps.setInt(1, (page*12)-12); // 0번부터 시작 
		  // 결과값 받기 
		  ResultSet rs=ps.executeQuery();
		  // 실행된 결과를 메모리에 저장 => ResultSet 
		  // next() => 처음부터 => 마지막까지 
		  // previous() => 마지막 => 처음 
		  /*
		   *   데이터 출력 
		   *   getInt() / getString() / getDouble()
		   *   getDate() 
		   */
		  // no,poster,goods_name,goods_price
		  while(rs.next())
		  {
			  GoodsVO vo=new GoodsVO();
			  vo.setNo(rs.getInt(1));
			  vo.setGoods_poster(rs.getString(2));
			  vo.setGoods_name(rs.getString(3));
			  vo.setGoods_price(rs.getString(4));
			  list.add(vo);
		  }
		  rs.close(); // 실행시마다 => 데이터 저장이 다르다 => 지역변수 
		  
	  }catch(Exception ex)
	  {
		  // 오류 출력 
		  ex.printStackTrace();
	  }
	  finally
	  {
		  // 오라클 닫기 => 오류가 있던 없던 상관없이 
		  disConnection();
	  }
	  return list;
  }
  // 총페이지 
  public int goodsTotalPage(int type)
  {
	  int total=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT CEIL(COUNT(*)/12.0) "
				    +"FROM "+tables[type];
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  total=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return total;
  }
  
}






