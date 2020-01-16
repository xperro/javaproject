/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;

public class funciones {

    private String Tag = "SISTEMA ADMINISTRADOR DE BENEFICIOS PARA LOS EMPLEADOS";

    public static boolean sendMessage(String mensaje, String subject, String correo) throws UnsupportedEncodingException {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "luis.espinoza.tapia@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("luis.espinoza.tapia@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(correo));
            message.setSubject(subject);
            message.setText(mensaje);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("gmail.com", "");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
  public String getRutaCotizacion() {
        File folder = new File("C:\\FULLERTON EXPORT\\cotizacion");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }
    public String getRutaExcel() {
        File folder = new File("C:\\FULLERTON EXPORT\\librosDiarios");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }
   public String getRutaFotoProducto() {
        File folder = new File("C:\\FULLERTON EXPORT\\fotosProductos");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }
    public String getRuta_benef(String fecha) {
        File folder = new File("C:\\FULLERTON EXPORT\\" + fecha + "_CARTAS");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_carta_deudores() {
        File folder = new File("C:\\FULLERTON EXPORT\\" +"CARTA DEUDORES DAB");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_contabilidad_dab() {
        File folder = new File("C:\\FULLERTON EXPORT\\CONTABILIDAD DAB");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_formatos() {
        File folder = new File("C:\\FULLERTON EXPORT\\FORMATOS DE ARCHIVOS DE ENTRADA");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_cheques() {
        File folder = new File("C:\\FULLERTON EXPORT\\CHEQUES");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_seguro() {
        File folder = new File("C:\\FULLERTON EXPORT\\SEGUROS Y ATENCIONES SOCIALES");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_reportes() {
        File folder = new File("C:\\FULLERTON EXPORT\\REPORTES");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String getRuta_cartaGarantia() {
        File folder = new File("C:\\FULLERTON EXPORT\\CARTAS DE GARANTIA");
        folder.mkdirs();
        return folder.getAbsolutePath() + "\\";
    }

    public String get_fecha(int op) {
        String day = null, month = null, year = null;
        Calendar c = Calendar.getInstance();
        day = Integer.toString(c.get(Calendar.DATE));
        month = Integer.toString(c.get(Calendar.MONTH));
        year = Integer.toString(c.get(Calendar.YEAR));
        if (op == 1) {
            return day;
        }
        if (op == 2) {
            return month;
        }
        month = get_mes(Integer.parseInt(month) + 1);
        if (op == 3) {
            return year;
        }
        if (op == 0) {
            return (day + " / " + month + " / " + year);
        }
        return "";
    }

    public float Redondear(float pNumero, int pCantidadDecimales) {
        // the function is call with the values Redondear(625.3f, 2)
        BigDecimal value = new BigDecimal(pNumero);
        value = value.setScale(pCantidadDecimales, RoundingMode.HALF_EVEN);
        return value.floatValue();
    }

    public String get_mes(int mes) {
        switch (mes) {
            case (1):
                return "Enero";
            case (2):
                return "Febrero";
            case (3):
                return "Marzo";
            case (4):
                return "Abril";
            case (5):
                return "Mayo";
            case (6):
                return "Junio";
            case (7):
                return "Julio";
            case (8):
                return "Agosto";
            case (9):
                return "Septiembre";
            case (10):
                return "Octubre";
            case (11):
                return "Noviembre";
            case (12):
                return "Diciembre";

        }
        return " ";
    }

    public String get_mesMay(int mes) {
        switch (mes) {
            case (1):
                return "ENERO";
            case (2):
                return "FEBRERO";
            case (3):
                return "MARZO";
            case (4):
                return "ABRIL";
            case (5):
                return "MAYO";
            case (6):
                return "JUNIO";
            case (7):
                return "JULIO";
            case (8):
                return "AGOSTO";
            case (9):
                return "SEPTIEMBRE";
            case (10):
                return "Octubre";
            case (11):
                return "NOVIEMBRE";
            case (12):
                return "DICIEMBRE";

        }
        return " ";
    }

    public String validar_rut(String x) {
        int rut = 0;
        try {
            rut = Integer.valueOf(x);
        } catch (Exception e) {
            return "vacio";
        }
        int m = 0, s = 0;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        if (s < 10) {
            return (String.valueOf(s));
        }

        if (s == 10) {
            return ("K");
        }

        if (s == 0) {
            return ("0");
        }
        return "";
    }

    public void limpiar_tabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                modelo.setValueAt("", i, j);
            }
        }
    }

    public String getFechaTxt(Date date) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
            String fecha = sdf.format(date);
            fecha = (fecha.split("-")[0].length() == 1) ? "0" + fecha : fecha;
            fecha = (fecha.split("-")[1].length() == 1) ? fecha.split("-")[0] + "-0" + fecha.split("-")[1] + "-" + fecha.split("-")[2] : fecha;
            return fecha;
        } catch (Exception e) {
            return "vacio";
        }
    }

    public Date getFechaDate(String f) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(f);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public String getNumcPto(int x) {
        String r = null;
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,###.##", simbolo);
        //DecimalFormat formateador = new DecimalFormat("###.###");
        r = (formateador.format(x)).replace(",", ".");
        return r;
    }

    public int getNumsPto(String x) {
        String r = null;
        String[] a = x.split(",");
        for (int i = 0; i < a.length; i++) {
            if (r == null) {
                r = a[i];
            } else {
                r = r + a[i];
            }
        }
        return Integer.valueOf(r);
    }

    public String getfechacorta(String fecha) {
        String aux4 = null;
        String[] f = fecha.split("-");
        if (Integer.valueOf(f[0]) < 10 & Integer.valueOf(f[1]) < 10) {
            aux4 = "0" + Integer.valueOf(f[0]) + "/0" + Integer.valueOf(f[1]) + "/" + f[2].substring(2);
        } else {
            aux4 = f[0] + "/" + f[1] + "/" + f[2].substring(2);
            if (Integer.valueOf(f[0]) < 10) {
                aux4 = "0" + Integer.valueOf(f[0]) + "/" + f[1] + "/" + f[2].substring(2);
            }
            if (Integer.valueOf(f[1]) < 10) {
                aux4 = f[0] + "/0" + Integer.valueOf(f[1]) + "/" + f[2].substring(2);
            }
        }

        return aux4;
    }

    public String Convertir(String numero, boolean mayusculas) {
        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
            parte_decimal = Num[1];
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
            } else {
                return (literal + parte_decimal);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }
    private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if (Integer.parseInt(num) > 99) {//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            }
        } else {//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + "millones ";
        } else {
            if (Integer.valueOf(millon) > 1) {
                n = getUnidades(millon) + "millones ";
            } else {
                n = getUnidades(millon) + "millon ";
            }
        }
        return n + getMiles(miles);
    }

    public String nombreSistema() {
        return this.Tag;
    }

    public int monto_cuota(int cuotas, int capital, float interes) {
        int r = 0;
        if (interes != 0) {
            double num = Math.pow((double) (1 + interes), cuotas);
            double den = (Math.pow((double) (1 + interes), cuotas) - 1) / interes;
            float factor = this.Redondear((float) (num / den), 4);
            r = (int) this.Redondear(factor * capital, 0);
        } else {
            r = capital / cuotas;
        }
        return r;
    }

    public int cap_ins_cuota(int cuotas, int capital, float interes, int cuota_actual) {
        int r = 0;
        if (interes != 0) {
            double num = Math.pow((double) (1 + interes), cuotas);
            double den = (Math.pow((double) (1 + interes), cuotas) - 1) / interes;
            double cap_ins = ((num / den) * capital) * ((1 - (Math.pow(1 + interes, -(cuotas - cuota_actual)))) / interes);
            r = (int) this.Redondear((float) cap_ins, 0);
        } else {
            r = capital / cuotas;
        }
        return r;
    }

    public int monto_cap_ptmo(int cuotas, int valor_cuota, float interes) {
        int r = 0;
        if (interes != 0) {
            double num = Math.pow((double) (1 + interes), cuotas);
            double den = (Math.pow((double) (1 + interes), cuotas) - 1) / interes;
            float factor = this.Redondear((float) (num / den), 4);
            r = (int) this.Redondear(valor_cuota / factor, 0);
        } else {
            r = valor_cuota * cuotas;
        }
        return r;
    }

    public String getFechaTxtInv(Date date) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
            String fecha = sdf.format(date);
            fecha = (fecha.split("-")[0].length() == 1) ? "0" + fecha : fecha;
            fecha = (fecha.split("-")[1].length() == 1) ? fecha.split("-")[0] + "-0" + fecha.split("-")[1] + "-" + fecha.split("-")[2] : fecha;
            fecha = fecha.split("-")[2] + "-" + fecha.split("-")[1] + "-" + fecha.split("-")[0];
            return fecha;
        } catch (Exception e) {
            return "vacio";
        }
    }
    
   public boolean restoreDatabase(String path, String mySqlPath)
    {
        List<String> args = new ArrayList<String>();
        mySqlPath = "C:/wamp/bin/mysql/mysql5.5.24/bin/mysql.exe";
        args.add(mySqlPath);

        // Comands
        args.add("-u ");
        args.add("root");
        args.add("--password=123");
        args.add("testingimport");
        args.add("<");
        path = "C:/Users/Kasper/Documents/testingimport.sql";
        args.add(path);


        try{
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.redirectError();
            Process p = pb.start();
            InputStream is = (InputStream) p.getInputStream();

            int in = -1;
            while((in = is.read()) != -1)
            {
                System.out.print(""+(char) in);
            }

            int proccessCompleted = p.waitFor();

            if(proccessCompleted == 0)
            {
                System.out.println("Dump done!");
                return true;
            }
            else
            {
                System.out.println("Error doing dump!");
                return false;
            }
        }
        catch(IOException | InterruptedException ex)
        {
            System.out.println("Exception exportDB -> " + ex.getMessage() + "|" + ex.getLocalizedMessage());
        }
        return false;
    }
}