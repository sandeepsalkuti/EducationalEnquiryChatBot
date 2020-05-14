<!DOCTYPE HTML>
<%@page import="edu.tkr.ai.chatboat.dao.AppDAO"%>
<%@page import="edu.tkr.ai.chatboat.form.Student"%>
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
					if (request.getParameter("status") != null) {
				%>
				<h1><%=request.getParameter("status")%></h1>
				<%
					} else {
				%>
				<h1>Update profile</h1>
				<%
					}
				%>

				<form action="UpdateStudentServlet" name="ff" method="post"
					onsubmit="return check()">
					
					<%
						String rollNumber=(String)request.getSession().getAttribute("username");
					
						Student st=AppDAO.getStudentByid(rollNumber);
						
					%>
					<div class="form_settings">
						
						<input type="hidden" value="<%=st.getRollnumber()%>" name="rollnumber" readonly="readonly">
						
						<p>
							<span>Email</span><input class="contact" type="text"
								name="email" id="user" value="<%=st.getEmail()%>"/>
						</p>
						<p>
							<span>Mobile</span><input class="contact" type="text"
								name="mobile" id="pass" value="<%=st.getMobile()%>"/>
						</p>
						<p>
							<span>Year</span><input class="contact" type="text"
								name="year" id="pass" value="<%=st.getYear()%>"/>
						</p>
						<p>
							<span>Sem</span><input class="contact" type="text"
								name="sem" id="pass" value="<%=st.getSem()%>"/>
						</p>
						
						<p>
							<span>Password</span><input class="contact" type="text"
								name="password" id="pass""/>
						</p>

						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Update" />
						</p>

					</div>
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
