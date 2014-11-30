var mapFrom;
var markerFrom;
var circleFrom;
var mapTo;
var markerTo;
var circleTo;


function initMap() {
	var latlng = new google.maps.LatLng(18.5333, 73.8667);
	var myOptions = {
		zoom : 12,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	
	mapTo = new google.maps.Map(document.getElementById("map_to_search"), myOptions);
	mapFrom = new google.maps.Map(document.getElementById("map_from_search"), myOptions);

	markerFrom = new google.maps.Marker({position : latlng,	draggable : true,map : mapFrom});
	markerTo = new google.maps.Marker({position : latlng,	draggable : true,map : mapTo});

	circleFrom = new google.maps.Circle({
		position : latlng,
		center : latlng,
		strokeColor : "#FF0000",
		fillColor : "#FF3000",
		strokeWeight : 0.5,
		radius : 1000,
		map : mapFrom
	});
	
	circleTo=new google.maps.Circle({
		position : latlng,
		center : latlng,
		strokeColor : "#FF0000",
		fillColor : "#FF3000",
		strokeWeight : 0.5,
		radius : 1000,
		map : mapTo
	});

	google.maps.event.addListener(mapFrom, 'click', function(event) {
		placeMarkerFrom(event.latLng);
	});
	google.maps.event.addListener(mapTo, 'click', function(event) {
		placeMarkerTo(event.latLng);
	});
	
	// Update the hidden fields with the correct values
	document.getElementById("map_from_search_latlng").value = latlng.toString();
	document.getElementById("map_to_search_latlng").value = latlng.toString();
}

function placeMarkerFrom(location) {
	markerFrom.setMap(null);
	circleFrom.setMap(null);
	markerFrom = new google.maps.Marker({
		position : location,
		draggable : true,
		map : mapFrom
	});
	circleFrom = new google.maps.Circle({
		position : location,
		center : location,
		radius : 1000,
		map : mapFrom,
		strokeColor : "#FF0000",
		fillColor : "#FF3000",
		strokeWeight : 0.5
	});
	mapFrom.setCenter(location);
	document.getElementById("map_from_search_latlng").value = location.toString();
}

function placeMarkerTo(location) {
	markerTo.setMap(null);
	circleTo.setMap(null);
	markerTo = new google.maps.Marker({
		position : location,
		draggable : true,
		map : mapTo
	});
	circleTo = new google.maps.Circle({
		position : location,
		center : location,
		radius : 1000,
		map : mapTo,
		strokeColor : "#FF0000",
		fillColor : "#FF3000",
		strokeWeight : 0.5
	});
	mapTo.setCenter(location);
	document.getElementById("map_to_search_latlng").value = location.toString();
}
