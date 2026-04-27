package com.sist.user;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

// 화면 변경 
public class ControlPanel extends JPanel{
	
	// 1. HOME 
	HomePanel hp;
	CardLayout card=new CardLayout();
    public ControlPanel()
    {
    	setBackground(Color.cyan);
    	setLayout(card);
    	hp=new HomePanel(this);
    	
    	add("HOME",hp);
    }
}
