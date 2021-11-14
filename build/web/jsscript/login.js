var userid , password;
function connectUser()
{
    userid = $("#username").val();
    password = $("#password").val();
    if(validate()==false)
    {
        swal("error" , "PLEASE FILL USERID/PASSWORD" , "error");
        return;
    }
    data = {
        username:userid , 
        password:password
    }
    xhr = $.post("LoginControllerServlet" , data ,processResponse );
    xhr.fail(handleError);
}
function validate()
{
    if(userid===""||password==="")
    {
        return false;
    }
    return true;
}
function processResponse(responseText)
{
    if(responseText.trim()==="error")
    {
        swal("ADMIN!" , "INVALID USERID/PASSWORD" , "error");
    }
    else if(responseText.trim().indexOf("jsessionid")!==-1)
    {
        pr = swal("ADMIN!" , "LOGIN SUCCESSFUL" , "success");
        pr.then((value)=>{
                window.location = responseText.trim();
        });
              
    }
}

function handleError()
{
    swal("error" , "PROBLEM IN SERVER COMMUNICATION "+xhr.statusText , "error");
}