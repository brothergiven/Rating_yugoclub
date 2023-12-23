package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import student.StudentInfoManager;



public class Frame_Record extends JFrame{
	JButton update;
	private StudentInfoManager SIM;
	public Frame_Record(StudentInfoManager ref) {
		SIM = ref;
		setTitle("전적 기록");
		setSize(300, 200);
		
		setVisible(true);
	}
	
	
}

