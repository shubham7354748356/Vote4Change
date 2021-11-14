html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="stylesheet/admin.css" rel="stylesheet">
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <script type="text/javascript" src="jsscript/jquery.js"></script>
    <script type="text/javascript" src="jsscript/adminoptions.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        #class1{
            position: absolute;
            top:200px;
            left:400px;
        } 
        #class2{
             position: absolute;
            top:200px;
            left:800px;
        }
    </style>
</head>
<body>
    <%
        HttpSession sess = request.getSession();
        String userid = (String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        %>
    <div class="sticky">
        <div class="candidate">VOTE FOR CHANGE</div><br>
        <div id="div1" style="text-align: right"><a href="login.html">LOGOUT</a></div>
        <div class="subcandidate">Login Page</div>
    </div>
    <div id="class1" onclick="redirectadministratorpage()">
        <image src="images/administrator.png"/>
        <h3>Admin Options</h3>
            
        
    </div>
     <div id="class2" onclick="redirectvotingpage()">
        <image src="images/voteadmin.jpg"/>
        <h3>Voting Page</h3>
            
        
    </div>
    
    
    
    <script>
        
        function redirectadministratorpage()
{
    swal("ADMIN!" , "REDIRECTING TO THE ADMIN ACTIONS PAGE!" , "success").then((value)=>{
        window.location="adminactions.jsp";
    });
}
function redirectvotingpage()
{
     swal("ADMIN!" , "REDIRECTING TO THE VOTINGCONTROLLER PAGE!" , "success").then((value)=>{
        window.location="VotingControllerServlet";
    });
}
    </script>
</body>
</html>