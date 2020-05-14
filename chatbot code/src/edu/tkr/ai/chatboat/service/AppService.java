package edu.tkr.ai.chatboat.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Faculty;
import edu.tkr.ai.chatboat.form.Student;

public class AppService {

	public static int uploadStudentExcelFile(String fileName)
	{
		int result=1;

		ArrayList dataHolder = new ArrayList();

		try {

			XSSFWorkbook workbook=new XSSFWorkbook(fileName);

			/** Get the first sheet from workbook**/
			XSSFSheet mySheet = workbook.getSheetAt(0);
			/** We now need something to iterate through the cells.**/
			Iterator rowIter = mySheet.rowIterator();

			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				ArrayList cellStoreArrayList = new ArrayList();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					cellStoreArrayList.add(myCell);
				}
				dataHolder.add(cellStoreArrayList);
			}
		} catch (Exception e) {

			result=0;
			e.printStackTrace();
		}
		
		try {

			ArrayList cellStoreArrayList = null;
			
			int i =0;
			//For inserting into database  
			for (i= 1; i < dataHolder.size(); i++) {
				
				cellStoreArrayList = (ArrayList) dataHolder.get(i);
				
				Student student=new Student();
				
				student.setRollnumber(((XSSFCell) cellStoreArrayList.get(0)).toString());
				student.setName(((XSSFCell) cellStoreArrayList.get(1)).toString());
				student.setEmail(((XSSFCell) cellStoreArrayList.get(2)).toString());
				student.setMobile(((XSSFCell) cellStoreArrayList.get(3)).toString());
				student.setYear(((XSSFCell) cellStoreArrayList.get(4)).toString());
				student.setDepartment(((XSSFCell) cellStoreArrayList.get(5)).toString());
				student.setSection(((XSSFCell) cellStoreArrayList.get(6)).toString());
				student.setSem(((XSSFCell) cellStoreArrayList.get(7)).toString());
				
				AppDAO.addStudent(student);
			}
		
		} catch (Exception e) {
			result=0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static void mailsend(String key, String email ) throws MessagingException
	{
		String host = "smtp.gmail.com";
		String from = "salkutisandeep222@gmail.com";
		String pass = "sandeep111";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email}; // added this line

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		// To get the array of addresses

		for( int i=0; i < to.length; i++ ) 
		{ 
			// changed from a while loop
			toAddress[i] = new InternetAddress(to[i]);
		}
		
		for( int i=0; i < toAddress.length; i++)
		{ 
			// changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject("Authentication Details of Chatboat Application");
		message.setText("pelase consider your username as your rollnumber and your password is \t:"+key);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
	}
	
	
	public static int uploadFacultyExcelFile(String fileName)
	{
		int result=1;
		
		ArrayList dataHolder = new ArrayList();

		try {

			XSSFWorkbook workbook=new XSSFWorkbook(fileName);

			/** Get the first sheet from workbook**/
			XSSFSheet mySheet = workbook.getSheetAt(0);
			/** We now need something to iterate through the cells.**/
			Iterator rowIter = mySheet.rowIterator();

			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				ArrayList cellStoreArrayList = new ArrayList();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					cellStoreArrayList.add(myCell);
				}
				
				dataHolder.add(cellStoreArrayList);
			}
		} catch (Exception e) {

			result=0;
			e.printStackTrace();
		}

		try {

			ArrayList cellStoreArrayList = null;
		
			int i =0;
			//For inserting into database  
			for (i= 1; i < dataHolder.size(); i++) {
				
				cellStoreArrayList = (ArrayList) dataHolder.get(i);
				
				Faculty faculty=new Faculty();
				
				faculty.setUserName(((XSSFCell) cellStoreArrayList.get(0)).toString());
				faculty.setName(((XSSFCell) cellStoreArrayList.get(1)).toString());
				faculty.setEmail(((XSSFCell) cellStoreArrayList.get(2)).toString());
				faculty.setMobile(((XSSFCell) cellStoreArrayList.get(3)).toString());
				faculty.setDepartment(((XSSFCell) cellStoreArrayList.get(4)).toString());
				
				AppDAO.addFaculty(faculty);
			}
			
		} catch (Exception e) {
			result=0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
