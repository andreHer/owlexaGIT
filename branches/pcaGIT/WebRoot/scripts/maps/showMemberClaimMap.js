var mapObject;

$(function(){

	
    map_init();
    var base = "/INSURA/";

    mapObject.mapIcons.markerIcon = map_createIcon("red");
    mapObject.mapIcons.markerHighlightIcon = map_createIcon("orange");
    mapObject.mapIcons.directionStartIcon = map_createIcon("start");
    mapObject.mapIcons.directionEndIcon = map_createIcon("end");

    mapObject.mapIcons.providerIcon = map_createCustomIcon({
        image: base + "images/hospitals.png",
        iconSize: new GSize(24, 24),
        iconAnchor: new GPoint(5, 24),
        infoWindowAnchor: new GPoint(12, 12)
    });


    $(window).unload(function(){
        GUnload();
    });

});

function map_init() {
    //initialize Google Map
    
    
        var mapCanvas = $("#mapCanvas");
        
        if(mapCanvas.length > 0) {
	        var mapOptions = {
	          center: new google.maps.LatLng(-6.22315575712729, 106.821441650391),
	          zoom: 5,
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

            st_mapInit();
        }
    
}

function st_mapInit() {
    google.maps.event.addListener(mapObject.map, "move", st_updateMapCenter);
    google.maps.event.addListener(mapObject.map, "zoomend", st_updateMapCenter);

    st_updateMapCenter();
}

function st_updateMapCenter() {
    var center = mapObject.map.getCenter();

    var status = "Map Center: " + center.lat() + ", " + center.lng();
    status += "&nbsp;&nbsp;&nbsp;&nbsp;";
    status += "Map Zoom Level: " + mapObject.map.getZoom();

    $("#status .mapInfo").html(status);
}

function map_clearData() {
    for(var i in mapObject.dataOverlays) {
        mapObject.map.removeOverlay(mapObject.dataOverlays[i]);
    }
    mapObject.dataOverlays = [];
}

function map_loadData(loadFlag) {
    var dataLoadFlag = $.extend(mapObject.dataLoadFlag, loadFlag);
    map_clearData();

    
    map_loadProviderList(function() {
        if(dataLoadFlag.displayCctvId) {
            map_displayCctvInfo(dataLoadFlag.displayCctvId);
            delete dataLoadFlag.displayCctvId;
        }
    });
}

function map_loadClaimList(loadCallback) {
    
    $.ajax({
        url: base + "provider?navigation=loadprovidermapjson",
        dataType: "json",
        data: {
            json: 1
        },
        success: function(result) {
    
            $.each(result, function(i, provider){
                var latlng = new google.maps.LatLng(provider.latitude, provider.longitude);
                var marker = new google.maps.Marker(latlng, {
                    icon: mapObject.mapIcons.providerIcon
                });
                marker.providerId = provider.id;
                mapObject.map.addOverlay(marker);
                mapObject.dataOverlays.push(marker);

                GEvent.addListener(marker, "click", function(){
                    map_displayProviderInfo(provider.id);
                });
            });

            if(typeof loadCallback == "function") {
                loadCallback();
            }
        }
    });
}

function map_displayProviderInfo(id) {
    mapObject.map.closeInfoWindow();

    //search cctv
    for(var i in mapObject.dataOverlays) {
        if(id == mapObject.dataOverlays[i].providerId) {
            var luid = ajax_loadMessage("Loading Provider Info");
            var marker = mapObject.dataOverlays[i];

            $.ajax({
                url: base + "provider?navigation=detailmapinfo&providerId=" + id,
                dataType: "html",
                success: function(result) {
                    ajax_loadDone(luid);
                    marker.openInfoWindowHtml(result, {
                        maxWidth: 350
                    });

                    var imageRefresher;

                    var listener = GEvent.addListener(marker, "infowindowopen", function(){
                        $(".infoWindow .tabPanel").tabPanel();
                        
                        GEvent.removeListener(listener);
                    });

                    var listener2 = GEvent.addListener(marker, "infowindowclose", function() {
                        clearInterval(imageRefresher);
                        GEvent.removeListener(listener2);
                    });
                }
            });
        }
    }
}
function map_createIcon(image) {
    var icon = new GIcon();
    var base = "/INSURA/";
    icon.image = base + "img/gicon/" + image + ".png";
    icon.shadow = base + "img/gicon/shadow.png";
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

