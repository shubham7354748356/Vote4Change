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
            left:200px;
        } 
        #class2{
             position: absolute;
            top:200px;
            left:600px;
        }
        #class3{
             position: absolute;
            top:200px;
            left:1000px;
        }
        img{
            height:200px;
            width: 300px;
        }
        table,th,tr,td{
            border: 1px solid greenyellow;
  border-collapse: collapse;
  text-align: center;
        }
        th,td{
            padding: 2px;
        }
        table{
             margin-left: auto;
  margin-right: auto;
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
    <div id="class1" onclick="manageuser()">
        <image src="images/muser.png"/>
        <h3>Manage User</h3>
            
        
    </div>
     <div id="class2" onclick="managecandidate()">
        <image src="images/ManageCandLists.jpg"/>
        <h3>Manage Candidate</h3>
            
        
    </div>
    <div id="class3" onclick="electionresult()">
        <image src="images/resultgraph.jpg"/>
        <h3>Show Result</h3>
            
        
    </div>
    <div id="result" style="position: absolute;top:500px;"></div>
    <script>
        function manageuser()
{
     swal("ADMIN!" , "REDIRECTING TO THE USER MANAGEMENT PAGE!" , "success").then((value)=>{
        window.location="manageuser.jsp";
    });
}
function managecandidate()
{
     swal("ADMIN!" , "REDIRECTING TO THE CANDIDATE MANAGEMENT PAGE!" , "success").then((value)=>{
        window.location="managecandidate.jsp";
    });
}
    </script>
    
</body>
</html>