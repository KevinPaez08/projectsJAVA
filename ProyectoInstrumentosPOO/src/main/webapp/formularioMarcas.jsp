<%-- 
    Document   : formularioMarcas
    Created on : 15 jul. 2020, 21:31:01
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    String titulo = "Agregar Marca", nom = "";
    String ruta = "agregarMarca.jsp";
    int id = 0;
    if (request.getParameter("idMarca") != null) {
        id = Integer.parseInt(request.getParameter("idMarca"));
        titulo = "Editar Marca";
        ruta = "editarMarca.jsp";
        CatalogoBD catalogo = new CatalogoBD();
        nom = catalogo.BuscarMarca(new Catalogo(Integer.parseInt(request.getParameter("idMarca"))));
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title><%=titulo%></title>
    </head>
    <body>
        <h1><%=titulo%></h1>
        <div class="container show-top-margin separate-rows tall-rows">
            <form action="<%=ruta%>" method="GET" class="form">
                <% if (id != 0) {%>
                <div>
                    <input type="hidden" value="<%=id%>" class="form-control" placeholder="id" name="id" />
                </div>
                <% }%>
                <div class="form-group">
                    <input type="text" value="<%=nom%>" class="form-control" placeholder="Nombre de la Marca" name="marca" required/>
                </div>
                <br>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-black"  value="Enviar"/>
                    <a class="btn btn-primary btn-black" href="catalogoMarca.jsp">Regresar</a>
                </div>
            </form>

        </div>
        
        
    </body>
</html>
