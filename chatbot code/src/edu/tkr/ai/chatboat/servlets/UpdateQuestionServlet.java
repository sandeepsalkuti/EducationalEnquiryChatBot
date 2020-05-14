package edu.tkr.ai.chatboat.servlets;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import edu.tkr.ai.chatboat.dao.AppDAO;
import edu.tkr.ai.chatboat.form.Enquiry;
import edu.tkr.ai.chatboat.form.Question;
import edu.tkr.ai.chatboat.util.AppUtil;
@WebServlet("/UpdateQuestionServlet")
public class UpdateQuestionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Question question=new Question();
		
		String uploadFilename = "";

		boolean isUploaded = false;

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);

				for (FileItem item : items) {

					// processes only fields that are not form fields
					if (!item.isFormField()) {

						//this will be true if file field is found in the List

						uploadFilename = item.getName();

						try {
							item.write(new File(AppUtil.FILE_PATH+uploadFilename));
							question.setFileName(uploadFilename);
							isUploaded=true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						String fieldname = item.getFieldName();
						//System.out.print("FieldName : "+fieldname);
						String fieldvalue = item.getString();
						//System.out.println(" Value: "+fieldvalue);

						Method[] methods=Question.class.getDeclaredMethods();
						
						for(Method method : methods)
						{
							String methodName=method.getName();
							
							if(methodName.equalsIgnoreCase("set"+fieldname))
							{
								try {
									method.setAccessible(true);
									method.invoke(question,fieldvalue);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}//try
			catch (FileUploadException e) {
				e.printStackTrace();
			}

		}
		
		if(isUploaded)
		{
			question.setAddedBy((String)request.getSession().getAttribute("username"));
			question.setFileName(uploadFilename);
			
			int result=AppDAO.addQuestion(question);
			
			if(result==1)
			{
				int eid=(Integer)request.getSession().getAttribute("eid");
				
				Enquiry enquiry=AppDAO.getEnquiryByid(eid);
				enquiry.setQuestionId(AppDAO.getMaxQuestionId());
				
				AppDAO.updateEnquiry(enquiry);
				
				response.sendRedirect("updatequestion.jsp?status=succes");
			}
			else
			{
				response.sendRedirect("updatequestion.jsp?status=failed");
			}
		}
		else
		{
			response.sendRedirect("updatequestion.jsp?status=failed");
		}
	}
}
