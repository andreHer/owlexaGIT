<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- Scripts -->

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvYNIDySAXxyu4Fxsxxrf7XdSy6hOw758&sensor=false" type="text/javascript"></script>
	<script src="scripts/maps/maps.js" type="text/javascript"></script>

	<div id="mapCanvas" class="mapCanvas"></div>
	
	<script>
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 8,
	    center: new google.maps.LatLng(-34.397, 150.644)
	  };
	  map = new google.maps.Map(document.getElementById('mapCcanvas'),
	      mapOptions);
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
	