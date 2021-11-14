function fun()
{
    
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id" , "candidateform");
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
   newdiv.innerHTML = newdiv.innerHTML+ "<form id='file11' method='post' enctype='multipart/form-data'>"
    +"<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>"+   
    "<tr><th>User Id:</th><td><input type='text' id='userid' onkeypress='return getDetails(event)'/></td></tr>" +
     "<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>"+
      
       "<tr><th>City:</th><td><select id='city'></select></td></tr>"+
       "<tr><th>Party:</th><td><input type='text' id='party'</td></tr>"+
       "<tr ><td colspan='2'><input type='file' id='symbol' value='Select Image'></td></tr>"+
     
          
        "<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>"+
           +"<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
              newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var result = document.getElementById("result");
    result.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
    
   
    data = {id:"getid"};
   $.post("AddCandidateControllerServlet" , data , function(responseText){$("#cid").val(responseText);$("#cid").prop("disabled" , true);});
}

function getDetails(e)
{
    var a = e.keyCode;
    if(a===13)
    {
       
        var data = {uid:$("#userid").val()};
        $.post("AddCandidateControllerServlet" , data ,function(responseText)
        {
         details = JSON.parse(responseText); 
        
         
         uname = details.username;
         console.log(uname+" is coming");
         cit = details.city;
         if(uname==="wrong")
         {
             swal("WRONG ADHAR NO !" , "ADHAR NUMBER IS INVALID","error");
             
         }
         else if(uname==="present")
         {
             swal("CANDIDATE ALREADY PRESENT!" , "THIS USER-ID IS ALREADY REGISTERED AS A CANDIDATE","error");
             return;
             
         }
         else
         {
             $("#cname").val(uname);
             $("#city").empty();
             $("#city").append(cit);
             $("#cname").prop("disabled" , true);
         }
                
                
                
                
                
        });
    }
}
function addcandidate()
{
  
     var input = document.querySelector('input[type=file]');
                         
                var file = input.files[0];
    var data = new FormData();

    
    var candidateid = $("#cid").val();
    var userid = $("#userid").val();
    var city = $("#city").val();
    var username = $("#cname").val();
    var party = $("#party").val();
            data.append("symbol" , file);
            data.append("cid" , candidateid);
            data.append("uid" , userid);
            data.append("party" , party);
            data.append("city" , city);
            data.append("cname" , username);
            
    $.ajax({
            type:"POST" , 
            enctype:"multipart/form-data",
            url:"AddNewCandidateControllerServlet",
            data:data , 
            processData:false,
            contentType:false,
            cache:false,
            timeout:30000,
            success:function(data)
            {
                console.log(data);
                if(data.trim()==="already")
                {
                     prom = swal("ADMIN!" , "CANDIDATE IS ALREADY PRESENT FOR THIS PARTY" , "error");
                     return;
                }
                prom = swal("ADMIN!" , "CANDIDATE ADDED SUCCESSFULLY" , "success");
                
                prom.then(function(value){
                    document.getElementById("result").innerHTML="";
                   fun(); 
                   
                    
                });
                
            },
            error: function (e) 
                {
                swal("Admin!", e, "error");
                }

        });
}



function clearText()
{
    $("#addres").html("");
}
 
