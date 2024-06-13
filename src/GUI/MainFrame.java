package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Main;

public class MainFrame extends JFrame {
	private int id;
	private String name;
	private JButton[] btns = new JButton[8];
	private JButton rankingBtn = new JButton("랭킹 출력"),			
			inputBtn = new JButton("전적 입력"),
			recordBtn = new JButton("전적 검색"),
			memberBtn = new JButton("부원 검색"), 
			infoBtn = new JButton("내 정보"),
			gameBtn = new JButton("대국 관리"), 
			adminBtn = new JButton("관리자"),
			logOutBtn = new JButton("로그아웃");

	MainFrame(int id, String name) {
		this.id = id;
		this.name = name;
		setTitle(name + "님 환영합니다.");
		setSize(600, 600);
		setVisible(true);		
		setLocation(660, 240);
		setLayout(new GridLayout(4, 2));
		btns[0] = rankingBtn; btns[1] = inputBtn; btns[2] = recordBtn; btns[3] = memberBtn;
		btns[4] = infoBtn; btns[5] = gameBtn; btns[6] = adminBtn; btns[7] = logOutBtn;
		for(int i = 0; i < 8; i++) {
			btns[i].setBackground(Color.white);
			btns[i].setForeground(Color.black);
			btns[i].setFont(new Font("Helvetica", Font.BOLD, 15));
			add(btns[i]);
		}

		
		
		
		rankingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RankingFrame();
			}
		});
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InputFrame();
			}
		});
		
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RecordFrame();
			}
		});
		
		memberBtn.addActionListener(new ActionListener() { // 부원 검색, InfoFrame 까지
			public void actionPerformed(ActionEvent e) {
				new SearchFrame(id);
			}
		});
		
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoFrame(id, id);
			}
		});
		
		gameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameFrame(id);
			}
		});
		
		
		adminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == 99999999)
					new AdminFrame();
				else {
					JOptionPane.showMessageDialog(null, "관리자만 실행할 수 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	

}
