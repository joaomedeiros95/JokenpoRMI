package br.ufrn.jokenpocliente;

import br.ufrn.jokenpo.interaces.Estatisticas;
import br.ufrn.jokenpo.interaces.Jogada;
import br.ufrn.jokenpo.interaces.Jokenpo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by joao on 29/04/16.
 */
public class JokenpoCliente {

    private static final String IP = "localhost";
    private static final String METHOD = "JokenpoServer";
    private static Jokenpo jokenpo;

    public static void main(String[] args) {
        try {
            System.setSecurityManager(new SecurityManager());

            String serverURL="rmi://" + IP + "/" + METHOD;
            jokenpo = (Jokenpo) Naming.lookup(serverURL);

            iniciarJogo();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void iniciarJogo() throws RemoteException {
        System.out.println("Jogo iniciado!");
        while(true) {
            System.out.println("\n=========================== Digite uma opção ===========================");
            System.out.println("1- Jogar Pedra\n2-Jogar Papel\n3-Jogar Tesoura\n4- Ver estatísticas\n5-Sair");
            Scanner scanner = new Scanner(System.in);


            Integer opcao = scanner.nextInt();

            if(opcao <= 3) {
                System.out.println("\n=========================== Resultado ===========================");
            }

            switch (opcao) {
                case 1:
                    System.out.println(jokenpo.fazerJogada(Jogada.PEDRA));
                    break;
                case 2:
                    System.out.println(jokenpo.fazerJogada(Jogada.PAPEL));
                    break;
                case 3:
                    System.out.println(jokenpo.fazerJogada(Jogada.TESOURA));
                    break;
                case 4:
                    showEstatisticas(jokenpo.getEstatisticas());
                    break;
                case 5:
                    System.out.println("Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            if(opcao == 5) {
                break;
            }
        }

    }

    private static void showEstatisticas(Estatisticas estatisticas) {
        System.out.println("\n=========================== Estatísticas ===========================");
        System.out.println("Jogados: " + estatisticas.getJogadas());
        System.out.println("Jogos Panhos (Rodada): " + estatisticas.getGanhos());
        System.out.println("Jogos Perdidos (Rodada): " + estatisticas.getPerdidas());
        System.out.println("Pontos Cliente: " + estatisticas.getPontosCliente());
        System.out.println("Pontos Servidor: " + estatisticas.getPontosServidor());
    }

}
