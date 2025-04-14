package org.scam.entities;

import org.scam.cadastros.AreaDeAtuacao;
import org.scam.cadastros.Curso;
import org.scam.classes.Aluno;
import org.scam.classes.Endereco;
import org.scam.classes.Mentor;

import javax.persistence.*;

@Entity
@Table(name = "tb_mentor")
public class MentorEntity implements UsuarioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "tipo_de_usuario", nullable = false, length = 45)
    private String tipoDeUsuario;

    @Column(name = "telefone", nullable = false, unique = true, length = 45)
    private String telefone;

    @Column(name = "tempo_experiencia", nullable = false)
    private String tempoDeExperiencia;

    @Column(name = "tipo_de_vinculo", nullable = false)
    private String tipoDeVinculo;

    @Column(name = "area_de_atuacao", nullable = false)
    private String areaDeAtuacao;

    //relacao com a tabela de Endereco
    @ManyToOne
    @JoinColumn(name = "fk_endereco_id", nullable = false)
    private EnderecoEntity endereco;

    public MentorEntity(){
    }

    public MentorEntity(
        String nome, String cpf, String email, String senha,
        String tipoDeUsuario, String telefone, String tempoDeExperiencia,
        String tipoDeVinculo, String areaDeAtuacao, EnderecoEntity endereco){

          this.nome = nome;
          this.cpf = cpf;
          this.email = email;
          this.senha = senha;
          this.tipoDeUsuario = tipoDeUsuario;
          this.telefone = telefone;
          this.tempoDeExperiencia = tempoDeExperiencia;
          this.tipoDeVinculo = tipoDeVinculo;
          this.areaDeAtuacao = areaDeAtuacao;
          this.endereco = endereco;

    }

    public MentorEntity(Object o, String nomeProjeto, String descricaoProjeto, AreaDeAtuacao tipoAreaDeAtucao, String dataInicio, String dataFinal, int qtdParticipante, Curso tipoCurso, String periodo, int raAluno, int mentor) {
    }

    //getters e setters
    public Long getIdMentor(){return id;}
    public void setIdMentor(Long id) {this.id = id;}

    public String getNome(){return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf(){return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getTipoDeUsuario(){return tipoDeUsuario;}
    public void setTipoDeUsuario(String tipoDeUsuario) {this.tipoDeUsuario = tipoDeUsuario;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getTempoDeExperiencia(){return tempoDeExperiencia;}
    public void setTempoExperiencia(String tempoDeExperiencia) {this.tempoDeExperiencia = tempoDeExperiencia;}

    public String getTipoDeVinculo(){return tipoDeVinculo;}
    public void setTipoDeVinculo(String tipoDeVinculo) {this.tipoDeVinculo = tipoDeVinculo;}

    public String getAreaDeAtuacao(){return areaDeAtuacao;}
    public void setAreaDeAtuacao(String areaDeAtuacao) {this.areaDeAtuacao = areaDeAtuacao;}

    public EnderecoEntity getEndereco() {return endereco;}
    public void setEndereco(EnderecoEntity endereco) {this.endereco = endereco;}

    public Mentor toMentor(){
        return new Mentor(id, nome, cpf, email, senha, tipoDeUsuario, telefone, tempoDeExperiencia, tipoDeVinculo, areaDeAtuacao, endereco);
    }
}
