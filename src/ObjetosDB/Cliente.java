package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends metodosDB  
{
    private int idCliente,tipo;
    private String nombre, telefono, email,direccion,giro,rut;
    
    public Cliente(int idd, String nombre, String telefono, String email,String direccion,int tipo, Boolean saveme,String giro,String rut) throws SQLException 
    { 
        this.idCliente=idd;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion=direccion;
        this.tipo=tipo;
          this.giro=giro;
          this.rut=rut;
        //Si saveme = true entonces guardamos el cliente en la base de datos
        if(saveme)
        {
            DB_connection c = new DB_connection();
            Connection conexion = c.getConnection();
            String query = "INSERT INTO Cliente (id_cliente,nombre,telefono,email,direccion,tipo,giro,rut_cliente) VALUES (?,?,?,?,?,?,?,?)";
            int id  = new metodosDB().getLastId("cliente");
                PreparedStatement stm = conexion.prepareStatement(query);
                stm.setInt(1,id+1);
                stm.setString(2,this.nombre);
                stm.setString(3,this.telefono);
                stm.setString(4, this.email);
                stm.setString(5, this.direccion);
                     stm.setInt(6, this.tipo);
                      stm.setString(7,this.giro);
                         stm.setString(8,this.rut);
                            System.out.println(stm);

                stm.executeUpdate();
                closeConnections(c,conexion,stm,null);
            
        }
    }

    public Cliente() {
    }

   
    /*Setters y Getters*/
    public Integer getIdCliente() 
    {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente)
    {
        this.setIdCliente((int) idCliente);
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
    /*Fin Setters y Getters*/

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the giro
     */
    public String getGiro() {
        return giro;
    }

    /**
     * @param giro the giro to set
     */
    public void setGiro(String giro) {
        this.giro = giro;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }
   
   
    
}
