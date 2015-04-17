/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var imageData;

$("#submit").click(function () {

    var point = $("#locX").val() + " " + $("#locY").val();
    // alert(point)
    var poi = {
        "value": $("#POIName").val(),
        "type": point,
        "base": $("#openHour").val(),
        "lang": $("#desc").val(),
        "href": imageData
    };

    var jsonData = JSON.stringify(poi);
    // alert(poi.value)

    $.ajax({
        type: "post",
        url: "http://localhost:8084/Tourism/rest/data",
        data: jsonData,
        dataType: "json",
        contentType: 'application/json',
        complete: function (jqXHR, textStatus) {
            alert("completed")
        },
        success: function (data, textStatus) {
            // alert("data.success");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // alert(textStatus);
        }
    });
})


$("#POIImage").change(function () {
    var preview = document.querySelector("img");
    var file = $("#POIImage")[0].files[0];
    var reader = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
        imageData = reader.result;

    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
});



//    $.get("http://localhost:8084/Tourism/rest/data", poi
//            , function (data) {
//                if (data.success === true) {
//                    console.log(data.success);
//
//                } else {
//                    $("#error").html(
//                            "<div class=\"alert alert-danger alert-dismissable\">" +
//                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>" +
//                            "data error" +
//                            "</div>"
//                            )
//                }
//            })
//
