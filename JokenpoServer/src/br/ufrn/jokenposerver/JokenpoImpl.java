package br.ufrn.jokenposerver;

import br.ufrn.jokenpo.interaces.Estatisticas;
import br.ufrn.jokenpo.interaces.Jogada;
import br.ufrn.jokenpo.interaces.Jokenpo;
import br.ufrn.jokenpo.interaces.Resultado;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * Created by joao on 29/04/16.
 */
public class JokenpoImpl extends UnicastRemoteObject implements Jokenpo {

    private Estatisticas estatisticas;

    protected JokenpoImpl() throws RemoteException {
        estatisticas = new Estatisticas();
    }

    @Override
    public Resultado fazerJogada(Jogada jogada) throws RemoteException {
        estatisticas.incrementarJogadas();
        
        Jogada jogadaServidor = gerarJogadaRandom();
        Resultado resultado = decidirResultado(jogada, jogadaServidor);
        Resultado resultadoRodada = null;
        
        if(estatisticas.getJogadas() % 3 == 0) {
            resultadoRodada = decidirRodada();
        }

        if(resultadoRodada != null) {
            return resultadoRodada;
        } else {
            return resultado;
        }
    }

    @Override
    public Estatisticas getEstatisticas() throws RemoteException {
        return estatisticas;
    }

    private Resultado decidirRodada() {
        try {
            if(estatisticas.getGanhos() > estatisticas.getPerdidas()) {
                estatisticas.incrementarPontosCliente();
                return Resultado.GANHOURODADA;
            } else if(estatisticas.getGanhos() < estatisticas.getPerdidas()) {
                estatisticas.incrementarPontosServidor();
                return Resultado.PERDEURODADA;
            } else {
                return Resultado.EMPATOURODADA;
            }
        } finally {
            estatisticas.reiniciarRodada();
        }
    }

    private Resultado decidirResultado(Jogada jogada, Jogada jogadaServidor) {
        /* EMPATE */
        if(jogadaServidor.equals(jogada)) {
            return Resultado.EMPATE;
        }

        /* CLIENTE GANHA */
        if((jogada.equals(Jogada.PAPEL) && jogadaServidor.equals(Jogada.PEDRA))
                || (jogada.equals(Jogada.PEDRA) && jogadaServidor.equals(Jogada.TESOURA))
                || (jogada.equals(Jogada.TESOURA) && jogadaServidor.equals(Jogada.PAPEL))) {
            estatisticas.incrementarGanhos();
            return Resultado.GANHOU;
        }

        /* CASO NENHUM IF ANTERIOR SEJA TRUE ENTÃO CLIENTE PERDEU */
        estatisticas.incrementarPerdidas();
        return Resultado.PERDEU;
    }

    private Jogada gerarJogadaRandom() {
        Random random = new Random();

        Integer escolhido = random.nextInt(3);

        if(escolhido == 0) {
            return Jogada.PAPEL;
        } else  if(escolhido == 1) {
            return Jogada.PEDRA;
        } else {
            return Jogada.TESOURA;
        }
    }
}
