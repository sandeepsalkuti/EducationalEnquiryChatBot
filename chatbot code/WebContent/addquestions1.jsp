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

					<li><a class="current" href="facultyhome.jsp">Home</a></li>
					<li class="hvr-sweep-to-bottom"><a href="addquestions1.jsp">AddQuestion</a></li>
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
				<h1>Add Question</h1>
				<%
					}
				%>
				
				<form action="AddQuestionServlet" name="ff" method="post" enctype="multipart/form-data"
					onsubmit="return check()">

					<div class="form_settings">
						<p>
							<span>Department</span>
							<select name="department">
								<option value="cse">CSE</option>
								<option value="ece">ECE</option>
								<option value="mech">MECH</option>
								<option value="eee">EEE</option>
								<option value="it">IT</option>
							</select>
						</p>
						<p>
							<span>Year</span>
							<select name="year">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</p>
						
						<p>
							<span>Semester</span>
							<select name="semester">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</p>
						<p>
							<span>Section</span>
							<select name="section">
								<option value="a">A</option>
								<option value="b">B</option>
								<option value="c">C</option>
								<option value="d">D</option>
							</select>
						</p>

						<p>
							<span>Category</span>
							<select name="category">
								<option value="notes">Notes</option>
								<option value="marks">Marks</option>
								<option value="attendance">Attendance</option>
							</select>
						</p>
						<p>
							<span>Question</span><input class="contact" type="text"
								name="question" id="pass" />
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
