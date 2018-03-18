function myFunction() {
    $("#add").slideToggle(1000);
}

function cancelEdit() {
    $("#edit").slideToggle(1000);
    $("#main").slideToggle(1000);
}

function repeatEvent() {
    $("#main").slideToggle(1000);
    $("#repeat").slideToggle(1000);

    var target = event.target || event.srcElement;
    var idText = target.id;
    var split = idText.split("-");
    var id = split[1];

    var title;
    var description;
    var day;
    var month;
    var year;

    jQuery.ajax({
        url: "/api/get/"+id,
        success: function (myEvent) {
            result = JSON.stringify(myEvent);

            title = myEvent.title;
            description = myEvent.description;
            day = myEvent.dayOfMonth;
            month = myEvent.month;
            year = myEvent.year;

        },
        dataType: "json",
        async: false
    });

    document.getElementById('repeat-id').value = String(id);
    document.getElementById('repeat-title').innerText += ' '+title;
    document.getElementById('repeat-form').action = '/edit/'+id;
    document.getElementById('repeat-eventTitle').value = title;
    document.getElementById('repeat-description').value = description;
}

function calculateNewDateOnRepeat() {

    var id = document.getElementById('repeat-id').value;

    var options = document.getElementsByName('time');
    var selected;

    var day,month,year;

    jQuery.ajax({
        url: "/api/get/"+id,
        success: function (myEvent) {
            day = myEvent.dayOfMonth;
            month = myEvent.month;
            year = myEvent.year;
        },
        dataType: "json",
        async: false
    });

    for (var i = 0 ; i < options.length ; i++){
        if($(options[i]).is(':checked')) {
            selected = options[i].value;
        }
    }

    var now = new Date();
    now.setDate(day);
    now.setMonth(now.getMonth()+1);

    now.setHours(0);
    now.setMinutes(0);
    now.setSeconds(0);
    now.setMilliseconds(0);


    if (selected === 'month'){

        now.setMonth(now.getMonth()+1);



        var MDate = now;
        var yearNew = ''+MDate.getFullYear();
        var monthNew = MDate.getMonth();

        if(monthNew < 10){
            monthNew = '0'+monthNew;
        }

        var dayNew = MDate.getDate();
        if (dayNew < 10 ){
            dayNew = '0'+dayNew;
        }

        document.getElementById('repeat-date').value = yearNew+'-'+monthNew+'-'+dayNew;

    }
    else if (selected === 'year'){

        now.setFullYear(now.getFullYear()+1);
        now.setMonth(month);

        var YDate = now;
        var yearNewY = ''+YDate.getFullYear();
        var monthNewY = YDate.getMonth();
        if(monthNewY < 10){
            monthNewY = '0'+monthNewY;
        }
        var dayNewY = YDate.getDate();
        if (dayNewY < 10 ){
            dayNewY = '0'+dayNewY;
        }
        document.getElementById('repeat-date').value = yearNewY+'-'+monthNewY+'-'+dayNewY;

    }


}



function replaceMyDiv() {
    $("#main").slideToggle(1000);
    $("#edit").slideToggle(1000);

    var isVisible = $('#add').is( ":visible" );
    if (isVisible){
        $('#add').slideToggle(1000);
    }

    var target = event.target || event.srcElement;
    var idText = target.id;
    var split = idText.split("-");
    var id = split[1];

    var result;

    var title;
    var description;
    var day;
    var month;
    var year;

    jQuery.ajax({
        url: "/api/get/"+id,
        success: function (myEvent) {
            result = JSON.stringify(myEvent);

            title = myEvent.title;
            description = myEvent.description;
            day = myEvent.dayOfMonth;
            month = myEvent.month;
            year = myEvent.year;

        },
        dataType: "json",
        async: false
    });

    if (day < 10){
        day = '0'+day;
    }
    if (month < 10){
        month = '0'+month
    }
    document.getElementById('edit-title').value = title;
    document.getElementById('edit-date').value = year+'-'+month+'-'+day;
    document.getElementById('edit-desc').value = description;
    document.getElementById('edit-form').action = '/edit/'+id;
}