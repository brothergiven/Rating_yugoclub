package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import student.StudentInfoManager;


public class Frame_Search extends JFrame{
	JTextField sIDField;
	JLabel ID;
	JPanel topPnl;
	JButton confirm;
	JTable infoTbl;
	String[] columnName = {"등수", "이름", "학과", "점수" };
	String[][] info = new String[0][4];
	DefaultTableModel dtm;
	private StudentInfoManager SIM;
	public Frame_Search(StudentInfoManager ref) {
		SIM = ref;
		SIM.updateRanking();
		setTitle("검색");
		setSize(300, 200);
		topPnl = new JPanel(new BorderLayout());
		
		ID = new JLabel("학번");
		sIDField = new JTextField();

		
		confirm = new JButton("확인");
		confirm.addActionListener(new Btn_confirm());
		
		topPnl.add(ID, BorderLayout.WEST);
		topPnl.add(sIDField, BorderLayout.CENTER);
		topPnl.add(confirm, BorderLayout.EAST);
		

		dtm = new DefaultTableModel(info, columnName);
		dtm.addRow(columnName);
		infoTbl = new JTable(dtm);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(topPnl, BorderLayout.NORTH);
		c.add(infoTbl, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	
	class Btn_confirm implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				dtm.removeRow(0);
				int id = Integer.parseInt(sIDField.getText());
				dtm.addRow(SIM.searchMember(id));
			} catch(NumberFormatException n) {
				new E2Frame();
				dtm.addRow(columnName);
			}
		}
	}
}

