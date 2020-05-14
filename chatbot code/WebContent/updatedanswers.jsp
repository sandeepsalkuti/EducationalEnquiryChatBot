<!DOCTYPE HTML>
<%@page import="java.util.Date"%>
<%@page import="edu.tkr.ai.chatboat.form.Enquiry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="edu.tkr.ai.chatboat.form.Student"%>
<%@page import="edu.tkr.ai.chatboat.dao.AppDAO"%>
<%@page import="edu.tkr.ai.chatboat.form.Question"%>
<%@page import="java.util.List"%>
<html>

<head>
<title></title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Tangerine&amp;v1" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz" />
<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
			
				<div class="slogan">Educational Enquiry Chat bot</div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<!-- put class="current" in the li tag for the selected page - to highlight which page you're on -->

					<li><a class="current" href="studenthome.jsp">Home</a></li>
					<li class="hvr-sweep-to-bottom"><a href="updatedanswers.jsp">Updated Answers</a></li>
					<li class="hvr-sweep-to-bottom"><a href="updateprofile.jsp">Update Profile</a></li>
					<li class="hvr-sweep-to-bottom"><a href="logout.jsp">Logout</a></li>
					
				</ul>
			</div>
		</div>
		<div id="site_content">

			<div id="content">
				<!-- insert the page content here -->

				<%
					String status=request.getParameter("status");
							
					if(status!=null)
					{
				%>
				<h1><%=status%></h1>
				<%		
					}
					else
					{
				%>
				<h1>View Updated Questions</h1>
				<%		
					}
				%>
	
				<table class="imagetable">
					<tr>
						<th>QID</th>
						<th>Answer</th>
						<th>File Download</th>
					</tr>
					<%
							String rollNumber=(String)request.getSession().getAttribute("username");
							
							Student student=AppDAO.getStudentByid(rollNumber);
							
							Set<Question> questions =AppDAO.getUpdateQuestions(rollNumber);
							
							Iterator<Question> iterator = questions.iterator();

							while (iterator.hasNext()) {
				
								Question question = iterator.next();
							%>
					<tr>
						<td><%=question.getQid()%></td>
						<td><%=question.getAnswer()%></td>
						<td> <a href="DownloadServlet?file=<%=question.getAnswer()%>">Download</a></td>
					</tr>

					<%
						}
				    %>
				</table>
				
			</div>
		</div>
		<div id="footer">
			<p>
				Copyright &copy;  | <a
					href="http://www.technologies.com">info</a>
			</p>
		</div>
	</div>
</body>
</html>
