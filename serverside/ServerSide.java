package project.serverside;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerSide {
         public static ServerSocket serverSocket;
         ArrayList<ServerConnection> connections = new ArrayList();
         private static final int PORT=3333;
   
          public static void main(String[] args) throws IOException{
                        new ServerSide();    
          }
          
          public ServerSide(){
              try
                 {
                 serverSocket = new ServerSocket(PORT);
                 }catch (IOException ioEx)
                 {
                 System.out.println("\nUnable to set up port!");
                 System.exit(1);
                 }
                 do
                 {
                  try {
                      //Wait for client
                      Socket client;
                      client = serverSocket.accept();
                      System.out.println("\nNew client accepted.\n");
                      
                    
                      ServerConnection handler = new ServerConnection(client,this);
                      System.out.println(client);
                       System.out.println(this);
                      connections.add(handler);
                      handler.start();
                  } catch (IOException ex) {
                      Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
                  }
                 
                 }while (true);
                 }
          }
         

                             
          
                     
    
        

