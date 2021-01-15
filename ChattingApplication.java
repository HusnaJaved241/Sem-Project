package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;

public class ChattingApplication extends JFrame  {
	 Socket s;
	 DataInputStream din;
	 DataOutputStream dout;
	 boolean shouldRun= true;
	 JPasswordField oldUserPassword;
	 private final static String newline = "\n";
	 History ss= new History();
	 JButton DeleteAccount;
	 JTextField olduserName;
	 JFrame frame= new JFrame(); 
	 JFrame f= new JFrame();
	 JPanel panel1=new JPanel();
	 JPanel panel2=new JPanel();
	 JPanel panel3=new JPanel();
	 JTextField Messages;
	 JTextArea Message;                 
	 JTextField username;
	 JButton changePassord;
	 JButton Login,send,history;
	 JButton Signup;
	 
	 Graphics g;
		public ChattingApplication() {
			
		}
		public class ClientConnection implements Runnable{
	        Socket s;
	        String uName;
	        
	    
	    public ClientConnection(Socket socket,String name ){
	        s=socket;
	        this.uName=name;
	    }
	    
	    
	    public void sendStringToServer(String text,String name){
	        try{
	            dout.writeUTF(name+":"+text);
	            dout.flush();
	        }catch(IOException e){
	            e.printStackTrace();
	            close();
	        }
	        
	    }
	    
	    public void run(){
	        
	        try{

	            din=new DataInputStream(s.getInputStream());
	            dout=new DataOutputStream(s.getOutputStream());
	             while(shouldRun){
	            try{
	                while(din.available() == 0){
	                    try{
	                        Thread.sleep(1);
	                    }catch(InterruptedException e){
	                        e.printStackTrace();
	                    }
	                }
	                
	                String reply=din.readUTF();
	             
	                Message.append(newline+reply);
	                System.out.println(reply);
	                
	            }catch(IOException e){
	            	JFrame f=new JFrame(); 
	    			JOptionPane.showMessageDialog(f,"User should be connected to server first."); 
	            }
	        }
	        }catch(IOException e){
	        	JFrame f=new JFrame(); 
    			JOptionPane.showMessageDialog(f,"User should connect server first."); 
	        }
	       
	    }
	    
	    public void close() {
	        try {
	            din.close();
	            dout.close();
	            s.close();
	            
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	    }
	    }
    
		    
public void window()
{ 
		panel1.setLayout(null);
		panel1.setBackground(Color.CYAN);
		panel1.setBounds(0,80,470,630);
		
		JLabel portaddress=new JLabel("Port Address");
		portaddress.setBounds(20,80,300,30);
		panel1.add(portaddress);
		
		JLabel requiredPortAddress=new JLabel("");
		requiredPortAddress.setBounds(190,100,200,40);
		requiredPortAddress.setForeground(Color.LIGHT_GRAY);
 		panel1.add(requiredPortAddress);
 		

		JTextField PortAddress=new JTextField();
		PortAddress.setBounds(180,80,140,30);
		PortAddress.setToolTipText("Port Address ");
		PortAddress.setText("Local Host");
		PortAddress.setEditable(false);
		panel1.add(PortAddress);
 		
		JLabel portnumber=new JLabel("Port Number");
		portnumber.setBounds(20,130,300,30);
		panel1.add(portnumber);
		
		JTextField PortNumber=new JTextField();
		PortNumber.setBounds(180,130,140,30);
		PortNumber.setText("3333");
		PortNumber.setForeground(Color.black);
		PortNumber.setEditable(false);
		PortNumber.setToolTipText("Port Number ");
		panel1.add(PortNumber);
		
		JLabel requiredPortNumber=new JLabel("");
		requiredPortNumber.setBounds(190,150,200,40);
		requiredPortNumber.setForeground(Color.red);
 		panel1.add(requiredPortNumber);
 		
		JButton Connect=new JButton("Connect");
		Connect.setBounds(350,100,100,40);
		Connect.setToolTipText("Press Button To Connect");
		Connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try{		        
			            s=new Socket("localhost",3333);   
			            Signup.setEnabled(true);
			            Login.setEnabled(true);
			                	           			            			            
			        }catch(IOException ee){
			        	JFrame f=new JFrame(); 
		    			JOptionPane.showMessageDialog(f,"please start server first."); 
			        }
			}	 			
	 	});
		panel1.add(Connect);
		
		
		JLabel Name=new JLabel("User Name");
		Name.setBounds(20,250,300,30);
		panel1.add(Name);
		
		username=new JTextField();
		username.setBounds(180,250,140,30);
		username.setToolTipText("Please Enter UserName ");
		panel1.add(username);
		
		JLabel requiredUserName=new JLabel("");
		requiredUserName.setBounds(190,270,200,40);
		requiredUserName.setForeground(Color.red);
 		panel1.add(requiredUserName);
		
		JLabel Password=new JLabel("User Password");
		Password.setBounds(20,300,300,30);
		panel1.add(Password);
		
		JPasswordField UserPassword=new JPasswordField(20);
		UserPassword.setBounds(180,300,140,30);
		UserPassword.setToolTipText("Please Enter Password ");
		panel1.add(UserPassword);
		
		JLabel requiredPassword=new JLabel("");
		requiredPassword.setBounds(190,320,200,40);
		requiredPassword.setForeground(Color.red);
 		panel1.add(requiredPassword);
		
 		
		
		Login=new JButton("Login");
		Login.setBounds(350,255,100,30);
		Login.setToolTipText("Press Button for Login");
		Login.setEnabled(false);
		
		
		Login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(username.getText().isEmpty()==true) 
		          {
	        	   requiredUserName.setText("User Name is requried");
		          }
				if(UserPassword.getText().isEmpty()==true) 
		          {
	        	   requiredPassword.setText("Password is requried");
		          }
				if(username.getText().isEmpty()==false && UserPassword.getText().isEmpty()==false) {
					Boolean flag= true;
					if(username.getText().isEmpty()==false)
			           {
						String name=username.getText();
			        	  for(int i=0;i<name.length();i++)
			 	            {
			        		  System.out.println(i);
			 	            	if((name.charAt(i)>='a')&&(name.charAt(i)<='z')||(name.charAt(i)>='A')&&(name.charAt(i)<='Z'))
			 	            	{
			 	            		
			 	            	}
			 	            	else
			 	            	{
			 	            		flag= false;
			 	            		requiredUserName.setText("Alphabets are required");
			 	            		requiredPassword.setText("");
			 	            	}
			 	            }
			           }
					System.out.println(flag);
					if(flag==true) {
				String EMPTY="";
     			
 				History h= new History();
 				String response=h.loginUser(username.getText(), UserPassword.getText());
 				if(response.equals(EMPTY)) {
 					JFrame f=new JFrame(); 
 					JOptionPane.showMessageDialog(f,"Invalid username or password"); 
 				}else {
 					
 				String userName=username.getText().toString();
 				olduserName.setText(userName);
 				olduserName.setEditable(false);
 				oldUserPassword.setText(UserPassword.getText());
 				oldUserPassword.setEditable(false);
 				changePassord.setEnabled(true);
 				history.setEnabled(true);
 				send.setEnabled(true);
 				DeleteAccount.setEnabled(true);
                                announceNewUser(s,userName);
 				} 
				}
				}
				
			}
	 			
	 	});
		
		panel1.add(Login);
		
		Signup=new JButton("Signup");
		Signup.setBounds(350,290,100,30);
		Signup.setToolTipText("Press Button for Signup");
		Signup.setEnabled(false);
		Signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName=username.getText().toString();
				String password=UserPassword.getText().toString();
				
 				olduserName.setText(userName);
 				olduserName.setEditable(false);
 				oldUserPassword.setText(UserPassword.getText());
 				oldUserPassword.setEditable(false);
 				changePassord.setEnabled(true);
 				DeleteAccount.setEnabled(true);
 				history.setEnabled(true);
 				send.setEnabled(true);
				ss.addUser(userName, password);
	            announceNewUser(s,userName);
			}	 			
	 	});
		panel1.add(Signup);
		
		
		
		panel2.setLayout(null);
		panel2.setBackground(Color.lightGray);
		panel2.setBounds(0,0,1400,80);	
		
		JLabel title=new JLabel("WELCOME TO CHATTING APPLICATION");
		title.setFont(new Font("Serif", Font.PLAIN, 20));
		title.setBounds(530,0, 400,100);
		panel2.add(title);
		
		panel3.setLayout(null);
		panel3.setBounds(470,80,470,630);
		panel3.setBackground(Color.decode("#42b3f5"));
		
		JLabel message=new JLabel("Messages");
		message .setBounds(175,40,70,50);
		panel3.add(message);
		
		Message=new JTextArea();
		Message.setBounds(25,100,420,350);
		panel3.add(Message);
		
		JLabel Msg=new JLabel("Message");
		Msg .setBounds(35,460,70,50);
		panel3.add(Msg);
		
		Messages=new JTextField(20);
		Messages.setBounds(90,470,250,30);
		Messages.setToolTipText("Please Type Message ");
		panel3.add(Messages);
		
		JLabel RequiredMsg=new JLabel("");
		RequiredMsg .setBounds(95,490,130,50);
		RequiredMsg.setForeground(Color.red);
		panel3.add(RequiredMsg);
		
		
		send=new JButton("Send Msg");
		send.setBounds(355,470,100,30);
		send.setEnabled(false);
		send.setToolTipText("Press to Send Message");
		
		send.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            
	            if(Messages.getText().isEmpty()==true)
	            {
	                RequiredMsg.setText("Type a Message");
	            }
	            else
	            {
	            	ss.addMessage(username.getText(), Messages.getText());
	                jButton1ActionPerformed(evt);
	            }
	            }
	        });
		
		history=new JButton("Get History");
		history.setBounds(355,520,100,30);
		history.setEnabled(false);
		history.setToolTipText("Press to View Histroy");
		history.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	Message.setText("");
	            	String his=ss.getHistory();
	            	Message.setText(his);
	                
	            }
	        });
		panel3.add(history);
		panel3.add(send);
		
		JPanel panel4=new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(600,80,470,200);
		panel4.setBackground(Color.decode("#93f5e6"));
		
		JLabel user=new JLabel("Change Password");
		user.setBounds(1100,120,200,50);
		panel4.add(user);
		
		JLabel oldusername=new JLabel("User Name");
		oldusername.setBounds(1000,180,150,30);
		panel4.add(oldusername);
		
		olduserName=new JTextField(20);
		olduserName.setBounds(1100,180,140,30);
		olduserName.setToolTipText("Please Enter User Name ");
		panel4.add(olduserName);
		
		JLabel Requiredoldusername=new JLabel("");
		Requiredoldusername.setForeground(Color.red);
		Requiredoldusername.setBounds(1100,205,150,30);
		panel4.add(Requiredoldusername);
		
		JLabel oldPassword=new JLabel("Old Password");
		oldPassword.setBounds(1000,230,150,30);
		panel4.add(oldPassword);

		oldUserPassword=new JPasswordField(20);
		oldUserPassword.setBounds(1100,230,140,30);
		oldUserPassword.setToolTipText("Please Enter Old Password ");
		panel4.add(oldUserPassword);
		

		JLabel RequiredoldPassword=new JLabel("");
		RequiredoldPassword.setForeground(Color.red);
		RequiredoldPassword.setBounds(1100,255,150,30);
		panel4.add(RequiredoldPassword);
		
		JLabel NewPassword=new JLabel("New Password");
		NewPassword.setBounds(1000,280,150,30);
		panel4.add(NewPassword);
		
		JPasswordField newpassword=new JPasswordField(20);
		newpassword.setBounds(1100,280,140,30);
		newpassword.setToolTipText("Please Enter New Password ");
		panel4.add(newpassword);
		
		JLabel RequirednewPassword=new JLabel("");
		RequirednewPassword.setForeground(Color.red);
		RequirednewPassword.setBounds(1100,310,150,30);
		panel4.add(RequirednewPassword);
		
		changePassord=new JButton("Change Password");
		changePassord.setBounds(1060,350,140,40);
		changePassord.setToolTipText("Press to Change Password ");
		panel4.add(changePassord);
		changePassord.setEnabled(false);
		
		changePassord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name=olduserName.getText();
				String oldpass= oldUserPassword.getText();
		           if(newpassword.getText().isEmpty()==true) 
			          {
		        	   RequirednewPassword.setText("New Password is requried");
			          }
		           else {
		        	  Boolean t= ss.changePassword(name, oldpass, newpassword.getText());
		        	  if(t== true) {
		        		  oldUserPassword.setText(newpassword.getText());
		        		  UserPassword.setText(newpassword.getText());
		        		  newpassword.setText("");
		        		  
		        	  }else{
		        		  JFrame f=new JFrame(); 
		      			  JOptionPane.showMessageDialog(f,"unable to update"); 
		        	  }
		           }
			}
			});
		DeleteAccount=new JButton("Delete Account");
		DeleteAccount.setBounds(1060,410,140,40);
		DeleteAccount.setEnabled(false);
		DeleteAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name=olduserName.getText();
				String oldpass= oldUserPassword.getText();
		          
		        	  Boolean t= ss.deleteAccount(name, oldpass);
		        	  if(t== true) {
		        		  oldUserPassword.setText("");
		        		  UserPassword.setText("");
		        		  newpassword.setText("");
		        		  olduserName.setText("");
		        		  username.setText("");	        
		        		  
		        	  }else{
		        		  JFrame f=new JFrame(); 
		      			  JOptionPane.showMessageDialog(f,"unable to delete"); 
		        	  }   
			}
			});
		DeleteAccount.setToolTipText("Press to Delete Account ");
		panel4.add( DeleteAccount);
	
		
		
		f.add(panel1);
		f.add(panel2);
		f.add(panel3);
		f.add(panel4);
		
		
		f.setBounds(100,10, 1000, 750);
		f.setVisible(true);
		f.setDefaultCloseOperation(3);
}

private void jButton1ActionPerformed(ActionEvent evt) {                                         
    
    String reply="";
    reply=Messages.getText();
    if(reply.contains("BYE")){
    	Message.append(newline+"disconnected");
        shouldRun=false;
        try{
       
        dout.writeUTF(username.getText()+":"+reply);
        dout.flush();
        
    }catch(IOException e){
        e.printStackTrace();
        close();
        
    }
    }else{
    try{
        dout.writeUTF(username.getText()+":"+reply);
        dout.flush();
        
    }catch(IOException e){
        e.printStackTrace();
        close();
        
    }}
}  


public void announceNewUser(Socket s,String userName){
    
    Thread t=new Thread(new ClientConnection(s,userName));
    t.start();
    
}   
     public void close() {
        try {
            din.close();
            dout.close();
            s.close();
            
        }catch(IOException e){
        	JFrame f=new JFrame(); 
			JOptionPane.showMessageDialog(f,"User should connect server first."); 
        }
    }
     
public static void main(String[]args)
{
	  java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
              ChattingApplication CA=new ChattingApplication();
              CA.window();
          }
      });
}
}
