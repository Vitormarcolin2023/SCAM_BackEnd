package org.scam.cadastros;


import org.scam.entities.AreaDeAtuacaoEntity;
import org.scam.repository.AreaDeAtuacaoRepository;
import org.scam.repository.CustomizerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Scanner;

public class AreaDeAtuacaoCadastro {

    public void cadastrarAreaDeAtuacao() {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = CustomizerFactory.getEntityManager();
        AreaDeAtuacaoRepository areaDeAtuacaoRepository = new AreaDeAtuacaoRepository(em);

        System.out.print("Nome da Área de Atuação: ");
        String nomeArea = scanner.nextLine();

        AreaDeAtuacaoEntity novaArea = new AreaDeAtuacaoEntity();
        novaArea.setDescricaoAreaDeAtuacao(nomeArea);

        try {
            areaDeAtuacaoRepository.salvar(novaArea);
            System.out.println("\nÁrea de atuação cadastrada com sucesso!"); // Mensagem mais específica
        } catch (PersistenceException e) {
            System.err.println("\nErro ao cadastrar área de atuação: " + e.getMessage());
        } finally {
            em.close(); // Importante: Fechar o EntityManager
        }
    }
}
