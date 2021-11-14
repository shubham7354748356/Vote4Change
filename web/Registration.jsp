<%-- 
    Document   : Registration
    Created on : May 9, 2021, 6:39:45 PM
    Author     : india
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String result = (String)request.getAttribute("result");
    String error = (String)request.getParameter("error");
    if(result==null && error==null)
    {
        out.println("true");
    }
    else if(result.equalsIgnoreCase("uap"))
    {
        out.println("uap");
    }
    else
    {
        out.println(error);
    }
    
    
    %>