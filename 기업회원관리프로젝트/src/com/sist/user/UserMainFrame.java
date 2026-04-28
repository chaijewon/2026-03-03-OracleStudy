package com.sist.user;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class UserMainFrame extends JFrame
implements ActionListener
{
    MenuPanel mp=new MenuPanel();
    ControlPanel cp;
    static boolean bLogin=false;
    static char isAdmin='n';
    Login login=new Login();
    public UserMainFrame()
    {
    	cp=new ControlPanel();
    	setLayout(null);
    	mp.setBounds(250, 15, 700, 45);
    	cp.setBounds(10,70, 980, 580);
    	add(mp);
    	add(cp);
    	
    	setSize(1024, 700);
    	setVisible(true);
    	mp.b3.addActionListener(this);
    	mp.b2.addActionListener(this);
    	mp.b1.addActionListener(this);
    	
    	// login
    	login.b1.addActionListener(this);// 로그인 
    	login.b2.addActionListener(this);// 취소 
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
        	UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }catch(Exception ex) {}
        new UserMainFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==mp.b3)
		{
			cp.card.show(cp, "JOIN");
		}
		else if(e.getSource()==mp.b1)
		{
			cp.card.show(cp, "HOME");
		}
		else if(e.getSource()==mp.b2)
		{
			login.tf.setText("");
			login.pf.setText("");
			login.setVisible(true);
		}
		// 로그인 
		else if(e.getSource()==login.b2)
		{
			login.setVisible(false);
		}
		else if(e.getSource()==login.b1)
		{
			
		}
		
	}

}
