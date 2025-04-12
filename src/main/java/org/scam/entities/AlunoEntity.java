package org.scam.entities;

import org.scam.classes.Aluno;

import javax.persistence.*;

@Entity(name = "tb_aluno")
public class AlunoEntity implements UsuarioEntity   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ra", nullable = false, unique = true)
    private int ra;

    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public Long getIdAluno() {
        return id;
    }

    public void setIdAluno(Long idAluno) {
        this.id = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AlunoEntity() {

    }
    public AlunoEntity(Long idAluno, String nome, int ra, String senha) {
        this.id = idAluno;
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
    }
}
