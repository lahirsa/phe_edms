$( document ).ready(function() {
    var username = document.getElementById('j_username::content').value;
    var password = document.getElementById('j_password::content').value;
    //var username = document.getElementById('j_username::content').value;
    //var password = document.getElementById('j_password::content').value;
    
//    window.location="http://kponwjap005:16300/urm/login/j_security_check?j_username=" + 
//        username + "&j_password=" + password;
 //   window.location="http://kponwjis013.pertamina.com:16300/urm/login/j_security_check?j_username=" + 
 //       username + "&j_password=" + password;
//    window.location="http://kponwjis013.pertamina.com:16300/urm/login/j_security_check?j_username=" + 
//        username + "&j_password=" + password;
}); //Menggunakan lempar parameter tanpa cookie 

/*
$( document ).ready(function() {
 
    var username = document.getElementById('j_username::content').value;
    var password = document.getElementById('j_password::content').value;
    
//nanda - 2702
//modified by nanda - 250215 - throw URM login in post form//
    document.loginURM.j_username.value = username;
    document.loginURM.j_password.value = password;
    document.forms["loginURM"].submit();

});//Menggunakan cookie dev version tanpa decript*/