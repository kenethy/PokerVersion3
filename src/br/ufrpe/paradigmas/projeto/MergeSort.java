package br.ufrpe.paradigmas.projeto;

import java.util.ArrayList;

public class MergeSort {

	private ArrayList<Carta> carta = new ArrayList<Carta>();

	public void sort(ArrayList<Carta> carta) {
		this.carta = carta;
	}

	public void mergeSort() {
		mergeDivide(0, this.carta.size() - 1);
	}

	public void mergeDivide(int indexMenor, int indexMaior) {
		if (indexMenor < indexMaior && (indexMaior - indexMenor) >= 1) {
			int indexMeio = (indexMenor + indexMaior) / 2;
			mergeDivide(indexMenor, indexMeio);
			mergeDivide(indexMeio + 1, indexMaior);
			merge(indexMenor, indexMeio, indexMaior);
		}
	}

	public void merge(int indexMenor, int indexMeio, int indexMaior) {
		ArrayList<Carta> mergeCard = new ArrayList<Carta>();

		int indexL = indexMenor;
		int indexR = indexMeio + 1;

		while (indexL <= indexMeio && indexR <= indexMaior) {
			if (carta.get(indexL).getValor() <= carta.get(indexR).getValor()) {
				mergeCard.add(carta.get(indexL));
				indexL++;
			} else {
				mergeCard.add(carta.get(indexR));
				indexR++;
			}
		}

		while (indexL <= indexMeio) {
			mergeCard.add(carta.get(indexL));
			indexL++;
		}

		while (indexR <= indexMaior) {
			mergeCard.add(carta.get(indexR));
			indexR++;
		}

		int i = 0;
		int j = indexMenor;

		while (i < mergeCard.size()) {
			carta.set(j, mergeCard.get(i++));
			j++;
		}
	}
}