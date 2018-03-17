function myFunction() {
    $("#add").slideToggle(1000);
}

function cancelEdit() {
    $("#edit").slideToggle(1000);
    $("#main").slideToggle(1000);
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