


function electionresult()
{
    console.log("the function call is successful");
    $.post("ElectionResultControllerServlet" , null,function(responseText){
        console.log("result is fetched");
        
     swal("RESULT FETCHED!" , "SUCCESS!" , "success");
     console.log(responseText);
    $("#result").html(responseText.trim()); 
       
        
        
        
    });
     
}



