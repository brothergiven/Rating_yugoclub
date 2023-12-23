package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import student.Record_Manager;
import student.SInfo;
import student.StudentInfoManager;



public class Frame_Record extends JFrame{
    
	JButton update;
	private StudentInfoManager SIM;
	Record_Manager RM = new Record_Manager(SIM);
	public Frame_Record(StudentInfoManager ref) {
		SIM = ref;
		SIM.resetRating();
		setTitle("전적 기록");
		setSize(300, 200);
		setVisible(true);
	}
	
	
}