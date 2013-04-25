<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
<script src="https://raw.github.com/allmarkedup/jQuery-URL-Parser/master/purl.js"></script>
<script src="resources/js/login.js"></script>

</head>
<body onload='document.f.j_username.focus();'>
	<h3>Login to the Arena Cinema Max online ticketing system</h3>
 
	<div class="errorblock">
		Your login attempt was not successful, try again.
	</div>
 
	<form name='f' action="j_spring_security_check" method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td><input name="submit" type="submit"
					value="submit" />
				</td>
				<td><input name="reset" type="reset" />
				</td>
			</tr>
		</table>
 
	</form>
</body>
</html>