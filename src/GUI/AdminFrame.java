package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;
import member.MemberList;
import member.RecordList;

public class AdminFrame extends JFrame {
	// 기능
	// 전적 입력 컨펌, 회원가입 컨펌
	// 관리자
	private JButton reg = new JButton("회원가입 관리");
	private JButton rec = new JButton("기록입력 관리");

	public AdminFrame() {
		setSize(600, 600);
		setLocation(660, 240);
		setLayout(new FlowLayout());

		add(reg);
		add(rec);

		reg.setSize(200, 100);
		reg.setBackground(Color.white);
		reg.setForeground(Color.black);
		reg.setFont(new Font("Helvetica", Font.BOLD, 15));
		reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> list = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ConfirmRegister.txt"), "utf-8"));) {
					String line;
					while ((line = br.readLine()) != null) {
						list.add(line);
					}
					br.close();
					if (list.size() == 0) {
						JOptionPane.showMessageDialog(null, "처리해야 할 회원가입이 없습니다.", "알림",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						for (String str : list) {
							String[] parts = str.split(":");
							if (parts.length == 5)
								new RegConfirm(parts);
						}
					}
					BufferedWriter bw = new BufferedWriter(new FileWriter("ConfirmRegister.txt"));
					bw.write("");
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		rec.setSize(200, 100);
		rec.setBackground(Color.white);
		rec.setForeground(Color.black);
		rec.setFont(new Font("Helvetica", Font.BOLD, 15));
		rec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> list = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ConfirmRecord.txt"), "utf-8"))) {
					String line;
					while ((line = br.readLine()) != null) {
						list.add(line);
					}
					br.close();
					if (list.size() == 0) {
						JOptionPane.showMessageDialog(null, "처리해야 할 기록이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

					} else {
						for (String str : list) {
							new RecConfirm(Main.recordRef.parseLine(str));
						}
					}
					br.close();
					BufferedWriter bw = new BufferedWriter(new FileWriter("ConfirmRecord.txt"));
					bw.write("");
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		setVisible(true);
	}

}
