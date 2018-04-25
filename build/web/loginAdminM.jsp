<%-- 
    Document   : loginAdmin
    Created on : 18/04/2018, 20:38:08
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Admin</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" /> 
    </head>
    
    <body>
        <h1>Entre com o login de administrador</h1>
        <form action="LoginAdminMServlet" method="post">
            <label for="usuario">Username: </label>
            <input type="text" name="usuario"/><br/>
            <label for="senha">Senha: </label>
            <input type="password" name="senha"/><br/>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
