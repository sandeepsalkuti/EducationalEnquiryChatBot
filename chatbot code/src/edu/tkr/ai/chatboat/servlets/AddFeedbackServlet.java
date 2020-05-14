package edu.tkr.ai.chatboat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Feedback;

@WebServlet("/AddFeedbackServlet")
public class AddFeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Feedback feedback=new Feedback();

		feedback.setRollNumber((String)request.getSession().getAttribute("username"));
		feedback.setFeedBack(request.getParameter("feedback"));

		if(AppDAO.addFeedback(feedback)==1)
		{
			response.sendRedirect("addfeedback.jsp?status=success");
		}
		else
		{
			response.sendRedirect("addfeedback.jsp?status=Exist");
		}
	}
}
