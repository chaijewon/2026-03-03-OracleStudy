package com.sist.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
    public GoodsDetailForm(ControlPanel cp)
    {
    	this.cp=cp;
    	setBackground(Color.orange);
    	setLayout(null);
    	b1.setBounds(10, 30, 100, 100);
    	add(b1);
    	b1.addActionListener(this);
    }
    public void print(int type,int gno)
    {
    	this.type=type;
    	this.gno=gno;
    	GoodsVO vo=dao.goodsDetailData(type, gno);
    	// 데이터 주입 
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			cp.card.show(cp, "HOME");
		}
	}
}
