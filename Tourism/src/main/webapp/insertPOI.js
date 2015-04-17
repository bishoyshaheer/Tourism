/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var imageData;

$("#submit").click(function () {

    alert("tets");
    $.ajax({
        type: "get",
        url: "http://localhost:8084/Tourism/rest/data",
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            alert(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error");
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


//  var point = $("#locX").val() + $("#locY").val();
//    var poi = {
//        label: $("#POIName").val(),
//        point: point,
//        open: $("#openHour").val(),
//        desc: $("#desc").val(),
//        image: imageData
//    }
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
