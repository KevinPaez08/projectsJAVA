<%-- 
    Document   : eliminarUsuario2
    Created on : 24 jun. 2020, 8:40:47
    Author     : Kevin_Paez
--%>
<%@page import="Dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    boolean resul;
    UsuarioBD usuario = new UsuarioBD();
    resul = usuario.EliminarUsuario(new Usuario(Integer.parseInt(request.getParameter("id_user"))));
    if (resul) {
        response.sendRedirect("index.jsp");
    }


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
        <!--<meta http-equiv="Refresh" content="0;URL=index.jsp">-->
    </head>
    <body>

    </body>
</html>