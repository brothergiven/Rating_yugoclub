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
	String[][] info = new String[0][4];
	DefaultTableModel dtm;
	private StudentInfoManager SIM;
	public Frame_Search(StudentInfoManager ref) {
		SIM = ref;
		setTitle("�˻�");
		setSize(300, 200);
		topPnl = new JPanel(new BorderLayout());
		
		ID = new JLabel("�й�");
		sIDField = new JTextField();

		
		confirm = new JButton("Ȯ��");
		confirm.addActionListener(new Btn_confirm());
		
		topPnl.add(ID, BorderLayout.WEST);
		topPnl.add(sIDField, BorderLayout.CENTER);
		topPnl.add(confirm, BorderLayout.EAST);
		
		String[] columnName = {"���", "�̸�", "�а�", "����" };
		dtm = new DefaultTableModel(info, columnName);
		infoTbl = new JTable(dtm);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(topPnl, BorderLayout.NORTH);
		c.add(infoTbl, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	
	class Btn_confirm implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(sIDField.getText());
			System.out.println(id+"�ҷ�����");
			dtm.addRow(SIM.searchMember(id));
		}
	}
}