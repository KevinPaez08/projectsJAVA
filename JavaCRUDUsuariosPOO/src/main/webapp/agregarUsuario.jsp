<%-- 
    Document   : agregarUsuaurio
    Created on : 12 jun. 2020, 9:22:07
    Author     : Kevin_Paez
--%>
<%@page import="Dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean resultado;
    UsuarioBD usuario = new UsuarioBD();
    resultado = usuario.InsertarUsuario(new Usuario(request.getParameter("usuario"), request.getParameter("password"), Integer.parseInt(request.getParameter("edad"))));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Agregar Usuario</title>
    </head>
    <body>
        <%
            if(resultado){
        %>
        <div class="alert alert-success" colspan="2">
            <h2>Se agrego exitosamente el usuario</h2>
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
           <a class="btn btn-success btn-black" href="index.jsp">Regresar</a>
        </div>                
    </body>
</html>