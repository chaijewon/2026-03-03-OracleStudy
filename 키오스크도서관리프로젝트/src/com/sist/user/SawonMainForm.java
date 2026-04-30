package com.sist.user;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sist.vo.*;
import com.sist.dao.*;
public class SawonMainForm extends JFrame
implements ActionListener
{
    CardLayout card=new CardLayout();
    LoginForm login=new LoginForm();
    SawonControlForm scf=new SawonControlForm();
    ///////
    static int empno;
    static String ename;
    static String isadmin;
    //////// 웹 => session (프로그램 종료시까지 서버에 정보 유지)
    public SawonMainForm()
    {
    	setLayout(card);
    	add("LOGIN",login);
    	add("SCF",scf);
    	
    	
    	
    	
    	setSize(800, 600);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	login.b1.addActionListener(this);
    	login.b2.addActionListener(this);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
        	UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        }catch(Exception ex) {}
        new SawonMainForm();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b2)
		{
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==login.b1)
		{
			String empno=login.tf1.getText();
			if(empno.trim().length()<1)
			{
				login.tf1.requestFocus();
				return;
			}
			String ename=login.tf2.getText();
			if(ename.trim().length()<1)
			{
				login.tf2.requestFocus();
				return;
			}
			
			EmpDAO dao=new EmpDAO();
			EmpVO vo=dao.isLogin(
					Integer.parseInt(empno), ename);
			if(vo.getMsg().equals("NOSABUN"))
			{
				JOptionPane.showMessageDialog(this, 
						"사번이 존재하지 않습니다!!");
				login.tf1.setText("");
				login.tf2.setText("");
				login.tf1.requestFocus();
			}
			else if(vo.getMsg().equals("NONAME"))
			{
				JOptionPane.showMessageDialog(this, 
						"이름이 틀립니다!!");
				login.tf2.setText("");
				login.tf2.requestFocus();
			}
			else
			{
				this.empno=vo.getEmpno();
				this.ename=vo.getEname();
				this.isadmin=vo.getIsadmin();
				if(isadmin.equals("y"))
				{
					 // 관리자
			    	 scf.tp.setEnabledAt(0, true);
			    	 scf.tp.setEnabledAt(1, true);
			    	 scf.tp.setEnabledAt(2, true);
			    	 scf.tp.setEnabledAt(3, true);
			    	 scf.tp.setEnabledAt(4, true);
			    	 scf.tp.setEnabledAt(5, true);
			    	 
			    	 scf.tp.setSelectedIndex(0);
				}
				else
				{
					 // 일반 사원 
					 scf.tp.setEnabledAt(0, false);
			    	 scf.tp.setEnabledAt(1, true);
			    	 scf.tp.setEnabledAt(2, true);
			    	 scf.tp.setEnabledAt(3, false);
			    	 scf.tp.setEnabledAt(4, false);
			    	 scf.tp.setEnabledAt(5, false);
			    	 
			    	 scf.tp.setSelectedIndex(2);
				}
				
				card.show(getContentPane(), "SCF");
				
			}
			
		}
	}

}
