package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegConfirm extends JFrame {

	private JLabel name, id, dept, level;
	private JTextField nameField, idField, deptField, levelField;
	private JButton admit = new JButton("승인"), reject = new JButton("거절");

	public RegConfirm(String[] parts) {
		setSize(600, 600);
		setLocation(660, 240);

		setLayout(new GridLayout(5, 2));
		name = new JLabel("이름");
		id = new JLabel("학번");
		dept = new JLabel("학과");
		level = new JLabel("기력");

		nameField = new JTextField(parts[1]);
		idField = new JTextField(parts[0]);
		deptField = new JTextField(parts[2]);
		levelField = new JTextField(parts[4]);

		name.setBackground(Color.white);
		name.setForeground(Color.black);
		name.setFont(new Font("Helvetica", Font.BOLD, 15));

		id.setBackground(Color.white);
		id.setForeground(Color.black);
		id.setFont(new Font("Helvetica", Font.BOLD, 15));

		dept.setBackground(Color.white);
		dept.setForeground(Color.black);
		dept.setFont(new Font("Helvetica", Font.BOLD, 15));

		level.setBackground(Color.white);
		level.setForeground(Color.black);
		level.setFont(new Font("Helvetica", Font.BOLD, 15));

		nameField.setBackground(Color.white);
		nameField.setForeground(Color.black);
		nameField.setFont(new Font("Helvetica", Font.BOLD, 15));
		nameField.setEditable(false);

		idField.setBackground(Color.white);
		idField.setForeground(Color.black);
		idField.setFont(new Font("Helvetica", Font.BOLD, 15));
		idField.setEditable(false);

		deptField.setBackground(Color.white);
		deptField.setForeground(Color.black);
		deptField.setFont(new Font("Helvetica", Font.BOLD, 15));
		deptField.setEditable(false);

		levelField.setBackground(Color.white);
		levelField.setForeground(Color.black);
		levelField.setFont(new Font("Helvetica", Font.BOLD, 15));
		levelField.setEditable(false);

		add(name);
		add(nameField);

		add(id);
		add(idField);

		add(dept);
		add(deptField);

		add(level);
		add(levelField);

		add(admit);
		add(reject);

		admit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("MemberList.txt", true), "utf-8"))) {
					BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("LoginInfo.txt", true), "utf-8"));
					bw1.write(String.format("%s:%s:%s:%s:1000.0", parts[0], parts[1], parts[2], parts[4]));
					bw1.newLine();
					bw2.write(String.format("%s:%s", parts[0], parts[3]));
					bw2.newLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "회원가입이 승인되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}

		});

		reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입이 거절되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		setVisible(true);
	}
}
