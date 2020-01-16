package ObjetosDB;

import Clases.DB_connection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*El objetivo de esta clase es hacer querys a la basde de datos retornando colecciones de datos con los objetos cargados.
La idea es cargar los datos desde la base de datos en esta clase y luego manejar los Arraylist en la clase Clases/Metodos_objetos*/
public class metodosDB 
{
    int StockCritico = 5;
    public metodosDB() //Constructor
    { 
    }
    
public ArrayList<VentaProducto> getMasVendidos(String mes, String year) throws SQLException
 {
     ArrayList<VentaProducto> vendidos = new ArrayList<VentaProducto>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM VentaProducto ORDER BY cantidad_producto DESC;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
     {
         String fecha = resultados.getString("fecha");
         String m = fecha.split("/")[1];
         String y = fecha.split("/")[2];
     

         if(y.equals(year))
         if(m.equals(mes))
         {
            /*int idVentaProducto,
             String fecha, 
             int precioUnitarioNeto, 
             int cantidadProducto,
             int precioUnitarioFinal, 
             int precioTotalNeto, 
             int precioTotalFinal, 
             int descuento, 
             Kitproductos kit, 
             Productos producto, 
             int idOrdenDeVenta,
             String kit_or_product) { */
             int id_producto = resultados.getInt("id_kit_producto");
             Productos p = this.getProductoById(id_producto);

             VentaProducto v = new VentaProducto(resultados.getInt("id_venta_producto"),
                     resultados.getString("fecha"),
                     resultados.getInt("precio_unitario_neto"),
                     resultados.getInt("cantidad_producto"),
                     resultados.getInt("precio_unitario_final"),
                     resultados.getInt("precio_total_neto"),
                     resultados.getInt("precio_total_final"),
                     resultados.getInt("descuento"),
                     null,
                     p,
                     0,
                     null);

             vendidos.add(v);
             
         }
     }
     return vendidos;
 }
/*METODOS DE UTILIDAD*/
 // <editor-fold defaultstate="collapsed" desc="Metodos de Utilidad">                          
 public int getAccesos(String tecla) throws SQLException
    {   //Retorna ArrayList con usuarios desde la base de datos
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM accesodirecto where valor=?";
        PreparedStatement stm = conexion.prepareStatement(query);
        stm.setString(1,tecla);
        int id=0;
        ResultSet resultados = stm.executeQuery();
          while(resultados.next())
        { System.out.println(id);
        id=resultados.getInt("id_producto");
        }
       
 
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
       return id;
        
    }
    public int getLastId(String nombreTabla) throws SQLException
    {   //retorna ultimo id desde la tabla cuyo nombre es : nombreTabla
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
  
        String columname = "";
        switch(nombreTabla)
        {
            case "cliente": 
                columname="id_cliente";
                break;
            case "compraproducto": 
                columname="id_compra_producto";
                break;
            case "kit_productos": 
                columname="id_kit_productos";
                break;
            case "ordendecompra": 
                columname="id_orden_compra";
                break;
            case "ordendeventa": 
                columname="id_orden_venta";
                break;
            case "productos": 
                columname="id_producto";
                break;
            case "usuarios": 
                columname="id_usuario";
                break;
            case "ventaproducto": 
                columname="id_venta_producto";
                break;
            case "promociones":
                columname = "id_promo";
                break;
            default : columname="";
                    break;
        }
        String query = "SELECT * FROM "+nombreTabla+" ORDER BY "+columname+" DESC LIMIT 1";
        PreparedStatement stm = conexion.prepareStatement(query);
        int id=1;
        ResultSet resultados = stm.executeQuery();
        if(resultados.next())
           id = resultados.getInt(columname);
        //Cerramos las conecciones y retornamos la id encontrada.
        closeConnections(c,conexion,stm,null);
        return id;
    }
    
    public void closeConnections(DB_connection c, Connection conexion, PreparedStatement stm, ResultSet resultados) 
    {   //Cierra las conecciones a la base de datos
        c = null;
        try {
            conexion.close();
            stm.close();

        } catch (SQLException ex) {System.out.println("Error Cerrando Conexiones!");}
        resultados = null;
        System.out.println("Conexiones cerradas correctamente!");
    }
    public boolean esMenorFecha(String fechaInicio, String fechaTermino)
     {  //Retorna un booleano para saber si fecha inicio es menor que fecha termino
         //Recordar que las fechas estan en formato "dia-mes-ano"
        /*Retornos de esta funcion
         fechaInicio<=fechaTermino = true
         fechaInicio>fechaTermino = false*/
         
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //Formateamos la fecha
        try {
            if((sdf.parse(fechaInicio).before(sdf.parse(fechaTermino)))||(sdf.parse(fechaInicio).equals(sdf.parse(fechaTermino))))
                return true;
            else return false;
        } catch (ParseException ex) {return false; }
        
     }
// </editor-fold> 
/*FIN METODOS DE UTILIDAD*/  
    
/*METODOS DE USUARIOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Usuarios">                          
 public int cargaUsuarioByRut(int rut) throws SQLException
    {   //Retorna ArrayList con usuarios desde la base de datos
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM usuarios where id_usuario=?";
        PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt(1,rut);
        int privilegios=0;
        ResultSet resultados = stm.executeQuery();
          while(resultados.next())
        { System.out.println(privilegios);
        privilegios=resultados.getInt("privilegios");
        }
       
 
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
       return privilegios;
        
    }
    public ArrayList<Usuarios> cargaUsuariosDB() throws SQLException
    {   //Retorna ArrayList con usuarios desde la base de datos
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM usuarios";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            Usuarios user = new Usuarios(resultados.getInt("id_usuario"),resultados.getString("nombre"),resultados.getInt("privilegios"),resultados.getString("password"));
            usuarios.add(user);
        }
  
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
       return usuarios;
        
    }
    
      public ArrayList<Usuarios> addUser(String nombre,String password,int id,int privilegios) throws SQLException
    {   //Retorna ArrayList con usuarios desde la base de datos
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
     
    String query = "INSERT INTO usuarios (id_usuario,nombre,password,privilegios) VALUES (?,?,?,?)";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1,id);
       stm.setString   (2, nombre);
      stm.setString(3, password);
             stm.setInt(4,0);
              stm.executeUpdate();
    stm.close();
    
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
     
       return usuarios;
        
    }
    public boolean updateUsers(int id, String nombre,String password) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE usuarios set  nombre= ? , password = ? where id_usuario=?";
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      
      
      stm.setString(1, nombre);
      stm.setString   (2, password);
      stm.setInt(3,id);
     
       stm.executeUpdate();
    stm.close();
    
    return true;
}
      
// </editor-fold>
/*FIN METODOS DE USUARIOS*/
    
/*METODOS DE CLIENTES*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Clientes"> 
    public Cliente getClienteById2(int id_cliente) throws SQLException
{
   Productos producto = null;
   Cliente cliente=null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM cliente WHERE id_cliente="+id_cliente;
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
         cliente = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"),resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));      
          conexion.close();
  stm.close();
         return cliente;
      }
       
    return null;
}
public Cliente addCliente(Cliente m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      
      if(this.getClienteById2(m.getIdCliente())==null){
 String query = "INSERT INTO cliente (id_cliente,nombre,telefono,email,direccion) VALUES (?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1, m.getIdCliente());
       stm.setString   (2, m.getNombre());
      stm.setString(3, m.getTelefono());
      stm.setString   (4, m.getEmail());
      
              stm.executeUpdate();
    stm.close();
return m;
}else{
    return m;
    }
    
    
}

public Cliente addCliente2(Cliente m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      
      if(this.getClienteById2(m.getIdCliente())==null){
 String query = "INSERT INTO cliente (id_cliente,nombre,telefono,email,direccion,tipo) VALUES (?,?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1, m.getIdCliente());
       stm.setString   (2, m.getNombre());
      stm.setString(3, m.getTelefono());
      stm.setString   (4, m.getEmail());
       stm.setInt   (5, 1);
              stm.executeUpdate();
    stm.close();
return m;
}else{
    return m;
    }
    
    
}
     public ArrayList<Cliente> getClientes() throws SQLException
    { //Retorna un ArrayList de clientes desde la base de datos.
        ArrayList<Cliente> clientesDB = new ArrayList<Cliente>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            Cliente cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));
            clientesDB.add(cliente);
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientesDB;
    }
     
         public boolean updateCliente(int idCliente, String nombre, String telefono, String email,String direccion,int tipo,String giro,String rut) throws SQLException{
             
   DB_connection c = new DB_connection();
            Connection conexion = c.getConnection();
            
                 String query = "UPDATE cliente set nombre = ? , telefono= ? , email = ? , direccion=?, tipo=?, giro=?,rut_cliente=? where id_cliente=?";
     
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setString(1,nombre);
       stm.setString (2, telefono);
      stm.setString(3, email);
      stm.setString(4, direccion);
       stm.setInt(5,tipo);
       stm.setString(6, giro);   stm.setString(7, rut);
      stm.setInt(8, idCliente); System.out.print(stm);
      
               stm.executeUpdate();
    stm.close();
           
             
             return true;
         }

     
     
     
     
      public Cliente getClienteById(int id_cliente) throws SQLException
    {//Retorna el cliente asociado a la orden de venta
        //Retorna un ArrayList de clientes desde la base de datos.
        Cliente cliente = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            if(resultados.getInt("id_cliente") == id_cliente)
            {
                cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));
                closeConnections(c,conexion,stm,resultados);
                return cliente;
            }
            
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return null;
    }
      
        public ArrayList<Cliente> getClientesByRut(String rut) throws SQLException
    {//Retorna el cliente asociado a la orden de venta
        //Retorna un ArrayList de clientes desde la base de datos.
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {String prove=resultados.getString("rut_cliente");
          
            if(prove.indexOf(rut) !=-1)
            {  System.out.println(prove); 
                cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));
            clientes.add(cliente);
            }
           
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientes;
    }  
        
        
        public ArrayList<Cliente> getClientesById(int rut) throws SQLException
    {//Retorna el cliente asociado a la orden de venta
        //Retorna un ArrayList de clientes desde la base de datos.
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {int prove=resultados.getInt("id_cliente");
          System.out.println("laweaes"+prove+"ybuscamo: "+rut);
            if(prove==rut)
            {  System.out.println(prove); 
                cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));
            clientes.add(cliente);
            System.out.println("laweaes"+cliente.getNombre());
            return clientes;
            }
           
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientes;
    }  
      public ArrayList<Cliente> getClientesByName(String nombre) throws SQLException
    {//Retorna el cliente asociado a la orden de venta
        //Retorna un ArrayList de clientes desde la base de datos.
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM cliente";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {String prove=resultados.getString("nombre");
          
            if(prove.indexOf(nombre) !=-1)
            {  System.out.println(prove); 
                cliente  = new Cliente(resultados.getInt("id_cliente"),resultados.getString("nombre"),resultados.getString("telefono"), resultados.getString("email"),resultados.getString("direccion"),resultados.getInt("tipo"),false,resultados.getString("giro"),resultados.getString("rut_cliente"));
            clientes.add(cliente);
            }
           
        }
        //Cerramos las conexiones a la BD. y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return clientes;
    }
// </editor-fold>
/*FIN METODOS CLIENTES*/
      
/*METODOS DE COMPRAPRODUCTO*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas CompraProducto">                          

     public ArrayList<CompraProducto> getCompraProductoEntreFechas(String fechaInicio, String fechaFinal) throws SQLException
     {//Retorna un ArrayList de comprasProducto, realizadas entre fechaInicio y fechaFinal.
      //Recordar que fecha es de formato dia/mes/ano
      ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM compraproducto";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Si la fecha de la compraProducto esta dentro del intervalo: fechaInicio<= fechaCompraProducto <= fechaFinal
          //Entonces nos interesa agregarla al ArrayList
          if((esMenorFecha(fechaInicio,resultados.getString("fecha")))&&(esMenorFecha(resultados.getString("fecha"),fechaFinal)))
          {
              //Buscamos el producto asociado desde la base de datos
              Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
              //Buscamos la orden de compra asociada desde la base de datos
              //OrdenDeCompra ordenCompra = new OrdenDeCompra().getOrdenDeCompraById(resultados.getInt("id_orden_de_compra"));
              
              //Armamos el objeto compraProducto
              CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_compra"),resultados.getInt("id_producto"));
              //Lo agregamos al ArrayList
              comprasEncontradas.add(compraProducto);
          }
      }
      //Finalmente, cerramos las conexiones a la base de datos y retornamos el ArrayList
      closeConnections(c,conexion,stm,resultados);
      return comprasEncontradas;   
     }
     public ArrayList<CompraProducto> getCompraProductoByIdOrdenCompra(int id_orden_compra) throws SQLException 
     {//Retorna un ArrayList de Compras producto segun la Orden de Compra asociada.
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM compraproducto";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
            if(resultados.getInt("id_orden_de_compra") == id_orden_compra)
            {
              //Buscamos el producto asociado desde la base de datos
              Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
              //Armamos el objeto compraProducto
              CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_de_compra"),resultados.getInt("id_producto"));
              //Lo agregamos al ArrayList
              comprasEncontradas.add(compraProducto);
            }
        }
        //Finalmente, cerramos las conexiones a la base de datos y retornamos el ArrayList
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
     public ArrayList<CompraProducto> getCompraProducto() throws SQLException
     {  //Retorna ArrayList con todas las compras producto de la base de datos
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM compraproducto";
        PreparedStatement stm = conexion.prepareStatement(query);
        ResultSet resultados = stm.executeQuery();
        while(resultados.next())
        {
           Productos producto = new Productos().getProductoById(resultados.getInt("id_producto"));
           CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_de_compra"),resultados.getInt("id_producto"));
        comprasEncontradas.add(compraProducto);
        }
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
     
  
       public ArrayList<CompraProducto> getCompraProductEntreFecha(String fecha,String fecha2) throws SQLException
     {  //Retorna ArrayList con todas las compras producto de la base de datos
         System.out.println(fecha);
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM compraproducto  WHERE fecha BETWEEN ? AND ?";
        PreparedStatement stm = conexion.prepareStatement(query);
               stm.setString(1,fecha2);
                stm.setString(2,fecha);
               System.out.println(stm);
                    System.out.println(stm);
        ResultSet resultados = stm.executeQuery();int rowcount=0;
        while(resultados.next())
        {
           CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_de_compra"),resultados.getInt("id_producto"));
       comprasEncontradas.add(compraProducto);   rowcount = resultados.getRow();
        }       System.out.println(rowcount);
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
     
         public ArrayList<CompraProducto> getCompraProductoFecha(String fecha,String fecha2) throws SQLException
     {  //Retorna ArrayList con todas las compras producto de la base de datos
         System.out.println(fecha);
        ArrayList<CompraProducto> comprasEncontradas = new ArrayList<CompraProducto>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM compraproducto where fecha between ? and ?";
        PreparedStatement stm = conexion.prepareStatement(query);
               stm.setString(1,fecha);
                    stm.setString(2,fecha2);
               System.out.println(stm);
                    System.out.println(stm);
        ResultSet resultados = stm.executeQuery();int rowcount=0;
        while(resultados.next())
        {
           CompraProducto compraProducto = new CompraProducto(resultados.getInt("id_compra_producto"),resultados.getString("fecha"),resultados.getInt("cantidad"),resultados.getInt("monto_unitario"),resultados.getInt("monto_total"),resultados.getString("proveedor"),resultados.getInt("id_orden_de_compra"),resultados.getInt("id_producto"));
       comprasEncontradas.add(compraProducto);   rowcount = resultados.getRow();
        }       System.out.println(rowcount);
        closeConnections(c,conexion,stm,resultados);
        return comprasEncontradas;
     }
// </editor-fold>
/*FIN METODOS DE COMPRAPRODUCTO*/
     
/*METODOS ORDENDECOMPRA*/
 // <editor-fold defaultstate="collapsed" desc="Metodos de Tablas OrdenDeCompra">                          

    public OrdenDeCompra getOrdenDeCompraById(int id_buscada) throws SQLException
    {
    /*Retorna un objeto OrdenDeCompra desde la base de datos, segun la id dada*/
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM OrdenDeCompra where id_orden_compra=?";
     PreparedStatement stm = conexion.prepareStatement(query);
         stm.setInt(1,id_buscada);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
     {
        
             //Buscamos el array de compras productos asociadas a esta orden de compra
             OrdenDeCompra comprasDeEstaOrden = new OrdenDeCompra(resultados.getInt("id_orden_compra"),resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),resultados.getString("tipo"),resultados.getString("fecha_emision"));
             //(int idOrdenCompra, String fecha, int montoTotal, int numeroFacturaRecibida, String proveedor, ArrayList<CompraProducto> comprasProducto) 
            // OrdenDeCompra ordenEncontrada = new OrdenDeCompra(id_buscada, resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),);
             closeConnections(c,conexion,stm,resultados);
          return comprasDeEstaOrden;
     
       
     }
     closeConnections(c,conexion,stm,resultados);
    return null;
    }
      
     public ArrayList<OrdenDeCompra> getOrdenDeCompra() throws SQLException
     { //Retorna un ArrayList con todas las ordenes de compra en la BD
      ArrayList<OrdenDeCompra> ordenesEncontradas = new ArrayList<OrdenDeCompra>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM OrdenDeCompra";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Buscamos el array de compras productos asociadas a esta orden de compra
          ArrayList<CompraProducto> comprasDeEstaOrden = new metodosDB().getCompraProductoByIdOrdenCompra(resultados.getInt("id_orden_compra"));
         // OrdenDeCompra ordenEncontrada = new OrdenDeCompra(resultados.getInt("id_orden_compra"), resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),comprasDeEstaOrden);
         // ordenesEncontradas.add(ordenEncontrada);  
      }
      closeConnections(c,conexion,stm,resultados);
      return ordenesEncontradas;
     }
// </editor-fold>
/*FIN METODOS ORDENDECOMPRA*/

/*METODOS VENTA PRODUCTO*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas VentaProducto">                          

     public ArrayList<VentaProducto> getVentaProductoByIdOrdenVenta(int id_orden_venta) throws SQLException 
     {      //Retorna un arraylist con ventasproducto segun la orden de venta dada.
        ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
        VentaProducto ventaEncontrada = null;
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ventaproducto WHERE id_orden_de_venta = ?";
        PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt(1,id_orden_venta);
        ResultSet resultados = stm.executeQuery();int i =0;
        while(resultados.next())
        {i++;
          Productos producto= this.getProductoByIdCompras(resultados.getInt("id_producto"));
                producto.setId_producto(resultados.getInt("id_producto"));//get producto by id;
                 ventaEncontrada = new VentaProducto(resultados.getInt("id_venta_producto"),resultados.getString("fecha"),resultados.getInt("precio_unitario_neto"),resultados.getInt("cantidad_producto"),resultados.getInt("precio_unitario_final"),resultados.getInt("precio_total_neto"),resultados.getInt("precio_total_final"),resultados.getInt("descuento"),null,producto,resultados.getInt("id_orden_de_venta"),"producto");


                
            ventasAsociadas.add(ventaEncontrada);
        }System.out.println("SON HARTOS: "+i);
         closeConnections(c,conexion,stm,resultados);
         return ventasAsociadas;
     }
     
//</editor-fold>
/*FIN METODOS VENTAPRODUCTO*/
     
/*METODOS ORDENDEVENTA*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas OrdenDeVenta">                          

     public ArrayList<OrdenDeVenta> getVentaClienteById(int id_cliente) throws SQLException
{
   Productos producto = null;
   Cliente cliente=null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      ArrayList<OrdenDeVenta> ordenesEncontradas=null;
        ordenesEncontradas = new ArrayList<OrdenDeVenta>();
       
      String query = "SELECT * FROM ordendeventa WHERE id_cliente="+id_cliente;
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      
      while(resultados.next())
          
      {   
          
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,null,null,resultados.getInt("descuento"));
          ordenesEncontradas.add(orden);
  
       
      }
               conexion.close();
  stm.close();
   return ordenesEncontradas;
}
       public OrdenDeVenta getOrdenDeVentabyID(int id) throws SQLException
     {//Retorna un ArrayList con todas las ordenes de vena en la BD
      ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
      ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM ordendeventa where numero_boleta=?";
      PreparedStatement stm = conexion.prepareStatement(query);
       stm.setInt(1,id);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          
          //Buscamos las ventas producto asociadas a esta orden de venta
          //Armamos el objeto OrdenDeVenta
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,null,null,resultados.getInt("descuento"));
          return orden;
      }
      closeConnections(c,conexion,stm,resultados);
      return null;
     }
   
     public ArrayList<OrdenDeVenta> getOrdenDeVenta() throws SQLException
     {//Retorna un ArrayList con todas las ordenes de vena en la BD
      ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
      ArrayList<VentaProducto> ventasAsociadas = new ArrayList<VentaProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM ordendeventa";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          //Buscamos el cliente asociado a esta orden de venta
          Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
          //Buscamos las ventas producto asociadas a esta orden de venta
          ventasAsociadas = getVentaProductoByIdOrdenVenta(resultados.getInt("id_orden_venta"));
          //Armamos el objeto OrdenDeVenta
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,null,null,resultados.getInt("descuento"));
          ordenesEncontradas.add(orden);
      }
      closeConnections(c,conexion,stm,resultados);
      return ordenesEncontradas;
     }
   
     public ArrayList<OrdenDeVenta> getVentasDia(String fecha,String end) throws SQLException
     {
        ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ordendeventa WHERE fecha BETWEEN ? AND ?";
        PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha);
             stm.setString(2, end);
            ResultSet resultados = stm.executeQuery();System.out.println(stm);
            while(resultados.next())
            {
              Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,cliente,null,resultados.getInt("descuento"));
           
          System.out.println(cliente.getNombre());
          ordenesEncontradas.add(orden);
            }
                        closeConnections(c,conexion,stm,resultados);

        return ordenesEncontradas;
     }
     public ArrayList<OrdenDeVenta> getVentasEntreDia(String fecha,String fecha2) throws SQLException
     {
        ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ordendeventa WHERE fecha BETWEEN ? AND ?";
        PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha2);
               stm.setString(2, fecha);
            ResultSet resultados = stm.executeQuery();System.out.println(stm);
            while(resultados.next())
            {
              Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,cliente,null,resultados.getInt("descuento"));
              ordenesEncontradas.add(orden);
            }
                        closeConnections(c,conexion,stm,resultados);

        return ordenesEncontradas;
     }
     
     public ArrayList<OrdenDeCompra> getComprasDia(String fecha,String fecha2) throws SQLException
     {
        ArrayList<OrdenDeCompra> ordenesEncontradas = new ArrayList<OrdenDeCompra>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ordendecompra WHERE fecha between ? and ?";
        PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha);
              stm.setString(2, fecha2);
            ResultSet resultados = stm.executeQuery();System.out.println(stm);
            while(resultados.next())
            {
              
          OrdenDeCompra orden  = new OrdenDeCompra(resultados.getInt("id_orden_compra"),resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),resultados.getString("tipo"),resultados.getString("fecha_emision"));
              ordenesEncontradas.add(orden);
            }
                        closeConnections(c,conexion,stm,resultados);

        return ordenesEncontradas;
     }
     
     public ArrayList<OrdenDeCompra> getComprasEntreDia(String fecha,String fecha2) throws SQLException
     {
        ArrayList<OrdenDeCompra> ordenesEncontradas = new ArrayList<OrdenDeCompra>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ordendecompra where fecha BETWEEN ? AND ?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha);
               stm.setString(2, fecha2);
            ResultSet resultados = stm.executeQuery();System.out.println(stm);
            while(resultados.next())
            {
              
          OrdenDeCompra orden  = new OrdenDeCompra(resultados.getInt("id_orden_compra"),resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),resultados.getString("tipo"),resultados.getString("fecha_emision"));
              
        
             ordenesEncontradas.add(orden); 
          
          
          
            }
                        closeConnections(c,conexion,stm,resultados);

        return ordenesEncontradas;
     }
     
     
     public ArrayList<OrdenDeVenta> getCotizacionesDia(String fecha) throws SQLException
     {
        ArrayList<OrdenDeVenta> ordenesEncontradas = new ArrayList<OrdenDeVenta>();
        DB_connection c = new DB_connection();
        Connection conexion = c.getConnection();
        String query = "SELECT * FROM ordendeventa WHERE fecha = ? and estado_presupuesto=1";
        PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, fecha);
            ResultSet resultados = stm.executeQuery();
            while(resultados.next())
            {
              Cliente cliente = getClienteById(resultados.getInt("id_cliente"));
          OrdenDeVenta orden  = new OrdenDeVenta(resultados.getInt("id_orden_venta"),resultados.getString("fecha"),resultados.getString("monto_total"),resultados.getInt("numero_boleta"),resultados.getString("medio_pago"),0,null,null,resultados.getInt("descuento"));
              ordenesEncontradas.add(orden);
            }
                        closeConnections(c,conexion,stm,resultados);

        return ordenesEncontradas;
     }
      public void nuevaVentasDiaria(OrdenDeVenta orden,int rut) throws SQLException
     {//Retorna un arraylist con las ventas de la fecha indicada
          
            DB_connection c = new DB_connection();
            Connection conexion = c.getConnection();
            
             String query = "INSERT INTO ordendeventa (id_orden_venta,fecha,monto_total,numero_boleta,medio_pago,estado_presupuesto,id_cliente) VALUES (?,?,?,?,?,?,?);";    
     
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1,orden.getIdOrdenVenta());
       stm.setString (2, orden.getFecha());
      stm.setString(3, orden.getMontoTotal());
      stm.setInt(4, orden.getIdOrdenVenta());
       stm.setString(5,orden.getMedioPago());
       stm.setInt(6, orden.getEstadoPresupuesto());
      stm.setInt(7, rut); System.out.print(stm);
      
               stm.executeUpdate();
    stm.close();
           
          
     }
      
      
      
      public void addOrdenVenta(OrdenDeVenta o) throws SQLException
      {
          DB_connection c = new DB_connection();
          Connection conexion = c.getConnection();

          String query = "INSERT INTO ordendeventa (id_orden_venta,fecha,monto_total,numero_boleta,medio_pago,estado_presupuesto,id_cliente,descuento) VALUES (?,?,?,?,?,?,?,?);";
          PreparedStatement stm = conexion.prepareStatement(query);
          stm.setInt(1, getLastId("ordendeventa")+1);
          stm.setString(2, o.getFecha());
          stm.setString(3,o.getMontoTotal());
          stm.setInt(4,o.getNumeroBoleta());
          stm.setString(5, o.getMedioPago());
          stm.setInt(6, o.getEstadoPresupuesto());
          stm.setInt(7, 3);
            stm.setInt(8, o.getDescuento());
          System.out.println("\n **addOrdenventa : "+stm);
          stm.executeUpdate();
          System.out.println("asddedordenventa");
          closeConnections(c,conexion,stm,null);
                    addVentaProducto(o.getVentasProducto(),o);


      }
      public void addVentaProducto(ArrayList<VentaProducto> ventas, OrdenDeVenta o) throws SQLException
      { //Para ventas de un producto
          DB_connection c = new DB_connection();
          Connection conexion = c.getConnection();
          String query = "INSERT INTO ventaproducto (id_venta_producto,id_orden_de_venta,id_producto,fecha,precio_unitario_neto,cantidad_producto,precio_unitario_final,precio_total_neto,precio_total_final,descuento) VALUES(?,?,?,?,?,?,?,?,?,?);";
          PreparedStatement stm = conexion.prepareStatement(query);

          for(VentaProducto v: ventas)
          {
              
              stm.setInt(1, getLastId("ventaproducto")+1);
              stm.setInt(2, o.getIdOrdenVenta()+1);
              stm.setInt(3, v.getIdProducto());
              stm.setString(4, v.getFecha());
              stm.setInt(5, v.getPrecioUnitarioNeto());
              stm.setInt(6,v.getCantidadProducto());
              stm.setInt(7, v.getPrecioUnitarioFinal());
              stm.setInt(8,v.getPrecioTotalNeto());
              stm.setInt(9,v.getPrecioTotalFinal());
              stm.setInt(10,v.getDescuento());
              System.out.println("\n addventa : "+stm);
              stm.executeUpdate();
              System.out.println("asdded venta producta");
          }
            closeConnections(c,conexion,stm,null);
      }
//</editor-fold>
/*FIN METODOS ORDENDEVENTA*/

/*METODOS PRODUCTOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Productos">
     public ArrayList<Productos> getProductoByNombre1() throws SQLException
{
    ArrayList<Productos> aux2 = new ArrayList<Productos>();
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos where disponible=0";
      System.out.println(query);
      PreparedStatement stm = conexion.prepareStatement(query);
     
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"), resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
              producto.setImagen(resultados.getString("imagen"));

        aux2.add(producto);
      }
        conexion.close();
         stm.close();
    return aux2;
}
     
public ArrayList<Productos> getProductos() throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos where disponible=0";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        Productos product = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
                       product.setImagen(resultados.getString("imagen"));

        productos.add(product);

      }
      closeConnections(c,conexion,stm,resultados);
    return productos;
}



public ArrayList<Productos> getProductosStockCritico() throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos where disponible=0";
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          if(resultados.getInt("cantidad_actual")<=resultados.getInt("critico"))
          { Productos product = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
                             product.setImagen(resultados.getString("imagen"));
  
          productos.add(product);

          }
      }
      closeConnections(c,conexion,stm,resultados);
    return productos;
}
 public boolean getCod(String cod) throws SQLException
{
    ArrayList<Productos> aux2 = new ArrayList<Productos>();
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos where codigo_barra=? and disponible=0";
      
      PreparedStatement stm = conexion.prepareStatement(query);
      stm.setString   (1, cod);

ResultSet resultados=stm.executeQuery();
      while(resultados.next())
      {
       return true;
      }
      
        conexion.close();
         stm.close();
    return false;
}
public boolean addProductos(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO productos (id_producto,codigo_barra,nombre,marca,talla,color,precio_compra,precio_venta,proveedor,cantidad_actual,tipo, imagen,harina,medida) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      if(this.getCod(m.getCodigo_barra())==false){
       stm.setInt(1,new metodosDB().getLastId("productos")+1);
       stm.setString   (3, m.getNombre());
      stm.setString(4, m.getMarca());
      stm.setString   (5, m.getTalla());
      stm.setString(6,m.getColor());
      stm.setInt   (7, m.getPrecioCompra());
      stm.setInt(8, m.getPrecioVenta());
      stm.setString   (9, m.getProveedor());
      stm.setInt(10, m.getCantidadActual());
          stm.setString   (11, m.getTipo());
              stm.setString   (2, m.getCodigo_barra());
               stm.setInt(13, m.getHarina());
                   stm.setString(14, m.getMedida());
       stm.setString(12,m.getImagen());
       System.out.print(stm);
              stm.executeUpdate();
    stm.close();}else{
          return false;
      }
    
    return true;
}


public int addProductos2(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO productos (id_producto,codigo_barra,nombre,marca,talla,color,precio_compra,precio_venta,proveedor,cantidad_actual,tipo, imagen,harina,medida,critico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";    
      int id=0;
      PreparedStatement stm = conexion.prepareStatement(query);
  
          
        id=   new metodosDB().getLastId("productos")+1;
       stm.setInt(1,id);
       stm.setString   (3, m.getNombre());
      stm.setString(4, m.getMarca());
      stm.setString   (5, m.getTalla());
      stm.setString(6,m.getColor());
      stm.setInt   (7, m.getPrecioCompra());
      stm.setInt(8, m.getPrecioVenta());
      stm.setString   (9, m.getProveedor());
      stm.setInt(10, m.getCantidadActual());
          stm.setString   (11, m.getTipo());
              stm.setString   (2, m.getCodigo_barra());
               stm.setInt(13, m.getHarina());
                   stm.setString(14, m.getMedida());
                       stm.setInt(15, m.getCritico());
       stm.setString(12,m.getImagen());
       System.out.print(stm);
              stm.executeUpdate();
    stm.close();
    
    return id;
}
public boolean updateProductos(Productos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE productos set nombre = ? , marca= ? , talla = ? , color=?, precio_compra=?, precio_venta=?, proveedor=?,cantidad_actual=?,tipo=?,codigo_barra=?,harina=?,medida=?,critico=? where id_producto=?";
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      
       stm.setString   (1, m.getNombre());
      stm.setString(2, m.getMarca());
      stm.setString   (3, m.getTalla());
      stm.setString(4,m.getColor());
      stm.setInt   (5, m.getPrecioCompra());
      stm.setInt(6, m.getPrecioVenta());
      stm.setString   (7, m.getProveedor());
      stm.setInt(8, m.getCantidadActual());
          stm.setString   (9, m.getTipo());
              stm.setString   (10, m.getCodigo_barra());
                  stm.setInt(11, m.getHarina());
                      stm.setString(12, m.getMedida());
                          stm.setInt(13, m.getCritico());
               stm.setInt(14, m.getId_producto());
       stm.executeUpdate();
    stm.close();
    
    return true;
}

public boolean updateTransformaciones(Transformacion m) throws SQLException
{
System.out.println("CLIENTE:"+ m.getIdCliente());
System.out.println("GR:"+ m.getTipo());
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query2=  "DELETE FROM transformacion WHERE id_producto=?";
      
        PreparedStatement stm2 = conexion.prepareStatement(query2);
      
      
       stm2.setInt   (1, m.getIdCliente());
      
       stm2.executeUpdate();
    stm2.close();
      
      
      
      
      String query = "INSERT INTO transformacion (id_producto,gr) VALUES (?,?);";    
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      
       stm.setInt   (1, m.getIdCliente());
      stm.setInt(2, m.getTipo());
      
       stm.executeUpdate();
    stm.close();
    
    return true;
}
public boolean updateOrdenesCompra(ArrayList<OrdenDeCompra> m) throws SQLException
{
     ArrayList<OrdenDeCompra> productos = new ArrayList<OrdenDeCompra>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE ordendecompra set  monto_total= ? , proveedor=?, numero_factura_recibida=?,tipo=? where id_orden_compra=?";
    
      PreparedStatement stm = conexion.prepareStatement(query);
      int i=0;
      while(i<m.size()){
      
      
      
     //  stm.setString   (1, m.get(i).getNombre());
      stm.setInt(1, m.get(i).getMontoTotal());
      stm.setString   (2, m.get(i).getProveedor());
      stm.setInt(3, m.get(i).getNumeroFacturaRecibida());
      stm.setString(4, m.get(i).getTipo());
               stm.setInt(5, m.get(i).getIdOrdenCompra());
       stm.executeUpdate();
     i++;
   
     } stm.close();
    return true;
}

public boolean updateCompraProducto(ArrayList<CompraProducto> m) throws SQLException
{
     ArrayList<CompraProducto> productos = new ArrayList<CompraProducto>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE compraproducto set  monto_total= ? , proveedor=?, fecha=?,cantidad=?,monto_unitario=? where id_compra_producto=?";
    
      PreparedStatement stm = conexion.prepareStatement(query);
      int i=0;
      while(i<m.size()){
      
      
      
     //  stm.setString   (1, m.get(i).getNombre());
      stm.setInt(1, m.get(i).getMontoTotal());
      stm.setString   (2, m.get(i).getProveedor());
      stm.setString(3, m.get(i).getFecha());
      stm.setInt(4, m.get(i).getCantidad());
               stm.setInt(5, m.get(i).getMontoUnitario());
                               stm.setInt(6, m.get(i).getIdCompraProducto());
       stm.executeUpdate();
     i++;
   
     } stm.close();
    return true;
}
public boolean updateProductos2(ArrayList<Productos> m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE productos set  marca= ? , precio_compra=?, precio_venta=?,cantidad_actual=?,critico=? where id_producto=?";
    
      PreparedStatement stm = conexion.prepareStatement(query);
      int i=0;
      while(i<m.size()){
      
      
      
     //  stm.setString   (1, m.get(i).getNombre());
      stm.setString(1, m.get(i).getMarca());
      stm.setInt   (2, m.get(i).getPrecioCompra());
      stm.setInt(3, m.get(i).getPrecioVenta());
      stm.setInt(4, m.get(i).getCantidadActual());
      stm.setInt(5, m.get(i).getCritico());
               stm.setInt(6, m.get(i).getId_producto());
                  
       stm.executeUpdate();
     i++;
   
     } stm.close();
    return true;
}
public boolean DeleteProductoById(int id_producto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
     
      String query = "UPDATE productos set disponible=? where id_producto=?";
    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
      System.out.println(id_producto);
       stm.setInt   (1, 1);
stm.setInt   (2, id_producto);
     stm.executeUpdate();
       conexion.close();
  stm.close();
    return true;
}

public Transformacion gettransById(int id_producto) throws SQLException
{
   Transformacion trans = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM transformacion WHERE id_producto=?";
      PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt   (1, id_producto);
      ResultSet resultados = stm.executeQuery();
      System.out.println(stm);
      while(resultados.next())
      {
        trans = new Transformacion(resultados.getInt("id_producto"),resultados.getInt("gr")  );
      }
        conexion.close();
  stm.close();
    return trans;
}




public Productos getProductoById(int id_producto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos WHERE id_producto=? and disponible=0";
      PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt   (1, id_producto);
      ResultSet resultados = stm.executeQuery();
      System.out.println(stm);
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre")
                ,resultados.getString("marca"),resultados.getString("talla")
                ,resultados.getString("color"),resultados.getInt("precio_compra")
                ,resultados.getInt("precio_venta"),resultados.getString("proveedor")
                ,resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
       producto.setImagen(resultados.getString("imagen"));
      }
        conexion.close();
  stm.close();
    return producto;
}

public Productos getProductoByIdCompras(int id_producto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos WHERE id_producto=?";
      PreparedStatement stm = conexion.prepareStatement(query);
        stm.setInt   (1, id_producto);
      ResultSet resultados = stm.executeQuery();
      System.out.println(stm);
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre")
                ,resultados.getString("marca"),resultados.getString("talla")
                ,resultados.getString("color"),resultados.getInt("precio_compra")
                ,resultados.getInt("precio_venta"),resultados.getString("proveedor")
                ,resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
       producto.setImagen(resultados.getString("imagen"));
      }
        conexion.close();
  stm.close();
    return producto;
}
public Productos getProductoByCodigo(String codigo) throws SQLException
{
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos WHERE codigo_barra=? and disponible=0";
  
      PreparedStatement stm = conexion.prepareStatement(query);
      stm.setString(1,codigo);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
              producto.setImagen(resultados.getString("imagen"));

      }
        conexion.close();
         stm.close();
    return producto;
}

public Productos getProductoByNombre(String nombre) throws SQLException
{
Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "SELECT * FROM productos WHERE nombre= ? and disponible=0";
      System.out.println(query);
      PreparedStatement stm = conexion.prepareStatement(query);
      stm.setString(1,nombre);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        producto = new Productos(resultados.getInt("id_producto"),resultados.getString("nombre"),resultados.getString("marca"),resultados.getString("talla"),resultados.getString("color"),resultados.getInt("precio_compra"),resultados.getInt("precio_venta"),resultados.getString("proveedor"),resultados.getInt("cantidad_actual"),resultados.getString("tipo"),resultados.getString("codigo_barra"),resultados.getInt("harina"),resultados.getString("medida"),resultados.getInt("critico"));      
              producto.setImagen(resultados.getString("imagen"));

      }
        conexion.close();
         stm.close();
    return producto;
}
public ArrayList<Productos> getProductosByIdCompraProducto(int id_compra) throws SQLException
{
 return null;
}

// </editor-fold>

/*FIN METODOS PRODUCTOS*/

   /*METODOS KITPRODUCTOS*/
// <editor-fold defaultstate="collapsed" desc="Metodos de Tablas Kit_productos">
public boolean updatekitproductos(Kitproductos m) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      String query = "UPDATE Kit_productos set nombre_kit = ? , precio_compra_productos= ? , precio_venta_kit = ? , descripcion_kit=? where id_kit_productos=?";
    
       String query2 = "DELETE FROM relacion_kit_productos WHERE id_kit_productos=?";
      PreparedStatement stm = conexion.prepareStatement(query);
       PreparedStatement stm2 = conexion.prepareStatement(query2);
      
       stm.setString   (1, m.getNombreKit());
      stm.setInt(2, m.getPrecioCompraProductos());
      stm.setInt   (3, m.getPrecioVentaKit());
      stm.setString(4,m.getDescripcionKit());
      stm.setInt   (5, m.getIdKitProductos());
       stm2.setInt   (1, m.getIdKitProductos());
       stm.executeUpdate();
        stm2.executeUpdate();
    stm.close();
    
    return true;
}
public int addKitProductos(Kitproductos kit) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO Kit_productos (id_kit_productos,nombre_kit,precio_compra_productos,precio_venta_kit,descripcion_kit) VALUES (?,?,?,?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      kit.setIdKitProductos(new metodosDB().getLastId("Kit_productos")+1);
       stm.setInt(1,kit.getIdKitProductos());
       stm.setString   (2, kit.getNombreKit());
      stm.setInt(3, kit.getPrecioCompraProductos());
      stm.setInt   (4, kit.getPrecioVentaKit());
       stm.setString   (5, kit.getDescripcionKit());
              stm.executeUpdate();
           
    stm.close();
    
    return kit.getIdKitProductos();
}

public boolean addKit_relacion_productos(int id_kit_productos,int id_producto) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "INSERT INTO relacion_kit_productos (id_kit_productos,id_producto) VALUES (?,?);";    
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt(1,id_kit_productos);
       stm.setInt   (2, id_producto);
   
              stm.executeUpdate();
    stm.close();
    
    return true;
}
public ArrayList<Kitproductos> getKitproductos() throws SQLException
{
     ArrayList<Kitproductos> kitproductos = new ArrayList<Kitproductos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM Kit_productos";    
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        Kitproductos kitproduct = new Kitproductos(resultados.getInt("id_kit_productos"),resultados.getString("nombre_kit"),resultados.getInt("precio_compra_productos"),resultados.getInt("precio_venta_kit"),resultados.getString("descripcion_kit"));      
        kitproductos.add(kitproduct);
      }
      closeConnections(c,conexion,stm,resultados);
      System.out.println("Kit Encontrados: !\n"+kitproductos.size());

    return kitproductos;
}
public Kitproductos getKitProductoByName( String name) throws SQLException
{
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM Kit_productos";    
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      Kitproductos kitproduct = null;
      while(resultados.next())
      {
        if(resultados.getString("nombre_kit").equals(name))
        {
                     kitproduct = new Kitproductos(resultados.getInt("id_kit_productos"),resultados.getString("nombre_kit"),resultados.getInt("precio_compra_productos"),resultados.getInt("precio_venta_kit"),resultados.getString("descripcion_kit"));      

        }      
          
      }
      closeConnections(c,conexion,stm,resultados);
      return kitproduct;

}
public ArrayList<Kitproductos> getKitsProductoByName( String name) throws SQLException
{
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
      ArrayList<Kitproductos> kits = new ArrayList<Kitproductos>(); 
 String query = "SELECT * FROM Kit_productos";    
      PreparedStatement stm = conexion.prepareStatement(query);
      ResultSet resultados = stm.executeQuery();
      Kitproductos kitproduct = null;
      String var="";
      while(resultados.next())
      {
       
                     kitproduct = new Kitproductos(resultados.getInt("id_kit_productos"),resultados.getString("nombre_kit"),resultados.getInt("precio_compra_productos"),resultados.getInt("precio_venta_kit"),resultados.getString("descripcion_kit"));      

                     kits.add(kitproduct);
                     
                     
          
          
      }
      closeConnections(c,conexion,stm,resultados);
      return kits;

}
public ArrayList<Productos> getrelacionKitproductos(int id_kit_productos) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM relacion_kit_productos where id_kit_productos=?";    
      PreparedStatement stm = conexion.prepareStatement(query);
       stm.setInt(1,id_kit_productos);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
        productos.add(this.getProductoById(resultados.getInt("id_producto")));
        
      }
      closeConnections(c,conexion,stm,resultados);
    return productos;
}
public int getIDKitproductos(int id_producto) throws SQLException
{
     ArrayList<Productos> productos = new ArrayList<Productos>();
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
 String query = "SELECT * FROM relacion_kit_productos where id_producto=?";    
      PreparedStatement stm = conexion.prepareStatement(query);
       stm.setInt(1,id_producto);
      ResultSet resultados = stm.executeQuery();
      while(resultados.next())
      {
          int var=resultados.getInt("id_kit_productos");
        productos.add(this.getProductoById(resultados.getInt("id_producto")));
         stm.close();
        return var;
      }
      closeConnections(c,conexion,stm,resultados);
       stm.close();
    return -1;
}
public boolean deleteKit(Kitproductos kitproducto) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
     
      String query = "DELETE FROM Kit_productos WHERE id_kit_productos=?";
     String query2 = "DELETE FROM relacion_kit_productos WHERE id_kit_productos=?";
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
    PreparedStatement stm2 = conexion.prepareStatement(query2);
       stm.setInt   (1, kitproducto.getIdKitProductos());
 stm2.setInt   (1, kitproducto.getIdKitProductos());
     stm.executeUpdate();
     stm2.executeUpdate();
  conexion.close();
  stm.close();
  stm2.close();
    return true;
}
public boolean deleteProductsKit(int id_producto, int id_kit) throws SQLException
{
   Productos producto = null;
      DB_connection c = new DB_connection();
      Connection conexion = c.getConnection();
     
      String query = "DELETE FROM Kit_productos WHERE id_kit_productos=?";
      
      PreparedStatement stm = conexion.prepareStatement(query);
      
       stm.setInt   (1, id_kit);
     stm.executeUpdate();
        String query2 = "DELETE FROM relacion_kit_productos WHERE id_kit_productos=?";
      
      PreparedStatement stm2 = conexion.prepareStatement(query2);
      
       stm2.setInt   (1, id_kit);
        stm2.executeUpdate();
  conexion.close();
  stm.close();
  stm2.close();
    return true;
}
/*FIN METODOS KITPRODUCTOS*/ 

/*FIN METODOS KITPRODUCTOS*/   
/**METODOS PROMOCIONES/
 *
 */
 public ArrayList<Promociones> getPromociones() throws SQLException
 {
     ArrayList<Promociones> promos = new ArrayList<Promociones>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM promociones ORDER BY id_promo;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {
            Promociones prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getInt("id_producto"),resultados.getInt("cantidad"));
            promos.add(prom);
            
        }
     closeConnections(c,conexion,stm,resultados);
     return promos;
 }
 
 public ArrayList<Promociones> getPromociones2() throws SQLException
 {
     ArrayList<Promociones> promos = new ArrayList<Promociones>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT DISTINCT id_promo FROM promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {
            Promociones prom = new Promociones(resultados.getInt("id_promo"));
            promos.add(prom);
            
        }
     closeConnections(c,conexion,stm,resultados);
     return promos;
 }
 
 
 public ArrayList<Promociones> getPromocionesDisponibles() throws SQLException
 {
     ArrayList<Promociones> promos = new ArrayList<Promociones>();
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {
           
                Promociones prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getInt("cantidad"),resultados.getInt("estado"));
                promos.add(prom);
            
        }
     closeConnections(c,conexion,stm,resultados);
     return promos;
 }
 public Promociones getPromocionesById(int id) throws SQLException
 {
     Promociones prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM promociones;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {   
            if(resultados.getInt("id_promo") == id)
            {
             prom = new Promociones(resultados.getInt("id_promo"),resultados.getInt("monto_dcto"),resultados.getInt("cantidad"),resultados.getInt("estado"));
             return prom;
            }
        }
     closeConnections(c,conexion,stm,resultados);
     return prom;
 }
 public int getPromosIdProducto(int id) throws SQLException
 {
     Promociones prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM promociones where id_producto=?;";    
     PreparedStatement stm = conexion.prepareStatement(query);
          stm.setInt(1,id);
     ResultSet resultados = stm.executeQuery();
     
     while(resultados.next())
        {   
           
          return   0;
            
        }
     closeConnections(c,conexion,stm,resultados);
    return 1;
 }
 
 public boolean addPromo(Promociones promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "INSERT INTO promociones (id_promo, monto_dcto , id_producto,cantidad) VALUES (? , ? , ? ,?); ";    
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdPromo());
     stm.setInt(2,promo.getMontoDcto());
     stm.setInt(3,promo.getId_producto());
     stm.setInt(4,promo.getCantidad());
     System.out.println("asdatemen2"+stm);
     stm.executeUpdate();
     return true;
 }
 public void deletePromo(Promociones promo) throws SQLException
 {
     
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "DELETE FROM promociones WHERE id_promo = ?";
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdPromo()+1);
     stm.executeUpdate();
     closeConnections(c,conexion,stm,null);
     
 }
 public boolean updatePromo(Promociones promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "UPDATE promociones set monto_dcto = ? , nombre = ? , estado = ?  where id_promo = ?; ";    
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getMontoDcto());
     stm.setInt(2,promo.getCantidad());
     stm.setInt(3,promo.getEstado());
     stm.setInt(4,promo.getIdPromo());
     System.out.println(stm);

     stm.executeUpdate();
     closeConnections(c,conexion,stm,null);
     return true;
 }
 
 
  public boolean addOrdenCompra(OrdenDeCompra promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "INSERT INTO ordendecompra (id_orden_compra, fecha, monto_total, numero_factura_recibida ,proveedor,tipo,fecha_emision) VALUES (? , ? , ? , ?,?,?,?); ";    
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,getLastId("ordendecompra")+1);
     stm.setString(2,promo.getFecha());
     stm.setInt(3,promo.getMontoTotal());
     stm.setInt(4,promo.getNumeroFacturaRecibida());//Reemplazado por ID producto.
     stm.setString(5,promo.getProveedor());
         stm.setString(6,promo.getTipo());
           stm.setString(7,promo.getFechaEmision());
     stm.executeUpdate();
     return true;
 }
    public boolean addOrdenCompraProducto(CompraProducto promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "INSERT INTO compraproducto (id_compra_producto, fecha, cantidad, monto_unitario ,monto_total,proveedor,id_producto,id_orden_de_compra) VALUES (? , ? , ? , ?,?,?,?,?); ";    
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,getLastId("compraproducto")+1);
     stm.setString(2,promo.getFecha());
     stm.setInt(3,promo.getCantidad());
     stm.setInt(4,promo.getMontoUnitario());//Reemplazado por ID producto.
     stm.setInt(5,promo.getMontoTotal());
     System.out.println("POR QUE NO FUNCIONA : "+promo.getIdProductoCompra());
     stm.setString(6,promo.getProveedor());
    stm.setInt(7,promo.getIdProductoCompra());
     stm.setInt(8,promo.getOrdenDeCompra());
     stm.executeUpdate();
     return true;
 }
  
    public boolean addrespaldoOrdenCompraProducto(CompraProducto promo) throws SQLException
 {
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "INSERT INTO respaldocompraproducto (id_compra_producto, fecha, cantidad, monto_unitario ,monto_total,proveedor,id_producto,id_orden_de_compra) VALUES (? , ? , ? , ?,?,?,?,?); ";    
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdOrdenDeCompra());
     stm.setString(2,promo.getFecha());
     stm.setInt(3,promo.getCantidad());
     stm.setInt(4,promo.getMontoUnitario());//Reemplazado por ID producto.
     stm.setInt(5,promo.getMontoTotal());
     System.out.println("POR QUE NO FUNCIONA : "+promo.getIdProductoCompra());
     stm.setString(6,promo.getProveedor());
    stm.setInt(7,promo.getIdProductoCompra());
     stm.setInt(8,promo.getOrdenDeCompra());
     stm.executeUpdate();
     return true;
 }
    
    
   public ArrayList<OrdenDeCompra> getordenescompras() throws SQLException
 {ArrayList<OrdenDeCompra> compras=new ArrayList<OrdenDeCompra>();
     OrdenDeCompra prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "SELECT * FROM ordendecompra;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {   
         
             prom = new OrdenDeCompra(resultados.getInt("id_orden_compra"),resultados.getString("fecha"),resultados.getInt("monto_total"),resultados.getInt("numero_factura_recibida"),resultados.getString("proveedor"),resultados.getString("tipo"),resultados.getString("fecha_emision"));
            compras.add(prom);
            
        }
     closeConnections(c,conexion,stm,resultados);
     return compras;
 }
   
   
    public void deleteOrden(OrdenDeCompra promo) throws SQLException
 {
     
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "DELETE FROM ordendecompra WHERE id_orden_compra = ?";
      String query2 = "DELETE FROM compraproducto WHERE id_orden_de_compra = ?";
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdOrdenCompra());
     stm.executeUpdate();
       PreparedStatement stm2 = conexion.prepareStatement(query2);
     stm2.setInt(1,promo.getIdOrdenCompra());
     stm2.executeUpdate();
     closeConnections(c,conexion,stm,null);
       closeConnections(c,conexion,stm2,null);
 }
    
    
    
        public void deleteOrdenVenta(OrdenDeVenta promo) throws SQLException
 {
     
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query2 = "DELETE FROM ordendeventa WHERE id_orden_venta = ?";
      String query = "DELETE FROM ventaproducto WHERE id_orden_de_venta = ?";
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo.getIdOrdenVenta());
     stm.executeUpdate();
       PreparedStatement stm2 = conexion.prepareStatement(query2);
     stm2.setInt(1,promo.getIdOrdenVenta());
     stm2.executeUpdate();
     closeConnections(c,conexion,stm,null);
       closeConnections(c,conexion,stm2,null);
 }
       public void deletePromocion(int  promo) throws SQLException
 {
     
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "DELETE FROM promociones WHERE id_promo = ?";
     System.out.println(query);
     PreparedStatement stm = conexion.prepareStatement(query);
     stm.setInt(1,promo);
     stm.executeUpdate();
    
     closeConnections(c,conexion,stm,null);
 }
    
    public void addrespaldoVentaProducto(ArrayList<VentaProducto> ventas, OrdenDeVenta o) throws SQLException
      { //Para ventas de un producto
          DB_connection c = new DB_connection();
          Connection conexion = c.getConnection();
          String query = "INSERT INTO respaldoventaproducto (id_venta_producto,id_orden_de_venta,id_producto,fecha,precio_unitario_neto,cantidad_producto,precio_unitario_final,precio_total_neto,precio_total_final,descuento) VALUES(?,?,?,?,?,?,?,?,?,?);";
          PreparedStatement stm = conexion.prepareStatement(query);

          for(VentaProducto v: ventas)
          {
              
              stm.setInt(1, getLastId("ventaproducto")+1);
              stm.setInt(2, o.getIdOrdenVenta());
              stm.setInt(3, v.getIdProducto());
              stm.setString(4, v.getFecha());
              stm.setInt(5, v.getPrecioUnitarioNeto());
              stm.setInt(6,v.getCantidadProducto());
              stm.setInt(7, v.getPrecioUnitarioFinal());
              stm.setInt(8,v.getPrecioTotalNeto());
              stm.setInt(9,v.getPrecioTotalFinal());
              stm.setInt(10,v.getDescuento());
              stm.executeUpdate();
             
          }
            closeConnections(c,conexion,stm,null);
      } 
    
    
        
   public ArrayList<Productos> getcantidadventasproductos() throws SQLException
 {ArrayList<Productos> compras=new ArrayList<Productos>();
     Productos prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "Select id_producto, sum(cantidad_producto) as suma from ventaproducto Group by id_producto;";    
     PreparedStatement stm = conexion.prepareStatement(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {   
 prom=this.getProductoById(resultados.getInt("id_producto")) ;if(prom!=null){
   prom.setCantidadp(resultados.getInt("suma"));
  
            compras.add(prom);  
 }

            
        }
     closeConnections(c,conexion,stm,resultados);
     return compras;
 }
   
    
   
    public ArrayList<Productos> getcantidadventasproductosFECHAS(String fecha1 , String fecha2) throws SQLException
 {ArrayList<Productos> compras=new ArrayList<Productos>();
     Productos prom = null;
     DB_connection c = new DB_connection();
     Connection conexion = c.getConnection();
     String query = "Select id_producto, sum(cantidad_producto) as suma from ventaproducto WHERE fecha BETWEEN ? AND ? Group by id_producto ;";    

     PreparedStatement stm = conexion.prepareStatement(query);
       stm.setString(1,fecha1);
         stm.setString(2,fecha2);
          System.out.println(query);
     ResultSet resultados = stm.executeQuery();
     while(resultados.next())
        {   
 prom=this.getProductoById(resultados.getInt("id_producto")) ;if(prom!=null){
   prom.setCantidadp(resultados.getInt("suma"));
  
            compras.add(prom);  
 }

            
        }
     closeConnections(c,conexion,stm,resultados);
     return compras;
 }
   
}
