package GUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

import GUI.LoginFrame.Btn_Success;
import GUI.LoginFrame.Join_Memebership;
import student.StudentInfoManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class administrate_Console extends JFrame {
		//전체 프레임
		JPanel mainPanel;
		
		//화면에 보이는 것들
		JButton successBtn;
		JButton joinMembership;
		JLabel passwordLabel;
		JLabel userIDLabel;
		
		private JTextField userIDField;
		private JPasswordField passwordField;

		administrate_Console(){
			setTitle("Login");
			setSize(300, 200);
			setLocation(500, 400);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			//전체 프레임 설정
			mainPanel = new JPanel();
			mainPanel.setBackground(Color.decode("#FFFFFF"));
			mainPanel.setLayout(new GridLayout(3, 1));
			
	        
	        //비밀번호 입력란
	        passwordLabel = new JLabel("관계자 이외 출입금지 :");
	        passwordLabel.setForeground(Color.BLACK);
	        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));   
	        passwordField = new JPasswordField();
	        passwordField.setBackground(Color.decode("#FFFFFF"));
	        passwordField.setForeground(Color.BLACK);
	        passwordField.setFont(new Font("Helvetica", Font.BOLD, 10));

	        //로그인 버튼
	        successBtn = new JButton("로그인");
	        successBtn.setBackground(Color.decode("#FFFFFF"));
	        successBtn.setForeground(Color.BLACK);
	        successBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
	        successBtn.addActionListener(new Btn_Success());

	        //만들었는거 화면에 다 띄우기
	        mainPanel.add(passwordLabel);
	        mainPanel.add(passwordField);
	        mainPanel.add(successBtn);
	        add(mainPanel);
	        setVisible(true);

	}
		
		class Btn_Success implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//로그인 유효성 검사
	        	String newPassword = new String(passwordField.getPassword());
				StudentInfoManager SIM = new StudentInfoManager();
	        	
				if (newPassword.equals("135789")) new MainFrame();
				else {JOptionPane.showMessageDialog(null, "로그인 실패", "관리자 이외에 접근 금지!", JOptionPane.ERROR_MESSAGE);}
				setVisible(false);
			}
		}
}

