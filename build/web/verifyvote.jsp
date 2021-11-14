<%-- 
    Document   : verifyvote
    Created on : Jun 11, 2021, 11:45:33 AM
    Author     : india
--%>

<%
    String userid = (String) session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    boolean result = (boolean) request.getAttribute("result");
    System.out.println("we are in the verify vote");
    if(result==true)
    {
        System.out.println("true in the if");
        out.println("success");
    }
    else
        out.println("failed");


%>