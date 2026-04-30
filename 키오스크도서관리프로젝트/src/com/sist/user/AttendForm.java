package com.sist.user;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.*;

import com.sist.dao.EmpDAO;
import com.sist.vo.AttendVO;
public class AttendForm extends JPanel
implements ActionListener
{
   JLabel la,la2;
   JButton b1,b2,b3,b4;
   JTable table;
   DefaultTableModel model;
   
   public AttendForm()
   {
	   Date date=new Date();
	   SimpleDateFormat sdf=
			   new SimpleDateFormat("yyyy-M-d");
	   String today=sdf.format(date);
	   StringTokenizer st=new StringTokenizer(today,"-");
	   int year=Integer.parseInt(st.nextToken());
	   int month=Integer.parseInt(st.nextToken());
	   int day=Integer.parseInt(st.nextToken());
	   Calendar cal=Calendar.getInstance();
	   cal.set(Calendar.YEAR, year);
	   cal.set(Calendar.MONTH, month-1);
	   cal.set(Calendar.DATE, day);
	   String[] strWeek={"","일","월","화","수","목","금","토"};
	   int week=cal.get(Calendar.DAY_OF_WEEK);
	   String msg=year+"년 "+month+"월 "+day+"일 "
			     +strWeek[week]+"요일입니다";
	   la=new JLabel(msg,JLabel.CENTER);
	   LocalTime time=LocalTime.now();
	   DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
	   String html="<html><body>"
			      +"<font color=blue size=25>"
			      +time.format(formatter)
			      +"</font></body></html>";
	   la2=new JLabel(html,JLabel.CENTER);
	   
	   setLayout(null);
	   la.setBounds(10, 15, 700, 30);
	   add(la);
	   la2.setBounds(10, 50, 700, 50);
	   add(la2);
	   b1=new JButton("😣 출근");
	   b2=new JButton("😍 퇴근");
	   b3=new JButton("🤗 조퇴");
	   b4=new JButton("🧾 조회");
	   JPanel p=new JPanel();
	   p.add(b1);p.add(b2);p.add(b3);p.add(b4);
	   p.setBounds(10, 115, 500, 35);
	   add(p);
	   
	   String[] col={"사번","이름","날짜","출근","퇴근","상태"};
	   String[][] row=new String[0][6];
	   model=new DefaultTableModel(row,col);
	   table=new JTable(model);
	   JScrollPane js=new JScrollPane(table);
	   js.setBounds(10, 160, 760, 350);
	   add(js);
	   
	   b1.addActionListener(this);
	   b2.addActionListener(this);
	   b3.addActionListener(this);
	   b4.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  if(e.getSource()==b1)// 출근 버튼 클릭 
	  {
		  EmpDAO dao=new EmpDAO();
		  int count=dao.checkIn_before(SawonMainForm.empno);
		  if(count==0)
		  {
			  String status=empStatus();
			  AttendVO vo=
					  new AttendVO();
			  vo.setEmpno(SawonMainForm.empno);
			  vo.setStatus(status);
			  dao.checkIn(vo);
			  JOptionPane.showMessageDialog(this, 
					  status+" 출근 완료!!");
		  }
		  else
		  {
			  JOptionPane.showMessageDialog(this, 
					  "이미 출근 했습니다!!");
		  }
	  }
	  else if(e.getSource()==b4)
	  {
		  for(int i=model.getRowCount()-1;i>=0;i--)
		  {
			  model.removeRow(i);
		  }
		  
		  EmpDAO dao=new EmpDAO();
		  List<AttendVO> list=dao.empAttendAllData();
		  // HH:mm:ss
		  SimpleDateFormat sdf=
				  new SimpleDateFormat("HH:mm:ss");
		  for(AttendVO vo:list)
		  {
			  String[] data={
				 String.valueOf(vo.getEmpno()),
				 vo.getEname(),
				 vo.getWork_date().toString(),
				 sdf.format(vo.getCheck_in()),
				 sdf.format(vo.getCheck_out()),
				 vo.getStatus()
			  };
			  model.addRow(data);
		  }
	  }
   }
   // check 
   public String empStatus() {
	   String status="정상";
	   LocalTime now=LocalTime.now();// 현재 시스템 시간 
	   LocalTime start=LocalTime.of(9, 0); // 09:00
	   LocalTime lateLimit=LocalTime.of(10,0);
	   if(!now.isAfter(start)) //9:00을 지나지 않은 경우 , 9:00이전 
	   {
		   status="정상";
	   }
	   else if(!now.isAfter(lateLimit))
	   {
		   status="지각";
	   }
	   else
	   {
		   status="결근";
	   }
	   return status;
   }
}
