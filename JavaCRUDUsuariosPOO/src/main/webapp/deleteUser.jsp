<%-- 
    Document   : editarUsuario
    Created on : 18 jun. 2020, 23:09:30
    Author     : Kevin_Paez
--%>
<%@page import="Dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean resul;
    UsuarioBD usuario = new UsuarioBD(); 
        resul = usuario.EliminarUsuario(new Usuario(Integer.parseInt(request.getParameter("id")))); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Editar Usuario</title>
    </head>
    <body>
        <%
           if(resul){
        %>
        <div class="alert alert-success" colspan="2">
            <h2>Se borró con éxito el usuario</h2>
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
           <a class="btn btn-info btn-black" href="index.jsp">Regresar</a>
        </div>
    </body>
</html>
