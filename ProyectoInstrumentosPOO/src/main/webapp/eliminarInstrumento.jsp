<%-- 
    Document   : eliminarInstrumento
    Created on : 15 jul. 2020, 19:44:56
    Author     : Kevin_Paez
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>

<%
    boolean resultado;
    InstrumentoBD instrumento = new InstrumentoBD();
    resultado = instrumento.EliminarInstrumento(new Instrumento(Integer.parseInt(request.getParameter("idInstru"))));
    if (resultado) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Instrumento</title>
    </head>
    <body>
    </body>
</html>
