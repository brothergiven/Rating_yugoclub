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


class LoginFrame extends JFrame{
	JPanel mainPanel;
	JButton successBtn; // 로그인 성공 버튼
	LoginFrame(){
		setTitle("Login");
		setSize(300, 200);
		setLocation(500, 400);
		mainPanel = new JPanel();
		
		Container c = getContentPane();
		successBtn = new JButton("시작");
		successBtn.addActionListener(new Btn_Success());
		c.add(successBtn);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class Btn_Success implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new MainFrame();
			setEnabled(false);
		}
	}
	
}

class MainFrame extends JFrame{
	// MainFrame에 들어갈 버튼 4개
	private JButton btnRanking = new JButton("Ranking");
	private JButton btnSearch = new JButton("검색");
	private JButton btnRecord = new JButton("전적 기록");
	private JButton btnInsert = new JButton("전적 입력");
	
	// 모든 정보를 관리할 객체 매니저
	StudentInfoManager SIM = new StudentInfoManager(); // 생성자 호출 후 파일에 있는 부원 정보 SIM.Members에 저장
	
	MainFrame(){
		// 생성자 호출시 파일에서 부원정보 읽어옴
	    SIM.loadMembers();
	    
		setTitle("Rating Project");
		setPreferredSize(new Dimension(300, 200));
		setLocation(500, 400);
	    
		Container c = getContentPane();
	    c.setLayout(new GridLayout(4, 1));
	    

	      
	    btnRanking.addActionListener(new Btn_Ranking());
	    btnSearch.addActionListener(new Btn_Search());
	    btnRecord.addActionListener(new Btn_Record());
	    btnInsert.addActionListener(new Btn_Insert());
	    
	    c.add(btnRanking);
	    c.add(btnSearch);
	    c.add(btnRecord);
	    c.add(btnInsert);
	    

	      
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
			new Frame_Record(SIM);
		}
	}
	
	class Btn_Insert implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Frame_Insert(SIM);
		}
	}
}






