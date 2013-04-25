$(document).ready(function(){
	var cinema = null;
	var selectedMovie = null;
	var cinemaMovies = null;
	
	$('#reset').bind('click', function(){reset();});
	
	$('#book').bind('click', function(){book();});
	
	$('#logout').bind('click', function(){window.location.replace("http://localhost:8080/SeatsBooking/logout");});
	
	$('#banner').attr('src', 'resources/images/spacer.jpg');
	
	$('#bannerCommingSoon').attr('src', 'resources/images/spacer.jpg');
	
	loadCinemaInformation();
	
	function book() {
		if (cinema == null) {
			alert('Please select Cinema first');
			return false;
		}
		if (!selectedMovie || selectedMovie.trim().length == 0) {
			alert('Please select Movie first');
			return false;
		}
		var selectedMovieShow = $('#movieShows').val();
		if (!selectedMovieShow || selectedMovieShow.trim().length == 0) {
			alert('Please select MovieShow first');
			return false;
		}
		var hasBookings = false;
		var bookedSeats = [];
		$('#seats td').each(function(){
		   var status = $(this).attr('class');
		   if (status == 'OCCUPIED_BY_ME_NEW') {
				hasBookings = true;
				var seatId = $(this).text();
				bookedSeats.push(seatId);
			}
		});	
		if (!hasBookings) {
			alert('Please book a seat first');
			return false;
		}
			var currentMovieShow = updateSeats(bookedSeats, selectedMovie, selectedMovieShow);
			
			$.ajax({  
			  url: "http://localhost:8080/SeatsBooking/seat/save",  
			  type: "POST",  
			  dataType: "json",  
			  contentType: "application/json",  
			  data: JSON.stringify(currentMovieShow),  
			  success: function(movieShow){    
			  	alert(movieShow.errorMessage);           
				updateCinema(movieShow);
				generateTheatreSeats(movieShow);
			  },  
			  error: function(){  
			    alert("fail :-(");  
			  }  
			});
		
	}
	
	function getCurrentMovies() {
		$.ajax({  
			  url: "http://localhost:8080/SeatsBooking/movie/" + cinema.id,  
			  type: "GET",  
			  dataType: "json",  
			  contentType: "application/json",  
			  success: function(movies){ 
				cinemaMovies = movies;
				generateMovies();
				getFutureMovies();          
			  },  
			  error: function(){  
			    alert("fail :-(");  
			  }  
			});
	}
	
	function getFutureMovies() {
		$.ajax({  
			  url: "http://localhost:8080/SeatsBooking/movie/futureMovies/" + cinema.id,  
			  type: "GET",  
			  dataType: "json",  
			  contentType: "application/json",  
			  success: function(movies){            
				generateFutureMovies(movies);
			  },  
			  error: function(){  
			    alert("fail :-(");  
			  }  
			});
	}
	
	function generateFutureMovies(movies) {
		var futureMoviesContents = '<table id="futureMoviesTable">';
		$.each(movies, function(i, movie) {
			futureMoviesContents += '<tr><td class="futureMovie" movie="'+movie.id+'">' + movie.name + '</td></tr>';
		});
		futureMoviesContents += '</table>';
		$('#futureMoviesList').html(futureMoviesContents);
		$('td.futureMovie').bind('click', function(){
			var movieId = $(this).attr('movie');
			var movie = findMovie(movieId, movies);
			generateBannerCommingSoon(movie);
		});
	}
	
	function updateCinema(movieShow) {
		var foundMovieShow = false;
		$.each(cinemaMovies, function(i, movie) {
			$.each(movie.movieShows, function(i, mShow) {
				    if (mShow.id == movieShow.id) {
						foundMovieShow = true;
						mShow.seats = movieShow.seats;
						return false;
					}
			});
			if (foundMovieShow) {
				return false;
			}
		});
	}
	
	function updateSeats(bookedSeats, selectedMoovie, selectedMovieShow) {
		var currentMovieShow = null;
		$.each(cinemaMovies, function(i, movie) {	    
			if(movie.id == selectedMoovie) {
				$.each(movie.movieShows, function(i, movieShow) {
					    if (movieShow.id == selectedMovieShow) {
							currentMovieShow = movieShow;
							$.each(movieShow.seats, function(i, seat) {
								if ($.inArray(seat.number+'', bookedSeats) >= 0) {
									seat.status = 'OCCUPIED_BY_ME_NEW';
								}
							});
						}
				});
			}
		});
		return currentMovieShow;
	}
	
	function reset() {
		$('#seats td').each(function(){
		   var status = $(this).attr('class');
		   if (status == 'OCCUPIED_BY_ME_NEW') {
				$(this).attr('class', 'FREE');
			}
		});	
	}
	
	function loadCinemaInformation() {
		$.ajax({
		    type: "GET",
		    url: "http://localhost:8080/SeatsBooking/cinema/list", 
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(c) {
			    cinema = c;
				$('.cinemaName').text(cinema.name);
				getCurrentMovies();
		    },
		    error: function(err) {
		        alert(err.toString());
			    alert('Error:' + err.responseText + '  Status: ' + err.status);
				cinema = null;
		    }
		});
	}


function generateMovies() {
	
	var futureMoviesContents = '<table id="movies">';
	$.each(cinemaMovies, function(i, movie) {
		futureMoviesContents += '<tr><td class="movie" movie="'+movie.id+'">' + movie.name + '</td></tr>';
	});
	futureMoviesContents += '</table>';
	$('#moviesList').html(futureMoviesContents);
	$('td.movie').bind('click', function(){
		selectedMovie = $(this).attr('movie');
		$('#seatsList').html('');
		generateMovieShows(selectedMovie);
	});
}

function generateBannerCommingSoon(currentMovie) {
	$('#bannerCommingSoon').attr('src', currentMovie.imageUrl);
	$('#bannerDescriptionCommingSoon').text(currentMovie.description);
}

function generateBanner(currentMovie) {
	$('#banner').attr('src', currentMovie.imageUrl);
	$('#bannerDescription').text(currentMovie.description);
}

function findMovie(selectedMovie, movies) {
	var theMovie = null;
	 $.each(movies, function(i, movie) {
		if (movie.id == selectedMovie) {
			theMovie = movie;
			return false;
		}
	 });
	return theMovie;
}

function generateMovieShows(selectedMovie) {
	var currentMovie = null;
	var selectList = "<select name='movieShows' id='movieShows'>";
	selectList += "<option value=''>Select movie show</option>";
	$.each(cinemaMovies, function(i, movie) {
		if (movie.id == selectedMovie) {
			currentMovie = movie;
			generateBanner(currentMovie);
			$.each(movie.movieShows, function(i, movieShow) {
				selectList += "<option value='"+movieShow.id+"'>" + movieShow.showDate + "</option>";
			});
			return false
		}	
	});
	
	selectList += "</select>";
	$('#movieShowsList').html(selectList);
	
	$('#movieShows').bind("change", function(){
		$('#seatsList').html('');
		var selectedMovieShow = $(this).val();
		if (selectedMovieShow.trim().length > 0) {
			generateSeats(selectedMovieShow, currentMovie);
		}
	});
}

function generateSeats(selectedMovieShow, selectedMovie) {
	var currentMovieShow = null;

	$.each(selectedMovie.movieShows, function(i, movieShow) {
		if (movieShow.id == selectedMovieShow) {
			currentMovieShow = movieShow;
			$('#currentTheatre').text(movieShow.theatreDto.name);
			return false;
		}
	});

	generateTheatreSeats(currentMovieShow);
}
	
	function generateTheatreSeats(currentMovieShow) {
		var counter = 0;
		var table = "<table id='seats' class='"+currentMovieShow.theatreDto.name+"'>";
		var hasRows = false;
		$.each(currentMovieShow.seats, function(i, seat) {
			
			if ((counter % 10) == 0) {
				if (hasRows) {
					table += '</tr>';
				}
				table += '<tr>';
				hasRows = true;
			}
			table += "<td class='"+seat.status+"'>" + seat.number + '</td>';
			counter ++;
		});
		table += '</table>';
		$('#seatsList').html(table);
		$('#seats td').bind('click', function(){
			var status = $(this).attr('class');
			if (status == 'FREE') {
				$(this).attr('class', 'OCCUPIED_BY_ME_NEW');
			} else if (status == 'OCCUPIED_BY_ME_NEW') {
				$(this).attr('class', 'FREE');
			}
		});
	}
});