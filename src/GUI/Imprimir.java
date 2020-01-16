/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ObjetosDB.OrdenDeVenta;
import ObjetosDB.Productos;
import ObjetosDB.VentaProducto;
import ObjetosDB.metodosDB;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Hack
 */
public class Imprimir { ArrayList<Productos> aux2a;
  public void imprimirFactura(String nboleta,String Fecha, String Cliente,
          String direccion,ArrayList<Productos> carroProductos,int monto,String rut,String fono,String debe){
        try {
            PrinterMatrix printer = new PrinterMatrix();

            Extenso e = new Extenso();

            e.setNumber(101.85);


            //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
                printer.setOutSize(25, 80);
            //Imprimir * de la 2da linea a 25 en la columna 1;
           // printer.printCharAtLin(2, 25, 1, "*");
            //Imprimir * 1ra linea de la columa de 1 a 80
          
        
           //  printer.mapearDocumentoImageFile(1, 2, "D:/fuller.jpg");
            //Imprimir Encabezado nombre del La EMpresa
                    printer.printTextWrap(1, 1, 15, 80, "TIENDA");
        printer.printTextWrap(2, 2, 15, 80, "VALE DE VENTA");
           //printer.printTextWrap(linI, linE, colI, colE, null);
         ArrayList<OrdenDeVenta> ordenesDia = new metodosDB().getVentasDia(getFecha(),end());
     
         int atencion= ordenesDia.size()+1;
        
           printer.printTextWrap(3, 3, 1, 22, "Num. Vale : " +nboleta);
           printer.printTextWrap(3, 3, 25, 55, "Fecha: "+Fecha );
                         printer.printTextWrap(4, 4, 1,80, "=========================================");
            printer.printTextWrap(5, 5, 1, 80,"Cantidad | Descripcion | Precio Unitario");
           printer.printTextWrap(6, 6, 1,80, "-----------------------------------------");
           int i=0;
    aux2a=carroProductos;
     ArrayList<VentaProducto> productos=  new metodosDB().getVentaProductoByIdOrdenVenta(Integer.parseInt(nboleta));
             for (VentaProducto a :productos){
               Productos product=   new metodosDB().getProductoById(a.getIdProducto());
             
                 if(product.getHarina()==0){String nombre = null;
                     if(productos.get(i).getProducto().getNombre().length()<30){
                         nombre=productos.get(i).getProducto().getNombre();
                         String largo=Integer.toString(productos.get(i).getProducto().getNombre().length());
                         
                          
                         String end="30";
                         String formato="%s"+largo;
                         //System.out.println("El largo es: "+largo + "El formato es: "+formato);
                         String.format(formato+"$"+end,nombre);
                     }else{
                          nombre=productos.get(i).getProducto().getNombre();
                       nombre=  product.getNombre().substring(0, 30);
                     }
                                                          printer.printTextWrap(7 + i+1, 10 + i+1, 1, 24, productos.get(i).getCantidadProducto()+"       "+nombre+"         ");
                                                     
                                                          printer.printTextWrap(7 + i+1, 10 + i+1, 25, 55,"        "+Integer.toString((int) (productos.get(i).getProducto().getPrecioVenta()*1.0)));

                 }else{
                                                  String nombre = null;
                     if(productos.get(i).getProducto().getNombre().length()<30){
                         nombre=productos.get(i).getProducto().getNombre();
                         String largo=Integer.toString(productos.get(i).getProducto().getNombre().length());
                         
                          
                         String end="30";
                         String formato="%s"+largo;
                         System.out.println("El largo es: "+largo + "El formato es: "+formato);
                         String.format(formato+"$"+end,nombre);
                     }else{
                          nombre=productos.get(i).getProducto().getNombre();
                       nombre=  product.getNombre().substring(0, 30);
                     }
                   printer.printTextWrap(8 + i+1, 8 + i+1, 1, 24, productos.get(i).getCantidadProducto()+"  "+nombre+"         ");
                printer.printTextWrap(8 + i+1, 8 + i+1, 1, 24, productos.get(i).getCantidadProducto()+"  "+nombre+"         ");
                                                     
                                                          printer.printTextWrap(8 + i+1, 10 + i+1, 25, 55,"        "+Integer.toString((int) (productos.get(i).getProducto().getPrecioVenta()*1.31)));
                 }
      
             i++;}
             
       
          
            printer.printTextWrap(8+i+1+1, 10+i+1+1,1, 80, "=========================================");
            printer.printTextWrap(8+i+2+1, 10+i+2+1, 1, 80, "                  TOTAL A PAGAR: " + monto);
            printer.printTextWrap(8+i+3+1, 10+i+3+1,1, 80, "=========================================");
             printer.printTextWrap(3, 3, 1, 22, "Atencion Número: " +atencion);
            printer.toFile("impresion.txt");

          FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream("impresion.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            if (inputStream == null) {
                return;
            }

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();


            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, attributeSet);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("No existen impresoras instaladas");
            }

            //inputStream.close();
        } catch (SQLException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
private String getFecha()
 {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        return FechaHoy;
 }
         private String end()
 {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        return FechaHoy;
 }
    }