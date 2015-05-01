/**
 * Created by Marwa on 4/29/2015.
 */
var currentMarker = null;

function initialize() {

    var mapOptions = {
        center: new google.maps.LatLng(-34.397, 150.644),
        zoom: 8
    };

    var map = new google.maps.Map(PF('map'),
        mapOptions);

    var drawingManager = new google.maps.drawing.DrawingManager({
        drawingMode: google.maps.drawing.OverlayType.MARKER,
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: [
                google.maps.drawing.OverlayType.MARKER,
                google.maps.drawing.OverlayType.CIRCLE,
                google.maps.drawing.OverlayType.POLYGON,
                google.maps.drawing.OverlayType.POLYLINE,
                google.maps.drawing.OverlayType.RECTANGLE
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

function handlePointClick(event) {
    if(currentMarker === null) {
        document.getElementById('lat').value = event.latLng.lat();
        document.getElementById('lng').value = event.latLng.lng();

        currentMarker = new google.maps.Marker({
            position:new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
        });

        PF('map').addOverlay(currentMarker);

        PF('dlg').show();
    }
}

function markerAddComplete() {
    var title = document.getElementById('title');
    currentMarker.setTitle(title.value);
    title.value = "";

    currentMarker = null;
    PF('dlg').hide();
}

function cancel() {
    PF('dlg').hide();
    currentMarker.setMap(null);
    currentMarker = null;

    return false;
}}