package com.sist.commons;
import java.io.File;
import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/*
 *  DAO => 데이터베이스 연동 
 *  --------------------
 *  DAO는 재사용이 가능 => table당 한개 생성 
 *  ---- 연결되는 부분 
 *  게시판 ======= 댓글 
 *  DAO     +    DAO  => Service
 *  
 *  => 기능 (회원 => 로그인) Member
 *  
 *  => 드라이버 등록 
 *  => 연결 
 *  => 닫기 
 *  --------------- 모든 DAO에서 사용 
 */
public class DBUtil {
   // 드라이버 등록  
   private String URL="";
   private String username="";
   private String password="";
   public DBUtil()
   {
	   try
	   {
		   DocumentBuilderFactory dbf=
				   DocumentBuilderFactory.newDefaultInstance();
		   DocumentBuilder db=dbf.newDocumentBuilder();
		   // 파서기 생성 
		   Document doc=db.parse(new File("c:\\oracleDev\\db.xml"));
		   Element root=doc.getDocumentElement();
		   // beans 
		   NodeList list=root.getElementsByTagName("bean");
		   Element bean=(Element)list.item(0);
		   URL=bean.getAttribute("url");
		   username=bean.getAttribute("username");
		   password=bean.getAttribute("password");
		   System.out.println(
		     "URL:"+URL
		     +" Username:"+username
		     +" Password:"+password
		   );
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }
   }
   // 오라클 연결
   public Connection getConnection()
   {
	   Connection conn=null;
	   try
	   {
		   conn=DriverManager.getConnection(URL,username,password);
	   }catch(Exception ex) {}
	   return conn;
   }
   public static void main(String[] args) {
	   new DBUtil();
   }
}



