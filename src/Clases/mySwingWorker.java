/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JProgressBar;

public class mySwingWorker extends javax.swing.SwingWorker {
//The first template argument, in this case, ArrayList<Integer>, is what s returned by doInBackground(), 
//and by get(). The second template argument, in this case, Integer, is what is published with the 
//publish method. It is also the data type which is stored by the java.util.List that is the parameter
//for the process method, which recieves the information published by the publish method.

    JProgressBar barra;

    public mySwingWorker(JProgressBar b) {
        barra = b;
        barra.setVisible(true);
        barra.setIndeterminate(true);
        barra.setString("ESPERE UN MOMENTO");
        barra.setStringPainted(true);
    }

    @Override
    protected Void doInBackground() throws InterruptedException {
//Returns items of the type given as the first template argument to the SwingWorker class.
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            System.out.println("javax.swing.SwingUtilities.isEventDispatchThread() returned true.");
        }
        Integer tmpValue = new Integer(1);
        for (int i = 0; i < 1000; i++) {
            if (i % 100 == 0) {
                barra.setString(barra.getString() + ".");
            }
            for (int j = 0; j < 1000; j++) { //find every 100th prime, just to make it slower
                tmpValue = FindNextPrime(tmpValue.intValue());
//isCancelled() returns true if the cancel() method is invoked on this class. That is the proper way
//to stop this thread. See the actionPerformed method.
                System.out.println("i:" + i + " j:" + j);
                if (isCancelled()) {
                    barra.setVisible(false);
                    System.out.println("SwingWorker - isCancelled");
                    return null;
                }
            }
//Successive calls to publish are coalesced into a java.util.List, which is what is received by process, 
//which in this case, isused to update the JProgressBar. Thus, the values passed to publish range from 
//1 to 100.

        }
        return null;
    }//Note, always use java.util.List here, or it will use the wrong list.

//        @Override
//        protected void process(java.util.List<Integer> progressList) {
//            if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
//                System.out.println("javax.swing.SwingUtilities.isEventDispatchThread() + returned false.");
//            }
//       
//        }
    @Override
    protected void done() {
        System.out.println("doInBackground is complete");
        barra.setVisible(false);
        if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
            System.out.println("javax.swing.SwingUtilities.isEventDispatchThread() + returned false.");
        }
    }

    boolean IsPrime(int num) { //Checks whether a number is prime
        int i;
        for (i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    protected Integer FindNextPrime(int num) { //Returns next prime number from passed arg.       
        do {
            if (num % 2 == 0) {
                num++;
            } else {
                num += 2;
            }
        } while (!IsPrime(num));
        return new Integer(num);
    }
}
