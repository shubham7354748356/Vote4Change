<%
    HttpSession sess = request.getSession();
    String userid = (String)sess.getAttribute("userid");
    if(userid==null)
    {
        sess.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    out.println("failed");
    
    
    
    %>