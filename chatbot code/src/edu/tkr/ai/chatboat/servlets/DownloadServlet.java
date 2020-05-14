package edu.tkr.ai.chatboat.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tkr.ai.chatboat.util.AppUtil;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {

			response.setContentType("text/html");  

			response.setContentType("APPLICATION/OCTET-STREAM");   

			response.setHeader("Content-Disposition","attachment; filename=\"" + request.getParameter("file") + "\"");   

			FileInputStream fileInputStream = new FileInputStream(AppUtil.FILE_PATH+request.getParameter("file"));  

			int i;   

			while ((i=fileInputStream.read()) != -1) {  
				out.write(i);   
			}   

			fileInputStream.close();   

			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
