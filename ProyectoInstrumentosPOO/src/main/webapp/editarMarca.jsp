<%-- 
    Document   : editarMarca
    Created on : 15 jul. 2020, 22:26:18
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    boolean resultado;
    CatalogoBD catalogo = new CatalogoBD();
    resultado = catalogo.EditarMarca(new Catalogo(Integer.parseInt(request.getParameter("id")), request.getParameter("marca")));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Editar Marca</title>
    </head>
    <body>
        <br><br>
        <div class="container show-top-margin separate-rows tall-rows">
            <%
                if(resultado){
            %>
            <div class="alert alert-success" colspan="2">
                <h2>La marca se modificó exitosamente</h2>
            </div>
            <%
                }else{
            %>
                <div class="alert alert-danger" colspan="2">
                   <h2>Ocurrio un error</h2>
                </div>
            <%
                }
            %>  
            <div>
               <a class="btn btn-success btn-black" href="catalogoMarca.jsp">Regresar</a>
            </div>
        </div>
    </body>
</html>
