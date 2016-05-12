var mapObject;

$(function(){

    map_init();
    var base = "/INSURA/";
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

function map_loadProviderList(loadCallback) {
    var luid = ajax_loadMessage("Loading Provider List");
    $.ajax({
        url: base + "provider?navigation=loadprovidermapjson",
        dataType: "json",
        data: {
            json: 1
        },
        success: function(result) {
            ajax_loadDone(luid);
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

                    var listener = google.maps.event.addListener(marker, "infowindowopen", function(){
                        $(".infoWindow .tabPanel").tabPanel();
                        
                        google.maps.event.removeListener(listener);
                    });

                    var listener2 = google.maps.event.addListener(marker, "infowindowclose", function() {
                        clearInterval(imageRefresher);
                        google.maps.event.removeListener(listener2);
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
    icon.iconSize = new google.maps.Size(12,20);
    icon.shadowSize = new google.maps.Size(22,20);
    icon.iconAnchor = new google.maps.Point(6,20);
    icon.infoWindowAnchor = new google.maps.Point(6,1);
    icon.infoShadowAnchor = new google.maps.Point(13,13);
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

    var icon = new GIcon();
    icon = $.extend(icon, data);
    return icon;
}

