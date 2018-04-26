<%-- 
    Document   : listaMedicoTodos
    Created on : 12/04/2018, 12:44:53
    Author     : luanbatista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Médicia</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Lista de Consulta Por Paciente</h1>
        <hr>
        <c:if test="${empty requestScope.todasConsultas}">
            Não há consultas para o paciente selecionado!
        </c:if>
        <c:if test="${!empty requestScope.todasConsultas}">
            <table>
                <tr>
                    <th>CRM</th>
                    <th>CPF</th>
                    <th>Data</th>
                </tr>
                <c:forEach items="${requestScope.todasConsultas}" var="consulta">
                    <tr>
                        <td>${consulta.ref_crm}</td>
                        <td>${consulta.ref_cpf}</td>
                        <td>${consulta.dataConsulta}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>