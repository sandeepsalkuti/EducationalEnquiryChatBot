package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Enquiry;

@WebServlet("/UdpateEnquiryServlet")
public class UdpateEnquiryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enquiry enquiry=AppDAO.getEnquiryByid(Integer.parseInt(request.getParameter("eid")));
		
		enquiry.setQuestionId(Integer.parseInt(request.getParameter("qid")));
		
		if(AppDAO.updateEnquiry(enquiry)==1)
		{
			response.sendRedirect("updateenquiry.jsp?status=success");
		}
		else
		{
			response.sendRedirect("updateenquiry.jsp?status=Exist");
		}
	}
}
