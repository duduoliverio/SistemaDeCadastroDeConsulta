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
        <h1>Novo paciente</h1>
        Atenção! Deseja realmente cadastrar?
        <br/><br/>
        CPF: ${sessionScope.novoPaciente.cpf}<br/><br/>
        Nome: ${sessionScope.novoPaciente.nome}<br/><br/>
        Telefone: ${sessionScope.novoPaciente.telefone}<br/><br/>
        Sexo: ${sessionScope.novoPaciente.sexo}<br/><br/>
        Data de nascimento: ${sessionScope.novoPaciente.dataDeNascimento}<br/><br/>
        <br/>
        <a href="GravarPacienteServlet">Confirmar</a>
        <a href="pacienteForm.jsp">Modificar</a>
        <a href="index.jsp">Cancelar</a>
    </body>
</html>