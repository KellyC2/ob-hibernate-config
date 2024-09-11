package com.improvingskills.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PERSISTENCIA:
 * 1. JAVA DATABASE CONNECTIVITY
 * 2. HIBERNATE (ORM Object Realational Mapping)
 * 3. Spring Data JPA
 *
 * CRUD-Create Retrieve Update Delete
 */
public class JDBC{


    private static String SQL_QUERY="SELECT * FROM productos";
    private static final String URL_PRODUCTOS="";
    private static final String USER="";
    private static final String PASSWORD="";

    public static void main( String[] args )
    {
        Connection conexion=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try{
            //1. Crear conexion
            conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/curso-sql","root", "");

            //2. Sentencia
            statement=conexion.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM productos");

            //3. Procesar resultado
            List<Producto> productos=new ArrayList<>();
            while(resultSet.next()){
                String codigoArticulo= resultSet.getString("CÓDIGOARTÍCULO");
                String seccion=resultSet.getString("SECCIÓN");
                String nombreArticulo=resultSet.getString("NOMBREARTÍCULO");
                Double precio=resultSet.getDouble("PRECIO");
                String fecha=resultSet.getString("FECHA");
                String importado=resultSet.getString("IMPORTADO");
                String paisDeOrigen=resultSet.getString("PAÍSDEORIGEN");
                Producto producto=new Producto(codigoArticulo,nombreArticulo,seccion,precio,paisDeOrigen,fecha,importado);
                productos.add(producto);

                //System.out.println(resultSet.getString("CÓDIGOARTÍCULO")+ " " + resultSet.getString("NOMBREARTÍCULO")+ " " + resultSet.getString("PRECIO") );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (resultSet!=null) resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (statement!=null) statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                if (conexion!=null) conexion.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
