package org.scam.entities;

import javax.persistence.*;

@Entity(name = "tb_aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAluno;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ra", nullable = false, unique = true)
    private String ra;

    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public AlunoEntity() {

    }
    public AlunoEntity(Long idAluno, String nome, String ra, String senha) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
    }
}
