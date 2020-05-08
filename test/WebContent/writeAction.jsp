<%@ page language="java" contentType="text/html; charset =UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="source.SourceDAO" %>
<%@ page import ="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="source" class="source.Source" scope="page"/>
<jsp:setProperty name="source" property="sourceTitle"/>
<jsp:setProperty name="source" property="sourceContent"/>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>source project</title>
</head>
<body>
    <%
			String userID = null;
			if(session.getAttribute("userID") != null){
				userID = (String) session.getAttribute("userID");
			}
			if (userID == null){
    			PrintWriter script = response.getWriter();
    			script.println("<script>");
    			script.println("alert('로그인을 하세요')");
    			script.println("location.href = 'login.jsp'");
    			script.println("</script>");
    			

			} else{
	    		if (source.getSourceTitle() == null || source.getSourceContent() == null){
	    			PrintWriter script = response.getWriter();
	            	script.println("<script>");
	            	script.println("alert('입력이 안 된 사항이 있습니다.')");
	            	script.println("history.back()");
	            	script.println("</script>");
	    		} else{
	            SourceDAO sourceDAO = new SourceDAO();
	            int result = sourceDAO.write(source.getSourceTitle(), userID, source.getSourceContent());
	            if (result ==-1){
	                PrintWriter script = response.getWriter();
	                script.println("<script>");
	                script.println("alert('글쓰기에 실패했습니다.')");
	                script.println("history.back()");
	                script.println("</script>");
	            }
	            else {
	                PrintWriter script = response.getWriter();
	                script.println("<script>");
	                script.println("location.href = 'source.jsp'");
	                script.println("</script>");
	            }
	    	}
			}


    %>
</html>