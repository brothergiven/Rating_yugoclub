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
    	readMembers();
	}
	private static final String FILE_NAME = "���ȸ���л����.txt";
    private HashMap<Integer, SInfo> Members = new HashMap<Integer, SInfo>();
    private HashMap<String, Integer> SIDs = new HashMap<String, Integer>(); 
    private String[][] ranking;
    
    String userHome = System.getProperty("user.home");
    Path desktopPath = Paths.get(userHome, "Desktop");
    String filePath = desktopPath.resolve(FILE_NAME).toString();
    
    // �̸����� �й� ã��
    
    public void readMembers() { // ���Ͽ��� ��� ����� ������ HashMap�� ����


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
        SInfo tmp;
        int SID, rating;
        String name, department;
        
        String[] parts = line.split(","); // ","�� �������� ���ڿ� �и� 
        
        if (parts.length == 4) {
        	// �и��� ���ڿ��� 4����� (�й�, �̸�, �а�, ������)
            name = parts[0].trim();
            SID = Integer.parseInt(parts[1].trim());
            department = parts[2].trim();
            rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(SID, name, department, rating);
            Members.put(SID, tmp);
            SIDs.put(name, SID);
        } else if (parts.length == 3){
        	// �и��� ���ڿ��� 3����� (�й�, �̸�, �а�) ������ ���� �߰� 
        	name = parts[0].trim();
        	SID = Integer.parseInt(parts[1].trim());
        	department = parts[2].trim();
        	tmp = new SInfo(SID, name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("�߸��� ������ ����: " + line);
        }	
    }

    public void writeMembers() { // ���� ��� HashMap�� ���Ͽ� ����
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
    
    
    
    
    public void updateRanking() {
    	// ���� Member �ؽø��� �������� ��ŷ ���
    	// 2���� �迭�� ���� (���, �̸�, �а�, ����)
    	List<SInfo> list = new ArrayList<SInfo>(Members.values());
    	Collections.sort(list, new Comparator<SInfo>() {
    		public int compare(SInfo s1, SInfo s2) {
    			return Integer.compare(s2.getRating(), s1.getRating());
    		}
		});
    	ranking = new String[list.size()][4];
    	System.out.println("List Size : "+list.size());
		int rank = 0;
    	for(int i = 0; i< list.size(); i++) {
    		

    		ranking[i][0] = Integer.toString(++rank);
    		System.out.println("Added Ranking : "+rank);
    		if(list.get(i).getRating() == 1000) {
    			ranking[i][3] = "������� ����";
    		} else {
    			ranking[i][3] = Integer.toString(list.get(i).getRating());
    		}
    		ranking[i][1] = list.get(i).getName();
    		ranking[i][2] = list.get(i).getDepartment();
    		
    	}
    }
    
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
    
    
   
   
    
    public void resetRating() { 
    	// ��� ������� ������ 1000���� �ʱ�ȭ(
    	Map<Integer, SInfo> tmpMap = Members;
    	for(SInfo value : tmpMap.values()) {
    		System.out.println(value.getName()+"������ �ʱ�ȭ : 1000");
    		value.setRating(1000);
    	}
    }
    
    public void updateRating(String bName, String wName, boolean matchresult) {
    	// �ϳ��� ������ ���Ͽ� ������ ������Ʈ
    	int bID = SIDs.get(bName);
    	int wID = SIDs.get(wName);
    	SInfo bInfo = Members.get(bID);
    	SInfo wInfo = Members.get(wID);
    	int tmpRatingB = bInfo.getRating();
    	int tmpRatingW = wInfo.getRating();
    	RatingManager rm = new RatingManager((double)bInfo.getRating(), (double)wInfo.getRating(), matchresult);
    	rm.calculateRatings();
    	bInfo.setRating((int)rm.getNewRatingA());
    	wInfo.setRating((int)rm.getNewRatingB());
    	Members.put(bID, bInfo);
    	Members.put(wID, wInfo);
    	System.out.println("������ ������Ʈ : " + bInfo.getName()+tmpRatingB+"->"+bInfo.getRating() + ", " + wInfo.getName()+tmpRatingW+"->"+wInfo.getRating());
    }
    
    public boolean findMembers(String newID, String newPassword) { // ���Ͽ��� ��� ����� ������ HashMap�� ����
    	boolean isLoginSuccess = false;

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
        String name, department;
        int sID, rating;
        if (parts.length == 4) {
            name = parts[0].trim();
            sID = Integer.parseInt(parts[1].trim());
            department = parts[2].trim();
            rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(sID, name, department, rating);
            //�� ��
            isLoginSuccess = String.valueOf(sID).equals(newID) && String.valueOf(sID).equals(newPassword);
        } else if (parts.length == 3){
        	name = parts[0].trim();
        	sID = Integer.parseInt(parts[1].trim());
        	department = parts[2].trim();
        	tmp = new SInfo(sID, name, department, 1000);
        	isLoginSuccess = String.valueOf(sID).equals(newID) && String.valueOf(sID).equals(newPassword);
        } else {
            System.out.println("�߸��� ������ ����: " + line);
        }
		return isLoginSuccess;	
        
    }
    

    public void registerMembers(String newUsername, String newMajor, String newID, String newPassword) { // ���Ͽ��� ��� ����� ������ HashMap�� ����
    	

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
    
    
    public void updateRatingByRecord() {
    	// �뱹��� ��������� �������� ��� ���� ������Ʈ
    	resetRating();
    	String FILE_NAME = "�뱹���.txt";
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
    
    public SInfo getSInfo(int id) {
    	return Members.get(id);
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

}