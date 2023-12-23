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

class Join_Membership extends JFrame{
	//기본 프레임
	JFrame registerFrame;
	//전체 패널
	JPanel registerPanel;
	
	//화면에 보이는 것들
	JLabel newIDLabel;
	JTextField newIDField;
	
	JLabel newPasswordLabel;
	JPasswordField newPasswordField;
	
	JLabel newMajorLabel;
	JTextField newMajorField;	
	
	JLabel newUsernameLabel;
	JTextField newUsernameField;	
	
	JButton submitButton;
	
	
	Join_Membership(){
        //기본 프레임 설정
		registerFrame = new JFrame("회원가입");
        registerFrame.setSize(300, 250);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);

        //전체 패널 기본 설정
        registerPanel = new JPanel();
        registerPanel.setBackground(Color.decode("#FFFFFF"));
        registerPanel.setLayout(new GridLayout(5, 2));
  
        //이름 입력란
        newUsernameLabel = new JLabel("이름");
        newUsernameLabel.setForeground(Color.BLACK);
        newUsernameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newUsernameField = new JTextField();
        newUsernameField.setBackground(Color.decode("#FFFFFF"));
        newUsernameField.setForeground(Color.BLACK);
        newUsernameField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //학과 입력란
        newMajorLabel = new JLabel("학과");
        newMajorLabel.setForeground(Color.BLACK);
        newMajorLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newMajorField = new JTextField();
        newMajorField.setBackground(Color.decode("#FFFFFF"));
        newMajorField.setForeground(Color.BLACK);
        newMajorField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //학번 입력란
        newIDLabel = new JLabel("학번 아이디:");
        newIDLabel.setForeground(Color.BLACK);
        newIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newIDField = new JTextField();
        newIDField.setBackground(Color.decode("#FFFFFF"));
        newIDField.setForeground(Color.BLACK);
        newIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //비밀번호 입력란
        newPasswordLabel = new JLabel("비밀번호:");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newPasswordField = new JPasswordField();
        newPasswordField.setBackground(Color.decode("#FFFFFF"));
        newPasswordField.setForeground(Color.BLACK);
        newPasswordField.setFont(new Font("Helvetica", Font.BOLD, 13));
           
        //등록 버튼
        submitButton = new JButton("등록");
        submitButton.setBackground(Color.decode("#FFFFFF"));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        
        //만들었는거 다 띄우기
        registerPanel.add(newUsernameLabel);
        registerPanel.add(newUsernameField);       
        registerPanel.add(newMajorLabel);
        registerPanel.add(newMajorField);
        registerPanel.add(newIDLabel);
        registerPanel.add(newIDField);        
        registerPanel.add(newPasswordLabel);
        registerPanel.add(newPasswordField);
        registerPanel.add(submitButton);        
        registerFrame.add(registerPanel);
        
        //등록 버튼 누른 후 처리과정
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//입력된 정보 변수에 저장
            	String newUsername = newUsernameField.getText();
            	String newMajor = newMajorField.getText();
            	String newID = newIDField.getText();
            	String newPassword = new String(newPasswordField.getPassword());
            	System.out.println("새로운 사용자 등록 - 이름: "+ newUsername + " 학과: " + newMajor + " 학번 아이디: " + newID + ", 비밀번호: " + newPassword);

                StudentInfoManager SIM = new StudentInfoManager();
                SIM.registerMembers(newUsername, newMajor, newID, newPassword);
                	
                registerFrame.dispose(); // 등록 창 닫기
            }
        });
        registerFrame.setVisible(true);
   }
}