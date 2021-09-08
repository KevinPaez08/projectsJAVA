<%-- 
    Document   : index
    Created on : 1 jun. 2020, 9:09:15
    Author     : Kevin_Paez
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Connection conexion = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/usuarios ","root","");
        stmt = conexion.createStatement();
        rs = stmt.executeQuery("Select * from users");
        while(rs.next()){
            out.println(rs.getString(1));
            out.println(rs.getString(2));
        }
    }catch(SQLException sqle){ 
        //out.println("Código error: "+sqle.getErrorCode());
        //out.println("Mensaje"+sqle.getMessage());
        if(sqle.getErrorCode()==1045){
            out.println("El usuario o la contraseña son incorrectos");
        }else{
            if(sqle.getErrorCode()==1049){
                out.println("No existe la base de datos");
            }else{
                if(sqle.getErrorCode()==1146){
                    out.println("No existe la tabla");
                }else{
                    if(sqle.getErrorCode()==1064){
                        out.println("Hay un error en la consulta SQL");
                    }else{
                        if(sqle.getErrorCode()==0){
                            out.println("No hay conexión a la base de datos");
                        }
                    }
                }
            }
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba</title>
    </head>
    <body>
        <h1></h1>
    </body>
</html>
