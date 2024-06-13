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



public class InputFrame extends JFrame {
	private JTextField blackID = new JTextField();
	private JTextField whiteID = new JTextField();
	private JTextField[] dateInput = new JTextField[3];
	private JButton blackWinButton = new JButton("흑 승");
	private JButton whiteWinButton = new JButton("백 승");
	private JLabel black = new JLabel("흑 학번");
	private JLabel white = new JLabel("백 학번");
	private String fileName = "ConfirmRecord.txt";

	public InputFrame() {
		setTitle("전적 입력");
		setSize(600, 400);
		setLocation(660, 240);
		setBackground(Color.white);

		setLayout(new GridLayout(3, 3));

		blackWinButton.setBackground(Color.white);
		blackWinButton.setForeground(Color.black);
		blackWinButton.setFont(new Font("Helvetica", Font.BOLD, 15));

		whiteWinButton.setBackground(Color.white);
		whiteWinButton.setForeground(Color.black);
		whiteWinButton.setFont(new Font("Helvetica", Font.BOLD, 15));

		black.setBackground(Color.white);
		black.setForeground(Color.black);
		black.setFont(new Font("Helvetica", Font.BOLD, 15));

		white.setBackground(Color.white);
		white.setForeground(Color.black);
		white.setFont(new Font("Helvetica", Font.BOLD, 15));

		add(dateInput[0] = new JTextField("년 입력"));
		add(dateInput[1] = new JTextField("월 입력"));
		add(dateInput[2] = new JTextField("일 입력"));

		add(black);
		add(blackID);
		add(blackWinButton);

		add(white);
		add(whiteID);
		add(whiteWinButton);

		blackWinButton.addActionListener(new BlackWin());
		whiteWinButton.addActionListener(new WhiteWin());

		setVisible(true);
	}

	class BlackWin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				inputRecord(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}

	class WhiteWin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				inputRecord(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}

	void inputRecord(boolean matchresult) throws IOException {
		
		try {
			int bID = Integer.parseInt(blackID.getText());
			int wID = Integer.parseInt(whiteID.getText());
			int year = Integer.parseInt(dateInput[0].getText());
			int month = Integer.parseInt(dateInput[1].getText());
			int day = Integer.parseInt(dateInput[2].getText());
			Member black = Main.memberRef.members.get(bID);
			Member white = Main.memberRef.members.get(wID);
			if (bID > 99999999 || bID < 10000000 || wID > 99999999 || wID < 10000000
					|| black == null || white == null) {
				JOptionPane.showMessageDialog(null, "올바르지 않은 학번 입력", "오류", JOptionPane.ERROR_MESSAGE);

			} else if (month > 12 || month < 1 || day > 31 || day < 1 || year > 2024 || year < 2020) {
				JOptionPane.showMessageDialog(null, "올바르지 않은 날짜 입력", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
				String s1 = String.format("%d:%d,", bID, wID);
				String s2 = String.format(",%d:%d:%d", year, month- 1, day);
				String line = matchresult ? s1 + "B" + s2 : s1 + "W" + s2;
				bw.write(line);
				bw.newLine();
				bw.close();
				JOptionPane.showMessageDialog(null, "입력되었습니다.", "입력", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "숫자를 입력하세요", "오류", JOptionPane.ERROR_MESSAGE);
		}

	}

}
