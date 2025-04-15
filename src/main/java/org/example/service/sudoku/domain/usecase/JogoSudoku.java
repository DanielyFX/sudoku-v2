package org.example.service.sudoku.domain.usecase;

import org.example.service.sudoku.domain.entities.Tabuleiro;

import java.util.Random;
import java.util.Scanner;

public class JogoSudoku {

    private Tabuleiro tabuleiro;

    public JogoSudoku(Tabuleiro tabuleiro) {

        this.tabuleiro = tabuleiro;
    }

    public void inicializarTabuleiro(){
        Integer random;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(avaliaSePreenche()){
                    do {
                        random = (int) (Math.random() * 10);
                    } while (!validaNumero(i, j, random));
                    tabuleiro.getPosicoes().get(i).get(j).setValor(random == 0 ? random + 1 : random);
                    tabuleiro.getPosicoes().get(i).get(j).setFixo(true);
                }
            }

        }
    }

    public void exibeTabuleiro(){
        System.out.println("    1 2 3   4 5 6   7 8 9"); // Cabeçalho de colunas
        System.out.println("  +-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            System.out.print((i + 1) + " | "); // Índice da linha
            for (int j = 0; j < 9; j++) {
                Integer valor = tabuleiro.getPosicoes().get(i).get(j).getValor();
                if (valor == null) {
                    System.out.print("  "); // Espaço vazio
                } else {
                    System.out.print(valor + " ");
                }

                if ((j + 1) % 3 == 0) System.out.print("| ");
            }

            System.out.println();

            if ((i + 1) % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
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
        if(numero > 9 || numero < 1){
            return false;
        }
        return validaNumeroLinha(linha, numero) && validaNumeroColuna(coluna, numero) && validaNumeroQuadrante(linha, coluna, numero);
    }

    public boolean avaliaSePreenche(){
        Random random = new Random();
        int chance = random.nextInt(100); // 0 até 99
        return chance < 50;

    }

    public void jogar(int linha, int coluna, int numero){
        if (!tabuleiro.getPosicoes().get(linha).get(coluna).getFixo()) {
            tabuleiro.getPosicoes().get(linha).get(coluna).setValor(numero);
        } else {
            System.out.println("Essa posição é fixa!.");
        }
    }

    public boolean fimJogo(){
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++){
            for (int j = 0; j < tabuleiro.getPosicoes().get(i).size(); j++){
                if(tabuleiro.getPosicoes().get(i).get(j).getValor() == null){
                    return false;
                }
            }
        }
        return true;

    }

    public void comecarJogo(){
        this.inicializarTabuleiro();
        Scanner scanner = new Scanner(System.in);
        while (!fimJogo()) {
            exibeTabuleiro();
            System.out.println("Digite a linha, coluna e o número (ou -1 para sair): ");
            int linha = scanner.nextInt();
            if (linha == -1) {
                break;
            }
            int coluna = scanner.nextInt();
            int numero = scanner.nextInt();
            if (validaNumero(linha - 1, coluna - 1, numero)) {
                jogar(linha - 1, coluna - 1, numero);
            } else {
                System.out.println("Número inválido!");
            }
        }

    }

    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        JogoSudoku jogoSudoku = new JogoSudoku(tabuleiro);
        jogoSudoku.comecarJogo();
    }
}
