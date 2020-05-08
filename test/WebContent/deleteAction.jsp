<%@ page language="java" contentType="text/html; charset =UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="source.SourceDAO" %>
<%@ page import ="source.Source" %>
<%@ page import ="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>

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
    			

			}
			int sourceID = 0;
			if(request.getParameter("sourceID") != null){
				sourceID = Integer.parseInt(request.getParameter("sourceID"));
			}
			if(sourceID == 0){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('유효하지 않은 글 입니다.')");
				script.println("location.href = 'source.jsp'");
				script.println("</script>");
			}
			Source source = new SourceDAO().getSource(sourceID);
			if(!userID.equals(source.getUserID())){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('권한이 없습니다.')");
				script.println("location.href = 'source.jsp'");
				script.println("</script>");
			} else{
		            SourceDAO sourceDAO = new SourceDAO();
		            int result = sourceDAO.delete(sourceID);
		            if (result ==-1){
		                PrintWriter script = response.getWriter();
		                script.println("<script>");
		                script.println("alert('글 삭제에 실패했습니다.')");
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
			


    %>
</html>