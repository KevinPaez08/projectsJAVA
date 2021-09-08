<%-- 
    Document   : ventaProducto
    Created on : 3 ago. 2020, 9:05:11
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    int cantidad, idProducto, idOrden;
    String nom="";
    double pre;
    idProducto = Integer.parseInt(request.getParameter("idProducto"));
    cantidad = Integer.parseInt(request.getParameter("cantidad"));
    idOrden = Integer.parseInt(request.getParameter("idOrden"));
    
    InstrumentoBD instrumento = new InstrumentoBD();
    nom = instrumento.BuscarInstrumento(new Instrumento(Integer.parseInt(request.getParameter("idProducto"))));
    
    InstrumentoBD precio = new InstrumentoBD();
    pre = precio.BuscarPrecio(new Instrumento(Integer.parseInt(request.getParameter("idProducto"))));
    double total=cantidad*pre;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Venta Producto</title>
    </head>
    <body>
        <br><br>
        <div class="container show-top-margin separate-rows tall-rows">
            <table class="table table-striped table-hover">
                <thead class="thead-green">
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">SubTotal</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row"><%=nom%></td>
                        <td scope="row"><%=pre%></td>
                        <td scope="row"><%=cantidad%></td>
                        <td scope="row"><%=pre%> * <%=cantidad%></td>
                        <td scope="row"><%=total%></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="container show-top-margin separate-rows tall-rows">
            <a class="btn btn-success btn-black" href="ventaTotal.jsp?idInstru=<%=idProducto%>&idOrden=<%=idOrden%>&nombre=<%=nom%>&precio=<%=pre%>&cantidad=<%=cantidad%>&total=<%=total%>">Guardar</a>
            <a class="btn btn-danger btn-black" href="index.jsp">Cancelar</a>       
        </div>
    </body>
</html>
