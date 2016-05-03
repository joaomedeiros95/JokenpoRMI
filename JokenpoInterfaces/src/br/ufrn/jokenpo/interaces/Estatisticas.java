package br.ufrn.jokenpo.interaces;

import java.io.Serializable;

/**
 * Created by joao on 29/04/16.
 */
public class Estatisticas implements Serializable {

    private static final long serialVersionUID = -6817588673816583511L;

    private int ganhos;
    private int perdidas;
    private int jogadas;
    private int pontosCliente;
    private int pontosServidor;

    public void incrementarGanhos() {
        ganhos++;
    }

    public void incrementarPerdidas() {
        perdidas++;
    }

    public void incrementarJogadas() {
        jogadas++;
    }

    public void incrementarPontosCliente() {
        pontosCliente++;
    }

    public void incrementarPontosServidor() {
        pontosServidor++;
    }

    public void reiniciarRodada() {
        ganhos = 0;
        perdidas = 0;
    }

    public int getGanhos() {
        return ganhos;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public int getJogadas() {
        return jogadas;
    }

    public int getPontosCliente() {
        return pontosCliente;
    }

    public int getPontosServidor() {
        return pontosServidor;
    }

}
