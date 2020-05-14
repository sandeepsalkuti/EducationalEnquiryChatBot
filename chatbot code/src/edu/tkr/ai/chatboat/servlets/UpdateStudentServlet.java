package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Student;

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Student student=AppDAO.getStudentByid((String)request.getSession().getAttribute("username"));
		
		student.setEmail(request.getParameter("email"));
		student.setMobile(request.getParameter("mobile"));
		student.setYear(request.getParameter("year"));
		student.setSem(request.getParameter("sem"));
		String password=request.getParameter("password");
		
		if(password!="")
		{
			AppDAO.updatePassword((String)request.getSession().getAttribute("username"),password);
		}
		
		int result=AppDAO.updateStudent(student);
		
		System.out.println("result is :\t"+result);
		
		if(result==1)
		{
			response.sendRedirect("updateprofile.jsp?status=success");
		}
		else
		{
			response.sendRedirect("updateprofile.jsp?status=Exist");
		}
	}
}
