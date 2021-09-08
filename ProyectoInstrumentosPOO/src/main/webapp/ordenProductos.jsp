<%-- 
    Document   : ordenProductos
    Created on : 1 ago. 2020, 14:47:55
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dao.*"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="java.util.Date"%> 
<%@page import="java.text.DateFormat"%> 
<!DOCTYPE html>
<%
    int idOrden, idProducto, cantidad;
    boolean resultado;
    String nom="";
    OrdenCompraBD fecha = new OrdenCompraBD();
    resultado = fecha.pruebafecha(); 
    
    Date date = new Date();
    DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //System.out.println("Hora y fecha: "+hourdateFormat.format(date));
    
    OrdenCompraBD fechayhora = new OrdenCompraBD();
    idOrden = fechayhora.BuscarIdOrden(hourdateFormat.format(date));
    
    InstrumentoBD instrumento = new InstrumentoBD();
    nom = instrumento.BuscarInstrumento(new Instrumento(Integer.parseInt(request.getParameter("idInstru"))));
    
    idProducto = Integer.parseInt(request.getParameter("idInstru"));
    cantidad = Integer.parseInt(request.getParameter("cantidad"));
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Orden Porducto</title>
    </head>
    <body>
        <br>
        <div class="container show-top-margin separate-rows tall-rows">
            <table class="table table-striped table-hover">
                <thead class="thead-green">
                    <tr>
                        <th scope="col">IdProducto</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Disponible en Stock</th>
                        <th scope="col">Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row"><%=idProducto%></td>
                        <td scope="row"><%=nom%></td>
                        <td scope="row"><%=cantidad%></td>
                        <td scope="row">
                            <form action="ventaProducto.jsp" method="GET" class="form">
                                <input type="hidden" value="<%=idOrden%>" class="form-control" name="idOrden" />
                                <input type="hidden" value="<%=idProducto%>" class="form-control" name="idProducto" />
                                <input type="number" class="form-control" min="1" max="<%=cantidad%>" name="cantidad"> <br>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-success btn-black"  value="Guardar"/>
                                    <a class="btn btn-primary btn-black" href="index.jsp">Regresar</a>
                                </div>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
