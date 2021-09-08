<%-- 
    Document   : catalogoMarca
    Created on : 5 jul. 2020, 17:54:21
    Author     : Kevin_Paez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<%
    List<Catalogo> catalogo = new CatalogoBD().ListadoCatalogo();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/css/bootstrap.min.css" integrity="sha384-VCmXjywReHh4PwowAiWNagnWcLhlEJLA5buUprzK8rxFgeH0kww/aWY76TfkUoSX" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js" integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J" crossorigin="anonymous"></script>
        <title>Catálogo De Marca</title>
        <style type="text/css">
            .table th{
                text-align: center;
            }
            .table td{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Catálogo de Marcas</h1>
        <div class="container show-top-margin separate-rows tall-rows">
            <div class="dropdown">
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Marca</th>
                        <th colspan="2"></th>
                    </tr>
                </thead>
                <%
                    for (Catalogo cat : catalogo) {
                %>
                <tbody>
                    <tr>
                        <td scope="row"><%= cat.getIdMarca() %></td>
                        <td scope="row"><%= cat.getMarca() %></td>
                        
                        <td>
                            <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Opciones</button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="formularioMarcas.jsp?idMarca=<%=cat.getIdMarca()%>">Editar Marca</a>
                                    <a class="dropdown-item" href="eliminarMarca.jsp?idMarca=<%=cat.getIdMarca()%>"> Eliminar Marca</a>
                                </div>
                        </td>
                    </tr>
                </tbody>
                <%}%>
            </table>
            </div>
        </div>
        <div class="container show-top-margin separate-rows tall-rows">
            <a class="btn btn-success btn-black" href="formularioMarcas.jsp">Agregar Marca</a>
            <a class="btn btn-primary btn-black" href="index.jsp">Regresar</a> 
        </div> 
        
    </body>
</html>
