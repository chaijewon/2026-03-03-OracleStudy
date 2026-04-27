package com.sist.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

public class GoodsDetailForm extends JPanel
implements ActionListener
{
	ControlPanel cp; // 화면 이동 cp.card.show()
	// => HTML에서 작업 => <a>
	GoodsDAO dao=new GoodsDAO();
	JButton b1=new JButton("목록");
	int type=0;
	int gno=0;
	JLabel poster;
    JLabel nameLa,priceLa,discountLa,deliveryLa;
    JLabel name,sub,discount,delivery,price;
    JButton b2;
    JComboBox<Integer> box;
    public GoodsDetailForm(ControlPanel cp)
    {
    	 this.cp=cp;
    	 setLayout(null);
    	 poster=new JLabel("");
         poster.setBounds(20, 20, 300, 500);
         add(poster);
        	 
         nameLa=new JLabel("상품명");
         name=new JLabel();
         nameLa.setBounds(330, 20, 80, 30);
         name.setBounds(415, 20, 250, 30);
         add(nameLa);add(name);
         
         sub=new JLabel();
         sub.setBounds(415, 55, 450, 30);
         add(sub);
         priceLa=new JLabel("가격");
         price=new JLabel();
        	 
         priceLa.setBounds(330, 90, 80, 30);
         price.setBounds(415, 90, 350, 30);
         add(priceLa);add(price);
        	 
         discountLa=new JLabel("할인");
         discount=new JLabel();
        	 
         discountLa.setBounds(330, 125, 80, 30);
         discount.setBounds(415, 125, 250, 30);
         add(discountLa);add(discount);
        	 
         deliveryLa=new JLabel("배송");
         delivery=new JLabel();
        	 
         deliveryLa.setBounds(330, 160, 80, 30);
         delivery.setBounds(415, 160, 250, 30);
         add(deliveryLa);add(delivery);
         box=new JComboBox<Integer>();
         box.addItem(1);
         box.addItem(2);
         box.addItem(3);
         box.addItem(4);
         box.addItem(5);
         b1=new JButton("구매");
         b2=new JButton("목록");
        	 
        	 
         JPanel p=new JPanel();
         p.add(box);p.add(b1);p.add(b2);
         p.setBounds(330, 200, 435, 35);
         add(p);
    }
    public void print(int type,int gno)
    {
    	this.type=type;
    	this.gno=gno;
    	//GoodsVO vo=dao.goodsDetailData(type, gno);
    	// 데이터 주입 
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
