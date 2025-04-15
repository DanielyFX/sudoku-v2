package org.example.service.sudoku.domain.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Posicao {

    public Posicao() {

    }

    public Posicao(int linha, int coluna, boolean fixo) {
        this.linha = linha;
        this.coluna = coluna;
        this.fixo  = fixo;
    }



    private int linha;
    private boolean fixo;
    private int coluna;
    private Integer valor;

    public boolean getFixo() {
        return this.fixo;
    }
}
