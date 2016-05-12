<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple markers</title>
    <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
    <script>

		function initMap() {
		  var myLatLng = {lat: <c:out value="${provider.latitude}" />, lng: <c:out value="${provider.longitude}" />};
		
		  var map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 18,
		    center: myLatLng
		  });
		
		
		  var marker = new google.maps.Marker({
		    position: myLatLng,
		    map: map,
		    title: '<c:out value="${provider.providerName}" />'
		  });
		}

    </script>
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvYNIDySAXxyu4Fxsxxrf7XdSy6hOw758&signed_in=true&callback=initMap"></script>
  </body>
</html>