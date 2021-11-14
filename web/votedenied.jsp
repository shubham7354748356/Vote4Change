<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="evoting.dto.CandidateInfo" %>
<html lang="en">
<head>
 
    <link href="stylesheet/showcandidate.css" rel="stylesheet">
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <title>Voting Details</title>
        
        
</head>
<body>
    <%
        String userid = (String)session.getAttribute("userid");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        System.out.println("no we are in the votingresponse.jsp");
        CandidateInfo ci = (CandidateInfo)session.getAttribute("candidate");
        
         StringBuffer displayBlock = new StringBuffer();
        displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
          displayBlock.append("<div class='subcandidate'>Thank you for voting</div><br><br>");
            displayBlock.append("<br><div class='candidateprofile'>Your vote added successfully!</div>");
            displayBlock.append("<br><div class='candidateprofile'><p>Candidate ID:"+ci.getCandidateId()+"<br>");
            displayBlock.append("<strong> You voted for </strong><br><img src='data:image/pdf;base64,"+ci.getSymbol()+"' style='height200px;width:300px;'>");
            displayBlock.append("<br>Candidate name:"+ci.getCandidateName());
            displayBlock.append("<br>Party:"+ci.getParty());
            displayBlock.append("<br><button><a href='login.html' style='color:red;'>OK</a></button>");
            out.println(displayBlock.toString());
%>