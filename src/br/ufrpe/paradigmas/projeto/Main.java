package br.ufrpe.paradigmas.projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

/**
 * VERS�O 2 - POKER 
 * - ALTERADOS ATRIBUTOS DO TIPO BYTE PARA TIPO INT
 * 
 * @author KENEDY FELIPE - MARCOS INACIO
 *
 */
public class Main {

	private static BufferedReader in;
	private static PrintWriter out;

	public static void main(String[] args) throws IOException {
		
		// TEMPO DO PROGRAMA
		long tempoInicial = System.currentTimeMillis();

		// CRIA��O DOS ARQUIVOS DE LEITURA E ESCRITA
		in = new BufferedReader(new FileReader("ArquivosEntrada/pokerM.txt"));
		out = new PrintWriter(new FileWriter("ArquivosEntrada/pokerM_out.txt"));

		// JOGADORES
		Player p1 = new Player();
		Player p2 = new Player();

		String poker = in.readLine();
		int value;
		int vitoriasP1 = 0;

		do {
			for (int i = 0; i < poker.length(); i++) {
				// INSER��O DAS CARTAS DO PRIMEIRO JOGADOR
				if (i < poker.length() / 2) {
					// VERIFICA��O DO TIPO DE CARTA (A, K, Q, J ,T)
					value = verificaNumero(poker.charAt(i++));
					p1.setCarta(value, poker.charAt(i++));
				}

				// INSER��O DAS CARTAS DO SEGUNDO JOGADOR
				if ((i > poker.length() / 2)) {
					value = verificaNumero(poker.charAt(i++));
					p2.setCarta(value, poker.charAt(i++));
				}
			}

			// ORDENA��O DAS CARTAS DOS JOGADORES
			Collections.sort(p1.carta, new ComparadorDeCartas());
			Collections.sort(p2.carta, new ComparadorDeCartas());

			// EXECU��O DA VERIFICA��O DAS MA�S DE POKER
			p1.ranking.rankingMao(p1);
			p2.ranking.rankingMao(p2);
			vitoriasP1 += verificaVencedor(p1, p2);

			// LIMPEZA DO ARRAY PARA NOVAS PARTIDAS
			p1 = new Player();
			p2 = new Player();

			// LEITURA DA PR�XIMA LINHA DO ARQUIVO
			poker = in.readLine();
			
		} while (poker != null);
		
		out.println(vitoriasP1);
		out.print(System.currentTimeMillis() - tempoInicial);
		out.close();
	}
	
	// VERIFICA��O DA M�O VENCEDORA
	public static int verificaVencedor(Player p1, Player p2) {
		int cardP1 = (int) p1.mao.get(p1.mao.size() - 1).ordinal();
		int cardP2 = (int) p2.mao.get(p2.mao.size() - 1).ordinal();

		// RANKING DE M�OS
		if (cardP1 > cardP2)
			return 1;
		else if (cardP1 < cardP2)
			return 0;
		
		// VALOR DA CARTA DO RANKING DE M�OS
		else if (p1.getCartaRanking() > p2.getCartaRanking())
			return 1;
		else if (p1.getCartaRanking() < p2.getCartaRanking())
			return 0;

		// VALOR DA CARTA MAIOR
		else if (p1.getCartaAlta() > p2.getCartaAlta())
			return 1;
		else if (p1.getCartaAlta() < p2.getCartaAlta())
			return 0;

		// VALOR DA CARTA DESEMPATE
		else if (p1.getKicker() > p2.getKicker())
			return 1;
		else if (p1.getKicker() < p2.getKicker())
			return 0;
		
		// CASO EMPATE
		return 0;

	}

	// FUN��O PARA ADICIONAR O VALOR REAL DAS CARTAS (A, K, Q, J, T)
	private static int verificaNumero(char c) {
		int number = 0;

		switch (c) {
		case 'A':
			number = 14;
			break;
		case 'K':
			number = 13;
			break;
		case 'Q':
			number = 12;
			break;
		case 'J':
			number = 11;
			break;
		case 'T':
			number = 10;
			break;
		default:
			number = Character.getNumericValue(c);
		}
		return number;
	}

	// IMPRESS�O DAS INFORMA��ES DOS JOGADORES
	public static void printJogadores(Player p1, Player p2, PrintWriter out) {
		// JOGADOR 1
		out.print("M�o Player 1: " + p1.mao.toString() + "\nCartas: ");
		for (Carta card : p1.carta) {
			out.print(card.getValor() + "" + card.getNaipe() + " ");
		}
		out.println("\nCarta Ranking: " + p1.getCartaRanking());
		out.println("Carta Alta: " + p1.getCartaAlta());
		out.println();

		// JOGADOR 2
		out.print("M�o Player 2: " + p2.mao.toString() + "\nCartas: ");
		for (Carta card : p2.carta) {
			out.print(card.getValor() + "" + card.getNaipe() + " ");
		}
		out.println("\nCarta Ranking: " + p2.getCartaRanking());
		out.println("Carta Alta: " + p2.getCartaAlta());
		out.println();
	}
}
