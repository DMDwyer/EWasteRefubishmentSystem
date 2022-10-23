/**
 * 
 */
 
 function verifyPassword() {  
  var pw1 = document.getElementById("password").value;  
  var pw2 = document.getElementById("confirm_password").value;  
  if(pw1 != pw2)  
  {   
	document.getElementById("message").innerHTML = "**Passwords do not match";
    return false; 
  } 
  
  return true;
  
}  