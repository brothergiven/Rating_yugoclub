package student;



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Record_Manager extends JFrame{
	private StudentInfoManager SIM;
	private Vector<Vector<String>> Records = new Vector<Vector<String>>();
	public Record_Manager(StudentInfoManager ref) {
		SIM = ref;
		readRecord();
	}
	private static final String FILE_Record = "�뱹���.txt";
	// ���� ������� Ȩ ���丮�� ����
	String userHome = System.getProperty("user.home");
	// ����ȭ�� ���丮 ��� ����
	Path desktopPath = Paths.get(userHome, "Desktop");
	String fileRecord = desktopPath.resolve(FILE_Record).toString();
	
	public void readRecord() { // �뱹��� ��� ������ �迭�� ����
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRecord), StandardCharsets.UTF_8))) {
    		String line;	
    		while((line = br.readLine()) != null) {
    			parseRecord(line);
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
	}
	    
	public void parseRecord(String line) { // line���� �������� �����ͼ� rating update
    	String[] parts = line.split(",");
    	String bName;
    	String wName;
    	String result;
    	try {
    		if(parts.length == 3) {
	    		bName = parts[0].trim();
	    		wName = parts[1].trim();
	    		if(parts[2].trim().equals("1")) result = "�� ��";
	    		else if(parts[2].trim().equals("0")) result = "�� ��";
	    		else result = "���º�";
	    		addRecord(bName, wName, result);
    		} else 
	    		System.out.println("�߸��� ������ ���� : " + line);
	    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    }
	    
	public void addRecord(String bName, String wName, String matchresult) {
		Vector<String> lineV = new Vector<String>();
		lineV.add(bName);
		lineV.add(wName);
		lineV.add(matchresult);
		Records.add(lineV);
	}
	
	public Vector<Vector<String>> getRecord(){
		return Records;
	}
	
	public void writeRecord() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRecord))) {
			// ���� ����� ���Ͽ� ����
				String line;
				Iterator<Vector<String>> it1 = Records.iterator();
				while(it1.hasNext()) {
					Iterator<String> it2 = it1.next().iterator();
					line = it2.next()+","+it2.next()+","+it2.next();
				}
		} catch (IOException e) {
			e.printStackTrace();
        }
    }
	
}