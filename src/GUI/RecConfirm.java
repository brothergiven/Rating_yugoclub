package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;
import member.*;
import member.Record;


public class RecConfirm extends JFrame {
	static void setColor(Component c) {

		c.setFont(new Font("Helvetica", Font.BOLD, 15));

		if(c instanceof JTextField)
			((JTextField) c).setEditable(false);
		else {
			c.setBackground(Color.white);
			c.setForeground(Color.black);

		}
	}

	private JButton admit = new JButton("승인"), reject = new JButton("거절");
	private JLabel wIDLabel = new JLabel("백 학번"), bIDLabel = new JLabel("흑 학번"), 
			wNameLabel = new JLabel("백 이름"), bNameLabel = new JLabel("흑 이름"), 
			result = new JLabel("결과"), dateLabel = new JLabel("날짜");
	private JTextField wIDField, bIDField, wNameField, bNameField, resultField, dateField;

	public RecConfirm(Record r) {
		setSize(600, 400);
		setLocation(660, 240);
		setLayout(new GridLayout(7, 2));


		int bID = r.getbID();
		int wID = r.getwID();
		String bName = Main.memberRef.members.get(bID).getName();
		String wName = Main.memberRef.members.get(wID).getName();
		bIDField = new JTextField(Integer.toString(bID));
		wIDField = new JTextField(Integer.toString(wID));
		bNameField = new JTextField(bName);
		wNameField = new JTextField(wName);
		
		String res = (r.getResult()) ? "흑 승" : "백 승";
		resultField = new JTextField(res);
		
		
		GregorianCalendar date = r.getDate();
		
		dateField = new JTextField(String.format("%d년 %d월 %d일", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
		
		setVisible(true);
		
		admit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recordRef.inputRecords(r);
				JOptionPane.showMessageDialog(null, "기록 등록이 승인되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}

		});
		
		reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "기록 등록이 거절되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		setColor(bIDLabel);
		setColor( bIDField);
		setColor( bNameLabel);
		setColor( bNameField);
		setColor( wIDLabel);
		setColor( wIDField);
		setColor( wNameLabel);
		setColor( wNameField);
		setColor( result);
		setColor( resultField);
		setColor( dateLabel);
		setColor( dateField);
		setColor( admit);
		setColor( reject);
		
		add(bIDLabel);
		add(bIDField);
		add(bNameLabel);
		add(bNameField);
		add(wIDLabel);
		add(wIDField);
		add(wNameLabel);
		add(wNameField);
		add(result);
		add(resultField);
		add(dateLabel);
		add(dateField);
		add(admit);
		add(reject);

		
	}
}
