package org.scam.controller.cadastros;

import org.scam.controller.classes.Endereco;
import org.scam.controller.classes.Mentor;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.entities.EnderecoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.services.BuscarCEP;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class MentorCadastro {

    public void salvarMentorPelaView(
            String nome, String cpf, String email, String senha, String telefone,
            String tempoExperiencia, String tipoVinculo, AreaDeAtuacao areaDeAtuacao, TipoMentor tipoMentor,
            String cep, String rua, int numero, String bairro, String cidade, String estado
    ) throws Exception {

        EntityManager em = CustomizerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            EnderecoEntity enderecoEntity = new EnderecoEntity();
            enderecoEntity.setRua(rua);
            enderecoEntity.setNumero(numero);
            enderecoEntity.setBairro(bairro);
            enderecoEntity.setCidade(cidade);
            enderecoEntity.setEstado(estado);
            enderecoEntity.setCep(cep);
            em.persist(enderecoEntity);

            MentorEntity mentor = new MentorEntity();
            mentor.setNome(nome);
            mentor.setCpf(cpf);
            mentor.setEmail(email);
            mentor.setSenha(senha);
            mentor.setTelefone(telefone);
            mentor.setTempoExperiencia(tempoExperiencia);
            mentor.setTipoDeVinculo(tipoVinculo);
            mentor.setAreaDeAtuacao(areaDeAtuacao);
            mentor.setTipoDeUsuario(tipoMentor);
            mentor.setEndereco(enderecoEntity);
            em.persist(mentor);

            tx.commit();

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new Exception("Erro ao salvar mentor no banco de dados: " + e.getMessage());

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void cadastrarMentor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== CADASTRO DE MENTOR ===");
    }

    public void editarMentor() {
        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository repository = new MentorRepository(em);
    }
}