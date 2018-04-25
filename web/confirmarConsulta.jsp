<%-- 
    Document   : confirmarPaciente
    Created on : 10/04/2018, 21:23:32
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
        <h1>Novo cadastro de consulta</h1>
        Atenção! Deseja realmente cadastrar?
        <br/><br/>
        CPF: ${sessionScope.novaConsulta.ref_cpf}<br/><br/>
        CRM: ${sessionScope.novaConsulta.ref_crm}<br/><br/>
        Data da consulta: ${sessionScope.novaConsulta.dataDoExame}<br/><br/>
        <br/>
        <a href="GravarConsultaServlet">Confirmar</a>
        <a href="consultaForm.jsp">Modificar</a>
        <a href="index.jsp">Cancelar</a>
    </body>
</html>