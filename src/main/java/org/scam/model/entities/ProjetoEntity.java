package org.scam.model.entities;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.Curso;
import org.scam.model.repository.StatusProjeto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_projeto")
public class ProjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_do_projeto", nullable = false, length = 45)
    private String nomeDoProjeto;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_de_atuacao")
    private AreaDeAtuacao areaDeAtuacao;

    @Column(name = "data_inicio_projeto", nullable = false)
    private LocalDate dataInicioProjeto;

    @Column(name = "data_final_projeto", nullable = false)
    private LocalDate dataFinalProjeto;

    @Column(name = "tamanho_do_grupo")
    private int tamanhoDoGrupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "curso")
    private Curso curso;

    @Column(name = "periodo", nullable = false, length = 45)
    private String periodo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_projeto_aluno",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_ra")
    )
    private List<AlunoEntity> alunos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_mentor_id", nullable = false)
    private MentorEntity mentor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusProjeto status;

    public List<AlunoEntity> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoEntity> alunos) {
        this.alunos = alunos;
    }

    public MentorEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorEntity mentor) {
        this.mentor = mentor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoProjeto() {
        return nomeDoProjeto;
    }

    public void setNomeDoProjeto(String nomeDoProjeto) {
        this.nomeDoProjeto = nomeDoProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AreaDeAtuacao getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(AreaDeAtuacao areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public LocalDate getDataInicioProjeto() {
        return dataInicioProjeto;
    }

    public void setDataInicioProjeto(LocalDate dataInicioProjeto) {
        this.dataInicioProjeto = dataInicioProjeto;
    }

    public LocalDate getDataFinalProjeto() {
        return dataFinalProjeto;
    }

    public void setDataFinalProjeto(LocalDate dataFinalProjeto) {
        this.dataFinalProjeto = dataFinalProjeto;
    }

    public int getTamanhoDoGrupo() {
        return tamanhoDoGrupo;
    }

    public void setTamanhoDoGrupo(int tamanhoDoGrupo) {
        this.tamanhoDoGrupo = tamanhoDoGrupo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public ProjetoEntity() {
        this.status = StatusProjeto.PENDENTE;
    }

    public ProjetoEntity(Long id, String nomeDoProjeto, String descricao, AreaDeAtuacao areaDeAtuacao,
                         LocalDate dataInicioProjeto, LocalDate dataFinalProjeto, int tamanhoDoGrupo,
                         Curso curso, String periodo, List<AlunoEntity> alunos, MentorEntity mentor) {
        this.id = id;
        this.nomeDoProjeto = nomeDoProjeto;
        this.descricao = descricao;
        this.areaDeAtuacao = areaDeAtuacao;
        this.dataInicioProjeto = dataInicioProjeto;
        this.dataFinalProjeto = dataFinalProjeto;
        this.tamanhoDoGrupo = tamanhoDoGrupo;
        this.curso = curso;
        this.periodo = periodo;
        this.alunos = alunos;
        this.mentor = mentor;
        this.status = StatusProjeto.PENDENTE;
    }

    @PrePersist
    @PreUpdate
    private void validarProjeto(){
        if(dataInicioProjeto != null && dataFinalProjeto != null){
            if(dataInicioProjeto.isAfter(dataFinalProjeto)){
                throw new IllegalArgumentException("A data do início do projeto não pode ser depois da data final.");
            }
        }

        if(tamanhoDoGrupo < 2 || tamanhoDoGrupo >6){
            throw new IllegalArgumentException("A quantidade de integrantes deve ser de mínimo 2 e máximo 6.");
        }
    }

    @Override
    public String toString() {
        return this.getNomeDoProjeto();
    }
}