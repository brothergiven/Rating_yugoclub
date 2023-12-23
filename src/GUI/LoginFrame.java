package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import student.*;

public class LoginFrame extends JFrame{
	
	//전체 프레임
	JPanel mainPanel;
	
	//화면에 보이는 것들
	JButton successBtn;
	JButton joinMembership;
	JLabel passwordLabel;
	JLabel userIDLabel;
	
	private JTextField userIDField;
	private JPasswordField passwordField;

	LoginFrame(){
		setTitle("Login");
		setSize(300, 200);
		setLocation(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//전체 프레임 설정
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.decode("#FFFFFF"));
		mainPanel.setLayout(new GridLayout(3, 2));
		
		//ID 입력란
        userIDLabel = new JLabel("학번:");
        userIDLabel.setForeground(Color.BLACK);
        userIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        userIDField = new JTextField();
        userIDField.setBackground(Color.decode("#FFFFFF"));
        userIDField.setForeground(Color.BLACK);
        userIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //비밀번호 입력란
        passwordLabel = new JLabel("비밀번호:");
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

        //회원가입 버튼
        joinMembership = new JButton("회원가입");
        joinMembership.setBackground(Color.decode("#FFFFFF"));
        joinMembership.setForeground(Color.BLACK);
        joinMembership.setFont(new Font("Helvetica", Font.BOLD, 15));
        joinMembership.addActionListener(new Join_Memebership());
               
        //만들었는거 화면에 다 띄우기
        mainPanel.add(userIDLabel);
        mainPanel.add(userIDField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(successBtn);
        mainPanel.add(joinMembership);
        add(mainPanel);
        setVisible(true);

	}
	
	class Btn_Success implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//로그인 유효성 검사
        	String newID = userIDField.getText();
        	String newPassword = new String(passwordField.getPassword());
			StudentInfoManager SIM = new StudentInfoManager();
        	SIM.findMembers(newID, newPassword);
			new MainFrame();
			setVisible(false);
		}
	}
	
	class Join_Memebership implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Join_Membership();
			setVisible(false);
		}
	}
	
}