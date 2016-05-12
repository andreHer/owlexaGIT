var mapObject;

$(function(){

    map_init();

    mapObject.mapIcons.markerIcon = map_createIcon("red");
    mapObject.mapIcons.markerHighlightIcon = map_createIcon("orange");
    mapObject.mapIcons.directionStartIcon = map_createIcon("start");
    mapObject.mapIcons.directionEndIcon = map_createIcon("end");

    mapObject.mapIcons.providerIcon = map_createCustomIcon({
        image:  "images/hospitals.png",
        iconSize: new  google.maps.Size(24, 24),
        iconAnchor: new  google.maps.Point(5, 24),
        infoWindowAnchor: new google.maps.Point(12, 12)
    });

        
   
   

});

function map_init() {
		
        var mapCanvas = $("#mapCanvas");
        var lat = document.getElementById("providerLat").value;
        var lng = document.getElementById("providerLong").value;
        var providerName = document.getElementById("providerName").value;
        
        if(mapCanvas.length > 0) {
	        var mapOptions = {
	          center: new google.maps.LatLng(lat, lng),
	          zoom: 12,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        };	        
            var map = new google.maps.Map(mapCanvas,mapOptions);
            mapObject = {
                map: map,
                mapCanvas: mapCanvas,
                dataOverlays: [],
                dataLoadFlag: {},
                mapIcons: {}
            };
            
             var myLatLng = new google.maps.LatLng(lat, lng);
    
    		var providerIcon = "images/hospitals.png";
		  	var beachMarker = new google.maps.Marker({
		      position: myLatLng,
		      map: map,
		      title: providerName,
		      icon: providerIcon
		 	});

			window.alert("MAP INIT");            
        }   
}

function map_createIcon(image) {
    var icon = new google.maps.MarkerImage(
    "images/gicon/" + image + ".png",
    new google.maps.Size(12,20),
    new google.maps.Point(6,20),
    new google.maps.Point(6,1));
    
    
    return icon;
}

function map_createCustomIcon(data) {
    data = $.extend({
        iconSize: new google.maps.Size(16, 16),
    }, data);
    data = $.extend({
        iconAnchor: new google.maps.Point(0.5 * data.iconSize.width, 0.5 * data.iconSize.height),
        infoWindowAnchor: new google.maps.Point(0.5 * data.iconSize.width, 0.5 * data.iconSize.height)
    }, data);

    var icon = new google.maps.MarkerImage();
    icon = $.extend(icon, data);
    return icon;
}

