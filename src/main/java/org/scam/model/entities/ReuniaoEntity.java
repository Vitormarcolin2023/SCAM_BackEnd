package org.scam.model.entities;

import org.scam.model.repository.StatusReuniao;
import org.scam.model.repository.TipoReuniao;

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

    @Column(name = "tipo_reuniao", nullable = false, length = 30)
    private TipoReuniao tipoReuniao;

    @Column(name = "local_reuniao", length = 100)
    private String localReuniao;

    @Column(name = "status_reuniao", nullable = false, length = 30)
    private StatusReuniao statusReuniao = StatusReuniao.AGENDADA;

    @Column(name = "confirmada", nullable = false)
    private boolean reuniaoConfirmada;

    @ManyToOne
    @JoinColumn(name = "fk_projeto_id") // nome da coluna no banco
    private ProjetoEntity projeto;

    public ReuniaoEntity(){}

    public ReuniaoEntity(int id, String motivoReuniao, LocalDate dataReuniao, LocalTime horarioReuniao, TipoReuniao tipoReuniao,
                         String localReuniao, StatusReuniao statusReuniao, boolean reuniaoConfirmada, ProjetoEntity projeto) {
        this.id = id;
        this.motivoReuniao = motivoReuniao;
        this.dataReuniao = dataReuniao;
        this.horarioReuniao = horarioReuniao;
        this.tipoReuniao = tipoReuniao;
        this.localReuniao = localReuniao;
        this.statusReuniao = statusReuniao;
        this.reuniaoConfirmada = reuniaoConfirmada;
        this.projeto = projeto;
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

    public boolean isReuniaoConfirmada() {
        return reuniaoConfirmada;
    }

    public void setReuniaoConfirmada(boolean reuniaoConfirmada) {
        this.reuniaoConfirmada = reuniaoConfirmada;
    }

    public ProjetoEntity getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoEntity projeto) {
        this.projeto = projeto;
    }
}

