<html>
<head>
    <title>Login Page</title>
</head>

<h2>Welcome page for log in:</h2>
<br><br>
<form action="j_security_check" method=post>
    <p><strong>Please enter your user name for testing jetty authentiphication: </strong>
        <input type="text" name="j_username" size="25">
    <p><p><strong>Please enter your password for testing jetty authentiphication: </strong>
    <input type="password" size="15" name="j_password">
    <p><p>
    <input type="submit" value="Log in">
    <input type="reset" value="Reset">
</form>
</html>