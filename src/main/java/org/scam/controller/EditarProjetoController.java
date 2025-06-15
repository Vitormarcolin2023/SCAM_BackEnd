package org.scam.controller;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.services.Sessao;
import org.scam.view.aluno.EditarProjetoView;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class EditarProjetoController {

    private final EditarProjetoView view;
    private final EntityManager em;
    private final ProjetoRepository projetoRepository;
    private final MentorRepository mentorRepository;

    //CONSTRUTOR QUE INICIALIAZA OS REPOSITORIOS E A VIEW
    public EditarProjetoController(EditarProjetoView view) {
        this.view = view;
        this.em = CustomizerFactory.getEntityManager();
        this.projetoRepository = new ProjetoRepository(em);
        this.mentorRepository = new MentorRepository(em);
    }

    //METODO PRINCIPAL DE INICIALIZACAO
    public void inicializar() {
        carregarProjetosDoAluno();
        adicionarAlunoDaSessao();
    }
    //CARREGA TODOS OS PROJETOS DO ALUNO ATUALMENTE LOGADO NA SESSAO
    public void carregarProjetosDoAluno() {
        try {
            int raAluno = Sessao.getRaAluno();
            List<ProjetoEntity> projetos = projetoRepository.buscarTodos(raAluno);
            view.setProjetos(projetos);

            if (!projetos.isEmpty()) {
                view.setDadosProjetoSelecionado(projetos.get(0));
            }
        } catch (Exception e) {
            view.showMensagem("Erro ao carregar projetos: " + e.getMessage());
        }
    }
    //ADICIONA O ALUNO OGADO NA LISTA DE ALUNOS DO PROJETO, SE AINDA NAO ESTIVER
    public void adicionarAlunoDaSessao() {
        try {
            int raAlunoLogado = Sessao.getRaAluno();
            AlunoEntity aluno = em.find(AlunoEntity.class, raAlunoLogado);
            if (aluno != null && !view.alunoJaAdicionado(aluno)) {
                view.adicionarAlunoNaLista(aluno);
            }
        } catch (Exception e) {
            view.showMensagem("Erro ao buscar aluno da sessão: " + e.getMessage());
        }
    }

    //ADICIONA UM ALUNO A LISTA COM BASE NO RA INFORMADO
    public void adicionarAlunoPorRa(String raText) {
        if (raText == null || raText.trim().isEmpty()) {
            view.showMensagem("Digite o RA do aluno.");
            return;
        }

        try {
            int ra = Integer.parseInt(raText.trim());
            AlunoEntity aluno = em.find(AlunoEntity.class, ra);

            if (aluno != null) {
                if (!view.alunoJaAdicionado(aluno)) {
                    view.adicionarAlunoNaLista(aluno);
                    view.limparCampoRa();
                } else {
                    view.showMensagem("Este aluno já foi adicionado.");
                }
            } else {
                view.showMensagem("Aluno com RA " + ra + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            view.showMensagem("O RA deve ser numérico.");
        }
    }
    //CARREGA MENTORES COM BASE NA AREA DE ATUACAO SELECIONADA
    public void carregarMentoresPorArea(AreaDeAtuacao area) {
        if (area != null) {
            List<MentorEntity> mentores = mentorRepository.buscarMentoresPorAreaDeAtuacao(area);
            view.setMentores(mentores);
        }
    }
    //ATUALIZA OS CAMPOS DA INTERFACE COM OS DADOS DO PROJETO SELECIONADO
    public void preencherCamposComProjeto(ProjetoEntity projeto) {
        if (projeto != null) {
            view.setDadosProjetoSelecionado(projeto);
        }
    }
    //ATUALIZA AS INFORMACOES DO PROJETO SELECIONADO COM OS DADOS DA TELA
    public void atualizarProjeto() {
        ProjetoEntity projeto = view.getProjetoSelecionado();

        if (projeto == null) {
            view.showMensagem("Selecione um projeto para atualizar.");
            return;
        }

        if (view.getProjetoSelecionado().getNomeDoProjeto().isEmpty() || view.getDescricaoProjeto().isEmpty()) {
            view.showMensagem("Preencha todos os campos obrigatórios.");
            return;
        }

        MentorEntity mentor = view.getProjetoSelecionado().getMentor();
        if (mentor == null) {
            view.showMensagem("Selecione um mentor para o projeto.");
            return;
        }

        try {
            LocalDate dataInicio = view.getDataSelecionada();

            //ATUALIZA OS DADOS DO PROJETO COM BASE NOS INPUTS DA TELA
            projeto.setNomeDoProjeto(view.getNomeProjeto());
            projeto.setDescricao(view.getDescricaoProjeto());
            projeto.setAreaDeAtuacao(view.getAreaSelecionada());
            projeto.setCurso(view.getCursoSelecionado());
            projeto.setPeriodo(view.getPeriodoSelecionado());
            projeto.setDataInicioProjeto(dataInicio);
            projeto.setDataFinalProjeto(dataInicio.plusMonths(5));
            projeto.setMentor(mentor);
            projeto.setAlunos(view.getAlunosSelecionados());
            projeto.setTamanhoDoGrupo(view.getAlunosSelecionados().size());

            // TENTA SALVAR NO BANCO
            if (projetoRepository.salvar(projeto)) {
                view.showMensagem("Projeto atualizado com sucesso!");
                AlunoController.notificarObservers(); // Notifica os observers
                ((JInternalFrame) view).dispose();
            } else {
                view.showMensagem("Erro ao atualizar o projeto.");
            }
        } catch (Exception e) {
            view.showMensagem("Erro ao salvar: " + e.getMessage());
        }
    }
}