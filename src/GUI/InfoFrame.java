package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;
import member.Member;

public class InfoFrame extends JFrame {
	Member member;
	private JLabel name, id, dept, level, rating;
	private JTextField nameField, idField, deptField, levelField, ratingField;
	private JButton modify = new JButton("수정");
	private JButton req = new JButton("대국 요청");

	public InfoFrame(int callerID, int showingID) {
		setTitle("정보");
		setSize(600, 600);
		setLocation(660, 240);
		member = Main.memberRef.members.get(showingID);

		setLayout(new GridLayout(6, 2));

		name = new JLabel("이름");
		id = new JLabel("학번");
		dept = new JLabel("학과");
		level = new JLabel("기력");
		rating = new JLabel("점수");

		nameField = new JTextField(member.getName());
		idField = new JTextField(Integer.toString(member.getID()));
		deptField = new JTextField(member.getDepartment());
		levelField = new JTextField(member.getLevel());
		ratingField = new JTextField(Double.toString(member.getRating()));

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

		rating.setBackground(Color.white);
		rating.setForeground(Color.black);
		rating.setFont(new Font("Helvetica", Font.BOLD, 15));

		nameField.setBackground(Color.white);
		nameField.setForeground(Color.black);
		nameField.setFont(new Font("Helvetica", Font.BOLD, 15));

		idField.setBackground(Color.white);
		idField.setForeground(Color.black);
		idField.setFont(new Font("Helvetica", Font.BOLD, 15));

		deptField.setBackground(Color.white);
		deptField.setForeground(Color.black);
		deptField.setFont(new Font("Helvetica", Font.BOLD, 15));

		levelField.setBackground(Color.white);
		levelField.setForeground(Color.black);
		levelField.setFont(new Font("Helvetica", Font.BOLD, 15));

		ratingField.setBackground(Color.white);
		ratingField.setForeground(Color.black);
		ratingField.setFont(new Font("Helvetica", Font.BOLD, 15));

		nameField.setEditable(false);
		idField.setEditable(false);
		deptField.setEditable(false);
		levelField.setEditable(false);
		ratingField.setEditable(false);

		add(name);
		add(nameField);

		add(id);
		add(idField);

		add(dept);
		add(deptField);

		add(level);
		add(levelField);

		add(rating);
		add(ratingField);

		if (callerID == showingID || callerID == 99999999) {
			modify.setBackground(Color.white);
			modify.setForeground(Color.black);
			modify.setFont(new Font("Helvetica", Font.BOLD, 15));
			deptField.setEditable(true);
			levelField.setEditable(true);
			nameField.setEditable(true);
			add(modify);
			modify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int val = JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);
					if (val == 0) {
						member.setDepartment(deptField.getText());
						member.setLevel(levelField.getText());
						member.setName(nameField.getText());
						dispose();
					} else {
						dispose();
					}
				}
			});

		} else { // callerID != admin && callerID != showingID
			req.setBackground(Color.white);
			req.setForeground(Color.black);
			req.setFont(new Font("Helvetica", Font.BOLD, 15));
			add(req);
			req.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int val = JOptionPane.showConfirmDialog(null, member.getName() + "님께 대국 요청을 하시겠습니까?", "경고",
							JOptionPane.YES_NO_OPTION);
					if (val == 0) {
						try (BufferedWriter bw = new BufferedWriter(new FileWriter("GameRequest.txt", true))) {
							// 요청자 학번:상대방 학번 형식으로 파일에 저장
							bw.write(String.format("%d:%d", callerID, showingID));
							bw.newLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		}

		setVisible(true);
	}
}
