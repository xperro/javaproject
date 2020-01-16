/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosDB;


public class CompraProducto  {
    private static final long serialVersionUID = 1L;

    private Integer idCompraProducto;
    private String fecha;
    private Integer cantidad;
    private Integer montoUnitario;
    private Integer montoTotal;
    private String proveedor;
    private Integer OrdenDeCompra;
    private Integer IdProductoCompra;

    //Constructor
    public CompraProducto(Integer idCompraProducto, String fecha, int cantidad, int montoUnitario, int montoTotal, String proveedor, int ordenDeCompra,int IdProducto) {
        this.idCompraProducto = idCompraProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.montoUnitario = montoUnitario;
        this.montoTotal = montoTotal;
        this.proveedor = proveedor;
        this.IdProductoCompra=IdProducto;
        this.OrdenDeCompra = ordenDeCompra;
    }

    /*Setters y Getters*/
    public Integer getIdCompraProducto() {
        return idCompraProducto;
    }

    public void setIdCompraProducto(Integer idCompraProducto) {
        this.idCompraProducto = idCompraProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMontoUnitario() {
        return montoUnitario;
    }

    public void setMontoUnitario(Integer montoUnitario) {
        this.montoUnitario = montoUnitario;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdOrdenDeCompra() {
        return this.getOrdenDeCompra();
    }

    public void setIdOrdenDeCompra(int idOrdenDeCompra) {
        this.setOrdenDeCompra((Integer) idOrdenDeCompra);
    }


    /*Fin setters y Getters*/

    /**
     * @return the OrdenDeCompra
     */
    public Integer getOrdenDeCompra() {
        return OrdenDeCompra;
    }

    /**
     * @param OrdenDeCompra the OrdenDeCompra to set
     */
    public void setOrdenDeCompra(Integer OrdenDeCompra) {
        this.OrdenDeCompra = OrdenDeCompra;
    }

    /**
     * @return the IdProductoCompra
     */
    public Integer getIdProductoCompra() {
        return IdProductoCompra;
    }

    /**
     * @param IdProductoCompra the IdProductoCompra to set
     */
    public void setIdProductoCompra(Integer IdProductoCompra) {
        this.IdProductoCompra = IdProductoCompra;
    }
    
}
