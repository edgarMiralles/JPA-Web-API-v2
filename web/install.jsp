<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "homework1";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                // INSERTAR UNA CREDENCIAL
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'nueva_credencial', 'contraseña')",

                // INSERTAR VARIOS CLIENTES
                "INSERT INTO " + schema + ".CUSTOMER VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'Nombre Cliente 1', 'correo1@ejemplo.com', '1234')",
                "INSERT INTO " + schema + ".CUSTOMER VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'Nombre Cliente 2', 'correo2@ejemplo.com', '5678')",
                // ... puedes agregar más clientes según sea necesario
                "INSERT INTO " + schema + ".CUSTOMER VALUES (NEXT VALUE FOR CUSTOMER_GEN, 'Nombre del Cliente', 'correo@ejemplo.com', '1234')",

                // INSERTAR VARIOS JUEGOS
                "INSERT INTO " + schema + ".GAME VALUES (NEXT VALUE FOR GAME_GEN, 'Nombre del Juego 1', 10, 'Descripción del Juego 1', 'Nombre de la Calle', 'Nombre de la Ciudad', 'Estado', 'Código Postal', 1)",
                "INSERT INTO " + schema + ".GAME VALUES (NEXT VALUE FOR GAME_GEN, 'Nombre del Juego 2', 15, 'Descripción del Juego 2', 'Nombre de la Calle', 'Nombre de la Ciudad', 'Estado', 'Código Postal', 2)",
                // ... puedes agregar más juegos según sea necesario

                // INSERTAR UN ALQUILER
                "INSERT INTO " + schema + ".RENTAL VALUES (NEXT VALUE FOR RENTAL_GEN, 4, '2023-11-30T08:00:00Z', '2023-12-15T18:30:00Z', 1, 1)",

                // INSERTAR UNA CONSOLA
                "INSERT INTO " + schema + ".CONSOLE VALUES (NEXT VALUE FOR CONSOLE_GEN, 'Nombre de la Consola 1')",
                "INSERT INTO " + schema + ".CONSOLE VALUES (NEXT VALUE FOR CONSOLE_GEN, 'Nombre de la Consola 2')",
                // ... puedes agregar más consolas según sea necesario

                // INSERTAR UN TIPO DE JUEGO
                "INSERT INTO " + schema + ".GAMETYPE VALUES (NEXT VALUE FOR GAMETYPE_GEN, 'Tipo 1')",
                "INSERT INTO " + schema + ".GAMETYPE VALUES (NEXT VALUE FOR GAMETYPE_GEN, 'Tipo 2')",
                // ... puedes agregar más tipos de juego según sea necesario

                // INSERTAR UN COMENTARIO
                "INSERT INTO " + schema + ".COMMENT VALUES (NEXT VALUE FOR COMMENT_GEN, 'Comentario 1', 1)",
                "INSERT INTO " + schema + ".COMMENT VALUES (NEXT VALUE FOR COMMENT_GEN, 'Comentario 2', 2)"
                // ... puedes agregar más comentarios según sea necesario

                // INSERTAR UNA CREDENCIAL
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')"

            };

            for (String datum : data) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                   return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
