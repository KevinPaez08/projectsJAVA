<%-- 
    Document   : editarContra
    Created on : 19 jun. 2020, 17:06:43
    Author     : Kevin_Paez
--%>
<%@page import="Dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Cambio de contraseña</title>
    </head>
    <body>
        <br>
<%
    String clave1="", clave2="";
    String nom="";
    int id = 0;
    if (request.getParameter("password") != null && request.getParameter("id") != null) {
        id = Integer.parseInt(request.getParameter("id"));
        clave1=request.getParameter("pass1");
        clave2=request.getParameter("pass2");
        
        UsuarioBD usuario = new UsuarioBD(); 
        nom = usuario.BuscarContra(new Usuario(request.getParameter("password"), Integer.parseInt(request.getParameter("id"))));
        
        if(nom == "vacio" ){
     %>
            <div class="alert alert-danger" colspan="2">
                <h2>Contraseña Incorrecta</h2>
            </div>
            <div class="form-group">
                <a class="btn btn-info btn-black" href="index.jsp">Regresar</a>
            </div>
     
     <%      
        }else{
            if(clave1.equals(clave2)){
%>
        <div class="alert alert-info">
            <label>Hola <%=nom%> </label>
            <lebel>¿Seguro que quieres cambiar tu contraseña?</lebel>
        </div>
            <form action="cambiarContra.jsp" class="form">
                <input type="hidden" value="<%=clave1%>" class="form-control" name="password" />
                <input type="hidden" value="<%=id%>" class="form-control" name="id" />
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-black"  value="Cambiar"/>
                    <a class="btn btn-danger btn-black" href="index.jsp">Cancelar</a>
                </div>
            </form>
 <%   
                }else{
%>
        <div class="alert alert-warning">
            <lebel>OPPS la contraseña nueva no coincide <br>vuelve a intentarlo</lebel>
        </div>
        <div class="form-group">
            <a class="btn btn-info btn-black" href="index.jsp">Regresar</a>
        </div>
<%
                }
            }  
        } 
%>
    </body>
</html>
