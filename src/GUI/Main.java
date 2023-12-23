package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;


import java.awt.event.*;
import java.util.*;

import student.*;

public class Main{
	public static void main(String[] args) {
		new LoginFrame();
	}
}


class MainFrame extends JFrame{
	// MainFrame에 들어갈 버튼 4개
	private JButton btnRanking = new JButton("Ranking");
	private JButton btnSearch = new JButton("검색");
	private JButton btnRecord = new JButton("전적 기록");
	private JButton btnInsert = new JButton("전적 입력");
	private JButton btnEtc = new JButton("설정");
	private JButton miniGame = new JButton("놓아보기");
	// 모든 정보를 관리할 객체 매니저
	StudentInfoManager SIM = new StudentInfoManager(); // 생성자 호출 후 파일에 있는 부원 정보 SIM.Members에 저장
	RecordManager RM = new RecordManager();
	MainFrame(){
		// 생성자 호출시 파일에서 부원정보 읽어옴
	    SIM.readMembers();
	    SIM.updateRanking();
	    RM.readRecord();
		setTitle("Rating Project");
		setPreferredSize(new Dimension(300, 200));
	//	setLocation(500, 400);
	    
		Container c = getContentPane();
	    c.setLayout(new GridLayout(3, 2));
	    

	      
	    btnRanking.addActionListener(new Btn_Ranking());
	    btnSearch.addActionListener(new Btn_Search());
	    btnRecord.addActionListener(new Btn_Record());
	    btnInsert.addActionListener(new Btn_Insert());
	    btnEtc.addActionListener(new Btn_Etc());
		miniGame.addActionListener(new Action_miniGame());
		
	    c.add(btnRanking);
	    c.add(btnSearch);
	    c.add(btnRecord);
	    c.add(btnInsert);
	    c.add(btnEtc);
		c.add(miniGame);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
	
	class Btn_Ranking implements ActionListener {
	   public void actionPerformed(ActionEvent e) {
		   new Frame_Ranking(SIM);
	   }
	}
	
	class Btn_Search implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_Search(SIM);
		}
	}

	class Btn_Record implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_Record(SIM, RM);
		}
	}
	
	class Btn_Insert implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_Insert(SIM, RM);
		}
	}
	
	class Btn_Etc implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_Etc(SIM, RM);
		}
	}
	
	class Action_miniGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_MiniGame();
		}
	}
}






