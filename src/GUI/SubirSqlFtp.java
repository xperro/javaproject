package GUI;    
import java.io.BufferedInputStream;
import java.io.FileInputStream; 
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class SubirSqlFtp 
{
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException
    {
        try
        {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(InetAddress.getByName("200.73.116.212/~fullerto/"));
            ftpClient.login("fullerto","et4nY56Y2h");
            
            //Verificar conexión con el servidor.
            
            int reply = ftpClient.getReplyCode();
            
            System.out.println("Respuesta recibida de conexión FTP:" + reply);
            
            if(FTPReply.isPositiveCompletion(reply))
            {
                System.out.println("Conectado Satisfactoriamente");    
            }
            else
                {
                    System.out.println("Imposible conectarse al servidor");
                }
           
            //Verificar si se cambia de direcotirio de trabajo
            
            ftpClient.changeWorkingDirectory("");//Cambiar directorio de trabajo
            System.out.println("Se cambió satisfactoriamente el directorio");
            
            //Activar que se envie cualquier tipo de archivo
            
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            BufferedInputStream buffIn = null;
            buffIn = new BufferedInputStream(new FileInputStream("D:/file.sql"));//Ruta del archivo para enviar
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile("", buffIn);//Ruta completa de alojamiento en el FTP
            
            buffIn.close(); //Cerrar envio de arcivos al FTP
            ftpClient.logout(); //Cerrar sesión
            ftpClient.disconnect();//Desconectarse del servidor
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    System.out.println("Algo malo sucedió");
                }
    }
}