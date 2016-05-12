var mapObject;

$(function(){

    map_init();

    mapObject.mapIcons.markerIcon = map_createIcon("red");
    mapObject.mapIcons.markerHighlightIcon = map_createIcon("orange");
    mapObject.mapIcons.directionStartIcon = map_createIcon("start");
    mapObject.mapIcons.directionEndIcon = map_createIcon("end");

    mapObject.mapIcons.providerIcon = map_createCustomIcon({
        image:  "images/hospitals.png",
        iconSize: new GSize(24, 24),
        iconAnchor: new GPoint(5, 24),
        infoWindowAnchor: new GPoint(12, 12)
    });

        
   
    $(window).unload(function(){
        GUnload();
    });

});

function map_init() {
        var mapCanvas = $("#mapCanvas");
        var lat = document.getElementById("groupLat").value;
        var lng = document.getElementById("groupLong").value;
        var groupName = document.getElementById("groupName").value;
        
        if(mapCanvas.length > 0) {
	        var mapOptions = {
	          center: new google.maps.LatLng(lat, lng),
	          zoom: 12,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        };	        
            var map = new google.maps.Map(mapCanvas[0],mapOptions);
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
		      title: groupName,
		      icon: providerIcon
		 	});
            
        }   
}

function map_createIcon(image) {
    var icon = new GIcon();
    
    icon.image = "images/gicon/" + image + ".png";
    icon.shadow = "images/gicon/shadow.png";
    icon.iconSize = new GSize(12,20);
    icon.shadowSize = new GSize(22,20);
    icon.iconAnchor = new GPoint(6,20);
    icon.infoWindowAnchor = new GPoint(6,1);
    icon.infoShadowAnchor = new GPoint(13,13);
    return icon;
}

function map_createCustomIcon(data) {
    data = $.extend({
        iconSize: new GSize(16, 16),
    }, data);
    data = $.extend({
        iconAnchor: new GPoint(0.5 * data.iconSize.width, 0.5 * data.iconSize.height),
        infoWindowAnchor: new GPoint(0.5 * data.iconSize.width, 0.5 * data.iconSize.height)
    }, data);

    var icon = new GIcon();
    icon = $.extend(icon, data);
    return icon;
}

