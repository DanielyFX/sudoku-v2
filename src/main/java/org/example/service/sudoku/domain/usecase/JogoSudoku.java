package org.example.service.sudoku.domain.usecase;

import org.example.service.sudoku.domain.entities.Tabuleiro;

import java.util.Random;

public class JogoSudoku {

    private Tabuleiro tabuleiro;

    public JogoSudoku(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void inicializarTabuleiro(){
        boolean preenche = false;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(avaliaSePreenche()){
                    do {
                        Integer random = (int) (Math.random() * 10);
                        tabuleiro.getPosicoes().get(i).get(j).setValor(random);
                    } while (!validaNumero(i, j, tabuleiro.getPosicoes().get(i).get(j).getValor()));
                }
            }

        }
    }

    public void exibeTabuleiro(){
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++){
            for (int j = 0; j < tabuleiro.getPosicoes().get(i).size(); j++){
                if(tabuleiro.getPosicoes().get(i).get(j).getValor() == null){
                    System.out.print(" ");
                } else {
                    System.out.print(tabuleiro.getPosicoes().get(i).get(j).getValor() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean validaNumeroLinha(int linha, int numero){
        for (int i = 0; i < tabuleiro.getPosicoes().get(linha).size(); i++) {
            if (tabuleiro.getPosicoes().get(linha).get(i).getValor() != null && tabuleiro.getPosicoes().get(linha).get(i).getValor().equals(numero)) {
                return false;
            }
        }
        return true;
    }

    public boolean validaNumeroColuna(int coluna, int numero){
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).get(coluna).getValor() != null && tabuleiro.getPosicoes().get(i).get(coluna).getValor().equals(numero)) {
                return false;
            }
        }
        return true;
    }

    public boolean validaNumeroQuadrante(int linha, int coluna, int numero){
        int linhaQuadrante = linha / 3;
        int colunaQuadrante = coluna / 3;
        for (int i = linhaQuadrante * 3; i < (linhaQuadrante + 1) * 3; i++) {
            for (int j = colunaQuadrante * 3; j < (colunaQuadrante + 1) * 3; j++) {
                if (tabuleiro.getPosicoes().get(i).get(j).getValor() != null && tabuleiro.getPosicoes().get(i).get(j).getValor().equals(numero)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validaNumero(int linha, int coluna, int numero) {
        return !validaNumeroLinha(linha, numero) && !validaNumeroColuna(coluna, numero) && !validaNumeroQuadrante(linha, coluna, numero);
    }

    public boolean avaliaSePreenche(){
        Random random = new Random();
        int chance = random.nextInt(100); // 0 até 99
        return chance < 40;

    }

    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        JogoSudoku jogoSudoku = new JogoSudoku(tabuleiro);
        jogoSudoku.inicializarTabuleiro();
        jogoSudoku.exibeTabuleiro();
    }
}
