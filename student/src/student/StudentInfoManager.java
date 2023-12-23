package student;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

import student.SInfo;

public class StudentInfoManager{
	static Scanner scanner = new Scanner(System.in);
    public StudentInfoManager() {
    	loadMembers();
	}
	private static final String FILE_NAME = "���ȸ���л����.txt";
    public static Map<Integer, SInfo> Members = new HashMap<Integer, SInfo>();
    
    public static Map<String, Integer> SIDs = new HashMap<String, Integer>(); 
    
    
  //�α��� �� �ʿ��� ����� ������ �� ����
    public boolean findMembers(String newID, String newPassword) { // ���Ͽ��� ��� ����� ������ HashMap�� ����
    	boolean isLoginSuccess = false;
        // ���� ������� Ȩ ���丮�� ����
        String userHome = System.getProperty("user.home");

        // ����ȭ�� ���丮 ��� ����
        Path desktopPath = Paths.get(userHome, "Desktop");

        // ������ ��ü ���
        String filePath = desktopPath.resolve(FILE_NAME).toString();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // ���Ͽ��� �� ���� �о�ͼ� Member ��ü�� ��ȯ
                isLoginSuccess = compareMember(line, newID, newPassword);
                if (isLoginSuccess) {
                    System.out.println("�α��� ����");
                    break; // �α��� ���������Ƿ� �ݺ��� ����
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return isLoginSuccess;
    }
      
    private boolean compareMember(String line, String newID, String newPassword) { // ���Ͽ��� Member �������� ����
        // ���Ͽ��� �о�� �� ���� �Ľ��Ͽ� Member ��ü�� ��ȯ
    	boolean isLoginSuccess = false;
        String[] parts = line.split(","); 
        SInfo tmp;
        if (parts.length == 4) {
            String name = parts[0].trim();
            int StudentInfoId = Integer.parseInt(parts[1].trim());
            String department = parts[2].trim();
            int rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(name, department, rating);
            //�� ��
            isLoginSuccess = String.valueOf(StudentInfoId).equals(newID) && String.valueOf(StudentInfoId).equals(newPassword);
        } else if (parts.length == 3){
        	String name = parts[0].trim();
        	int StudentInfoId = Integer.parseInt(parts[1].trim());
        	String department = parts[2].trim();
        	tmp = new SInfo(name, department, 1000);
        	isLoginSuccess = String.valueOf(StudentInfoId).equals(newID) && String.valueOf(StudentInfoId).equals(newPassword);
        } else {
            System.out.println("�߸��� ������ ����: " + line);
        }
		return isLoginSuccess;	
        
    }
    
    
	//�α��� �� ��� ����� ������ ���� �� �ҷ�����
    public void loadMembers() { // ���Ͽ��� ��� ����� ������ HashMap�� ����

        // ���� ������� Ȩ ���丮�� ����
        String userHome = System.getProperty("user.home");

        // ����ȭ�� ���丮 ��� ����
        Path desktopPath = Paths.get(userHome, "Desktop");

        // ������ ��ü ���
        String filePath = desktopPath.resolve(FILE_NAME).toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // ���Ͽ��� �� ���� �о�ͼ� Member ��ü�� ��ȯ
            	parseMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseMember(String line) { // ���Ͽ��� Member �������� ����
        // ���Ͽ��� �о�� �� ���� �Ľ��Ͽ� Member ��ü�� ��ȯ
        String[] parts = line.split(","); 
        SInfo tmp;
        if (parts.length == 4) {
            String name = parts[0].trim();
            int StudentInfoId = Integer.parseInt(parts[1].trim());
            String department = parts[2].trim();
            int rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(name, department, rating);
            Members.put(StudentInfoId, tmp);
            SIDs.put(name, StudentInfoId);
        } else if (parts.length == 3){
        	String name = parts[0].trim();
        	int SID = Integer.parseInt(parts[1].trim());
        	String department = parts[2].trim();
        	tmp = new SInfo(name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("�߸��� ������ ����: " + line);
        }	
    }

    
    
    //ȸ������ �� ��� ���
    public void registerMembers(String newUsername, String newMajor, String newID, String newPassword) { // ���Ͽ��� ��� ����� ������ HashMap�� ����
    	
        // ���� ������� Ȩ ���丮�� ����
        String userHome = System.getProperty("user.home");

        // ����ȭ�� ���丮 ��� ����
        Path desktopPath = Paths.get(userHome, "Desktop");

        // ������ ��ü ���
        String filePath = desktopPath.resolve(FILE_NAME).toString();
        System.out.println(filePath);
        try {
        	FileWriter fileWriter = new FileWriter(filePath, true);
        	String newUserInfo = newUsername+ "," + newID + ","+ newMajor + "," + 1000 + "\n";
            fileWriter.write(newUserInfo);
            fileWriter.close();
            System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    public String[][] getRanking() {
    	List<SInfo> list = new ArrayList<SInfo>(Members.values());
    	
    	Collections.sort(list, new Comparator<SInfo>() {
    		public int compare(SInfo s1, SInfo s2) {
    			return Integer.compare(s2.getRating(), s1.getRating());
    		}
		});
    	
    	String[][] result = new String[list.size()][4];
    	
    	for(int i = 0; i< list.size(); i++) {
    		result[i][0] = Integer.toString(i+1);
    		result[i][1] = list.get(i).getName();
    		result[i][2] = list.get(i).getDepartment();
    		result[i][3] = Integer.toString(list.get(i).getRating());
    	}
    	
    	return result;
    }
    

    
    public String[] searchMember(int id) {
    	String[][] rankInfo = getRanking();
    	SInfo info = Members.get((Integer)id);
    	int i;
    	for(i = 0; i < rankInfo.length; i++) {
    		if(info.getRating() == Integer.parseInt(rankInfo[i][3]))
    			return rankInfo[i];
    	}
    	return null;
    }
    
    public SInfo getSInfo(int id) {
    	return Members.get(id);
    }
    
    
    

    public void saveMembers() { // ���� ��� HashMap�� ���Ͽ� ����
       // ���� ������� Ȩ ���丮�� ����
       String userHome = System.getProperty("user.home");

       // ����ȭ�� ���丮 ��� ����
       Path desktopPath = Paths.get(userHome, "Desktop");

       // ������ ��ü ���
       String filePath = desktopPath.resolve(FILE_NAME).toString();

       try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
           // �ο� ����� ���Ͽ� ����
    	   Iterator<Integer> keys = Members.keySet().iterator();
           while(keys.hasNext()) {
        	   int SID = keys.next();
        	   SInfo info = Members.get(SID);
               bw.write(info.getName() + "," + SID + ","
                       + info.getDepartment() + "," + info.getRating());
               bw.newLine();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    
    public void resetRating() { // ��� ������� Rating�� 1000����.
                // ���� ������� Ȩ ���丮�� ����
        String userHome = System.getProperty("user.home");

        // ����ȭ�� ���丮 ��� ����
        Path desktopPath = Paths.get(userHome, "Desktop");

        // ������ ��ü ���
        String filePath = desktopPath.resolve(FILE_NAME).toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // ���Ͽ��� �� ���� �о�ͼ� Member ��ü�� ��ȯ
            	resetMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void resetMember(String line) { // ���Ͽ��� Member �������� ����
        // ���Ͽ��� �о�� �� ���� �Ľ��Ͽ� Member ��ü�� ��ȯ
        String[] parts = line.split(","); 
        SInfo tmp;
        if (parts.length == 4) {
            String name = parts[0].trim();
            int StudentInfoId = Integer.parseInt(parts[1].trim());
            String department = parts[2].trim();
            int rating = 1000;
            tmp = new SInfo(name, department, rating);
            Members.put(StudentInfoId, tmp);
            SIDs.put(name, StudentInfoId);
        } else if (parts.length == 3){
        	String name = parts[0].trim();
        	int SID = Integer.parseInt(parts[1].trim());
        	String department = parts[2].trim();
        	tmp = new SInfo(name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("�߸��� ������ ����: " + line);
            return;
        }	
        System.out.println(tmp.getName()+" ������ �ʱ�ȭ : 1000");
    }
    
    public void parseRecord(String line) { // line���� �������� �����ͼ� rating update
    	String[] parts = line.split(",");
    	String bName;
    	String wName;
    	boolean matchresult;
    	try {
    	if(parts.length == 3) {
    		bName = parts[0].trim();
    		wName = parts[1].trim();
    		
    		if(Integer.parseInt(parts[2].trim()) == 1) matchresult = true;
    		else matchresult = false;
    		updateRating(bName, wName, matchresult);
    		
    	}else 
    		System.out.println("�߸��� ������ ���� : " + line);
    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    }
    
    public void updateRating(String bName, String wName, boolean matchresult) {
    	int bID = SIDs.get(bName);
    	int wID = SIDs.get(wName);
    	SInfo bInfo = Members.get(bID);
    	SInfo wInfo = Members.get(wID);
    	System.out.println("������ ������Ʈ : " + bInfo.getName() + ", " + wInfo.getName());
    	RatingManager rm = new RatingManager((double)bInfo.getRating(), (double)wInfo.getRating(), matchresult);
    	rm.updateRatings();
    	bInfo.setRating((int)rm.getNewRatingA());
    	wInfo.setRating((int)rm.getNewRatingB());
    	Members.put(bID, bInfo);
    	Members.put(wID, wInfo);
    	saveMembers();
    	loadMembers();
    }
    
    public void updateAllRating() {
    	String FILE_NAME = "�뱹���.txt";
    	String userHome = System.getProperty("user.home");
    	
    	Path desktopPath = Paths.get(userHome, "Desktop");
    	
    	String filePath = desktopPath.resolve(FILE_NAME).toString();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    		String line;
    		
    		while((line = br.readLine()) != null) {
    			parseRecord(line);
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }


}