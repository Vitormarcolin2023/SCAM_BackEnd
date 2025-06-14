    package org.scam.model.services;

    import org.scam.model.entities.MentorEntity;
    import org.scam.model.entities.ProjetoEntity;
    import org.scam.model.entities.ReuniaoEntity;
    import org.scam.model.repository.*;

    import javax.persistence.EntityManager;
    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.util.ArrayList;
    import java.util.List;

    public class ReuniaoService {

        private static EntityManager em = CustomizerFactory.getEntityManager();
        private static ReuniaoRepository reuniaoRepository = new ReuniaoRepository(em);
        private static ProjetoRepository projetoRepository = new ProjetoRepository(em);

        public static List<ReuniaoEntity> getReunioes(List<ProjetoEntity> projetos){

            ReuniaoRepository reuniaoRepository = new ReuniaoRepository(em);
            ProjetoRepository projetoRepository = new ProjetoRepository(em);

            List<ReuniaoEntity> reunioes = new ArrayList<>();
            projetos.forEach(projeto -> {
                reunioes.addAll(reuniaoRepository.buscarReunioes(projeto.getId()));
            });
            return reunioes;
        }

        public static List<ProjetoEntity> buscarProjetosAluno(int ra){
            List<ProjetoEntity> projetos = projetoRepository.buscarTodos(ra);
            return projetos;
        }

        public static List<ProjetoEntity> buscarProjetosMentor(int id){
            MentorEntity mentorEntity = em.find(MentorEntity.class, id);
            List<ProjetoEntity> projetos = projetoRepository.findByMentor(mentorEntity);
            return projetos;
        }

        public static boolean agendarReuniao(String motivo, LocalDate data, LocalTime hora, String local, TipoReuniao tipo, ProjetoEntity projeto, TipoUsuario tipoUsuario){

            ReuniaoEntity novaReuniao = new ReuniaoEntity();

            novaReuniao.setMotivoReuniao(motivo);
            novaReuniao.setDataReuniao(data);
            novaReuniao.setHorarioReuniao(hora);
            novaReuniao.setLocalReuniao(local);
            novaReuniao.setTipoReuniao(tipo);
            novaReuniao.setProjeto(projeto);
            novaReuniao.setStatusReuniao(StatusReuniao.AGUARDANDO_CONFIRMACAO);
            novaReuniao.setMotivoCancelamento(null);
            novaReuniao.setSolicitante(tipoUsuario);

            if(reuniaoRepository.salvar(novaReuniao)){
                return true;
            }
            else {
                return false;
            }
        }

        public static boolean alterarStatus(long idProjeto, StatusReuniao novoStatus){
            if(reuniaoRepository.alterarStatus(idProjeto, novoStatus)){
                return true;
            }
            return false;
        }

        public static boolean cancelarReuniao(long id, String motivo){
            if(reuniaoRepository.cancelarPorId(id, motivo)){
                return true;
            }
            else return false;
        }
    }
