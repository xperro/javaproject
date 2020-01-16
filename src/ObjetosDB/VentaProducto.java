package ObjetosDB;

public class VentaProducto   {
    private static final long serialVersionUID = 1L;
    
    private Integer idVentaProducto;
    private String fecha;
    private Integer precioUnitarioNeto;
    private Integer cantidadProducto;
    private Integer precioUnitarioFinal;
    private Integer precioTotalNeto;
    private Integer precioTotalFinal;
    private Integer descuento;
    private Integer kitProducto;
    private Productos producto;
    private Kitproductos kit;
    private Integer idOrdenDeVenta;
   
    private String kit_or_product;//(Dos opciones: "kit", "producto");

    public VentaProducto(int idVentaProducto, String fecha, int precioUnitarioNeto, int cantidadProducto, int precioUnitarioFinal, int precioTotalNeto, int precioTotalFinal, int descuento, Kitproductos kit, Productos producto, int idOrdenDeVenta,String kit_or_product) { //Constructor
    this.idVentaProducto = idVentaProducto;
    this.fecha = fecha;
    this.precioUnitarioNeto = precioUnitarioNeto;
    this.cantidadProducto = cantidadProducto;
    this.precioUnitarioFinal = precioUnitarioFinal;
    this.precioTotalNeto = precioTotalNeto;
    this.precioTotalFinal = precioTotalFinal;
    this.descuento = descuento;
    this.kit = kit;
    this.producto = producto;
    this.idOrdenDeVenta = idOrdenDeVenta;
    this.kit_or_product= kit_or_product;
    }

    /*Setters y Getters*/
    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }
       public int getIdProducto()
    {
        return this.getProducto().getId_producto();
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getPrecioUnitarioNeto() {
        return precioUnitarioNeto;
    }

    public void setPrecioUnitarioNeto(Integer precioUnitarioNeto) {
        this.precioUnitarioNeto = precioUnitarioNeto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(Integer precioUnitarioFinal) {
        this.precioUnitarioFinal = precioUnitarioFinal;
    }

    public Integer getPrecioTotalNeto() {
        return precioTotalNeto;
    }

    public void setPrecioTotalNeto(Integer precioTotalNeto) {
        this.precioTotalNeto = precioTotalNeto;
    }

    public Integer getPrecioTotalFinal() {
        return precioTotalFinal;
    }

    public void setPrecioTotalFinal(Integer precioTotalFinal) {
        this.precioTotalFinal = precioTotalFinal;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public int getKitProducto() {
        return kitProducto;
    }

    public void setKitProducto(int idKitProducto) {
        this.kitProducto = idKitProducto;
    }

    public int getIdOrdenDeVenta() {
        return idOrdenDeVenta;
    }

    public void setIdOrdenDeVenta(int idOrdenDeVenta) {
        this.idOrdenDeVenta = idOrdenDeVenta;
    }

    /*Fin Setters y Getters*/

    /**
     * @return the producto
     */
    public Productos getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
