
function asd() {
        $("#1").slideToggle(1000);
}

function enableRepeatSubmitButton() {
    document.getElementById('repeat-submit').disabled = false;
}

function removeMyEventM2() {

    var target = event.target || event.srcElement;
    var idText = target.id;
    var split = idText.split("-");
    var id = split[1];

    var a = confirm("Czy na pewno chcesz usunac to wydarzenie ?");

    if (a === true){
        window.location.href = '/delete/event/'+id;
    }

}

function removeMyEvent() {

    var target = event.target || event.srcElement;
    var idText = target.id;
    var split = idText.split("-");
    var id = split[1];



                var a = confirm("Czy na pewno chcesz usunac to wydarzenie ?");

                if (a === true){

                        $.ajax({
                            headers: {
                                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                            },
                        url: "http://localhost:8080/api/delete/event/"+id,
                            type: 'DELETE',
                            async: false,
                        success: function() {
                    alert("Usunieto !");
                }
            });
        }






}