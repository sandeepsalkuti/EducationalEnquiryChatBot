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
			<div>
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
				<h1>Add Your Feedback</h1>
				<%
					}
				%>
				
				<form action="AddFeedbackServlet" name="ff" method="post"
					onsubmit="return check()">

					<div class="form_settings">
						<p>
							<span>Feedback</span><input class="contact" type="text"
								name="feedback" id="user" />
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Send" />
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
