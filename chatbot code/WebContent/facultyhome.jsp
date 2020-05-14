<!DOCTYPE HTML>
<%@page import="com.sun.speech.freetts.Voice"%>
<%@page import="com.sun.speech.freetts.VoiceManager"%>
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
			<div >
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
				<h1>Search Query</h1>
				<%		
					}
				%>
		
			  <form action="facultyhome.jsp" name="ff" method="get"
					onsubmit="return check()">

					<div class="form_settings">
						
						<p>
							<span>Select Category</span>
							<select name="category">
								<option value="academic">academic</option>
								<option value="admission">admission</option>
								<option value="sports">sports</option>
								<option value="other">other</option>
							</select>
						</p>
						
						<p>
							<span>Query</span><input class="contact" type="text"
								name="query" id="user" />
						</p>
				
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Search"/>
						</p>

					</div>
				</form>
				
				<%
					String category=request.getParameter("category");
					String query=request.getParameter("query");
					
					if(category!=null && query!=null)
					{
				%>
				<table class="imagetable">
					<tr>
						<th>QID</th>
						<th>Answer</th>
						<th>File Download</th>
					</tr>
					<%
							Set<Question> questions =AppDAO.getQuestionsByKeyWord(query);
							
							Iterator<Question> iterator = questions.iterator();

							while (iterator.hasNext()) {
				
								Question question = iterator.next();
								
				                Voice helloVoice=null; 
				                
								String voiceName ="kevin16"; 
				            	VoiceManager voiceManager = VoiceManager.getInstance();
				                helloVoice = voiceManager.getVoice(voiceName);
				                		
				                helloVoice.allocate();
				                String data=question.getAnswer();
				                helloVoice.speak(data);
				                helloVoice.deallocate();

							%>
					<tr>
						<td><%=question.getQid()%></td>
						<td><%=question.getAnswer()%></td>
						<td> <a href="DownloadServlet?file=<%=question.getFileName()%>">Download</a></td>
					</tr>

					<%
						}
				    %>
				</table>
				
				<%
					 }
				%>
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
