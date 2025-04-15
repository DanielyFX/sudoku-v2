package org.example.service.sudoku.domain.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tabuleiro {
    private final List<List<Posicao>> posicoes;

    public Tabuleiro(){
        posicoes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Posicao> linha = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                linha.add(new Posicao(i, j, false)); // ou outro construtor
            }
            posicoes.add(linha);
        }
    }
}
