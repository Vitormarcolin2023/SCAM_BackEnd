package org.scam.cadastros;

import org.scam.entities.AreaDeAtuacaoEntity;
import org.scam.entities.CursoEntity;
import org.scam.repository.AreaDeAtuacaoRepository;
import org.scam.repository.CursoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class CursoCadastro {

    public void cadastrarCurso() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = CustomizerFactory.getEntityManager();

        try {
            // Repositórios
            CursoRepository cursoRepository = new CursoRepository(em);
            AreaDeAtuacaoRepository areaDeAtuacaoRepository = new AreaDeAtuacaoRepository(em);

            // Listar áreas de atuação
            List<AreaDeAtuacaoEntity> areasDeAtuacao = areaDeAtuacaoRepository.listarTodas();
            System.out.println("\nÁreas de Atuação disponíveis:");
            for (AreaDeAtuacaoEntity area : areasDeAtuacao) {
                System.out.println(area.getId() + " - " + area.getDescricaoAreaDeAtuacao());
            }

            // Obter o ID da área de atuação do usuário
            System.out.print("\nDigite o ID da Área de Atuação: ");
            int idAreaAtuacao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha deixada pelo nextLong()


            System.out.print("Nome do Curso: ");
            String nomeCurso = scanner.nextLine();

            CursoEntity novoCurso = new CursoEntity();
            novoCurso.setDescricaoCurso(nomeCurso);
            novoCurso.setAreaDeAtuacao(idAreaAtuacao); // Associar a área de atuação ao curso

            cursoRepository.salvar(novoCurso);
            System.out.println("\nCurso cadastrado com sucesso!");

        } catch (PersistenceException e) {
            System.err.println("\nErro ao cadastrar curso: " + e.getMessage());
            // Lógica para lidar com a exceção...
        } finally {
            em.close();
        }
    }
}