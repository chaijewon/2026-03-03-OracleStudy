package com.sist.temp;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.event.*;
public class 근태관리 extends JFrame implements ActionListener{
    JLabel la,nameLa,dateLa;
    JButton b1,b2,b3,b4,b5,b6,b7;
    JLabel timeLa;
    JLabel inLa,outLa;
    LocalTime onTime = LocalTime.of(9, 0);
	LocalTime lateLimit = LocalTime.of(9, 30);
    public 근태관리()
    {
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
    	String today=sdf.format(date);
    	StringTokenizer st=new StringTokenizer(today,"-");
    	Calendar cal=Calendar.getInstance();
    	int year=Integer.parseInt(st.nextToken());
    	int month=Integer.parseInt(st.nextToken());
    	int day=Integer.parseInt(st.nextToken());
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.MONTH, month);
    	cal.set(Calendar.DATE, day);
    	String[] week={"","일","월","화",
    			       "수","목","금","토"};
    	int w=cal.get(Calendar.DAY_OF_WEEK);
    	la=new JLabel("안녕하세요.");
    	nameLa=new JLabel("홍길동님");
    	nameLa.setForeground(Color.blue);
    	
    	dateLa=new JLabel(year+"년 "+month+"월 "+day+"일 "+week[w]+"요일입니다");
    	
    	b1=new JButton("ℹ️ 나의 정보");
    	b2=new JButton("💵 나의 급여");
    	b3=new JButton("🧾 근태 기록부");
    	b4=new JButton("⌚ 출근");
    	b5=new JButton("⏰ 퇴근");
    	b6=new JButton("⌚ 조퇴");
    	b7=new JButton("⏰ 외출");
    	
    	setLayout(null);
    	la.setBounds(20, 20, 70, 30);
    	nameLa.setBounds(90, 20, 100, 30);
    	dateLa.setBounds(20,55, 250, 30);
    	add(la);
    	add(nameLa);
    	add(dateLa);
    	JPanel p=new JPanel();
    	p.add(b1);p.add(b2);p.add(b3);
    	p.setBounds(20,90,400,35);
    	add(p);
    	LocalTime now=LocalTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
    	timeLa=new JLabel(now.format(formatter),JLabel.CENTER);
    	timeLa.setFont(new Font("맑은 고딕", Font.BOLD, 45));
    	timeLa.setBounds(20, 130,300,40);
    	add(timeLa);
    	JPanel p2=new JPanel();
    	p2.setLayout(new GridLayout(2, 2,5,5));
    	p2.add(b4);p2.add(b5);
    	p2.add(b6);p2.add(b7);
    	p2.setBounds(20, 180, 400, 200);
    	add(p2);
    	//b4.setBounds(month, day, w, w);
    	inLa=new JLabel("출근 시간:");
    	outLa=new JLabel("퇴근 시간:");
    	inLa.setBounds(20, 390, 300, 40);
    	outLa.setBounds(20, 435, 300, 40);
    	add(inLa);
    	add(outLa);
    	setSize(450, 550);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	b4.addActionListener(this);
    	b5.addActionListener(this);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
        	UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }catch(Exception ex) {}
        new 근태관리();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b4)
		{
			checkIn();
		}
		else if(e.getSource()==b5)
		{
			checkOut();
		}
	}
//	 // 출근 체크
//    public void checkAttendance() {
//        LocalDateTime now = LocalDateTime.now();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formatted = now.format(formatter);
//
//        String status = getStatus(now.toLocalTime());
//
//        inLa.setText("<html>출근 시간: " + formatted + "<br>상태: " + status + "</html>");
//
//        saveLog(formatted, status);
//    }
//
//    // 상태 판단
//    public String getStatus(LocalTime now) {
//        if (!now.isAfter(onTime)) {
//            return "정상 출근";
//        } else if (!now.isAfter(lateLimit)) {
//            return "지각";
//        } else {
//            return "결석";
//        }
//    }
//
//    // 파일 저장
//    public void saveLog(String time, String status) {
//        try (FileWriter fw = new FileWriter("attendance.txt", true);
//             BufferedWriter bw = new BufferedWriter(fw)) {
//
//            bw.write(time + " / " + status);
//            bw.newLine();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
	public void checkIn() {
        LocalDateTime now = LocalDateTime.now();

        String time = format(now);
        String status = getStatus(now.toLocalTime());

        inLa.setText("<html>출근 시간: " + time + "<br>상태: " + status + "</html>");

        saveLog("출근", time, status);
    }

    // 퇴근
    public void checkOut() {
        LocalDateTime now = LocalDateTime.now();

        String time = format(now);

        outLa.setText("퇴근 시간: " + time);

        saveLog("퇴근", time, "-");
    }

    // 시간 포맷
    public String format(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    // 상태 판단
    public String getStatus(LocalTime now) {
        if (!now.isAfter(onTime)) {
            return "정상 출근";
        } else if (!now.isAfter(lateLimit)) {
            return "지각";
        } else {
            return "결석";
        }
    }

    // 로그 저장
    public void saveLog(String type, String time, String status) {
        try (FileWriter fw = new FileWriter("attendance.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(type + " / " + time + " / " + status);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
