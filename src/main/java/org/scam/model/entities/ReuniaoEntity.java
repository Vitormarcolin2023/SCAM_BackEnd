package org.scam.model.entities;

import org.scam.model.repository.StatusReuniao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_reuniao")
public class ReuniaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "motivo_reuniao", nullable = false, length = 500)
    private String motivoReuniao;

    @Column(name = "data_reuniao", nullable = false)
    private LocalDate dataReuniao;

    @Column(name = "horario_reuniao", nullable = false)
    private LocalTime horarioReuniao;

    @Column(name = "local_reuniao", nullable = false, length = 100)
    private String localReuniao;

    @Column(name = "status_reuniao", nullable = false)
    private StatusReuniao statusReuniao = StatusReuniao.AGENDADA;

    @ManyToOne
    @JoinColumn(name = "fk_mentor_id", nullable = false)
    private MentorEntity mentor;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tb_reuniao_aluno",
            joinColumns = @JoinColumn(name = "id_reuniao"),
            inverseJoinColumns = @JoinColumn(name = "ra_aluno")
    )
    private List<AlunoEntity> alunos = new ArrayList<>();

    public ReuniaoEntity(int id, String motivoReuniao, LocalDate dataReuniao, String localReuniao, MentorEntity mentor, List<AlunoEntity> alunos) {
        this.id = id;
        this.motivoReuniao = motivoReuniao;
        this.dataReuniao = dataReuniao;
        this.localReuniao = localReuniao;
        this.mentor = mentor;
        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivoReuniao() {
        return motivoReuniao;
    }

    public void setMotivoReuniao(String motivoReuniao) {
        this.motivoReuniao = motivoReuniao;
    }

    public LocalDate getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(LocalDate dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public String getLocalReuniao() {
        return localReuniao;
    }

    public void setLocalReuniao(String localReuniao) {
        this.localReuniao = localReuniao;
    }

    public MentorEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorEntity mentor) {
        this.mentor = mentor;
    }

    public List<AlunoEntity> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoEntity> alunos) {
        this.alunos = alunos;
    }

    public StatusReuniao getStatusReuniao() {
        return statusReuniao;
    }

    public void setStatusReuniao(StatusReuniao statusReuniao) {
        this.statusReuniao = statusReuniao;
    }
}
