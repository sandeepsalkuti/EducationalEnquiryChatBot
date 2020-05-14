<!DOCTYPE HTML>
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
				<h1>Success</h1>
				<%
					} else {
				%>
				<h1>Upload Faculty</h1>
				<%
					}
				%>
				
				<form action="AddFacultyServlet" name="ff" method="post" enctype="multipart/form-data"
					onsubmit="return check()">

					<div class="form_settings">
						
						<p>
							<span></span><input class="contact" type="file"
								name="file" id="pass" />
						</p>

						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Upload" />
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
