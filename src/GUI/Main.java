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
	// MainFrame�� �� ��ư 4��
	private JButton btnRanking = new JButton("Ranking");
	private JButton btnSearch = new JButton("�˻�");
	private JButton btnRecord = new JButton("���� ���");
	private JButton btnInsert = new JButton("���� �Է�");
	
	// ��� ������ ������ ��ü �Ŵ���
	StudentInfoManager SIM = new StudentInfoManager(); // ������ ȣ�� �� ���Ͽ� �ִ� �ο� ���� SIM.Members�� ����
	
	MainFrame(){
		// ������ ȣ��� ���Ͽ��� �ο����� �о��
	    SIM.readMembers();
	    SIM.updateRanking();
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