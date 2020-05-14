package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("user").trim();
		
		int result=AppDAO.isValidUser(username,request.getParameter("pass").trim());
		
		if(result!=0)
		{
			request.getSession().setAttribute("username",username);
			
			if(result==1)
			{
				response.sendRedirect("adminhome.jsp");
			}
			else
			{
				if(result==2)
				{
					response.sendRedirect("facultyhome.jsp");
				}
				else
				{
					if(result==3)
					{
						response.sendRedirect("studenthome.jsp");
					}
				}
			}
		}
		else
		{
			response.sendRedirect("login.jsp?status=Invalid Username and Password");
		}
	}
}
