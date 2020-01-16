/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

public class Command { 
public void executeLocal() { 
try {
      Process p = Runtime
            .getRuntime()
            .exec("C:/xampp/mysql/bin/mysqldump -u root -pDDfjTTE4tZKF6X7e fullerton");

      InputStream is = p.getInputStream();
      FileOutputStream fos = new FileOutputStream("D:/file.sql");
      byte[] buffer = new byte[1000];

      int leido = is.read(buffer);
      while (leido > 0) {
         fos.write(buffer, 0, leido);
         leido = is.read(buffer);
      }

      fos.close();

   } catch (Exception e) {
      e.printStackTrace();
   }
}

public void executeExterno() { 
 try {
      Process p = Runtime
            .getRuntime()
            .exec("C:/xampp/mysql/bin/mysqldump -u root -pDDfjTTE4tZKF6X7e fullerton");

      InputStream is = p.getInputStream();java.util.Date fecha = new Date();
      FileOutputStream fos = new FileOutputStream("D:backup_local"+fecha.getTime()+".sql");
      byte[] buffer = new byte[1000];

      int leido = is.read(buffer);
      while (leido > 0) {
         fos.write(buffer, 0, leido);
         leido = is.read(buffer);
      }

      fos.close();

   } catch (Exception e) {
      e.printStackTrace();
   }
} } 