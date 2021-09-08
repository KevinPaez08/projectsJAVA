<%-- 
    Document   : index
    Created on : 2 jul. 2020, 20:06:16
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    List<Instrumento> instrumentos = new InstrumentoBD().ListadoInstrumento();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/css/bootstrap.min.css" integrity="sha384-VCmXjywReHh4PwowAiWNagnWcLhlEJLA5buUprzK8rxFgeH0kww/aWY76TfkUoSX" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js" integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J" crossorigin="anonymous"></script>
        <title>Instrumentos</title>
        <style type="text/css">
            .table-striped tbody tr:nth-of-type(odd){
                background-color: rgb(237,245,245);
            }
            .table-hover tbody tr:hover{
                background-color: #d9ebeb;
                color: rgb(112,24,78);
            }
            .thead-green{
                background-color: rgb(0, 99, 71);
                color: white;
            }
            .table th{
                text-align: center;
            }
            .table td{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1> Instrumentos</h1>
        <br>
        <div class="container show-top-margin separate-rows tall-rows">
            <div class="dropdown">
                <table class="table table-striped table-hover">
                    <thead class="thead-green">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Marca</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Stock</th>
                            <th scope="col">Descripción</th>
                            <th colspan="3"></th>
                        </tr>
                    </thead>
                    <%
                        for (Instrumento instrumento : instrumentos) {
                    %>
                    <tbody>
                        <tr>
                            <td scope="row"><%=instrumento.getIdInstru()%></td>
                            <td scope="row"><%=instrumento.getNombre()%></td>
                            <td scope="row"><%=instrumento.getMarca()%></td>
                            <td scope="row"><%=instrumento.getPrecio()%></td>
                            <td scope="row"><%= instrumento.getCantidad()%></td>
                            <td scope="row"><%=instrumento.getDescripcion()%></td>
                            
                            <td>
                                <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Opciones</button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="formularioInstrumentos.jsp?idInstru=<%=instrumento.getIdInstru()%>">Editar Instrumento</a>
                                    <a class="dropdown-item" href="eliminarInstrumento.jsp?idInstru=<%=instrumento.getIdInstru()%>"> Eliminar Instrumento</a>
                                </div>
                            </td>
                            
                            <td><a class="btn btn-success" href="ordenProductos.jsp?idInstru=<%=instrumento.getIdInstru()%>&cantidad=<%= instrumento.getCantidad()%>">Comprar</a> </td> 
                        </tr>
                    </tbody>
                    <%}%>
                </table>
            </div>
        </div>
        <br>
        <div class="container show-top-margin separate-rows tall-rows">
            <a class="btn btn-success btn-black" href="formularioInstrumentos.jsp">Agregar Instrumento </a>
            <a class="btn btn-secondary btn-black" href="catalogoMarca.jsp">Catálogo de Marcas</a>       
            <a class="btn btn-dark btn-black" href="listadoVentas.jsp">Listado de Ventas</a>       
        </div>
        <br><br><br>

    </body>
</html>
