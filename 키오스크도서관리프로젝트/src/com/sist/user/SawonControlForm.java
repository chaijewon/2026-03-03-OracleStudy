package com.sist.user;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class SawonControlForm extends JPanel{
     JTabbedPane tp=new JTabbedPane();
     SawonList sList=new SawonList();
     SawonFind sFind=new SawonFind();
     public SawonControlForm()
     {
    	 setLayout(new BorderLayout());
    	 tp.addTab("사원관리",sList);
    	 tp.addTab("사원검색",sFind);
    	 tp.addTab("근태관리",new JPanel());
    	 tp.addTab("급여관리",new JPanel());
    	 tp.addTab("부서관리",new JPanel());
    	 tp.addTab("인사관리",new JPanel());
//    	 tp.setEnabledAt(0, false);
//    	 tp.setEnabledAt(2, true);
//    	 tp.setEnabledAt(3, false);
//    	 tp.setEnabledAt(4, false);
//    	 tp.setEnabledAt(5, false);
    	 add("Center",tp);
     }
    
}
