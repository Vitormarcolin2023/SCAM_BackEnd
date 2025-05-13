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

    @Column(name = "fk_curso_id")
    private int cursoId;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public AlunoEntity() {

    }

    public AlunoEntity(String nome, int ra, String senha, int cursoId, String email) {
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
        this.cursoId = cursoId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
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

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Aluno toAluno() {
        return new Aluno(nome, email, senha, id.intValue(), ra);
    }
}
