<%-- 
    Document   : consultaForm
    Created on : 19/04/2018, 09:43:58
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendamento de consultas médicas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Nova consulta</h1>
        <hr>
        
        <form action="NovaConsultaServlet" method="post">
            CPF do Paciente: <input name="cpf" type="text" value="${sessionScope.novaConsulta.cpf}"><br/>
            CRM do Médico: <input name="crm" type="text" value="${sessionScope.novaConsulta.crm}"><br/>
            Data da Consulta: <input name="dataDoExame" type="text" value="${sessionScope.novaConsulta.dataDoExame}"><br/>
            <input type="submit" value="Enviar"/>
        
        </form>
    </body>
</html>
