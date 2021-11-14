function addvote()
{
    var id=$("input[type=radio][name=flat]:checked").attr("id");
    data={
        candidateid:id
    };
    $.post("AddVoteControllerServlet",data ,function(resp){
        console.log(resp);
        resp = resp.trim();
        console.log(resp);
         if(resp==="success")
        {
        
        var prom =  swal("Success!","Voting done" , "success");
        prom.then(fun1);
        }
      else
        {
            var prom1 = swal("Failure","Voting failed","error");
            prom1.then(fun1);
        }
            
    });
}
function fun1()
{
    
     window.location="votingresponse.jsp";
}
  
