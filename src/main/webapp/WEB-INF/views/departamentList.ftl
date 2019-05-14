<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Departaments</title>
</head>
<body>
<h1>Departament list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Capacity</th>
    </tr>
    <#list users as user>
        <tr>
        <td><a href="/departament/${departament.id}">${departament.id}</a></td>
        <td>${departament.name}</td>
        <td>${departament.capacity}</td>
        <td><a href="/delete/${departament.id}">Delete</a></td>
        <td><a href="/update/${departament.id}">Update</a></td>
        </tr>
    </#list>
</table>
<a href="/addDepartament">Create new departament</a>
</html>