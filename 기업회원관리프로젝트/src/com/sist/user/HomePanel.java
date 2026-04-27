package com.sist.user;
// 메인 화면 
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.sist.commons.ImageChange;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
public class HomePanel extends JPanel{
   UserMainFrame uf;
   JPanel pan=new JPanel();
   JButton b1,b2,b3,b4,b5,b6;
   JLabel la=new JLabel("0 page / 0 pages");
   JLabel[] imgs=new JLabel[12];
   int curpage=1;
   int totalpage=0;
   GoodsDAO dao=new GoodsDAO();
   public HomePanel(UserMainFrame uf)
   {
	   this.uf=uf;
	   pan.setLayout(new GridLayout(3,4,8,8));
	   pan.setBounds(10,50, 650, 580);
	   //pan.setBackground(Color.pink);
	   add(pan);
	   init();
	   print();
   }
   public void init()
   {
	   for(int i=0;i<imgs.length;i++)
	   {
		   imgs[i]=new JLabel("");
	   }
	   
	   pan.removeAll(); // JLabel 지우기
	   pan.validate();// Panel 재배치 
   }
   // 화면 출력 
   public void print()
   {
	   List<GoodsVO> list=dao.goodsListData(1, curpage);
	   for(int i=0;i<list.size();i++)
	   {
		   GoodsVO vo=list.get(i);
		   // list에 값을 한개씩 가지고 온다 
		   try
		   {
			   URL url=new URL(vo.getGoods_poster());
			   Image image=
					 ImageChange.getImage(new ImageIcon(url), 150, 180);
			   imgs[i]=new JLabel(new ImageIcon(image));
			   imgs[i].setToolTipText(vo.getGoods_name());
			   pan.add(imgs[i]);
		   }catch(Exception ex) {}
	   }
   }
}
