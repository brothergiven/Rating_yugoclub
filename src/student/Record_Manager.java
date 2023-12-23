package student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.JFrame;

public class Record_Manager extends JFrame{
	private StudentInfoManager SIM;
	private Vector<Vector<String>> Records = new Vector<Vector<String>>();
	public Record_Manager(StudentInfoManager ref) {
		readRecord();
	}
	
	private static final String FILE_NAME = "�뱹���.txt";
	String userHome = System.getProperty("user.home");
	Path desktopPath = Paths.get(userHome, "Desktop");
	String filePath = desktopPath.resolve(FILE_NAME).toString();
	
	public void readRecord() { // �뱹��� ��� ������ �迭�� ����
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            parseRecord(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    	
    	Iterator<Vector<String>> it = Records.iterator();
    	while(it.hasNext()) {
    		Iterator<String> it2 = it.next().iterator();
    		while(it2.hasNext()) {
    			System.out.print(it2.next());
    		}
    		System.out.println();
    	}
	}
	    
	public void parseRecord(String line) { // line���� �������� �����ͼ� rating update
		Vector<String> lineV = new Vector<String>();
    	String[] parts = line.split(",");
    	try {
    		if(parts.length == 3) {
	    		lineV.add(parts[0].trim());
	    		lineV.add(parts[1].trim());
	    		lineV.add(parts[2].trim());
    		} else 
	    		System.out.println("�߸��� ������ ���� : " + line);
	    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    	Records.add(lineV);
    }
	    
	    /*
	    public String[][] getRanking() {
	    	return ranking;
	    }
	    
	    public String[] searchMember(int id) {
	    	// �й� ���� �޾Ƽ� �ش��ϴ� ��ŷ �� ���
	    	SInfo info = Members.get((Integer)id);
	    	int i;
	    	for(i = 0; i < ranking.length; i++) {
	    		System.out.println(info.getName().equals(ranking[i][1]));
	    		if(info.getName().equals(ranking[i][1]))
	    			return ranking[i];
	    	}
	    	return null;
	    }
	    */
	//���� ����
	//���Ͽ��� �������� �Լ�
	//����ڿ��� �Է� �޾Ƽ� �����ϴ� �Լ�
	//����� �����͸� ���Ͽ� ���� �Լ�
}
