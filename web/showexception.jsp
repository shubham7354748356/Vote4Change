<%-- 
    Document   : showexception
    Created on : May 14, 2021, 6:20:08 PM
    Author     : india
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception e = (Exception)request.getAttribute("Exception");
    System.out.println(e);
    out.println("SORRY SOME EXCEPTION OCCURED "+e.getMessage());
    
    
    %>
    