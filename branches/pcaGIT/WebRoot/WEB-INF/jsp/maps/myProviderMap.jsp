<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvYNIDySAXxyu4Fxsxxrf7XdSy6hOw758&sensor=false" type="text/javascript"></script>

<div id="mapCanvas" class="mapCanvas" style="width:950px;height:450px;"></div>
	
<script>
	var map;
	
	function initialize() {
	  		var mapOptions = {
	    		center: new google.maps.LatLng(-6.22315575712729, 106.821441650391),
	          	zoom: 10,
	          	mapTypeId: google.maps.MapTypeId.ROADMAP
	  		};
	  	map = new google.maps.Map(document.getElementById('mapCanvas'),    mapOptions);
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
	