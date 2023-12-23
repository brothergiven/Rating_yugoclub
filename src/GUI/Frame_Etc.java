package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import student.*;

public class Frame_Etc extends JFrame {
	StudentInfoManager SIM;
	RecordManager RM;
	Container c = getContentPane();
	JButton updateAll = new JButton("전적 업데이트");
	JButton resetRating = new JButton("레이팅 초기화");
	JButton writeRecord = new JButton("전적 저장");
	JButton writeMembers = new JButton("부원 정보 저장");
	public Frame_Etc(StudentInfoManager refS, RecordManager refR) {
		SIM = refS;
		RM = refR;
		setTitle("설정");
		setSize(300, 200);
		setVisible(true);
		c.setLayout(new GridLayout(4, 1));
		
		updateAll.addActionListener(new Action_updateAll());
		resetRating.addActionListener(new Action_reset());
		writeRecord.addActionListener(new Action_writeRecord());
		writeMembers.addActionListener(new Action_writeMembers());
		
		c.add(updateAll);
		c.add(resetRating);
		c.add(writeRecord);
		c.add(writeMembers);
	}
	
	class Action_updateAll implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateByRecord();
		}
	}
	
	class Action_reset implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SIM.resetRating();
		}
	}

	class Action_writeRecord implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RM.writeRecord();
		}
	}
	
	class Action_writeMembers implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SIM.writeMembers();
		}
	}
	
	public void updateByRecord() {
		SIM.resetRating(); // 1000으로 리셋
		String bName, wName;
		boolean matchResult;
		System.out.println("RecordManager : updateByRecord()");
		for(int i = 0; i < RM.getRecord().size(); i++) {
			bName = RM.getRecord().get(i).get(0);
			wName = RM.getRecord().get(i).get(1);
			String result = RM.getRecord().get(i).get(2);
			if(result.equals("흑 승")) matchResult = true;
			else if(result.equals("백 승")) matchResult = false;
			else {
				System.out.println(bName + " vs " + wName + " : 무승부");
				continue;
			}
			SIM.updateRating(bName, wName, matchResult);
		}
		
	}
}
