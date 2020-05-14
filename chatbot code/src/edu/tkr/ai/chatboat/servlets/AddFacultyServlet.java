package edu.tkr.ai.chatboat.servlets;

import java.io.File;
import java.io.IOException;
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

import edu.tkr.ai.chatboat.service.AppService;
import edu.tkr.ai.chatboat.util.AppUtil;

@WebServlet("/AddFacultyServlet")
public class AddFacultyServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

						  uploadFilename =AppUtil.FILE_PATH+item.getName();

						try {
							item.write(new File(uploadFilename));
							isUploaded=true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
			int result=AppService.uploadFacultyExcelFile(uploadFilename);
			
			if(result==1)
			{
				response.sendRedirect("addfacultys.jsp?status=succes");
			}
			else
			{
				response.sendRedirect("addfacultys.jsp?status=success");
			}
		}
		else
		{
			response.sendRedirect("addfacultys.jsp?status=success");
		}
	}
}
