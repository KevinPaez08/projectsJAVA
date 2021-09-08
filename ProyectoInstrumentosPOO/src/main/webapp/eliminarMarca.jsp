<%-- 
    Document   : eliminarMarca
    Created on : 15 jul. 2020, 22:35:32
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    boolean resultado;
    CatalogoBD catalogo = new CatalogoBD();
    resultado = catalogo.EliminarMarca(new Catalogo(Integer.parseInt(request.getParameter("idMarca"))));
    if (resultado) {
        response.sendRedirect("catalogoMarca.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Marca</title>
    </head>
    <body>
        
    </body>
</html>
