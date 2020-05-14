<!DOCTYPE HTML>
<%@page import="edu.tkr.ai.chatboat.form.Student"%>
<%@page import="edu.tkr.ai.chatboat.dao.AppDAO"%>
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

<script>
	function check() {
		var a = document.ff.user.value;
		var b = document.ff.pass.value;
		if (a == 0) {
			alert('Please Enter UserId');
			return false;
			document.getElementById("name").focus();
		}
		if (b == 0) {
			alert('Please Enter Password');
			return false;
			document.getElementById("pass").focus();
		}
	}
</script>



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

					<li><a class="current" href="adminhome.jsp">Home</a></li>
					<li><a class="current" href="viewenquires.jsp">Enquires</a></li>
					<li><a class="current" href="addquestions.jsp">Add Questions</a></li>
					<li><a class="current" href="addstudents.jsp">Add Students</a></li>
					
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
				<h1>Invalid User Name and Password</h1>
				<%
					} else {
				%>
				<h1>Login Here</h1>
				<%
					}
				%>
				
				<%
					String username=(String)request.getSession().getAttribute("username");
				
					Student st=AppDAO.getStudentByid(username);
				%>
				<form action="UpdateStudentServlet" name="ff" method="post"
					onsubmit="return check()">

					<div class="form_settings">
					
						<p>
							<span>Email</span><input class="contact" type="text"
								name="email" id="user"  value="<%=st.getEmail()%>"/>
						</p>
						<p>
							<span>Mobile</span><input class="contact" type="text"
								name="mobile" id="pass"  value="<%=st.getMobile()%>"/>
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
							<span>Sem</span>
							
							<select name="sem">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
							
						</p>
					
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Login" />
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
