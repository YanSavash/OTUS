<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>Welcome ${admin}</h1>
<p>List of users:
<table cellspacing="2" border="1" cellpadding="5" width="600" class="showTable">
    <tr>
        <td>id</td>
        <td>user</td>
        <td>age</td>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.age}</td>
    </tr>
    </#list>

</table>
</body>
</html>