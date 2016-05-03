package br.ufrn.jokenposerver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by joao on 29/04/16.
 */
public class JokenpoServer {

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            JokenpoImpl jokenpo = new JokenpoImpl();
            Naming.rebind("rmi://localhost/JokenpoServer", jokenpo);
            System.out.println("Servidor iniciado com sucesso!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
