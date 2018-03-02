

function verifyUser() {
    var msg = "";

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var isUsernameAvaiable =  checkByAjax(username);
    if (isUsernameAvaiable === false){
        msg+="\n PODANY LOGIN JEST JUZ ZAJETY \n"
    }

    if (username.length < 3){
        msg+="\n NAZWA UZYTKOWNIKA MUSI SKLADAC SIE Z MINIMUM 3 ZNAKOW \n"
    }
    if (password.length < 3){
        msg+="\n HASLO MUSI SKLADAC SIE Z MINIMUM 3 ZNAKOW \n"
    }




    if (msg === "" ){
        alert(" ZAREJESTROWANO ! ");
        return true
    }
    else {
        alert(msg);
        return false;
    }
}



function checkByAjax(username) {

    var result = null;

        jQuery.ajax({
            url: "http://localhost:8080/api/verifyUsername/"+username,
            success: function( isUsernameAvailable ) {
                    result = isUsernameAvailable;},
                    async: false
        });

        return result;


}






//async problem
function check(username) {

    var result = null;

    $.get( "http://localhost:8080/api/verifyUsername/"+username, function( isUsernameAvailable ) {
        alert("info from api: "+isUsernameAvailable);
        result = isUsernameAvailable;
    });
    alert("checkResult="+result);

    return result;
}


