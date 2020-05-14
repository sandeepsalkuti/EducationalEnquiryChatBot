package edu.tkr.ai.chatboat.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.tkr.ai.chatboat.form.Enquiry;
import edu.tkr.ai.chatboat.form.Faculty;
import edu.tkr.ai.chatboat.form.Feedback;
import edu.tkr.ai.chatboat.form.Question;
import edu.tkr.ai.chatboat.form.Student;
import edu.tkr.ai.chatboat.service.AppService;
import edu.tkr.ai.chatboat.service.QueryService;

public class AppDAO {

	public static int isValidUser(String username,String password)
	{
		int roleId=0;
		
		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select role_id from login where username='"+username+"' and password='"+password+"'");

			while (rs.next()) {

				roleId=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roleId;
	}
	
	public static int getRole(String username)
	{
		int roleId=0;
		
		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select role_id from login where username='"+username+"'");

			while (rs.next()) {

				roleId=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roleId;
	}
	
	public static int getMaxQuestionId()
	{
		int roleId=0;
		
		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select max(qid) from questions");

			while (rs.next()) {

				roleId=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roleId;
	}
	
	public static int updatePassword(String username,String newPassword)
	{
		int result=0;

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update login set password=? where username=?");
			
			ps.setString(1,newPassword);
			ps.setString(2,username);

			result=ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static int isUserRegistred(String username)
	{
		int result=0;

		try {

			ResultSet rs=MyConnection.getConnection().createStatement().executeQuery("select count(*) from login where username='"+username+"'");

			while (rs.next()) {

				result=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static int addStudent(Student student)
	{
		int result=0; 

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into students values(?,?,?,?,?,?,?,?)");

			ps.setString(1,student.getRollnumber());
			ps.setString(2,student.getName());
			ps.setString(3,student.getEmail());
			ps.setString(4,student.getMobile());
			ps.setString(5,student.getYear());
			ps.setString(6,student.getDepartment());
			ps.setString(7,student.getSection());
			ps.setString(8,student.getSem());
			
			ps.executeUpdate();
			
			Random random=new Random();

			String pattern="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
			
			String password="";
			
			for(int i=0;i<5;i++)
			{
				password=password+pattern.charAt(random.nextInt(60));
			}
			
			MyConnection.getConnection().prepareStatement("insert into login values('"+student.getRollnumber()+"','"+password+"','3')").executeUpdate();
			
			try {

				AppService.mailsend(password,student.getEmail());

			} catch (Exception e) {

				e.printStackTrace();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int updateStudent(Student student)
	{
		int result=0; 
		
		System.out.println(student.getEmail()+" \t"+student.getMobile()+"\t"+student.getYear()+"\t"+student.getSem()+"\t"+student.getRollnumber());
		
		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update students set email=?,mobile=?,year=?,sem=? where rollnumber=?");

			ps.setString(1,student.getEmail());
			ps.setString(2,student.getMobile());
			ps.setString(3,student.getYear());
			ps.setString(4,student.getSem());
			ps.setString(5,student.getRollnumber());
			
			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Student> getStudents()
	{
		List<Student> studentsList=new ArrayList<Student>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from students");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Student student=new Student();
			
				student.setRollnumber(rs.getString(1));
				student.setName(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setMobile(rs.getString(4));
				student.setYear(rs.getString(5));
				student.setDepartment(rs.getString(6));
				student.setSection(rs.getString(7));
				student.setSem(rs.getString(8));

				studentsList.add(student);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentsList;
	}
	
	public static List<Student> getStudentsByDepartment(String department)
	{
		List<Student> studentsList=new ArrayList<Student>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from students where department='"+department+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Student student=new Student();
			
				student.setRollnumber(rs.getString(1));
				student.setName(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setMobile(rs.getString(4));
				student.setYear(rs.getString(5));
				student.setDepartment(rs.getString(6));
				student.setSection(rs.getString(7));
				student.setSem(rs.getString(8));

				studentsList.add(student);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentsList;
	}
	
	public static Student getStudentByid(String rollNumber)
	{
		Student student=new Student();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from students where rollnumber='"+rollNumber+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				student.setRollnumber(rs.getString(1));
				student.setName(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setMobile(rs.getString(4));
				student.setYear(rs.getString(5));
				student.setDepartment(rs.getString(6));
				student.setSection(rs.getString(7));
				student.setSem(rs.getString(8));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;
	}
	
	public static int addFaculty(Faculty faculty)
	{
		int result=0; 

		try {
		
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into faculty values(?,?,?,?,?)");
			
			ps.setString(1,faculty.getUserName());
			ps.setString(2,faculty.getName());
			ps.setString(3,faculty.getEmail());
			ps.setString(4,faculty.getMobile());
			ps.setString(5,faculty.getDepartment());

			result=ps.executeUpdate();
			
			Random random=new Random();

			String pattern="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
			
			String password="";

			for(int i=0;i<5;i++)
			{
				password=password+pattern.charAt(random.nextInt(60));
			}
			
			MyConnection.getConnection().prepareStatement("insert into login values('"+faculty.getUserName()+"','"+password+"','2')").executeUpdate();
			
			try {

				AppService.mailsend(password,faculty.getEmail());

			} catch (Exception e) {

				e.printStackTrace();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Faculty> getFacultys()
	{
		List<Faculty> facultysList=new ArrayList<Faculty>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from faculty");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Faculty faculty=new Faculty();
				
				faculty.setUserName(rs.getString(1));
				faculty.setName(rs.getString(2));
				faculty.setEmail(rs.getString(3));
				faculty.setMobile(rs.getString(4));
				faculty.setDepartment(rs.getString(5));

				facultysList.add(faculty);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return facultysList;
	}
	
	public static Faculty getFacultyByid(String username)
	{
		Faculty faculty=new Faculty();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from facultys where username='"+username+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				faculty.setUserName(rs.getString(1));
				faculty.setName(rs.getString(2));
				faculty.setEmail(rs.getString(3));
				faculty.setMobile(rs.getString(4));
				faculty.setDepartment(rs.getString(5));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return faculty;
	}
	
	public static List<Faculty> getFacultysByQuery(String department)
	{
		List<Faculty> facultysList=new ArrayList<Faculty>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("SELECT * FROM facultys where department='"+department+"'");

			ResultSet rs =ps.executeQuery();

			while (rs.next()) {

				Faculty faculty=new Faculty();

				faculty.setUserName(rs.getString(1));
				faculty.setName(rs.getString(2));
				faculty.setEmail(rs.getString(3));
				faculty.setMobile(rs.getString(4));
				faculty.setDepartment(rs.getString(5));

				
				facultysList.add(faculty);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return facultysList;
	}
	
	public static int addQuestion(Question question)
	{
		int result=0; 

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into questions values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1,0);
			ps.setString(2,question.getDepartment());
			ps.setString(3,question.getYear());
			ps.setString(4,question.getSemester());
			ps.setString(5,question.getSection());
			ps.setString(6,question.getCategory());
			ps.setString(7,question.getQuestion());
			ps.setString(8,question.getAnswer());
			ps.setString(9,question.getFileName());
			ps.setString(10,question.getKeyword1());
			ps.setString(11,question.getKeyword2());
			ps.setString(12,question.getKeyword3());
			ps.setString(13,question.getKeyword4());
			ps.setString(14,question.getKeyword5());
			ps.setString(15,question.getAddedBy());
			ps.setDate(16,new java.sql.Date(new java.util.Date().getTime()));
			
			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static Set<Question> getQuestions()
	{
		Set<Question> questionsList=new HashSet<Question>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from questions");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Question question=new Question();

				question.setQid(rs.getInt(1));
				question.setDepartment(rs.getString(2));
				question.setYear(rs.getString(3));
				question.setSemester(rs.getString(4));
				question.setSection(rs.getString(5));
				
				question.setCategory(rs.getString(6));
				question.setQuestion(rs.getString(7));
				question.setAnswer(rs.getString(8));
				question.setFileName(rs.getString(9));
				
				question.setKeyword1(rs.getString(10));
				question.setKeyword2(rs.getString(11));
				question.setKeyword3(rs.getString(12));
				question.setKeyword4(rs.getString(13));
				question.setKeyword5(rs.getString(14));
				
				question.setAddedBy(rs.getString(15));
				question.setDate(rs.getDate(16));

				questionsList.add(question);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionsList;
	}
	
	public static Set<Question> getQuestions(String query,String department,String year,String semester,String section,String category)
	{
		Set<Question> questionsList=new HashSet<Question>();

		try {
			
			Set<String> words=QueryService.getQueryData(query);
			
			Iterator<String> wordit=words.iterator();
			
			while(wordit.hasNext())
			{
				query= wordit.next();
						
				query="%"+query+"%";
				
				PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from questions where question like ? and department=? and year=? and semester=? and section=? and category=?");
		
				System.out.println("year "+year);
				System.out.println("sem "+semester);
				System.out.println("department "+department);
				System.out.println("query "+query);
				System.out.println("section "+section);
				System.out.println("category "+category);
				
				ps.setString(1,query);
				ps.setString(2,department);
				ps.setString(3,year);
				ps.setString(4,semester);
				ps.setString(5,section);
				ps.setString(6,category);
				
				ResultSet rs=ps.executeQuery();
	
				while (rs.next()) {
	
					Question question=new Question();
	
					question.setQid(rs.getInt(1));
					question.setDepartment(rs.getString(2));
					question.setYear(rs.getString(3));
					question.setSemester(rs.getString(4));
					question.setSection(rs.getString(5));
					
					question.setCategory(rs.getString(6));
					question.setQuestion(rs.getString(7));
					question.setAnswer(rs.getString(8));
					question.setFileName(rs.getString(9));
					
					question.setKeyword1(rs.getString(10));
					question.setKeyword2(rs.getString(11));
					question.setKeyword3(rs.getString(12));
					question.setKeyword4(rs.getString(13));
					question.setKeyword5(rs.getString(14));
					
					question.setAddedBy(rs.getString(15));
					question.setDate(rs.getDate(16));
	
					questionsList.add(question);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionsList;
	}
	
	public static Set<Question> getQuestionsByKeyWord(String keyword)
	{
		Set<Question> questionsList=new HashSet<Question>();
		
		keyword ="%"+keyword+"%";
		
		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from questions where question like ?");
			ps.setString(1,keyword);
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Question question=new Question();

				question.setQid(rs.getInt(1));
				question.setDepartment(rs.getString(2));
				question.setYear(rs.getString(3));
				question.setSemester(rs.getString(4));
				question.setSection(rs.getString(5));
				
				question.setCategory(rs.getString(6));
				question.setQuestion(rs.getString(7));
				question.setAnswer(rs.getString(8));
				question.setFileName(rs.getString(9));
				
				question.setKeyword1(rs.getString(10));
				question.setKeyword2(rs.getString(11));
				question.setKeyword3(rs.getString(12));
				question.setKeyword4(rs.getString(13));
				question.setKeyword5(rs.getString(14));
				
				question.setAddedBy(rs.getString(15));
				question.setDate(rs.getDate(16));

				questionsList.add(question);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionsList;
	}
	
	public static Question getQuestionByid(int qid)
	{
		Question question=new Question();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from questions where qid='"+qid+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				question.setQid(rs.getInt(1));
				question.setDepartment(rs.getString(2));
				question.setYear(rs.getString(3));
				question.setSemester(rs.getString(4));
				question.setSection(rs.getString(5));
				
				question.setCategory(rs.getString(6));
				question.setQuestion(rs.getString(7));
				question.setAnswer(rs.getString(8));
				question.setFileName(rs.getString(9));
				
				question.setKeyword1(rs.getString(10));
				question.setKeyword2(rs.getString(11));
				question.setKeyword3(rs.getString(12));
				question.setKeyword4(rs.getString(13));
				question.setKeyword5(rs.getString(14));
				
				question.setAddedBy(rs.getString(15));
				question.setDate(rs.getDate(16));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return question;
	}
	
	/*public static List<Question> getQuestionsByQuery(String status)
	{
		List<Question> questionsList=new ArrayList<Question>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("SELECT * FROM questions where status='"+status+"'");

			ResultSet rs =ps.executeQuery();

			while (rs.next()) {

				Question question=new Question();

				question.setQid(rs.getInt(1));
				question.setDepartment(rs.getString(2));
				question.setYear(rs.getString(3));
				question.setSemester(rs.getString(4));
				question.setSection(rs.getString(5));
				
				question.setCategory(rs.getString(6));
				question.setQuestion(rs.getString(7));
				question.setAnswer(rs.getString(8));
				question.setFileName(rs.getString(9));
				
				question.setKeyword1(rs.getString(10));
				question.setKeyword2(rs.getString(11));
				question.setKeyword3(rs.getString(12));
				question.setKeyword4(rs.getString(13));
				question.setKeyword5(rs.getString(14));
				
				question.setAddedBy(rs.getString(15));
				question.setDate(rs.getDate(16));
				
				questionsList.add(question);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionsList;
	}*/
	
	public static int addEnquiry(Enquiry enquiry)
	{
		int result=0; 

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into enquirys values(?,?,?,?,?,?)");
			
			ps.setInt(1,0);
			ps.setString(2,enquiry.getRollNumber());
			ps.setString(3,enquiry.getQuery());
			ps.setDate(4,new java.sql.Date(enquiry.getDate().getTime()));
			ps.setString(5,enquiry.getStatus());
			ps.setInt(6,enquiry.getQuestionId());

			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static int updateEnquiry(Enquiry enquiry)
	{
		int result=0; 

		try {
			
			String status="updated";
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("update enquirys set qid=?,status='"+status+"' where eid=?");
			
			ps.setInt(1,enquiry.getQuestionId());
			ps.setInt(2,enquiry.getEid());
			
			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	
	public static int deleteEnquiry(int eid)
	{
		int result=0; 

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("delete from enquirys where eid='"+eid+"'");
		
			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Enquiry> getEnquirys()
	{
		List<Enquiry> enquirysList=new ArrayList<Enquiry>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from enquirys");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Enquiry enquiry=new Enquiry();
				
				enquiry.setEid(rs.getInt(1));
				enquiry.setRollNumber(rs.getString(2));
				enquiry.setQuery(rs.getString(3));
				enquiry.setDate(rs.getDate(4));
				enquiry.setStatus(rs.getString(5));
				enquiry.setQuestionId(rs.getInt(6));

				enquirysList.add(enquiry);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enquirysList;
	}
	
	public static List<Enquiry> getEnquirysByRollNumber(String rollNumber)
	{
		List<Enquiry> enquirysList=new ArrayList<Enquiry>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from enquirys where rollnumber='"+rollNumber+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Enquiry enquiry=new Enquiry();
				
				enquiry.setEid(rs.getInt(1));
				enquiry.setRollNumber(rs.getString(2));
				enquiry.setQuery(rs.getString(3));
				enquiry.setDate(rs.getDate(4));
				enquiry.setStatus(rs.getString(5));
				enquiry.setQuestionId(rs.getInt(6));

				enquirysList.add(enquiry);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enquirysList;
	}
	
	public static Set<Question> getUpdateQuestions(String rollNumber)
	{
		Set<Question> questionsList=new HashSet<Question>();
		
		try {
			
			Iterator<Enquiry> enIterator=AppDAO.getEnquirysByRollNumber(rollNumber).iterator();
			
			while(enIterator.hasNext())
			{
				Enquiry enquiry=enIterator.next();
				
				PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from questions where qid='"+enquiry.getQuestionId()+"'");

				ResultSet rs=ps.executeQuery();

				while (rs.next()) {

					Question question=new Question();

					question.setQid(rs.getInt(1));
					question.setDepartment(rs.getString(2));
					question.setYear(rs.getString(3));
					question.setSemester(rs.getString(4));
					question.setSection(rs.getString(5));
					
					question.setCategory(rs.getString(6));
					question.setQuestion(rs.getString(7));
					question.setAnswer(rs.getString(8));
					question.setFileName(rs.getString(9));
					
					question.setKeyword1(rs.getString(10));
					question.setKeyword2(rs.getString(11));
					question.setKeyword3(rs.getString(12));
					question.setKeyword4(rs.getString(13));
					question.setKeyword5(rs.getString(14));
					
					question.setAddedBy(rs.getString(15));
					question.setDate(rs.getDate(16));

					questionsList.add(question);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return questionsList;
	}
	
	public static Enquiry getEnquiryByid(int eid)
	{
		Enquiry enquiry=new Enquiry();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from enquirys where eid='"+eid+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				enquiry.setEid(rs.getInt(1));
				enquiry.setRollNumber(rs.getString(2));
				enquiry.setQuery(rs.getString(3));
				enquiry.setDate(rs.getDate(4));
				enquiry.setStatus(rs.getString(5));
				enquiry.setQuestionId(rs.getInt(6));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enquiry;
	}
	
	public static List<Enquiry> getEnquirysByQuery(String department)
	{
		List<Enquiry> enquirysList=new ArrayList<Enquiry>();

		try {
			
			List<Student> rollNumbers=getStudentsByDepartment(department);
			
			Iterator<Student> it=rollNumbers.iterator();
			
			while(it.hasNext())
			{
				Student st=it.next();
				
				PreparedStatement ps=MyConnection.getConnection().prepareStatement("SELECT * FROM enquirys where rollnumber='"+st.getRollnumber()+"'");

				ResultSet rs =ps.executeQuery();

				while (rs.next()) {
					
					Enquiry enquiry=new Enquiry();

					enquiry.setEid(rs.getInt(1));
					enquiry.setRollNumber(rs.getString(2));
					enquiry.setQuery(rs.getString(3));
					enquiry.setDate(rs.getDate(4));
					enquiry.setStatus(rs.getString(5));
					enquiry.setQuestionId(rs.getInt(6));
					
					enquirysList.add(enquiry);
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enquirysList;
	}
	
	public static int addFeedback(Feedback feedback)
	{
		int result=0; 

		try {
			
			PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into feedbacks values(?,?,?)");
			
			ps.setInt(1,0);
			ps.setString(2,feedback.getRollNumber());
			ps.setString(3,feedback.getFeedBack());

			result=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Feedback> getFeedbacks()
	{
		List<Feedback> feedbacksList=new ArrayList<Feedback>();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from feedbacks");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				Feedback feedback=new Feedback();

				feedback.setFid(rs.getInt(1));
				feedback.setRollNumber(rs.getString(2));
				feedback.setFeedBack(rs.getString(3));

				feedbacksList.add(feedback);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return feedbacksList;
	}
	
	public static Feedback getFeedbackByid(int fid)
	{
		Feedback feedback=new Feedback();

		try {

			PreparedStatement ps=MyConnection.getConnection().prepareStatement("select * from feedbacks where fid='"+fid+"'");

			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				feedback.setFid(rs.getInt(1));
				feedback.setRollNumber(rs.getString(2));
				feedback.setFeedBack(rs.getString(3));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return feedback;
	}
}
