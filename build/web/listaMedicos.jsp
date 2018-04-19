<%-- 
    Document   : listaMedicos
    Created on : 18/04/2018, 18:33:05
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultas Médicas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Lista de médicos</h1>
        <hr>
        <c:if test="${empty requestScope.listaMedicos}">
            Não há médicos!
        </c:if>
        <c:if test="${!empty requestScope.listaMedicos}">
            <table>
                <tr>
                    <th>CRM</th>
                    <th>Nome</th>
                    <th>Especialidade</th>
                </tr>
                <c:forEach items="${requestScope.listaMedicos}" var="medico">
                    <tr>
                        <td>${medico.crm}</td>
                        <td>${medico.nome}</td>
                        <td>${medico.especialidade}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>