package dc;
import java.io.*;
import java.net.*;
public class client 
{
	public static void main(String [] args)throws IOException
	{
		String str;
		int n;
		Socket  cs=new Socket("localhost",56);
		DataInputStream in=new DataInputStream(cs.getInputStream());	
		DataOutputStream out=new DataOutputStream(cs.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Press Enter without text to quit");
		
		//this is for student name
		String st=in.readLine();
		System.out.println(st);
		str=br.readLine();
		out.writeBytes(str+"\n");
		
		//this is for student roll no
		st=in.readLine();
		System.out.println(st);
		str=br.readLine();
		out.writeBytes(str+"\n");
		
		//this is for subject count
		st=in.readLine();
		System.out.println(st);
		str=br.readLine();
		out.writeBytes(str+"\n");
		n=Integer.parseInt(str);
		
		//subject detail generation
		for(int i=0;i<n;i++)
		{
			// below 4 line are to send subjectcode
			st=in.readLine();
			System.out.println(st);
			str=br.readLine();
			out.writeBytes(str+"\n");
			
			// below 4 line are to send the name of the subject
			st=in.readLine();
			System.out.println(st);
			str=br.readLine();
			out.writeBytes(str+"\n");
			
			// below 4 lines are to send no of credits of the subject
			st=in.readLine();
			System.out.println(st);
			str=br.readLine();
			out.writeBytes(str+"\n");
			
			// below 4 lines are to send mks of the subject
			st=in.readLine();
			System.out.println(st);
			str=br.readLine();
			out.writeBytes(str+"\n");
		
		}
		cs.close();
	}
}
