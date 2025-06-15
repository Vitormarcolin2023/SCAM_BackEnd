package org.scam.model.entities;

import org.scam.model.repository.StatusReuniao;
import org.scam.model.repository.TipoReuniao;
import org.scam.model.repository.TipoUsuario;

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
    private Long id;

    @Column(name = "motivo_reuniao", nullable = false, length = 500)
    private String motivoReuniao;

    @Column(name = "data_reuniao", nullable = false)
    private LocalDate dataReuniao;

    @Column(name = "horario_reuniao", nullable = false)
    private LocalTime horarioReuniao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_reuniao", nullable = false, length = 30)
    private TipoReuniao tipoReuniao;

    @Column(name = "local_reuniao", length = 100)
    private String localReuniao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reuniao", nullable = false, length = 30)
    private StatusReuniao statusReuniao = StatusReuniao.AGENDADA;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "solicitante")
    private TipoUsuario solicitante;

    @ManyToOne
    @JoinColumn(name = "fk_projeto_id") // nome da coluna no banco
    private ProjetoEntity projeto;

    public ReuniaoEntity(){}

    public ReuniaoEntity(Long id, String motivoReuniao, LocalDate dataReuniao, LocalTime horarioReuniao, TipoReuniao tipoReuniao,
                         String localReuniao, StatusReuniao statusReuniao, String motivoCancelamento,
                         ProjetoEntity projeto) {
        this.id = id;
        this.motivoReuniao = motivoReuniao;
        this.dataReuniao = dataReuniao;
        this.horarioReuniao = horarioReuniao;
        this.tipoReuniao = tipoReuniao;
        this.localReuniao = localReuniao;
        this.statusReuniao = statusReuniao;
        this.motivoCancelamento = motivoCancelamento;
        this.projeto = projeto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TipoReuniao getTipoReuniao() {
        return tipoReuniao;
    }

    public void setTipoReuniao(TipoReuniao tipoReuniao) {
        this.tipoReuniao = tipoReuniao;
    }

    public StatusReuniao getStatusReuniao() {
        return statusReuniao;
    }

    public void setStatusReuniao(StatusReuniao statusReuniao) {
        this.statusReuniao = statusReuniao;
    }

    public LocalTime getHorarioReuniao() {
        return horarioReuniao;
    }

    public void setHorarioReuniao(LocalTime horarioReuniao) {
        this.horarioReuniao = horarioReuniao;
    }

    public ProjetoEntity getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoEntity projeto) {
        this.projeto = projeto;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public TipoUsuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(TipoUsuario solicitante) {
        this.solicitante = solicitante;
    }
}

