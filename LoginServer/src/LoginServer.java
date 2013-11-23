import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;  

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class LoginServer extends JFrame implements MouseMotionListener,MouseListener,ActionListener

{
	static LoginServer L=new LoginServer();
	static TextArea show=new TextArea();
	static Button clear=new Button("清屏");
	// 设置端口号
	
	public static int portNo = 3333;
	
		   
		    //this.getContentPane().setLayout(null);
		   
	static  JPanel p=new JPanel();
			 //JLabel1 = new JLabel();
		    
		    
		    //jLabel1.setText("URL:");
		    //jLabel1.setBounds(14, 21, 35, 21);
		   
	public static void main(String[] args) throws IOException 

	{	show.setEditable(false);
		show.setBackground(Color.black);
		show.setForeground(Color.white);
		show.setSize(500, 200);
		clear.setBounds(230, 220, 40, 20);
		p.add(show);
		L.add(clear);
		p.setSize(500, 300);
		clear.addMouseListener(L);
		clear.addActionListener(L);
		L.setSize(500, 300);
		L.setTitle("基于DAS算法的图形化密码研究与改进——服务端");
		L.getContentPane().add(p);
		L.getContentPane().setLayout(null);
		L.setVisible(true);
		
		//String id=new String();
		//String pw=new String();
		ServerSocket s = new ServerSocket(portNo);
		Socket socket=null;
		boolean enflag=false;
		boolean flag=true;
		String fileroute=System.getProperty("user.dir").replace("\\", "/");
		 RSA en=new RSA();
		 en.genKey(1024);
		// en.setPrivateKey(en.getPrivateKey());
		 //en.setPrivateKey(en.getPublicKey());
		 XmlOperate xml=new XmlOperate(fileroute+"/record.xml");
		show.append("The Server is start: " + s);
 
		// 阻塞,直到有客户端连接

		while(flag)
		{	
			socket = s.accept();


			try 

			{              

				System.out.println("Accept the Client: " + socket);                   
				show.append("Accept the Client: " + socket+"\n");
				//设置IO句柄

				BufferedReader in = new BufferedReader(new InputStreamReader(socket

						.getInputStream()));

				PrintWriter out = new PrintWriter(new BufferedWriter(

						new OutputStreamWriter(socket.getOutputStream())), true);                    

				while (true)

				{
					String str=new String();
					//if(in.readLine()!=null)


					str = in.readLine();

					if (str.equals("register"))
					
					{
						show.append("In Server reveived : " + "record"+"\n");

						out.println("recording");


						String uid=in.readLine();
						show.append("In Server reveived : " + uid+"\n");

						out.println(uid);
						String pwd=in.readLine();
						//out.println(pwd);
						JOptionPane.showMessageDialog(null, pwd);
						if(enflag==true)
						{
							String buff1=en.decrypt(pwd);
							//byte[] buff=en.decrypt(buff1);
						    //String buffs=new String(buff1);
							pwd=buff1;
							
						}
						if(!xml.Exist(uid))
						{
							xml.addXmlCode(uid, pwd);
						
						out.println("registered");
						//System.out.println("registered");
						}
						else
							out.println("failed");
						continue;
					}
					if(str.equals("encrypt"))
					{	
						enflag=true;
						show.append("In Server reveived : " + "encrypt mode"+"\n");
						out.println("sending key");
						out.println(en.getPublicKey().get("n").toString());
						out.println(en.getPublicKey().get("b").toString());
					}
					if(str.equals("login"))
					{
						show.append("In Server reveived : " + "login"+"\n");

						out.println("logining");


						String uid=in.readLine();
						show.append("In Server reveived : " + uid+"\n");

						out.println(uid);
						String pwd=in.readLine();
						show.append("In Server reveived : " + pwd+"\n");
						
						//out.println(pwd);
						if(enflag==true)
						{
							String buff1=en.decrypt(pwd);
							//byte[] buff=en.decrypt(buff1);
						    //String buffs=new String(buff1);
							pwd=buff1;
							
						}
						if(xml.check(uid, pwd)==true)
						{	
							out.println("Successfully login");
							
						}
							else
							{
								out.println("error");
								
							}
						continue;
					}
					if(str.equals("quit"))
					{



						flag=false;
						show.append("In Server reveived : " + str+"\n");

						out.println(str);
						break;

					}

					
					{	
						show.append("In Server reveived : " + str+"\n");

					    out.println(str);
					}
					
				}

			} 

			finally 

			{

				socket.close();
				s.close();
				show.append("Server has been ended"+"\n");
			}

		}


	}
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==clear)// TODO Auto-generated method stub
				  show.setText(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

}
