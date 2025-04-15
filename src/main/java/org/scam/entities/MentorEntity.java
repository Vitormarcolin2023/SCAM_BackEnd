package org.scam.entities;
import org.scam.cadastros.AreaDeAtuacao;
import org.scam.classes.Mentor;
import org.scam.classes.TipoMentor;

import javax.persistence.*;

@Entity(name = "tb_mentor")
public class MentorEntity implements UsuarioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        TipoMentor tipoDeUsuario, String telefone, String tempoDeExperiencia,
        String tipoDeVinculo, AreaDeAtuacao areaDeAtuacao, EnderecoEntity endereco){

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

    public TipoMentor getTipoDeUsuario() { return tipoDeUsuario; }
    public void setTipoDeUsuario(TipoMentor tipoDeUsuario) { this.tipoDeUsuario = tipoDeUsuario; }

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

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
