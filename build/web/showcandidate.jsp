<%-- 
    Document   : showcandidate
    Created on : Jun 10, 2021, 12:10:23 PM
    Author     : india
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList , evoting.dto.CandidateInfo" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="stylesheet/login.css" rel="stylesheet">
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <script type="text/javascript" src="jsscript/jquery.js"></script>
    <script type="text/javascript" src="jsscript/vote.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="jsscript/login.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <%
        String userid = (String )session.getAttribute("userid");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        StringBuffer displayBlock = new StringBuffer();
        displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
                                                 "<div class='subcandidate'>WHOM DO YOU WANT TO VOTE ?</div>"+
                
                                                "<div class='logout'><a href='login.html'>logout</a></div></div>");
        displayBlock.append("<div style='position:absolute;top:200px;right:600px;color:white;'>");
        
        ArrayList<CandidateInfo> candidatelist = (ArrayList<CandidateInfo>)request.getAttribute("candidateList");
        for(CandidateInfo c : candidatelist)
        {
            displayBlock.append("<input type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addvote()'>");
            displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='height:200px;width:300px;'></label>");
            displayBlock.append("<br><div class='candidateprofile'><p>Candidate ID: "+c.getCandidateId());
            displayBlock.append("<br>Candidate Name: "+c.getCandidateName());
            displayBlock.append("<br>Party: "+c.getParty());
            displayBlock.append("</p></div>");
        
        }
            displayBlock.append("<div>");
            
            out.println(displayBlock.toString());
        %>
</body>
</html>
