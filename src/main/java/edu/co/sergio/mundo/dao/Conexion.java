package edu.co.sergio.mundo.dao;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Conexion {
	
	private static Connection conexion=null;
        
        
    	public static Connection getConnection() throws URISyntaxException, ClassNotFoundException, SQLException{
         
        PreparedStatement sentencia = null;
        ResultSet rs = null;   
        boolean valido = false;
        
        try {
            
 URI dbUri = new URI(System.getenv("DATABASE_URL"));
 Class.forName("com.mysql.jdbc.Driver");
 String consulta = "select nombre from Usuarios where nombre =? and clave =?";
 sentencia = conexion.prepareStatement(consulta);
 sentencia.setString(1, dbUri.getUserInfo().split(":")[0]);
 sentencia.setString(2, dbUri.getUserInfo().split(":")[1]);
 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
 rs = sentencia.executeQuery();
 if (rs.next())
 valido = true;
 
 rs.close();
 rs = null;
 sentencia.close();
 sentencia = null;
 
} catch (ClassNotFoundException e) {
 
e.printStackTrace();
 } catch (SQLException e) {
 
e.printStackTrace();
 } finally {
 
if (rs != null) {
 try {
 rs.close();
 } catch (SQLException e) {
 //log
 }
 rs = null;
 }
 if (sentencia != null) {
 try {
 sentencia.close();
 } catch (SQLException e) {
 //log
 }
 sentencia = null;
 }
 if (conexion != null) {
 try {
 conexion.close();
 } catch (SQLException e) {
 //log
 }
 conexion = null;
 }
 }
         if(valido == true){
			  	try {

                                        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/arquitecturajava","root","blog");                        	
                                } catch (SQLException e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
				}

				
		   }
 
		   return conexion;
 
}
            
	
	
	public static void closeConnection(){
		 try {
			 if(conexion!=null){
				 conexion.close();
				 conexion=null;
			 }
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	

}
