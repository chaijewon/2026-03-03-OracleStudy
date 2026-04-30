package com.sist.user;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class SawonList extends JPanel{
   JTable table;
   DefaultTableModel model;
   JButton b1,b2,b3;
   public SawonList()
   {
	   b1=new JButton("사원 추가");
	   b2=new JButton("사원 수정");
	   b3=new JButton("사원 삭제");
	   String[] col={"사번","이름","직위",
			   "입사일","부서명","근무지"};
	   String[][] row=new String[0][6];
	   model=new DefaultTableModel(row,col);
	   table=new JTable(model);
	   JScrollPane js=new JScrollPane(table);
	   setLayout(new BorderLayout());
	   JPanel p=new JPanel();
	   p.add(b1);p.add(b2);p.add(b3);
	   add("North",p);
	   add("Center",js);
   }
}
