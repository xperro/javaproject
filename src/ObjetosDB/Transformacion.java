package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transformacion extends metodosDB  
{
    private int idCliente,tipo;
    
    public Transformacion(int idd, int nombre) throws SQLException 
    { 
        this.idCliente=idd;
  
        this.tipo=nombre;
     
       
    }

    public Transformacion() {
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   
   
   
    
}
