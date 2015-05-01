///**
// * Created by AYA on 01/05/2015.
// */
//
var i = 1; // Global Variable for Name
var j = 1; // Global Variable for E-mail
///*
// =================
// Creating Text Box for name field in the Form.
// =================

function textBoxCreate(){
    var y = document.createElement("INPUT");
    y.setAttribute("type", "text");
    y.setAttribute("Name", "Name_" + i);
    document.getElementById("myForm").appendChild(y);
    i++;
}
/*
// =================
// Creating Text Box for email field in the Form.
// =================
// */
//function emailBoxCreate(){
//    var y = document.createElement("INPUT");
//    var t = document.createTextNode("Email");
//    y.appendChild(t);
//    y.setAttribute("Placeholder", "Email_" + j);
//    y.setAttribute("Name", "Email_" + j);
//    document.getElementById("myForm").appendChild(y);
//    j++;
//}
