package dc;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.net.*;
public class server 
{
	public static void main(String[] args)throws IOException
	{
		//socket creation and acceptance
		ServerSocket ss= new ServerSocket(56);
		Socket s=ss.accept();
				
		//variables used in program(few may have been declared later in program)
	    String Text,str,name,grade,stuname,stdregno,code;
		int n,mks,credits,tc=0;
		float sum=0,avg;
		
		//input and output streams
		DataInputStream in=new DataInputStream(s.getInputStream());
		DataOutputStream out=new DataOutputStream(s.getOutputStream());
		System.out.println("Server Ready");
		
		//to get student name
		str="enter the name os student";
		out.writeBytes(str+"\n");
		stuname=in.readLine();
		System.out.println(stuname);
				
		//to get student register no
		str="enter the regno ";
		out.writeBytes(str+"\n");
		stdregno=in.readLine();
		
		//document creation requisites
		XWPFDocument document = new XWPFDocument();
	    FileOutputStream ou = new FileOutputStream(new File("d:gradecardd.docx"));
	    XWPFParagraph paragraph = document.createParagraph();
	    XWPFRun run = paragraph.createRun();
		
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run = paragraph.createRun();
	    run.setFontSize(34);
	    run.setText("PRESIDENCY UNIVERSITY"+"\n");
	    
	    paragraph = document.createParagraph();
	    paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run = paragraph.createRun();
	    run.setFontSize(20);
	    run.setText("BANGALORE, KARNATAKA-560064");

	    paragraph = document.createParagraph();
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run = paragraph.createRun();
	    run.setBold(true);
	    run.setFontSize(20);
	    run.setText("GRADE CARD");
	    
		
		paragraph = document.createParagraph();
	    paragraph.setAlignment(ParagraphAlignment.LEFT);
	    run = paragraph.createRun();
	    run.setBold(true);
	    run.setFontSize(12);
	    run.setText("Student Name: "+stuname);
		
		
		
		paragraph = document.createParagraph();
	    paragraph.setAlignment(ParagraphAlignment.LEFT);
	    run = paragraph.createRun();
	    run.setBold(true);
	    run.setFontSize(12);
	    run.setText("Roll Number: "+stdregno);
		
		//the below 4 line is for subject count
		str="enter the no of subjects";
		out.writeBytes(str+"\n");
		Text=in.readLine();
		n=Integer.parseInt(Text);
		
		//creates a table
		XWPFTable tab = document.createTable();
		
	    //create first row
	    XWPFTableRow row = tab.getRow(0);
	    row.getCell(0).setText("Course Code");
	    row.addNewTableCell().setText("Course Name");
	    row.addNewTableCell().setText("Credits");
	    row.addNewTableCell().setText("Grade");
		
		for(int i=0;i<n;i++)
		{
			// below 3 line are to store subject code
			str="enter  the subject code";
			out.writeBytes(str+"\n");
			code=in.readLine();
			
			// below 3 line are to store the name of the subject
			str="enter  the subject name";
			out.writeBytes(str+"\n");
			name=in.readLine();
			
			// below 4 lines are to store no of credits of the subject
			str="enter the credits of the subject";
			out.writeBytes(str+"\n");
			Text=in.readLine();
			credits=Integer.parseInt(Text);
			
			
			// below 4 lines are to store mks of the subject
			str="enter the marks of the subject for 100";
			out.writeBytes(str+"\n");
			Text=in.readLine();
			mks=Integer.parseInt(Text);
			
			
			//the below if else statements is to generate grade
			//grade decision
			if(mks<40)
			{
				grade="F";
				sum+=0;
				credits=0;
			}
			else if(mks<50)
			{
				grade="D";
				sum+=4;
				tc+=credits;
			}
			else if(mks<60)
			{
				grade="C";
				sum+=5;
				tc+=credits;
			}
			else if(mks<70)
			{
				grade="B";
				sum+=4;
				tc+=credits;
			}
			else if(mks<80)
			{
				grade="B+";
				sum+=4;
				tc+=credits;
			}
			else if(mks<90)
			{
				grade="A";
				sum+=8;
				tc+=credits;
			}
			else if(mks<95)
			{
				grade="A+";
				sum+=9;
				tc+=credits;
			}
			else
			{
				grade="O";
				sum+=10;
				tc+=credits;
			}
			String cr=Integer.toString(credits);;
			// Second Row  
			row = tab.createRow(); 
	        row.getCell(0).setText(code);  
	        row.getCell(1).setText(name);  
	        row.getCell(2).setText(cr);
	        row.getCell(3).setText(grade);
			
		}
		//calculates average which is nothing but SGPA 
		avg=sum/n;
		
		
		str=Integer.toString(tc);
		
		row = tab.createRow(); 
        row.getCell(0).setText(" ");  
        row.getCell(1).setText("total credits");  
        row.getCell(2).setText(str);
        row.getCell(3).setText("");
		
		
		paragraph = document.createParagraph();
	    paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run = paragraph.createRun();
	    run.setText("******************************************************");
	    
	    
	    paragraph = document.createParagraph();
	    paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
	    paragraph.setAlignment(ParagraphAlignment.CENTER);
	    run = paragraph.createRun();
	    run.setBold(true);
	    run.setText("SGPA: "+avg);
	    
	    
	    
		
		System.out.println("your grade card is ready");
		
		//THIS LINE WRITES ALL DATA INTO THE WORD FILE
		document.write(ou);
		
		//closing socket,output stream and document 
		ou.close();
	    document.close();
		ss.close();
	}
}
