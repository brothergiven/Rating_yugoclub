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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import member.Login;

public class LoginFrame extends JFrame {

	// 전체 프레임
	JPanel mainPanel;

	// 화면에 보이는 것들
	JButton successBtn;
	JButton joinMembership;
	JLabel passwordLabel;
	JLabel userIDLabel;

	private JTextField userIDField;
	private JPasswordField passwordField;

	public LoginFrame() {
		setTitle("Login");
		setSize(300, 200);
		setLocation(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// 전체 프레임 설정
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.decode("#FFFFFF"));
		mainPanel.setLayout(new GridLayout(3, 2));

		// ID 입력란
		userIDLabel = new JLabel("학번:");
		userIDLabel.setForeground(Color.BLACK);
		userIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		userIDField = new JTextField();
		userIDField.setBackground(Color.decode("#FFFFFF"));
		userIDField.setForeground(Color.BLACK);
		userIDField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 비밀번호 입력란
		passwordLabel = new JLabel("비밀번호:");
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.decode("#FFFFFF"));
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Helvetica", Font.BOLD, 10));

		// 로그인 버튼
		successBtn = new JButton("로그인");
		successBtn.setBackground(Color.decode("#FFFFFF"));
		successBtn.setForeground(Color.BLACK);
		successBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
		successBtn.addActionListener(new Login_Btn());

		// 회원가입 버튼
		joinMembership = new JButton("회원가입");
		joinMembership.setBackground(Color.decode("#FFFFFF"));
		joinMembership.setForeground(Color.BLACK);
		joinMembership.setFont(new Font("Helvetica", Font.BOLD, 15));
		joinMembership.addActionListener(new Register_Btn());

		// 만들었는거 화면에 다 띄우기
		mainPanel.add(userIDLabel);
		mainPanel.add(userIDField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);
		mainPanel.add(successBtn);
		mainPanel.add(joinMembership);
		add(mainPanel);
		setVisible(true);

	}

	class Login_Btn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String textID = userIDField.getText();
			int ID;
			try {
	            ID = Integer.parseInt(textID);
	        } catch (NumberFormatException ex) {
	            // 숫자가 아닌 값을 입력했을 때 처리
	            JOptionPane.showMessageDialog(null, "숫자를 입력하세요.", "로그인에 실패했습니다.", JOptionPane.ERROR_MESSAGE);
	            return;
	        } 
			
			if(textID.length() != 8) {
				JOptionPane.showMessageDialog(null, "올바른 학번을 입력하세요.", "로그인에 실패했습니다.", JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
			char[] pwArr = passwordField.getPassword();
			String PW = new String(pwArr);
			if(Main.loginRef.login(ID, PW)) {
				String name;
				if(ID == 99999999) {
					name = "관리자";
				}
				else 
					name = Main.memberRef.members.get(ID).getName();
				JOptionPane.showMessageDialog(null, name + "님 환영합니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
				new MainFrame(ID, name);
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "존재하지 않는 학번 또는 비밀번호입니다.", "로그인에 실패했습니다.", JOptionPane.ERROR_MESSAGE);
		}
	}

	class Register_Btn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new RegisterFrame();
		}
	}

}
