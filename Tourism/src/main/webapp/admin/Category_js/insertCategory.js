/* 
 * the java Script Function that will Call After Press 
 * Submit To Insert A Category
 */




$("#submit").click(function () {

    // prepare the Json Object to send to the web Service create as an object of POIBaseType

    var data = {
        "type": $("#catName").val(),
        "value": $("#desc").val(),
        "lang": $("#lang").val()
    };

    var jsonData = JSON.stringify(data);


    /**** Prepare the ajax function to call the web Service ****/
    $.ajax({
        type: "post",
        url: "http://localhost:8084/Tourism/rest/category",
        data: jsonData,
        dataType: "json",
        contentType: 'application/json',
        complete: function (jqXHR, textStatus) {
            // alert("completed")
        },
        success: function (data, textStatus) {
            $(".demo-container").append("<div class=\"alert alert-success\">" +
                    "data inserted successFully" +
                    "</div>");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(".demo-container").append("<div class=\"alert alert-danger\">" +
                    textStatus +
                    "</div>");
        }
    });
})


