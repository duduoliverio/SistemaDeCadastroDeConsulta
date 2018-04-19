<%-- 
    Document   : listaConsultas
    Created on : 19/04/2018, 10:47:59
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
        <h1>Lista de consultas</h1>
        <hr>
        <c:if test="${empty requestScope.listaConsultaMedicos}">
            Não há consultas!
        </c:if>
        <c:if test="${!empty requestScope.listaConsultaMedicos}">
            <table>
                <tr>
                    <th>CRM</th>
                    <th>CPF</th>
                    <th>Data da Consulta</th>
                </tr>
                <c:forEach items="${requestScope.listaConsultaMedicos}" var="consultasm">
                    <tr>
                        <td>${consultasm.crm}</td>
                        <td>${consultasm.cpf}</td>
                        <td>${consultasm.dataDoExame}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>