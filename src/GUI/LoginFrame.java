package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

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
	private String newID;
	private String newPassword; 
	private char[] passwordChars;
	
	LoginFrame(){
		/*
	 	setLayout(new GridLayout(3, 2)): 컨테이너의 구성 요소를 격자 모양으로 배치
		setForeground: 텍스트나 그래픽의 색상을 설정
		setBackground: 배경색 설정
		setFont(new Font("Helvetica", Font.BOLD, 15)): 컴포넌트의 폰트 설정. 'Helvetica'라는 이름의 폰트를 굵게 (Font.BOLD) 설정하고, 폰트 크기를 15포인트로 설정
		setTitle: 이 메소드는 창의 제목을 설정. 전달된 문자열이 창의 제목 표시줄에 나타남
		setSize: 이 메소드는 창의 크기를 설정. 첫 번째 매개변수는 창의 너비, 두 번째 매개변수는 창의 높이를 픽셀 단위로 설정
		setLocation: 이 메소드는 창의 위치를 설정. 첫 번째 매개변수는 창의 x 좌표 위치, 두 번째 매개변수는 창의 y 좌표 위치를 픽셀 단위로 설정
		setDefaultCloseOperation: 이 메소드는 창이 닫힐 때의 동작을 설정. 
		setLocationRelativeTo: 이 메소드는 창의 위치를 다른 컴포넌트에 상대적으로 설정. 매개변수로 null을 전달하면 창이 화면 중앙에 위치
		JPanel: JPanel은 컨테이너 클래스로, 다른 Swing 컴포넌트들을 그룹화하여 단일 패널에서 관리할 수 있게 해줌. 예를 들어, 버튼, 레이블, 텍스트 필드 등을 JPanel에 추가하고, 이 JPanel을 JFrame에 추가할 수 있음
		JLabel: JLabel은 텍스트나 이미지를 표시하는데 사용되는 컴포넌트. 사용자와의 상호작용은 없지만, 다른 컴포넌트를 설명하는 데 유용하게 사용된다.
		JTextField: JTextField는 사용자로부터 텍스트 입력을 받는데 사용되는 컴포넌트. 사용자는 이 필드에 텍스트를 입력할 수 있으며, 프로그램은 이 텍스트를 읽을 수 있음
		JPasswordField: JPasswordField는 JTextField와 유사하지만, 사용자가 입력하는 텍스트를 마스킹하여 보안을 유지하도록 설계됨. 주로 비밀번호 입력에 사용.
		JButton: JButton은 사용자가 클릭할 수 있는 버튼 컴포넌트. 버튼에는 텍스트나 이미지를 표시할 수 있으며, 사용자가 버튼을 클릭하면 이벤트가 발생하고 이에 대응하는 코드가 실행됨.
		*/
		
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
	        newID = userIDField.getText();
	        passwordChars = passwordField.getPassword();
	        newPassword = new String(passwordChars);    //new String(char[]) 생성자를 사용. 비밀번호는 string보다 char이 더 낫다

	        //ID String을 int로 변환
	        int newIDInt = 0;
	        try {
	            newIDInt = Integer.parseInt(newID);
	        } catch (NumberFormatException ex) {
	            // 숫자가 아닌 값을 입력했을 때 처리
	            JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        StudentInfoManager SIM = new StudentInfoManager();
	        boolean isLoginSuccess = SIM.findMembers(newIDInt, newPassword);

	        // 비밀번호를 사용한 후에는 char[]를 비워준다.
	        Arrays.fill(passwordChars, '0');

	        if (isLoginSuccess) {
	            new MainFrame();
	            setVisible(false);
	        } else {
	            // 로그인 실패 알림
	            JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
	        }
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
        registerFrame.setSize(600, 600);
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
        newIDLabel = new JLabel("아이디(학번을 입력하세요.): ");
        newIDLabel.setForeground(Color.BLACK);
        newIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        
        newIDField = new JTextField();
        newIDField.setBackground(Color.decode("#FFFFFF"));
        newIDField.setForeground(Color.BLACK);
        newIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //비밀번호 입력란
        newPasswordLabel = new JLabel("비밀번호(g+학번 ex)g22410101 ):");
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
            	   	
            	// 아이디가 2로 시작하고, 8자리 정수형인지 검사
                if(!newID.matches("2\\d{7}")) {
                    // 조건에 맞지 않는 경우, 오류 창 띄우기
                    JOptionPane.showMessageDialog(registerFrame, "아이디는 학번을 입력해야합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
                // 비밀번호가 'g' + newID와 일치하는지 검사
                else if(!newPassword.equals("g" + newID)) {
                    // 일치하지 않는 경우, 오류 창 띄우기
                    JOptionPane.showMessageDialog(registerFrame, "비밀번호가 잘못되었습니다. 비밀번호는 'g'와 학번 아이디가 결합한 형태여야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                } else {
                    // 모든 조건이 일치하는 경우, 회원가입 진행
                    System.out.println("새로운 사용자 등록 - 이름: "+ newUsername + " 학과: " + newMajor + " 학번 아이디: " + newID + ", 비밀번호: " + newPassword);
                    StudentInfoManager SIM = new StudentInfoManager();
                    SIM.registerMembers(newUsername, newMajor, newID, newPassword);
                    
                    // 회원가입 완료 메시지 띄우기
                    JOptionPane.showMessageDialog(registerFrame, "회원가입이 완료되었습니다.", "회원가입 완료", JOptionPane.INFORMATION_MESSAGE);
                    
                    registerFrame.dispose(); // 등록 창 닫기
                }
            }
        });
        registerFrame.setVisible(true);
   }
}