package org.scam.model.entities;

import org.scam.controller.classes.Aluno;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_aluno")
public class AlunoEntity implements UsuarioEntity   {

    @Id
    @Column(name = "ra", nullable = false, unique = true)
    private int ra;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "alunos")
    private List<ProjetoEntity> projetos = new ArrayList<>();

    public List<ProjetoEntity> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<ProjetoEntity> projetos) {
        this.projetos = projetos;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AlunoEntity(Long id, String nome, int ra, String senha, String email) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
        this.email = email;
    }

    public Aluno toAluno() {
        return new Aluno(nome, email, senha, id.intValue(), ra);
    }
}
