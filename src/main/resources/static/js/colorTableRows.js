function colorTable() {

    var x = document.getElementById('mainTable').rows.length-1;
    for (var i = 1 ; i <= x ; i++){
        var daysLeftString = document.getElementById('left-'+i).innerText;
        var daysLeft = parseInt(daysLeftString);
        var leftBorder = document.getElementById(String(i));
        if (daysLeft < 0 ){
            leftBorder.style.borderLeft = "5px solid #e08585";
        }
        if (daysLeft >= 0){
            leftBorder.style.borderLeft = "5px solid #ffff66";
        }
        if (daysLeft >= 7){
            leftBorder.style.borderLeft = "5px solid #80ff80";
        }
    }

}
