username , user_id , password , city , address , email , mobile . cpassword;
function addUser()
{
    username = $("#username").val();
    user_id = $("#adhar").val();
    password = $("#password").val();
    city=$("#city").val();
    address = $("#address").val();
    mobile = $("#mobile").val();
    email = $("#email").val();
    cpassword = $("#cpassword").val();
    if(validate()==false)
    {
        swal("ERROR","PLEASE FILL ALL THE FIELDS" , "error");
        return;
    }
    if(password!==cpassword)
    {
        swal("ERROR","PASSWORD DOES NOT MATCHES" , "error");
        return;
    }
    if(email.indexOf("@")==-1)
    {
        swal("ERROR" , "PLEASE FILL THE VALID EMAIL ADDRESS"."error");
        return
    }
    data={
        username:username,
        password:password,
        city:city,
        address:address,
        email:email,
        mobile:mobile,
        user_id:user_id 
        
    }
     xhr = $.post("RegistrationControllerServlet" , data ,processResponse);
     xhr.error(handleError)
}
function processRespones(responseText , textStatus , xhr)
{
    if(responseText.trim()==="uap")
    {
        swal("ERROR" , "USER ID ALREADY PRESENT" , "error");
    }
    else if(responseText.trim()=="success")
    {
        swal("SUCCESS" , "LOGIN ACCEPTED" , "success");
        setTimeOut(3000 , redirect);
    }
    else
    {
        swal("error" , "PROBLEM WITH THE DATABASE TRY LATER" , "error");
    }
}
function redirect()
{
    window.location="login.html";
}
function validate()
{
    if(username==="" || user_id===""||password===""||cpassword===""||city===""||address===""||mobile===""||email==="")
    {
        return false;
        
    }
    return true;
}

function add()
{
    console.log("add is running successfully");
}


