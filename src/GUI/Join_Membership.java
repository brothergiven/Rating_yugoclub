package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import student.StudentInfoManager;

class Join_Membership extends JFrame{
	//�⺻ ������
	JFrame registerFrame;
	//��ü �г�
	JPanel registerPanel;
	
	//ȭ�鿡 ���̴� �͵�
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
        //�⺻ ������ ����
		registerFrame = new JFrame("ȸ������");
        registerFrame.setSize(300, 250);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);

        //��ü �г� �⺻ ����
        registerPanel = new JPanel();
        registerPanel.setBackground(Color.decode("#FFFFFF"));
        registerPanel.setLayout(new GridLayout(5, 2));
  
        //�̸� �Է¶�
        newUsernameLabel = new JLabel("�̸�");
        newUsernameLabel.setForeground(Color.BLACK);
        newUsernameLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newUsernameField = new JTextField();
        newUsernameField.setBackground(Color.decode("#FFFFFF"));
        newUsernameField.setForeground(Color.BLACK);
        newUsernameField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //�а� �Է¶�
        newMajorLabel = new JLabel("�а�");
        newMajorLabel.setForeground(Color.BLACK);
        newMajorLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newMajorField = new JTextField();
        newMajorField.setBackground(Color.decode("#FFFFFF"));
        newMajorField.setForeground(Color.BLACK);
        newMajorField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //�й� �Է¶�
        newIDLabel = new JLabel("�й� ���̵�:");
        newIDLabel.setForeground(Color.BLACK);
        newIDLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newIDField = new JTextField();
        newIDField.setBackground(Color.decode("#FFFFFF"));
        newIDField.setForeground(Color.BLACK);
        newIDField.setFont(new Font("Helvetica", Font.BOLD, 13));
        
        //��й�ȣ �Է¶�
        newPasswordLabel = new JLabel("��й�ȣ:");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        newPasswordField = new JPasswordField();
        newPasswordField.setBackground(Color.decode("#FFFFFF"));
        newPasswordField.setForeground(Color.BLACK);
        newPasswordField.setFont(new Font("Helvetica", Font.BOLD, 13));
           
        //��� ��ư
        submitButton = new JButton("���");
        submitButton.setBackground(Color.decode("#FFFFFF"));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 15));
        
        //������°� �� ����
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
        
        //��� ��ư ���� �� ó������
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//�Էµ� ���� ������ ����
            	String newUsername = newUsernameField.getText();
            	String newMajor = newMajorField.getText();
            	String newID = newIDField.getText();
            	String newPassword = new String(newPasswordField.getPassword());
            	System.out.println("���ο� ����� ��� - �̸�: "+ newUsername + " �а�: " + newMajor + " �й� ���̵�: " + newID + ", ��й�ȣ: " + newPassword);

                StudentInfoManager SIM = new StudentInfoManager();
                SIM.registerMembers(newUsername, newMajor, newID, newPassword);
                	
                registerFrame.dispose(); // ��� â �ݱ�
            }
        });
        registerFrame.setVisible(true);
   }
}
