

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList , evoting.dao.CandidateDao , evoting.dto.CandidateDetails , org.json.JSONObject"%>
<%
    String userid = (String)session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result = (String)request.getAttribute("result");
    StringBuffer displayBlock = new StringBuffer();
    if(result!=null && result.equalsIgnoreCase("candidatelist"))
    {
        displayBlock.append("<option value=''>CHOOSE ID</option>");
        ArrayList<String > al = (ArrayList<String>)request.getAttribute("candidateid");
        for(String x : al)
        {
            displayBlock.append("<option value='"+x+"'>"+x+"</option>");
        }
           JSONObject json = new JSONObject();
           json.put("cid", displayBlock.toString());
           System.out.println(displayBlock);
           out.println(json);
    }
    else if(result!=null && result.equalsIgnoreCase("details"))
    {
        CandidateDetails cd = (CandidateDetails)request.getAttribute("candidate");
        String str = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"' style='width:300px;height:200px'>";
        displayBlock.append("<table>");
        displayBlock.append("<tr><th>User Id:<th><td>"+cd.getUserid()+"</td></tr>");
         displayBlock.append("<tr><th>Candidate Name:<th><td>"+cd.getCandidateName()+"</td></tr>");
          displayBlock.append("<tr><th>City:<th><td>"+cd.getCity()+"</td></tr>");
           displayBlock.append("<tr><th>Party:<th><td>"+cd.getParty()+"</td></tr>");
            displayBlock.append("<tr><th>Symbol:<th><td>"+str+"</td></tr>");
            displayBlock.append("</table>");
               JSONObject json = new JSONObject();
               json.put("subdetails", displayBlock.toString());
           System.out.println(displayBlock);
           out.println(json);
    }
 
    
    
    
    
    %>