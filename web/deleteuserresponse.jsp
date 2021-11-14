<%@page import="evoting.dto.UserDetails , org.json.JSONObject" %>
<%
    String userid = (String)session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    UserDetails user = (UserDetails)request.getAttribute("details");
    String result = (String)request.getAttribute("result");
    System.out.println(result);
    if(result==null && user!=null)
    {
    StringBuffer sb = new StringBuffer();
    sb.append("<table>");
    sb.append("<tr><th>User Name :</th><td>"+user.getUsername()+"</td></tr>");
     sb.append("<tr><th>City :</th><td>"+user.getCity()+"</td></tr>");
      sb.append("<tr><th>Address :</th><td>"+user.getAddress()+"</td></tr>");
       sb.append("<tr><th>Mobile Number :</th><td>"+user.getMobile()+"</td></tr>");
        sb.append("<tr><th>Email :</th><td>"+user.getEmail()+"</td></tr>");
        sb.append("<tr><th><input type='button' value='Delete' onclick='con()'></th></tr>");
        sb.append("</table>");
        JSONObject json = new JSONObject();
        json.put("subdetails", sb.toString());
        out.println(json);
    }
    
    else if(result.equalsIgnoreCase("success"))
            out.println("success");
    else out.println("failed");
    
    
    
    
    %>