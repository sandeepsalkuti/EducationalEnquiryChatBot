package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Enquiry;

@WebServlet("/AddEnquiryServlet")
public class AddEnquiryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enquiry enquiry=new Enquiry();
		
		enquiry.setRollNumber((String)request.getSession().getAttribute("rollnumber"));
		enquiry.setQuery(request.getParameter("username"));
		enquiry.setDate(new Date());
		enquiry.setStatus(request.getParameter("status"));
		
		if(AppDAO.addEnquiry(enquiry)==1)
		{
			response.sendRedirect("addenquiry.jsp?status=success");
		}
		else
		{
			response.sendRedirect("addenquiry.jsp?status=User All Ready Exist");
		}
	}
}
