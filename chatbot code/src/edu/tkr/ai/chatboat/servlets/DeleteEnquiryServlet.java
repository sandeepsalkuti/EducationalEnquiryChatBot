package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;

@WebServlet("/DeleteEnquiryServlet")
public class DeleteEnquiryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(AppDAO.deleteEnquiry(Integer.parseInt(request.getParameter("eid")))==1)
		{
			response.sendRedirect("adminhome.jsp?status=success");
		}
		else
		{
			response.sendRedirect("adminhome.jsp?status=Exist");
		}
	}
}
