var mapObject;

$(function(){

    map_init();
    map_loadProviderList();
   
       $(window).unload(function(){
        GUnload();
    });

});

function map_init() {
        var mapCanvas = $("#mapCanvas")
        
        if(mapCanvas.length > 0) {
	        var mapOptions = {
	          center: new google.maps.LatLng(10.660607953624762, 115.3125),
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
        }   
}


function map_loadProviderList() {
    $.ajax({
        url: "provider?navigation=loadprovidermapjson",
        dataType: "json",
        data: {
            json: 1
        },
        success: function(result) {
            $.each(result, function(i, provider){
                var latlng = new google.maps.LatLng(provider.latitude, provider.longitude);
            
            	var providerIcon = "images/hospitals.png";
		  		var beachMarker = new google.maps.Marker({
		      		position: latlng,
		      		map: mapObject.map,
		      		title: provider.name,
		      		icon: providerIcon
		 		});
		 	
                beachMarker.providerId = provider.id;
                
                //mapObject.map.addOverlay(beachMarker);
               // mapObject.dataOverlays.push(beachMarker);

                
            });

        }
    });
}

