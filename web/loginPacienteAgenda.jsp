<%-- 
    Document   : loginPaciente
    Created on : 18/04/2018, 23:35:21
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Paciente</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" /> 
    </head>
    
    <body>
        <h1>Entre com o login de paciente</h1>
        <form action="LoginPacienteAgendaServlet" method="post">
            <label for="cpf">CPF </label>
            <input type="text" name="cpf"/><br/>
            <label for="senha">Senha: </label>
            <input type="password" name="senha"/><br/>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
