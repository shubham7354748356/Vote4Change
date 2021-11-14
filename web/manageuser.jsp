<html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="stylesheet/admin.css" rel="stylesheet">
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    
     <script type="text/javascript" src="jsscript/dynamic.js"></script>
    
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
        
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
    %>
   
    
    <div class="sticky">
        <div class="candidate">VOTE FOR CHANGE</div><br>
        <div id="logout" style="text-align: right"><a href="login.html">LOGOUT</a></div>
        <div class="subcandidate">Login Page</div>
    </div>
    
    
    <div id="class1" onclick="update()" style="position:absolute;top:200px;left:300px;">
        <image style="height:200px;width:300px;" src="images/update1.jpg"/>
        <h3>Update User</h3>
      </div>
    
    
     <div id="class2"  onclick="del()" style="position:absolute;top:200px;left:800px;">
        <image style="height:200px;width:300px;" src="images/update3.jpg"/>
        <h3>Delete User</h3>
      </div>
   
    <div id="result" style="padding-left: 400px; padding-top: 600px;"></div>
    <div id="addres" style="padding-left: 500px"></div>
    
    
    
    <script>
        function update()
  {
      console.log("update is calling");
             var r = document.getElementById("result");
    r.innerHTML="";
    var newdiv = document.createElement("div");
    newdiv.setAttribute("border" , "solid 2px red");
    newdiv.setAttribute("float" , "left");
    newdiv.setAttribute("padding-left" , "10px");
    newdiv.innerHTML = "<h3>Update candidate</h3>";
    
    
     newdiv.innerHTML = newdiv.innerHTML+ "<form id='file11' method='post' enctype='multipart/form-data'><table>"
    +"<tr><th>User Id:</th><td><select id='uid' onchange='getdetail()'></select></td></tr>"+
    
     "<tr><th>User Name:</th><td><input type='text' id='uname'></td></tr>"+
      
       "<tr><th>City:</th><td><input type='text' id='city'></td></tr>"+
       "<tr><th>Address:</th><td><input type='text' id='address'></td></tr>"+
       "<tr><th>Mobile Number:</th><td><input type='text' id='mobile'></td></tr>"+
       "<tr><th>Email :</th><td><input type='text' id='email'></td></tr>"+
       
       
       
     
          
        "<tr><th><input type='button' value='Update User' onclick='addupdateduser()' id='addcnd'></th></tr></table></form>";
  
    var addDiv = $("#addres")[0];
    addDiv.innerHTML="";
    addDiv.appendChild(newdiv);
    var data = {data:"getusername"};
    $.post("ShowUserControllerServlet" , data , function(responseText)
        {
            console.log("fetched data successfully");
            console.log(JSON.parse(responseText));
        var userList = JSON.parse(responseText);
        console.log(userList.username);
        $("#uid").append(userList.username);
        });
        
 }
 function getdetail()
 {
     var u = $("#uid").val();
     console.log("selected id is => "+u);
     var data={data:"getdetails",uid:u};
     $.post("ShowUserControllerServlet" , data , function(responseText)
        {
        var user = JSON.parse(responseText);
        $("#uname").val(user.uname).prop("disabled",true);
        $("#city").val(user.city);
        $("#mobile").val(user.mobile);
        $("#email").val(user.email);
        $("#address").val(user.address);
        });
 }
 
 
 function addupdateduser()
 {
     console.log("in the add update user");
     var data={
         data:"update",
         uid:$("#uid").val(),
         
         city:$("#city").val(),
         email:$("#email").val(),
         address:$("#address").val(),
         mobile:$("#mobile").val()
         
     };
     $.post("ShowUserControllerServlet" , data , function(responseText){
         
       if(responseText.trim()==='success')
       {
           var p1 = swal("USER UPDATION","USER UPDATED SUCCESSFULLY","success");
           p1.then(re);
       }
       else
       {
            var p2 = swal("USER UPDATION","USER NOT UPDATED SUCCESSFULLY","error");
           p2.then(re);
       }
         
     });
 }
 function re()
 {
     window.location="manageuser.jsp";
 }
 
        function del()
        {
             var r = document.getElementById("result");
    r.innerHTML="";
    var newdiv = document.createElement("div");
    newdiv.setAttribute("border" , "solid 2px red");
    newdiv.setAttribute("float" , "left");
    newdiv.setAttribute("padding-left" , "10px");
    newdiv.innerHTML = "<h3>delete User</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='font-weight:bold;color:white;'>Candidate Id:</div><div><select id='uid' onchange='getuserdetails()'></select></div>";
    var addDiv = $("#addres")[0];
    addDiv.innerHTML="";
    addDiv.appendChild(newdiv);
    var data = {data:"getusername"};
    $.post("ShowUserControllerServlet" , data , function(responseText)
        {
            console.log("fetched data successfully");
            console.log(JSON.parse(responseText));
        var userList = JSON.parse(responseText);
        console.log(userList.username);
        $("#uid").append(userList.username);
        });
   }
    function getuserdetails()
    {
        console.log("User details are fetching");
        var u = $("#uid").val();
     console.log("selected id is => "+u);
     var data={data:u};
     $.post("DeleteUserControllerServlet" , data , function(responseText){
          var res = JSON.parse(responseText);
              
               console.log(res);
              let add  =$("#addres")[0];
             
               $("#addres").append(res.subdetails);
         
     });
    }
       function con()
       {
           console.log("no the time to delete");
           var us = $("#uid").val();
           var data = {data:us,task:"delete"};
           $.post("DeleteUserControllerServlet" , data , function(responseText){
               console.log(responseText);
              if(responseText.trim()==='success')
              {
                  swal("ADMIN!","USER DELETED SUCCESSFULLY","success").then(function(){
                     window.location="manageuser.jsp"; 
                      
                  });
              }
               else
              {
                  swal("ADMIN!","USER NOT DELETED SUCCESSFULLY","error").then(function(){
                     window.location="manageuser.jsp"; 
                      
                  });
              }
               
               
           });
       }
        
    </script>
    
</body></html>