<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/common.css">
<script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
<script src="https://raw.github.com/douglascrockford/JSON-js/master/json2.js"></script>
<script src="resources/js/cinema.js"></script>
</head>
<body>
	<h3>Welcome to <span class="cinemaName"></span> Mobile Version<input type='button' id='logout' value='Logout'></input></h3>
	<table cellspacing="0" cellpadding="0" class="mainTable">
		<tr>
			<td>
	<div id="favoriteMovie">Choose your movie</div>
	<div id="moviesList"></div> <div id="movieShowsList"></div>	<div id="currentTheatre"></div>
	
	
	<br clear="all"/>
	<div id="availableSeats">Available seats:</div>
	<span id="seatsList"></span>
	<br clear="all"/>
	<input type='button' id='book' value='Purchase'></input><input type='button' id='reset' value='reset'></input>
	<div id="legend">
		<div id="legendFree"></div><div class="legendText">Free seats</div><div id="legendOccupied"></div><div class="legendText">Busy seats</div><div id="legendOccupiedByMe"></div><div class="legendText">My seats</div><div class="clear-both"></div>
	</div>
	</td>
	<td>
	<div id='bannerDiv'>
	<img id='banner' src=''></img>
	<div id='bannerDescription'></div>
	</div>
	</td>
	<td>
		Coming soon:
		<div id="futureMoviesList"></div>
	</td>
	<td>
	<div id='bannerDivCommingSoon'>
	<img id='bannerCommingSoon' src=''></img>
	<div id='bannerDescriptionCommingSoon'></div>
	</div>
	</td>
	</tr>
	</table>
	
</body>
</html>