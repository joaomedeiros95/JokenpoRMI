package br.ufrn.jokenpo.interaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by joao on 29/04/16.
 */
public interface Jokenpo extends Remote {

    public Resultado fazerJogada(Jogada jogada) throws RemoteException;

    public Estatisticas getEstatisticas() throws RemoteException;

}
