package Clases;

import ObjetosDB.*;
import java.util.ArrayList;

/*El objetivo de esta clase es contener los métodos sobre las colecciones de objetos
que han sido cargados de la base de datos. La idea es tener los métodos de los arraylist que ya están en memoria acá
y dejas las querys de la base de datos en la clase ObjetosDB/metodosDB*/
public class Metodos_objetos {
    private static final int criticalStock = 10; //Numero definido por el cliente como stock critico
    public Metodos_objetos() //Constructor
    {}
    
    public ArrayList<String> getTiposProductos(ArrayList<Productos> productos)
    {   //Retorna los tipos de los productos encontrados en la base de datos.
        ArrayList<String> tiposEncontrados = new ArrayList<String>();
        for(Productos thisProducto:productos)
        {
            //tipodEncontrados.add(thisProducto.getTipo());
            
        }
        return tiposEncontrados;
    }
    
    public ArrayList<Productos> getProductosStockCritico(ArrayList<Productos> productos)
    {   //Retorna un array con los productos que tienen stock critico
        ArrayList<Productos> stockCritico = new ArrayList<Productos>();
        for(Productos product: productos)
            if (product.getCantidadActual()<=criticalStock)
               stockCritico.add(product);
        
        return stockCritico;
    }
    
    public int getVentasByFecha(ArrayList<OrdenDeVenta> ordenes)
    {   //Retorna la suma total de las ventas dado un dia.
        int sumaTotal = 0 ;
        for(OrdenDeVenta orden: ordenes)
        {
            sumaTotal += Integer.parseInt(orden.getMontoTotal());
        }
        return sumaTotal;
    }
    public void testProductos(ArrayList<Productos> productos)
    {
        for(Productos product:productos)
        {
            System.out.println("\nProductoEncontrado!!!: \n");
            System.out.println("Nombre: "+product.getNombre());
            System.out.println("\nColor: "+product.getColor());
            System.out.println("\nProveedor: "+product.getProveedor());
            System.out.println("\nMarca: "+product.getMarca());
            System.out.println("\nTalla: "+product.getTalla());
            System.out.println("\nCantidadActual: "+product.getCantidadActual());
            System.out.println("\nPrecioVenta: "+product.getPrecioVenta());
            System.out.println("\n PRecioCompra: "+product.getPrecioCompra());
        }
    }
    
}
