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
import java.net.URI;
import java.net.URL;
public class HomePanel extends JPanel
implements ActionListener
{
   ControlPanel cp;
   JPanel pan=new JPanel();
   JButton b1,b2,b3,b4,b5,b6;
   JLabel la=new JLabel("0 page / 0 pages");
   JLabel[] imgs=new JLabel[12];
   int curpage=1;
   int totalpage=0;
   GoodsDAO dao=new GoodsDAO();
   int type=1;
   public HomePanel(ControlPanel cp)
   {
	   this.cp=cp;
	   setLayout(null);
	   b1=new JButton("전체 상품");
	   b2=new JButton("베스트 상품");
	   b3=new JButton("신상품");
	   b4=new JButton("특가 상품");
	   JPanel p=new JPanel();
	   p.add(b1);
	   p.add(b2);
	   p.add(b3);
	   p.add(b4);
	   p.setBounds(10,20, 400, 35);
	   add(p);
	   pan.setLayout(new GridLayout(3,4,8,8));
	   pan.setBounds(10,60, 920, 480);
	   //pan.setBackground(Color.pink);
	   add(pan);
	   init();
	   print();
	   
	   b1.addActionListener(this);
	   b2.addActionListener(this);
	   b3.addActionListener(this);
	   b4.addActionListener(this);
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
	   List<GoodsVO> list=dao.goodsListData(type, curpage);
	   //pan.setLayout(new GridLayout(3,4,8,8));
	   for(int i=0;i<list.size();i++)
	   {
		   GoodsVO vo=list.get(i);
		   // list에 값을 한개씩 가지고 온다 
		   try
		   {
			   URI uri=new URI(vo.getGoods_poster());
			   URL url=uri.toURL();
			   // new URL() => 경고
			   Image image=
					 ImageChange.getImage(new ImageIcon(url), 250, 180);
			   imgs[i]=new JLabel(new ImageIcon(image));
			   imgs[i].setToolTipText(vo.getGoods_name());
			   pan.add(imgs[i]);
		   }catch(Exception ex) {}
	   }
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	   if(e.getSource()==b1)
	   {
		   type=1;
		   curpage=1;
		   init();
		   print();
		   revalidate();
	   }
	   else if(e.getSource()==b2)
	   {
		   init();
		   type=2;
		   curpage=1;
		   print();
		   revalidate();
	   }
	   else if(e.getSource()==b3)
	   {
		   init();
		   type=3;
		   curpage=1;
		   print();
		   revalidate();
	   }
	   else if(e.getSource()==b4)
	   {
		   init();
		   type=4;
		   curpage=1;
		   print();
		   revalidate();
	   }
   }
}
