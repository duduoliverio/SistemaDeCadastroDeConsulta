<%-- 
    Document   : confirmarPaciente
    Created on : 12/04/2018, 21:23:32
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultas Médicas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Novo paciente</h1>
        Atenção! Deseja realmente cadastrar?
        <br/><br/>
        CRM: ${sessionScope.novoMedico.crm}<br/><br/>
        Nome: ${sessionScope.novoMedico.nome}<br/><br/>
        Especialidade: ${sessionScope.novoMedico.especialidade}<br/><br/>
        <br/>
        <a href="GravarMedicoServlet">Confirmar</a>
        <a href="medicoForm.jsp">Modificar</a>
        <a href="index.jsp">Cancelar</a>
    </body>
</html>