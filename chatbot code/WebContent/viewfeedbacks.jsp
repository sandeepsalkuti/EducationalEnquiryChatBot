<!DOCTYPE HTML>
<%@page import="java.util.Iterator"%>
<%@page import="edu.tkr.ai.chatboat.dao.AppDAO"%>
<%@page import="edu.tkr.ai.chatboat.form.Feedback"%>
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
				<h1></h1>
				<div class="slogan">Educational Feedback Chat bot</div>
			</div>
			<div>
				<ul id="menu">

					<li><a class="current" href="adminhome.jsp">Home</a></li>
					<li><a class="current" href="viewenquires.jsp">Enquires</a></li>
					<li><a class="current" href="addquestions.jsp">Add
							Questions</a></li>
					<li><a class="current" href="addstudents.jsp">Add Students</a></li>
					<li><a class="current" href="addfacultys.jsp">Add Faculty</a></li>
					<li><a class="current" href="viewfeedbacks.jsp">View
							Feedbacks</a></li>
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
				<h1>View Feedbacks</h1>
				<%		
					}
				%>

				<table class="imagetable">
					<tr>
						<th>FID</th>
						<th>RollNumber</th>
						<th>FeedBack</th>
					</tr>
					<%
							List<Feedback> feedbacks =AppDAO.getFeedbacks();

							Iterator<Feedback> iterator = feedbacks.iterator();

							while (iterator.hasNext()) {
									
								Feedback feedback = iterator.next();
							%>
					<tr>
						<td><%=feedback.getFid()%></td>
						<td><%=feedback.getRollNumber()%></td>
						<td><%=feedback.getFeedBack()%></td>
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
