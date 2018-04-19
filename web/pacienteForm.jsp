<%-- 
    Document   : medicoForm
    Created on : 10/04/2018, 21:14:24
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
        <h1>Novo médico</h1>
        <hr>
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                    </c:forEach>
            </ul>
            <hr>
        </c:if>


        <form action="NovoPacienteServlet" method="post">
            Digite seus dados:<br/><br/>
            CPF: <input name="cpf" type="text" value="${sessionScope.novoPaciente.cpf}" /><br/>
            Nome: <input name="nome" type="text" value="${sessionScope.novoPaciente.nome}" /><br/>
            Senha: <input name="senha" type="password" value="${sessionScope.novoPaciente.senha}" /><br/>
            Telefone: <input name="telefone" type="text" value="${sessionScope.novoPaciente.telefone}" /><br/>
            Sexo: <input name="sexo" type="text" value="${sessionScope.novoPaciente.sexo}" /><br/>
            Data de nascimento: <input name="dataDeNascimento" type="text" value="${sessionScope.novoPaciente.dataDeNascimento}" /><br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
