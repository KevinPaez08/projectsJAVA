<%-- 
    Document   : eliminarUsuario
    Created on : 19 jun. 2020, 21:52:14
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int id = 0;
    if (request.getParameter("id_user") != null) {
        id = Integer.parseInt(request.getParameter("id_user")); 
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <div class="alert alert-danger" colspan="2">
            <h2>¿Estas seguro de eliminar el usuario?</h2>
        </div>
        <form action="deleteUser.jsp" class="form">
            <input type="hidden" value="<%=id%>" class="form-control" name="id" />
            <div class="form-group">
                <input type="submit" class="btn btn-danger btn-black"  value="Eliminar"/>
                <a class="btn btn-info btn-black" href="index.jsp">Cancelar</a>
            </div>
        </form>
    </body>
</html>
