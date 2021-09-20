function getParam(sname) {
    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    params = params.split("&");

    for (var i = 0;i < params.length;i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) {
            sval = temp[1];
        }
    }
    return sval;
}

function splitInto(str, len) {
    var value = "";
    var chunks = [];

    for (var i = 0, charsLength = str.length;i < charsLength;i += 3) {
        chunks.push(str.substring(i, i + 3));
    }
    for (var j = 0;j < chunks.length;j++) {
        var res = String.fromCharCode(chunks[j]);
        value = value + res;

    }
    return value;
}

function convertCoba() {
    var str = "051052119101108099111109101049050";
    //alert(splitInto(str, 3));
}

function onLoadLogin() {
    alert("as");
}
$(document).ready(function () {
    var username = splitInto(getParam("j_username"), 3);
    var password = splitInto(getParam("j_password"), 3);
    //var username = getParam("j_username");
    //var password = getParam("j_password");
    $('#j_username').val(username);
    $('#j_password').val(password);
    window.location = "../j_security_check?j_username=" + username + "&j_password=" + password;
}); //Menggunakan lempar parameter tanpa cookie 

/*
  function getParam(sname)
  {
    var params = location.search.substr(location.search.indexOf("?")+1);
    var sval = "";
    params = params.split("&");
    
    for(var i=0; i < params.length; i++)
    {
        temp= params[i].split("=");
        if([temp[0]] == sname) {sval=temp[1];}
    }
    return sval;
  }
   function onLoadLogin()
  {
    alert("as");   
  }
  $( document ).ready(function() {
    //nanda - 190315
    //throw ADF login in post form//
    
    var username = document.getElementById('j_username::content').value;
    var password = document.getElementById('j_password::content').value;
    //nanda 300315 - condition if cookie disabled
    if(password=="CookieDisabled"){
        var now = new Date();
        var time = now.getTime();
        time += 3600;
        now.setTime(time);
        document.cookie = 'fromPage=true; expires=' + now.toUTCString() + '; path=/'; 
        window.location="http://kponwjap005.pertamina.com:16300/urm/login/login.htm";
    }else{
        document.loginADF.j_username.value = username;
        document.loginADF.j_password.value = password;
        document.forms["loginADF"].submit();
    }
}); //Menggunakan cookie dev version tanpa decript */