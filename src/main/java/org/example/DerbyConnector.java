package org.example;

import java.sql.*;

public class DerbyConnector {
    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Error conectando "+e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        String uri = "jdbc:derby:MyDerbyDb;create=true";
        try {
            Connection conn = DriverManager.getConnection(uri);

            //creo tabla

          ///////// ACA AÑADO VALIDACION DE SI NO EXISTE CREAR Ó LUEGO DE LA PRIMER EJECUCIÓN COMENTO LA CREACIÓN Y ADD DE PERSONAS

            /*try{
                createTables(conn);
                System.out.println("Tabla creada");
            } catch (Exception e) {
                System.out.println("Error creando la tabla "+e.getMessage());
                e.printStackTrace();
                System.exit(2);
            }
            //creo 2 registros
            try{
                addPerson(conn,1,"Ampi",20);
                addPerson(conn,2,"Pantufla",30);
            }catch(SQLException e){
                System.out.println("Error ejecutando "+e.getMessage());
                e.printStackTrace();
                System.exit(3);
            }catch (Exception e){
                System.out.println("Error ejecutando "+e.getMessage());
                e.printStackTrace();
                System.exit(4);
            }*/

            ResultSet rs= null;
            //hago select
            try{
                String select = "SELECT * FROM persona";
                PreparedStatement ps = conn.prepareStatement(select);
                rs = ps.executeQuery();
            }catch (SQLException e){
                System.out.println("Error ejecutando "+e.getMessage());
                e.printStackTrace();
                System.exit(5);
            }catch (Exception e){
                System.out.println("Error ejecutando "+e.getMessage());
                e.printStackTrace();
                System.exit(6);
            }
            if(rs==null){
                System.out.println("No encontrado");
                System.exit(7);
            }else{
                while(rs.next()){
                    System.out.println(rs.getInt(1)+", "+rs.getString(2)+", "+rs.getInt(3)+".");
                }
            }


            //cierro conexión

            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(9);
        }

    }

    private static void addPerson(Connection conn, int id, String name, int years) throws SQLException {
        String add = "INSERT INTO persona (id, name, years) VALUES (?, ?, ?)" ;
        PreparedStatement ps = conn.prepareStatement(add);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, years);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
    private static void createTables(Connection conn) throws SQLException {
        String table = "CREATE TABLE persona("+
                "id INT,"+
                "name VARCHAR(500)," +
                "years INT," +
                "PRIMARY KEY(id))";
        conn.prepareStatement(table).execute();
        conn.commit();
    }
}
