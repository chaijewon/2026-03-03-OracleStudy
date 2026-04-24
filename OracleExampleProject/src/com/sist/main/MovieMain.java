package com.sist.main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class MovieMain extends JFrame{
	JButton prevBtn,nextBtn;
    JLabel pageLa,titleLa;
    JTable table;
    DefaultTableModel model;
    TableColumn column;
    public MovieMain()
    {
    	
    	prevBtn=new JButton("이전");
    	nextBtn=new JButton("다음");
    	pageLa=new JLabel("0 page / 0 pages"); //<label>0 page / 0 pages</label>
    	titleLa=new JLabel("영화 목록",JLabel.CENTER);// <table>
    	titleLa.setFont(new Font("맑은 고딕",Font.BOLD,30)); //<h3></h3>
    	
    	String[] col={"번호","영화명","출연","개봉일","장르"};//<tr><th></th>....</tr>
    	String[][] row=new String[0][5];
    	// 한줄에 5개 데이터를 첨부 
    	model=new DefaultTableModel(row,col) // 데이터 관리
    	{

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
    		 // 익명의 클래스 => 포함 클래스 => 상속없이 오버라이딩 => 클릭 => 편집기 => 편집방지 
    		 
    	};
    	table=new JTable(model); // 테이블 모양 관리 
    	JScrollPane js=new JScrollPane(table);
    	for(int i=0;i<col.length;i++)
    	{
    		column=table.getColumnModel().getColumn(i);
    		if(i==0)
    		{
    			column.setPreferredWidth(50);
    		}
    		else if(i==1)
    		{
    			column.setPreferredWidth(350);
    		}
    		else if(i==2)
    		{
    			column.setPreferredWidth(100);
    		}
    		else if(i==3)
    		{
    			column.setPreferredWidth(150);
    		}
    		else if(i==4)
    		{
    			column.setPreferredWidth(50);
    		}
    	}
    	table.getTableHeader().setReorderingAllowed(false);
    	table.setShowVerticalLines(false);
    	table.setRowHeight(30);
    	table.getTableHeader().setBackground(Color.pink);
    	
    	// 배치 
    	setLayout(null);
    	titleLa.setBounds(10, 15, 820, 50);
    	add(titleLa);
    	
    	js.setBounds(10, 110, 800, 450);
    	add(js);
    	
    	JPanel p=new JPanel();
    	p.add(prevBtn);
    	p.add(pageLa);
    	p.add(nextBtn);
    	
    	p.setBounds(10, 570, 800, 35);
    	add(p);
    	
    	setSize(850, 730);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MovieMain();
	}

}
