package org.scam.entities;

import org.scam.classes.Aluno;
import org.scam.classes.Coordenador;

import javax.persistence.*;

@Entity(name = "tb_coordenacao")
public class CoordenacaoEntity implements UsuarioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL no PostgreSQL = IDENTITY
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    //construtor vazio
    public CoordenacaoEntity(){
    }

    //construtor com todos os campos
    public CoordenacaoEntity(Long id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //getters e setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha(){return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public Coordenador toCoordenador(){
        return new Coordenador(nome, email, senha);
    }

}