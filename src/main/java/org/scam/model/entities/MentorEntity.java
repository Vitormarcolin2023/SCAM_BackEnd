package org.scam.model.entities;
import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.classes.Mentor;
import org.scam.controller.classes.TipoMentor;
import org.scam.controller.cadastros.StatusMentor;

import javax.persistence.*;

@Entity(name = "tb_mentor")
public class MentorEntity implements UsuarioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_usuario", nullable = false, length = 45)
    private TipoMentor tipoDeUsuario;

    @Column(name = "telefone", nullable = false, unique = true, length = 45)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMentor status = StatusMentor.ANALISE; // por padrão, todos mentor é ANALISE, para coordenção analisar

    @Column(name = "motivo_desativacao", length = 255)
    private String motivoDesativacao;

    @Column(name = "tempo_experiencia", nullable = false)
    private String tempoDeExperiencia;

    @Column(name = "tipo_de_vinculo", nullable = false)
    private String tipoDeVinculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_de_atuacao", nullable = false)
    private AreaDeAtuacao areaDeAtuacao;

    //relacao com a tabela de Endereco
    @ManyToOne
    @JoinColumn(name = "fk_endereco_id", nullable = false)
    private EnderecoEntity endereco;


    public MentorEntity(){
    }

    public MentorEntity(
        String nome, String cpf, String email, String senha,
        TipoMentor tipoDeUsuario, String telefone, StatusMentor status, String motivoDesativacao, String tempoDeExperiencia,
        String tipoDeVinculo, AreaDeAtuacao areaDeAtuacao, EnderecoEntity endereco){

          this.nome = nome;
          this.cpf = cpf;
          this.email = email;
          this.senha = senha;
          this.tipoDeUsuario = tipoDeUsuario;
          this.telefone = telefone;
          this.status = status;
          this.motivoDesativacao = motivoDesativacao;
          this.tempoDeExperiencia = tempoDeExperiencia;
          this.tipoDeVinculo = tipoDeVinculo;
          this.areaDeAtuacao = areaDeAtuacao;
          this.endereco = endereco;

    }

    //getters e setters
    public int getIdMentor(){return id;}
    public void setIdMentor(int id) {this.id = id;}

    public String getNome(){return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf(){return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public TipoMentor getTipoDeUsuario() { return tipoDeUsuario; }
    public void setTipoDeUsuario(TipoMentor tipoDeUsuario) { this.tipoDeUsuario = tipoDeUsuario; }

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public StatusMentor getStatus() {return status;}
    public void setStatus(StatusMentor status) {this.status = status;}

    public String getMotivoDesativacao(){return motivoDesativacao;}
    public void setMotivoDesativacao(String motivoDesativacao){this.motivoDesativacao = motivoDesativacao;}

    public String getTempoDeExperiencia(){return tempoDeExperiencia;}
    public void setTempoExperiencia(String tempoDeExperiencia) {this.tempoDeExperiencia = tempoDeExperiencia;}

    public String getTipoDeVinculo(){return tipoDeVinculo;}
    public void setTipoDeVinculo(String tipoDeVinculo) {this.tipoDeVinculo = tipoDeVinculo;}

    public AreaDeAtuacao getAreaDeAtuacao(){return areaDeAtuacao;}
    public void setAreaDeAtuacao(AreaDeAtuacao areaDeAtuacao) {this.areaDeAtuacao = areaDeAtuacao;}

    public EnderecoEntity getEndereco() {return endereco;}
    public void setEndereco(EnderecoEntity endereco) {this.endereco = endereco;}

    public Mentor toMentor(){
        return new Mentor(id, nome, cpf, email, senha, tipoDeUsuario, telefone, tempoDeExperiencia, tipoDeVinculo, areaDeAtuacao, endereco);
    }
}
