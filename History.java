package project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// import sun.nio.cs.StandardCharsets;

public class History {
	private static String EMPTY = "";
public void addUser(String name, String password){
     System.out.println(name + " pass" + password);
	try(FileWriter fileWriter = new FileWriter("C:/Users/Marhaba/Desktop/user.txt",true)) {
	 String val =	loginUser(name,password);
	 System.out.println(val);
	if(val.equals(EMPTY)) {
		String fileContent =  "Username:"+name+ " Password:"+password+ System.lineSeparator() ;
	    fileWriter.append(fileContent);
	    fileWriter.close();
	}else {
		JFrame f=new JFrame(); 
		JOptionPane.showMessageDialog(f,"User with these credentials already exists."); 
	}
	}catch (IOException e) {
		System.out.println(e);
	    // Cxception handling
	}
	}
public String loginUser(String name, String password){
	try  
	{  
	File file=new File("C:/Users/Marhaba/Desktop/user.txt");    //creates a new file instance  
	FileReader fr=new FileReader(file);   //reads the file  
	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
	String line; 
	String returnLine="";
	while((line=br.readLine())!=null)  
	{  
	String username= line.split(" ")[0].split(":")[1];
	String userpass= line.split(" ")[1].split(":")[1];
	if(name.equals(username) && password.equals(userpass)) {
		 returnLine=line;
	}
	
	}
	return returnLine;
	}
	catch(IOException e)  
	{  
		e.printStackTrace(); 
		return "Exception";
	 
	}  
}

public void addMessage(String sender, String message){
    
	try(FileWriter fileWriter = new FileWriter("C:/Users/Marhaba/Desktop/history.txt",true)) {
	
	
		String fileContent =  "Sender:"+sender+ " Message:"+message + System.lineSeparator() ;
	    fileWriter.append(fileContent);
	    fileWriter.close();
	
	}catch (IOException e) {
		System.out.println(e);
	    // Cxception handling
	}
	}



public String getHistory(){
	try  
	{  
	File file=new File("C:/Users/Marhaba/Desktop/history.txt");    //creates a new file instance  
	FileReader fr=new FileReader(file);   //reads the file  
	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
	String line; 
	String returnLine="";
	String newLine="";
	while((line=br.readLine())!=null)  
	{  
		
		returnLine=returnLine.concat(line.split(" ")[0].split(":")[1]+":"+line.split("Message:")[1]) + "\n";
	}
	return returnLine;
	}
	catch(IOException e)  
	{  
		e.printStackTrace(); 
		return "Exception";
	 
	}  
}

public Boolean changePassword(String name, String password, String changepass){
	try  
	{  
	File file=new File("C:/Users/Marhaba/Desktop/user.txt");    //creates a new file instance  
	FileReader fr=new FileReader(file);   //reads the file  
	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
	String line; 
	String returnLine="";
	String newfileval="";
	while((line=br.readLine())!=null)  
	{  
		
		
	String username= line.split(" ")[0].split(":")[1];
	String userpass= line.split(" ")[1].split(":")[1];
	if(name.equals(username) && password.equals(userpass)) {
		
		newfileval+="Username:"+name+ " Password:"+changepass +System.lineSeparator();
	}
	else {
		newfileval+=line +System.lineSeparator();
	}
	
	}
	try(FileWriter fileWriter = new FileWriter("C:/Users/Marhaba/Desktop/user.txt")) {
		 
		
		    fileWriter.append(newfileval);
		    fileWriter.close();
		
			JFrame f=new JFrame(); 
			JOptionPane.showMessageDialog(f,"User with these credentials updated successfully."); 
			return true;
		}
		catch (IOException e) {
			System.out.println(e);
			return false;
		    // Cxception handling
		}
	}
	catch(IOException e)  
	{  
		e.printStackTrace();
	 return false;
	}  
}


public Boolean deleteAccount(String name, String password){
	try  
	{  
	File file=new File("C:/Users/Marhaba/Desktop/user.txt");    //creates a new file instance  
	FileReader fr=new FileReader(file);   //reads the file  
	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
	String line; 
	String returnLine="";
	String newfileval="";
	while((line=br.readLine())!=null)  
	{  
		
		
	String username= line.split(" ")[0].split(":")[1];
	String userpass= line.split(" ")[1].split(":")[1];
	if(name.equals(username) && password.equals(userpass)) {
	}
	else {
		newfileval+=line +System.lineSeparator();
	}
	
	}
	try(FileWriter fileWriter = new FileWriter("C:/Users/Marhaba/Desktop/user.txt")) {
		 
		
		    fileWriter.append(newfileval);
		    fileWriter.close();
		
			JFrame f=new JFrame(); 
			JOptionPane.showMessageDialog(f,"User Account has been deleted successfully."); 
			return true;
		}
		catch (IOException e) {
			System.out.println(e);
			return false;
		    // Cxception handling
		}
	}
	catch(IOException e)  
	{  
		e.printStackTrace();
	 return false;
	}  
}

}

