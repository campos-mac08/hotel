package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Estados")
public class Estado {

    @Id
    @Column(name = "sigla", nullable = false, length = 2)
    private String sigla;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @OneToMany( mappedBy = "estado", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Cidade> cidades = new ArrayList<>();

    public Estado() {}

    public Estado(String nome, String sigla) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;
        return sigla.equals(estado.sigla);
    }

    @Override
    public int hashCode() {
        return sigla.hashCode();
    }
}

