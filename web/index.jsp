<%-- 
    Document   : index
    Created on : 10/04/2018, 20:17:25
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:remove scope="session" var="novoPaciente" />
<c:remove scope="session" var="novoMedico" />
<c:remove scope="session" var="novaConsulta" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultas Médicas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />        
    </head>
    <body>
        <h1>Bem-vindo ao sistema de Consultas Médicas!</h1>
        <hr>
        <c:if test="${!empty mensagem}">
            ${mensagem}
            <hr>
        </c:if>
        <p>Escolha o que deseja fazer:</p><br/>
        <a href="loginAdminM.jsp">Cadastro de médico</a><br/><br/>
        <a href="loginAdminP.jsp">Cadastro de paciente</a><br/><br/>
        <a href="VerMedicosServlet">Listar todos os médicos</a><br/><br/>
        <a href="loginPacienteAgenda.jsp">Agendar consulta</a><br/><br/>
        <a href="loginPacienteListar.jsp">Listar consulta de um paciente</a><br/><br/>
        <a href="loginMedico.jsp">Listar consulta de um médico</a><br/><br/>
        
        <div>
                <form action="VerMedicosServlet" method="GET">
                <input type="text" name="especialidade"/>
                <button type="submit">Procurar Médico por Especialidade</button>
                </form>
        </div>
        
    </body>
</html>