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
		update = new JButton("최신화");
		update.addActionListener(new Btn_update());
		getContentPane().add(update);
		setVisible(true);
	}
	
	class Btn_update implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			SIM.resetRating();
			SIM.updateAllRating();
			SIM.saveMembers();
		}
	}
}

