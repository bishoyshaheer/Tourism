var currentMarker = null;

var markers = [];
var circles = [];
var polygons = [];
var polylines = [];
var rectangles = [];

function initialize() {
    // alert("jjjj");
    var mapOptions = {
        center: new google.maps.LatLng(-34.397, 150.644),
        zoom: 8
    };

    var map = PF('map').getMap();


    var drawingManager = new google.maps.drawing.DrawingManager({
        drawingMode: google.maps.drawing.OverlayType.MARKER,
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: [
                google.maps.drawing.OverlayType.MARKER,
                //google.maps.drawing.OverlayType.CIRCLE,
                google.maps.drawing.OverlayType.POLYGON,
                google.maps.drawing.OverlayType.POLYLINE
                //google.maps.drawing.OverlayType.RECTANGLE
            ]
        },
        markerOptions: {
            draggable: true
//                     icon: 'images/beachflag.png',
        },
        polylineOptions: {
            draggable: true
        },
        polygonOptions: {
            draggable: true
        },
        rectangleOptions: {
            draggable: true
        },
        circleOptions: {
            fillColor: '#ffff00',
            fillOpacity: 0.3,
            strokeWeight: 5,
            clickable: true,
            editable: true,
            zIndex: 1
        }

    });
    drawingManager.setMap(map);

    google.maps.event.addListener(drawingManager, 'overlaycomplete', function (event) {
        // action of drawing marker
        if (event.type == google.maps.drawing.OverlayType.MARKER) {
            var newMarker = event.overlay;
            newMarker.content = "marker #" + markers.length;
            // add marker in array
            markers.push(newMarker)
           // alert(newMarker.getPosition().lat());
            document.getElementById('lat').value = newMarker.getPosition().lat();
            document.getElementById('lng').value = newMarker.getPosition().lng();

            currentMarker = new google.maps.Marker({
                position: new google.maps.LatLng(newMarker.getPosition().lat(), newMarker.getPosition().lng())
            });

            PF('map').addOverlay(currentMarker);

            PF('dlg').show();
            //document.getElementById('myform:hiddenId').nodeValue = newMarker;

        }

        // action of drawing polygon
        else if (event.type == google.maps.drawing.OverlayType.POLYGON) {
            var newPolygon = event.overlay;
            newPolygon.content = "polygon #" + polygons.length;

            // add marker in array
            polygons.push(newPolygon);
            document.getElementById("lngPol").value = newPolygon.getPath().getArray();

            var triangleCoords=[];
            for(i=0 ;i<newPolygon.getPath().getArray().length;i++){

                    triangleCoords.push(new google.maps.LatLng(newPolygon.getPath().getAt(i).lat(),newPolygon.getPath().getAt(i).lng()))
          }

         currentMarker= new google.maps.Polygon({
             paths: triangleCoords,
             strokeColor: '#FF0000',
             strokeOpacity: 0.8,
             strokeWeight: 2,
             fillColor: '#FF0000',
             fillOpacity: 0.35
         });
            PF('map').addOverlay(currentMarker);

            PF('dlgpoly').show();
        }

        // action of drawing polyline
        else if (event.type == google.maps.drawing.OverlayType.POLYLINE) {
            var newPolyline = event.overlay;
            newPolyline.content = "polyline #" + polylines.length;
            // add marker in array
            polylines.push(newPolyline);

            document.getElementById("cooLine").value = newPolyline.getPath().getArray();
          //  alert(newPolyline.getPath().getArray())
            var triangleCoords=[];
            for(i=0 ;i<newPolyline.getPath().getArray().length;i++){

                triangleCoords.push(new google.maps.LatLng(newPolyline.getPath().getAt(i).lat(),newPolyline.getPath().getAt(i).lng()))
            }
            //alert(newPolyline.getPath().getArray())
            currentMarker= new google.maps.Polyline({
                paths: triangleCoords,
                strokeColor: '#FF0000',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.35
            });
            PF('map').addOverlay(currentMarker);

            PF('dlgline').show();

        }

        // action of drawing rectangle
        //else if (event.type == google.maps.drawing.OverlayType.RECTANGLE) {
        //    var newRect = event.overlay;
        //    newRect.content = "rect #" + rectangles.length;
        //    google.maps.event.addListener(newRect, 'click', function (event) {
        //        // alert(event.latLng)
        //    });
        //    // add marker in array
        //    rectangles.push(newRect);
        //}
    });

}
//
//
////
//
function markerAddComplete() {
    //var title = document.getElementfunction handlePointClick(event) {
//    alert("test");
//    if (currentMarker === null) {
//        document.getElementById('lat').value = event.latLng.lat();
//        document.getElementById('lng').value = event.latLng.lng();
//
//        currentMarker = new google.maps.Marker({
//            position: new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
//        });
//
//        PF('map').addOverlay(currentMarker);
//
//        PF('dlg').show();
//    }
//    alert(event.latLng.lat());
//}ById('title');
    //currentMarker.setTitle(title.value);
    //title.value = "";
    //
    //currentMarker = null;
    PF('dlg').hide();
    PF('dlgpoly').hide();
    PF('dlgline').hide();
}

function cancel() {
    PF('dlg').hide();
    PF('dlgpoly').hide();
    PF('dlgline').hide();
    currentMarker.setMap(null);
    currentMarker = null;

    return false;
}
google.maps.event.addDomListener(window, 'load', initialize);

//var markers = [];
//var circles = [];
//var polygons = [];
//var polylines = [];
//var rectangles = [];
//
//function initialize() {
//   // alert("jjjj");
//    var mapOptions = {
//        center: new google.maps.LatLng(-34.397, 150.644),
//        zoom: 8
//    };
//
//    var map = new google.maps.Map(document.getElementById('map-canvas'),
//        mapOptions);
//
//    var drawingManager = new google.maps.drawing.DrawingManager({
//        drawingMode: google.maps.drawing.OverlayType.MARKER,
//        drawingControl: true,
//        drawingControlOptions: {
//            position: google.maps.ControlPosition.TOP_CENTER,
//            drawingModes: [
//                google.maps.drawing.OverlayType.MARKER,
//                google.maps.drawing.OverlayType.CIRCLE,
//                google.maps.drawing.OverlayType.POLYGON,
//                google.maps.drawing.OverlayType.POLYLINE,
//                google.maps.drawing.OverlayType.RECTANGLE
//            ]
//        },
//        markerOptions: {
//            draggable: true
////                     icon: 'images/beachflag.png',
//        },
//        polylineOptions: {
//            draggable: true
//        },
//        polygonOptions: {
//            draggable: true
//        },
//        rectangleOptions: {
//            draggable: true
//        },
//        circleOptions: {
//            fillColor: '#ffff00',
//            fillOpacity: 0.3,
//            strokeWeight: 5,
//            clickable: true,
//            editable: true,
//            zIndex: 1
//        }
//
//    });
//    drawingManager.setMap(map);
//
//    google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
//        // action of drawing marker
//        if (event.type == google.maps.drawing.OverlayType.MARKER) {
//            var newMarker = event.overlay;
//            newMarker.content = "marker #" + markers.length;
//
//            google.maps.event.addListener(newMarker, 'click', function(event) {
//                for (var i = 0; i <  markers.length ; i++) {
//                    if (markers[i] == newMarker) {
//                       // alert("jijj");
//                    }
//                }
//            });
//            // add marker in array
//            markers.push(newMarker)
//            //document.getElementById('myform:hiddenId').nodeValue = newMarker;
//           // alert(newMarker.getLatitude);
//        }
//        // action of drawing circle
//        else if (event.type == google.maps.drawing.OverlayType.CIRCLE) {
//            var newCircle = event.overlay;
//            newCircle.content = "circle #" + circles.length;
//            google.maps.event.addListener(newCircle, 'click', function(event) {
//               // alert(newCircle.content)
//            });
//            // add marker in array
//            circles.push(newCircle);
//        }
//
//        // action of drawing polygon
//        else if (event.type == google.maps.drawing.OverlayType.POLYGON) {
//            var newPolygon = event.overlay;
//            newPolygon.content = "polygon #" + polygons.length;
//            google.maps.event.addListener(newPolygon, 'click', function(event) {
//              //  alert(event.latLng)
//            });
//            // add marker in array
//            polygons.push(newPolygon);
//        }
//
//        // action of drawing polyline
//        else if (event.type == google.maps.drawing.OverlayType.POLYLINE) {
//            var newPolyline = event.overlay;
//            newPolyline.content = "polyline #" + polylines.length;
//            //alert(polylines.length);
//            google.maps.event.addListener(newPolyline, 'click', function(event) {
//                //alert(event.latLng)
//            });
//            // add marker in array
//            polylines.push(newPolyline);
//          //  alert(polylines[0]);
//          }
//
//        // action of drawing rectangle
//        else if (event.type == google.maps.drawing.OverlayType.RECTANGLE) {
//            var newRect = event.overlay;
//            newRect.content = "rect #" + rectangles.length;
//            google.maps.event.addListener(newRect, 'click', function(event) {
//               // alert(event.latLng)
//            });
//            // add marker in array
//            rectangles.push(newRect);
//        }
//    });
//
//    //~~~~~~~~~~~~~~~~~~~~~~~~right click menu*********************//
//    //	create the ContextMenuOptions object
//    //var contextMenuOptions = {};
//    //contextMenuOptions.classNames = {menu: 'context_menu', menuSeparator: 'context_menu_separator'};
//    //
//    ////	create an array of ContextMenuItem objects
//    //var menuItems = [];
//    //menuItems.push({className: 'context_menu_item', eventName: 'zoom_in_click', label: 'Zoom in'});
//    //menuItems.push({className: 'context_menu_item', eventName: 'zoom_out_click', label: 'Zoom out'});
//    ////	a menuItem with no properties will be rendered as a separator
//    //menuItems.push({});
//    //menuItems.push({className: 'context_menu_item', eventName: 'center_map_click', label: 'Center map here'});
//    //contextMenuOptions.menuItems = menuItems;
//    //
//    ////	create the ContextMenu object
//    //var contextMenu = new ContextMenu(map, contextMenuOptions);
//    //
//    ////	display the ContextMenu on a Map right click
//    //google.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
//    //    contextMenu.show(mouseEvent.latLng);
//    //});
//    //
//    ////	listen for the ContextMenu 'menu_item_selected' event
//    //google.maps.event.addListener(contextMenu, 'menu_item_selected', function(latLng, eventName) {
//    //    //	latLng is the position of the ContextMenu
//    //    //	eventName is the eventName defined for the clicked ContextMenuItem in the ContextMenuOptions
//    //    switch (eventName) {
//    //        case 'zoom_in_click':
//    //            map.setZoom(map.getZoom() + 1);
//    //            break;
//    //        case 'zoom_out_click':
//    //            map.setZoom(map.getZoom() - 1);
//    //            break;
//    //        case 'center_map_click':
//    //            map.panTo(latLng);
//    //            break;
//    //    }
//    //});
//}
////google.maps.event.addDomListener(window, 'load', initialize);  // action of drawing circle
//else if (event.type == google.maps.drawing.OverlayType.CIRCLE) {
//    var newCircle = event.overlay;
//    newCircle.content = "circle #" + circles.length;
//    google.maps.event.addListener(newCircle, 'click', function (event) {
//        // alert(newCircle.content)
//    });
//    // add marker in array
//    circles.push(newCircle);
//}




