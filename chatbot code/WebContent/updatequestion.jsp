<!DOCTYPE HTML>
<%@page import="edu.tkr.ai.chatboat.form.Student"%>
<%@page import="edu.tkr.ai.chatboat.dao.AppDAO"%>
<%@page import="edu.tkr.ai.chatboat.form.Enquiry"%>
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
				<h1>
					
				</h1>
				<div class="slogan">Educational Enquiry Chat bot</div>
			</div>
			<div>
				<ul id="menu">
					<!-- put class="current" in the li tag for the selected page - to highlight which page you're on -->

					<li><a class="current" href="adminhome.jsp">Home</a></li>
					<li><a class="current" href="viewenquires.jsp">Enquires</a></li>
					<li><a class="current" href="addquestions.jsp">Add Questions</a></li>
					<li><a class="current" href="addstudents.jsp">Add Students</a></li>
					<li><a class="current" href="addfacultys.jsp">Add Faculty</a></li>
					<li><a class="current" href="viewfeedbacks.jsp">View Feedbacks</a></li>
					<li><a class="current" href="visitorquerys.jsp">Visitors</a></li>
					<li class="hvr-sweep-to-bottom"><a href="logout.jsp">Logout</a></li>
					
				</ul>
			</div>
		</div>
		<div id="site_content">

			<div id="content">
				<!-- insert the page content here -->

				<%
					if (request.getParameter("status") != null) {
				%>
				<h1><%=request.getParameter("status")%></h1>
				<%
					} else {
				%>
				<h1>Update Question</h1>
				<%
					}
				%>
				
				<form action="UpdateQuestionServlet" name="ff" method="post" enctype="multipart/form-data"
					onsubmit="return check()">
					
					<%
						String eid=request.getParameter("eid");
					
						if(eid!=null)
						{
						Enquiry enquiry=AppDAO.getEnquiryByid(Integer.parseInt(request.getParameter("eid")));
						
						request.getSession().setAttribute("eid",Integer.parseInt(request.getParameter("eid")));
						
						Student student=AppDAO.getStudentByid(enquiry.getRollNumber());
					%>
					<div class="form_settings">
						<p>
							<span>Department</span><input type="text" name="department" value="<%=student.getDepartment()%>" readonly="readonly">
						</p>
						<p>
							<span>Year</span>
							<input type="text" name="year" value="<%=student.getYear()%>" readonly="readonly">
						</p>
						
						<p>
							<span>Semester</span>
							<input type="text" name="semester" value="<%=student.getSem()%>" readonly="readonly">
						</p>
						<p>
							<span>Section</span>
							<input type="text" name="section" value="<%=student.getSection()%>" readonly="readonly">
						</p>

						<p>
							<span>Category</span>
							<select name="category">
								<option value="academic">academic</option>
								<option value="admission">admission</option>
								<option value="sports">sports</option>
								<option value="other">other</option>
							</select>
						</p>
						<p>
							<span>Question</span><input type="text" name="question" value="<%=enquiry.getQuery()%>" readonly="readonly">
						</p>
						
						<p>
							<span>Answer</span><input class="contact" type="text"
								name="answer" id="user" />
						</p>
						<p>
							<span></span><input class="contact" type="file"
								name="file" id="pass" />
						</p>

						<p>
							<span>Keyword 1</span><input class="contact" type="text"
								name="keyword1" id="user" />
						</p>
						
						<p>
							<span>Keyword 2</span><input class="contact" type="text"
								name="keyword2" id="user" />
						</p>
						
						<p>
							<span>Keyword 3</span><input class="contact" type="text"
								name="keyword3" id="user" />
						</p>
						
						<p>
							<span>Keyword 4</span><input class="contact" type="text"
								name="keyword4" id="user" />
						</p>
						
						<p>
							<span>Keyword 5</span><input class="contact" type="text"
								name="keyword5" id="user" />
						</p>
					
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Add Question" />
						</p>

					</div>
					
					<%
						}
					%>
				</form>

			</div>
		</div>
	</div>
	<div id="footer">
		<p>
			Copyright &copy;  | <a href="http://www.technologies.com">info</a>
		</p>
	</div>
</body>
</html>
