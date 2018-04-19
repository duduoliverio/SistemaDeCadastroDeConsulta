<%-- 
    Document   : medicoForm
    Created on : 10/04/2018, 21:14:24
    Author     : duduoliverio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.usuario != 'admin'}">
        <c:redirect url="index.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultas Médicas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Novo paciente</h1>
        <hr>
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                    </c:forEach>
            </ul>
            <hr>
        </c:if>


        <form action="NovoMedicoServlet" method="post">
            Digite seus dados:<br/><br/>
            CRM: <input name="crm" type="text" value="${sessionScope.novoMedico.crm}" /><br/>
            Nome: <input name="nome" type="text" value="${sessionScope.novoMedico.nome}" /><br/>
            Senha: <input name="senha" type="password" value="${sessionScope.novoMedico.senha}" /><br/>
            Especialidade: <input name="especialidade" type="text" value="${sessionScope.novoMedico.especialidade}" /><br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
