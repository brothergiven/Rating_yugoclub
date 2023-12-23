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
	JButton miniGame = new JButton("미니게임");
	public Frame_Etc(StudentInfoManager refS, RecordManager refR) {
		SIM = refS;
		RM = refR;
		setTitle("설정");
		setSize(300, 200);
		setVisible(true);
		c.setLayout(new GridLayout(3, 1));
		
		updateAll.addActionListener(new Action_updateAll());
		resetRating.addActionListener(new Action_reset());
		
		
		c.add(updateAll);
		c.add(resetRating);
		c.add(miniGame);
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
	
	class Action_miniGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		//	new Frame_MiniGame();
		}
	}
	
	public void updateByRecord() {
		SIM.resetRating(); // 1000으로 리셋
		String bName, wName;
		boolean matchResult;
		Iterator<Vector<String>> it1 = RM.getRecord().iterator();
		while(it1.hasNext()) {
			Iterator<String> it2 = it1.next().iterator();
			bName = it2.next();
			wName = it2.next();
			String result = it2.next();
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
