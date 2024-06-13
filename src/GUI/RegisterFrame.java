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

public class RegisterFrame extends JFrame {
	// 전체 패널
	JPanel registerPanel;

	// 화면에 보이는 것들
	JLabel newIDLabel;
	JTextField newIDField;

	JLabel newPasswordLabel;
	JPasswordField newPasswordField;

	JLabel newMajorLabel;
	JTextField newMajorField;

	JLabel newUsernameLabel;
	JTextField newUsernameField;

	JLabel newLevelLabel;
	JTextField newLevelField;

	JButton submitButton;

	RegisterFrame() {
		setTitle("회원가입");
		setSize(600, 600);
		setLocation(660, 240);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// 전체 패널 기본 설정
		registerPanel = new JPanel();
		registerPanel.setBackground(Color.decode("#FFFFFF"));
		registerPanel.setLayout(new GridLayout(6, 2));

		// 이름 입력란
		newUsernameLabel = new JLabel("이름");
		newUsernameLabel.setForeground(Color.BLACK);
		newUsernameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		newUsernameField = new JTextField();
		newUsernameField.setBackground(Color.decode("#FFFFFF"));
		newUsernameField.setForeground(Color.BLACK);
		newUsernameField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 학과 입력란
		newMajorLabel = new JLabel("학과");
		newMajorLabel.setForeground(Color.BLACK);
		newMajorLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		newMajorField = new JTextField();
		newMajorField.setBackground(Color.decode("#FFFFFF"));
		newMajorField.setForeground(Color.BLACK);
		newMajorField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 학번 입력란
		newIDLabel = new JLabel("아이디");
		newIDLabel.setForeground(Color.BLACK);
		newIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		newIDField = new JTextField();
		newIDField.setBackground(Color.decode("#FFFFFF"));
		newIDField.setForeground(Color.BLACK);
		newIDField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 비밀번호 입력란
		newPasswordLabel = new JLabel("비밀번호");
		newPasswordLabel.setForeground(Color.BLACK);
		newPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		newPasswordField = new JPasswordField();
		newPasswordField.setBackground(Color.decode("#FFFFFF"));
		newPasswordField.setForeground(Color.BLACK);
		newPasswordField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 기력
		newLevelLabel = new JLabel("기력");
		newLevelLabel.setForeground(Color.black);
		newLevelLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		newLevelField = new JTextField();
		newLevelField.setBackground(Color.decode("#FFFFFF"));
		newLevelField.setForeground(Color.BLACK);
		newLevelField.setFont(new Font("Helvetica", Font.BOLD, 13));

		// 등록 버튼
		submitButton = new JButton("등록");
		submitButton.setBackground(Color.decode("#FFFFFF"));
		submitButton.setForeground(Color.BLACK);
		submitButton.setFont(new Font("Helvetica", Font.BOLD, 15));

		// 만들었는거 다 띄우기
		registerPanel.add(newUsernameLabel);
		registerPanel.add(newUsernameField);
		registerPanel.add(newMajorLabel);
		registerPanel.add(newMajorField);
		registerPanel.add(newIDLabel);
		registerPanel.add(newIDField);
		registerPanel.add(newPasswordLabel);
		registerPanel.add(newPasswordField);
		registerPanel.add(newLevelLabel);
		registerPanel.add(newLevelField);
		registerPanel.add(submitButton);
		add(registerPanel);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// register 등록
				String newUsername = newUsernameField.getText();
				String newMajor = newMajorField.getText();
				String newID = newIDField.getText();
				String newPassword = new String(newPasswordField.getPassword());
				String newLevel = newLevelField.getText();

				if (!newID.matches("\\d{8}")) {
					JOptionPane.showMessageDialog(null, "아이디는 학번 8자리를 입력해야합니다.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(newPassword.contains(",") || newPassword.contains(":")) {
					JOptionPane.showMessageDialog(null, "비밀번호에 \':\' 또는 \',\'는 사용할 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				Main.loginRef.requestRegister(Integer.parseInt(newID), newUsername, newMajor, newPassword, newLevel);
				dispose();
			}
		});
	}

}
