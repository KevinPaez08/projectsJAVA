<%-- 
    Document   : listadoVentas
    Created on : 3 ago. 2020, 22:38:54
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    List<VentaTotal> ventas = new VentaTotalBD().ListadoVentas();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Listado de Ventas</title>
    </head>
    <body>
        <br><br>
        <div class="container show-top-margin separate-rows tall-rows">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Fecha y Hora</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <%
                    for (VentaTotal venta : ventas) {
                %>
                <tbody>
                    <tr>
                        <td scope="row"><%=venta.getIdCompra()%></td>
                        <td scope="row"><%=venta.getIdOrden()%></td>
                        <td scope="row"><%=venta.getNombre()%></td>
                        <td scope="row"><%=venta.getPrecio()%></td>
                        <td scope="row"><%=venta.getCantidad()%></td>
                        <td scope="row"><%=venta.getTotal()%></td>
                    </tr>
                </tbody>
                <%}%>
            </table>
        </div>
            <div class="container show-top-margin separate-rows tall-rows">     
            <a class="btn btn-primary btn-black" href="index.jsp">Regresar</a>       
        </div>
    </body>
</html>
