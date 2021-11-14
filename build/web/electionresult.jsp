<%@page import="evoting.dao.VoteDao"%>
<%@page import="java.util.Map"%>
<
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.PartyWiseResult , java.util.LinkedHashMap,java.util.Iterator" %>
<%
    String userid = (String) session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    System.out.println("in the election result jsp");
    LinkedHashMap<PartyWiseResult , Integer> resultDetails = (LinkedHashMap)request.getAttribute("result");
    int voteCount = (int ) request.getAttribute("votecount");
    System.out.println("we get total vote =>"+voteCount);
    Iterator it = resultDetails.entrySet().iterator();
    StringBuffer displayBlock = new StringBuffer();
    displayBlock.append("<table style='border:solid 1px white'>");
    displayBlock.append("<tr><th>Party</th><th>Symbol</th><th>Vote Count</th><th>Vote %</th><th>Male vote%</th><th>Female vote%</th></tr>");
    while(it.hasNext())
    {
        Map.Entry<PartyWiseResult , Integer> e = (Map.Entry)it.next();
        PartyWiseResult cd = e.getKey();
        float per = (e.getValue()*100.0f)/voteCount;
        displayBlock.append("<tr><td>"+cd.getParty()+"</td><td>><img src='"+cd.getSymbol()+"' style='height:200px;width;300px;'></td><td>"+e.getValue()+"</td><td>"+ per+"</td>");
        displayBlock.append("<td>"+ ((VoteDao.getMaleVoteCount(cd.getParty())*100)/voteCount) +"</td><td>"+(((e.getValue()-VoteDao.getMaleVoteCount(cd.getParty())))*100)/voteCount +"</td></tr>");
        
    }
    
    displayBlock.append("</table>");
    System.out.println("we are just going to display result");
    out.println(displayBlock.toString());
    

%>