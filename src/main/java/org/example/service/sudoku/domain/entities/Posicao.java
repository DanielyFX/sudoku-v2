package org.example.service.sudoku.domain.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Posicao {

    public Posicao(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    private int linha;
    private int coluna;
    private Integer valor;
}
