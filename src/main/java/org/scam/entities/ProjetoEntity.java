package org.scam.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_projeto")
public class ProjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_do_projeto", nullable = false, length = 45)
    private String nomeDoProjeto;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "area_de_atuacao", nullable = false, length = 45)
    private String areaDeAtuacao;

    @Column(name = "data_inicio_projeto", nullable = false)
    private LocalDate dataInicioProjeto;

    @Column(name = "data_final_projeto", nullable = false)
    private LocalDate dataFinalProjeto;

    @Column(name = "tamanho_do_grupo", nullable = false)
    private int tamanhoDoGrupo;

    @Column(name = "curso", nullable = false, length = 45)
    private String curso;

    @Column(name = "periodo", nullable = false, length = 45)
    private String periodo;

    //foreign keys
    @ManyToOne
    @JoinColumn(name = "fk_aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "fk_mentor_id", nullable = false)
    private MentorEntity mentor;

    //getters e setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNomeDoProjeto() {return nomeDoProjeto;}
    public void setNomeDoProjeto(String nomeDoProjeto) {this.nomeDoProjeto = nomeDoProjeto;}

    public String getDescricao(){return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getAreaDeAtuacao() {return areaDeAtuacao;}
    public void setAreaDeAtuacao(String areaDeAtuacao) {this.areaDeAtuacao = areaDeAtuacao;}

    public LocalDate getDataInicioProjeto() {return dataInicioProjeto;}
    public void setDataInicioProjeto(LocalDate dataInicioProjeto) {this.dataInicioProjeto = dataInicioProjeto;}

    public LocalDate getDataFinalProjeto() {return dataFinalProjeto;}
    public void setDataFinalProjeto(LocalDate dataFinalProjeto) {this.dataFinalProjeto = dataFinalProjeto;}

    public int getTamanhoDoGrupo() {return tamanhoDoGrupo;}
    public void setTamanhoDoGrupo(int tamanhoDoGrupo) {this.tamanhoDoGrupo = tamanhoDoGrupo;}

    public String getCurso(){return curso;}
    public void setCurso(String curso) {this.curso = curso;}

    public String getPeriodo() {return periodo;}
    public void setPeriodo(String periodo) {this.periodo = periodo;}

    public AlunoEntity getAluno() {return aluno;}
    public void setAluno(AlunoEntity aluno) {this.aluno = aluno;}

    public MentorEntity getMentor() {return mentor;}
    public void setMentor(MentorEntity mentor) {this.mentor = mentor;}

    public ProjetoEntity(Long id, String nomeDoProjeto, String descricao, String areaDeAtuacao, LocalDate dataInicioProjeto, LocalDate dataFinalProjeto, int tamanhoDoGrupo, String curso, String periodo, AlunoEntity aluno, MentorEntity mentor) {
        this.id = id;
        this.nomeDoProjeto = nomeDoProjeto;
        this.descricao = descricao;
        this.areaDeAtuacao = areaDeAtuacao;
        this.dataInicioProjeto = dataInicioProjeto;
        this.dataFinalProjeto = dataFinalProjeto;
        this.tamanhoDoGrupo = tamanhoDoGrupo;
        this.curso = curso;
        this.periodo = periodo;
        this.aluno = aluno;
        this.mentor = mentor;
    }
}
