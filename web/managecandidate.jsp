<html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="stylesheet/admin.css" rel="stylesheet">
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <script type="text/javascript" src="jsscript/jquery.js"></script>
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
    <div id="class1" onclick="fun()" style="position:absolute;top:200px;right:1200px;">
        <image style="height:200px;width:300px;" src="images/addcandidate.png"/>
        <h3>Add Candidate</h3>
      </div>
     <div id="class2"  onclick="updatecandidate()" style="position:absolute;top:200px;right:850px;">
        <image style="height:200px;width:300px;" src="images/update1.jpg"/>
        <h3>Update Candidate</h3>
            
        
    </div>
     <div id="class3"  onclick="showcandidate()" style="position:absolute;top:200px;right:450px;">
        <image style="height:200px;width:300px;" src="images/candidate.jpg"/>
        <h3>Show Candidate</h3>
            
        
    </div>
     <div id="class4" onclick="deletecandidate()" style="position:absolute;top:200px;right:100px;">
        <image style="height:200px;width:300px;" src="images/update3.jpg"/>
        <h3>Delete Candidate</h3>
     </div>
    <div id="result" style="padding-left: 400px; padding-top: 600px;"></div>
    <div id="addres" style="padding-left: 500px"></div>
    
    
    
    <script type="text/javascript">
        
 function showcandidate()
{
    var r = document.getElementById("result");
    r.innerHTML="";
    var newdiv = document.createElement("div");
    newdiv.setAttribute("border" , "solid 2px red");
    newdiv.setAttribute("float" , "left");
    newdiv.setAttribute("padding-left" , "10px");
    newdiv.innerHTML = "<h3>show candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='font-weight:bold;color:white;'>Candidate Id:</div><div><select id='cid'></select></div>";
    var addDiv = $("#addres")[0];
    addDiv.innerHTML="";
    addDiv.appendChild(newdiv);
    var data = {data:"getid"};
    $.post("ShowCandidateControllerServlet" , data , function(responseText){
        var candidateList = JSON.parse(responseText);
        console.log(candidateList.cid);
        $("#cid").append(candidateList.cid);
        });
        
        $("#cid").change(function(){
           var cid = $("#cid").val(); 
           if(cid==='')
           {
               
               return;
           }
           var data = {data:cid};
           $.post("ShowCandidateControllerServlet" , data , function(responseText){
               var res = JSON.parse(responseText);
              
               console.log(res);
              let add  =$("#addres")[0];
              add.innerHTML="";
               $("#addres").append(res.subdetails);
               
               
           });
            
            
        });
        
}




function fun1()
{
   
}

function updatecandidate()
{
     var r = document.getElementById("result");
    r.innerHTML="";
    var newdiv = document.createElement("div");
    newdiv.setAttribute("border" , "solid 2px red");
    newdiv.setAttribute("float" , "left");
    newdiv.setAttribute("padding-left" , "10px");
    newdiv.innerHTML = "<h3>Update candidate</h3>";
    
    
     newdiv.innerHTML = newdiv.innerHTML+ "<form id='file11' method='post' enctype='multipart/form-data'><table>"
    +"<tr><th>Candidate Id:</th><td><select id='cid' onchange='detail()'></select></td></tr>"+
    "<tr><th>User Id:</th><td><input type='text' id='userid' /></td></tr>" +
     "<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>"+
      
       "<tr><th>City:</th><td><select id='city'></select></td></tr>"+
       "<tr><th>Party:</th><td><input type='text' id='party'</td></tr>"+
       "<tr ><td colspan='2'><input type='file' id='symbol' value='Select Image'></td></tr>"+
     
          
        "<tr><th><input type='button' value='Update Candidate' onclick='addupdatedcandidate()' id='addcnd'></th></tr></table></form><br><img src='' id='im' style='width:300px;height:200px; display:none'/>";
  
    var addDiv = $("#addres")[0];
    addDiv.innerHTML="";
    addDiv.appendChild(newdiv);
    var data = {data:"getid"};
    $.post("ShowCandidateControllerServlet" , data , function(responseText)
        {
        var candidateList = JSON.parse(responseText);
        console.log(candidateList.cid);
        $("#cid").append(candidateList.cid);
        });
        
        
        
       
   
    }
    function detail()
        {
           console.log("candidate id is changed successfully"+$("#cid").val());
           var data={
             data:  $("#cid").val()};
         $.post("UpdateCandidateControllerServlet",data,function(responseText){
             
             $("#cname").val(JSON.parse(responseText).username).prop("disabled",true);
             $("#party").val(JSON.parse(responseText).party);
             $("#userid").val(JSON.parse(responseText).userid).prop("disabled",true);
             $("#im").attr('src' ,JSON.parse(responseText).symbol);
             $("#im").show();
             $("#city").append(JSON.parse(responseText).city);
         });
          
        }
    function addupdatedcandidate()
    {
        
         var input = document.querySelector('input[type=file]');
                       
                var file = input.files[0];
    var data = new FormData();

    
    
   
    var city = $("#city").val();
    var party = $("#party").val();
    var id = $("#cid").val();
    console.log(city+" "+party+" "+id);
            data.append("symbol" , file);
            
            data.append("party" , party);
            data.append("city" , city);
            data.append("candidateid" , id);
            
            
    $.ajax({
            type:"POST" , 
            enctype:"multipart/form-data",
            url:"AddUpdatedCandidateControllerServlet",
            data:data , 
            processData:false,
            contentType:false,
            cache:false,
            timeout:30000,
            success:function(data)
            {
                prom = swal("ADMIN!" , "CANDIDATE UPDATED SUCCESSFULLY", "success");
                
                prom.then(function(value){
                   window.location="managecandidate.jsp";
                   
                    
                });
                
            },
            error: function (e) 
                {
                swal("Admin!", "CANDIDATE CANNOT UPDATED SUCCESSFULLY", "error");
                }

        });
    }




function deletecandidate()
{
     var r = document.getElementById("result");
    r.innerHTML="";
    var newdiv = document.createElement("div");
    newdiv.setAttribute("border" , "solid 2px red");
    newdiv.setAttribute("float" , "left");
    newdiv.setAttribute("padding-left" , "10px");
    newdiv.innerHTML = "<h3>delete candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='font-weight:bold;color:white;'>Candidate Id:</div><div><select id='cid' onchange='detailsfordelete()'></select></div>";
    var addDiv = $("#addres")[0];
    addDiv.innerHTML="";
    addDiv.appendChild(newdiv);
    var data = {task:"getid"};
    $.post("DeleteCandidateControllerServlet" , data , function(responseText){
       
        console.log(responseText);
        $("#cid").append(responseText);
        });
}


function detailsfordelete()
{
     var cid = $("#cid").val(); 
           if(cid==='')
           {
               
               return;
           }
           var data = {candidateid:cid};
           $.post("DeleteCandidateControllerServlet" , data , function(responseText){
               var res = JSON.parse(responseText);
              
               console.log(res);
              let add  =$("#addres")[0];
             
               $("#addres").append(res.subdetails);
               
               
           });
}

function confirmdelete()
{
    var candidateid = $("#cid").val();
    var data = {candidateid:candidateid , task:"delete"};
    $.post("DeleteCandidateControllerServlet" , data , function(responseText){
       if(responseText.trim()==='success')
       {
           prom = swal('CANDIDATE DELETED!',"CANDIDATE DELETED SUCCESSFULLY" , "success");
           prom.then(function(){
               window.location = "managecandidate.jsp";
           });
       }
       else
       {
            prom = swal('CANDIDATE NOT DELETED!',"CANDIDATE NOT DELETED SUCCESSFULLY" , "error");
           prom.then(function(){
               deletecandidate();
           });
       }
        
        
    });
}


    </script>
</body>
</html>