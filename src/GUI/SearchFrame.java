package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;

public class SearchFrame extends JFrame {
	int callerID;
	private JButton byName = new JButton("이름으로 검색"), byID = new JButton("학번으로 검색");
	private JLabel nameLabel = new JLabel("이름"), idLabel = new JLabel("ID");
	private JTextField nameField = new JTextField(), idField = new JTextField();

	public SearchFrame(int id) {
		callerID = id;

		setSize(600, 400);
		setLocation(660, 240);
		setBackground(Color.white);

		setLayout(new GridLayout(2, 3));

		add(nameLabel);
		add(nameField);
		add(byName);

		nameLabel.setBackground(Color.white);
		nameLabel.setForeground(Color.black);
		nameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		nameField.setBackground(Color.white);
		nameField.setForeground(Color.black);
		nameField.setFont(new Font("Helvetica", Font.BOLD, 15));

		byName.setBackground(Color.white);
		byName.setForeground(Color.black);
		byName.setFont(new Font("Helvetica", Font.BOLD, 15));

		add(idLabel);
		add(idField);
		add(byID);

		idLabel.setBackground(Color.white);
		idLabel.setForeground(Color.black);
		idLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		idField.setBackground(Color.white);
		idField.setForeground(Color.black);
		idField.setFont(new Font("Helvetica", Font.BOLD, 15));

		byID.setBackground(Color.white);
		byID.setForeground(Color.black);
		byID.setFont(new Font("Helvetica", Font.BOLD, 15));

		byID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int showingid = Integer.parseInt(idField.getText());
					if (Main.memberRef.members.containsKey(showingid)) {
						new InfoFrame(id, showingid);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "존재하지 않는 학번입니다.", "오류", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		byName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				try {
					int showingid = Main.memberRef.nametoid.get(name);
					new InfoFrame(id, showingid);
					dispose();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 이름입니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setVisible(true);
	}
}
