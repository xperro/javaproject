
package ObjetosDB;


/**
 *
 * @author ttars
 */

public class Promociones   {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
   
    private Integer idPromo;
    
    private int montoDcto;
  
    private int cantidad;
   
    private int estado =0;
    
    private int id_producto=0;

    public Promociones(int monto, int cantidad) {
        this.montoDcto = monto;
        this.cantidad = cantidad;
    }
    public Promociones(int monto, int idpromo, int id_producto)
    {
        
        this.montoDcto = monto;
        this.idPromo = idpromo;
        this.id_producto=id_producto;
    }

    public Promociones(Integer idPromo) {
        this.idPromo = idPromo;
    }
    

    public Promociones(Integer idPromo, int montoDcto, int idproducto, int cantidad) {
        this.idPromo = idPromo;
        this.montoDcto = montoDcto;
        this.cantidad = cantidad;
        this.id_producto = idproducto;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public int getMontoDcto() {
        return montoDcto;
    }

    public void setMontoDcto(int montoDcto) {
        this.montoDcto = montoDcto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the id_producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    

 
    
}
