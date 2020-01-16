package ObjetosDB;

public class Kitproductos {
    private static final long serialVersionUID = 1L;
    
    private Integer idKitProductos;
    private String nombreKit;
    private Integer precioCompraProductos;
    private Integer precioVentaKit;
    private String descripcionKit;
    
    public Kitproductos()
    {}
    public Kitproductos(int idKitProductos,String nombre,int compra,int venta,String descripcion) { 


//Constructor
        
        
        this.idKitProductos=idKitProductos;
        this.descripcionKit=descripcion;
        this.nombreKit=nombre;
        this.precioCompraProductos=compra;
        this.precioVentaKit=venta;
    }

    /*Setters y Getters*/
    public Integer getIdKitProductos() {
        return idKitProductos;
    }

    public void setIdKitProductos(Integer idKitProductos) {
        this.idKitProductos = idKitProductos;
    }

    public String getNombreKit() {
        return nombreKit;
    }

    public void setNombreKit(String nombreKit) {
        this.nombreKit = nombreKit;
    }

    public Integer getPrecioCompraProductos() {
        return precioCompraProductos;
    }

    public void setPrecioCompraProductos(Integer precioCompraProductos) {
        this.precioCompraProductos = precioCompraProductos;
    }

    public Integer getPrecioVentaKit() {
        return precioVentaKit;
    }

    public void setPrecioVentaKit(Integer precioVentaKit) {
        this.precioVentaKit = precioVentaKit;
    }

    public String getDescripcionKit() {
        return descripcionKit;
    }

    public void setDescripcionKit(String descripcionKit) {
        this.descripcionKit = descripcionKit;
    }

    
    public Kitproductos getKitById(int id)
    { //implementar
        return null;
    }
    /*Fin Setters y Getters*/
    
}
