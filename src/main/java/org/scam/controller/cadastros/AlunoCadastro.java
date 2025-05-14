package org.scam.controller.cadastros;


import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.CursoEntity;
import org.scam.model.repository.AlunoRepository;
import org.scam.model.repository.CursoRepository;
import org.scam.model.repository.CustomizerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class AlunoCadastro {

    public void cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = CustomizerFactory.getEntityManager();
        AlunoRepository alunoRepository = new AlunoRepository(em);
        CursoRepository cursoRepository = new CursoRepository(em);

        System.out.println("Digite o seu nome: ");
        String nomeAluno = scanner.nextLine();

        System.out.println("Digite o seu RA: ");
        int raAluno = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite a sua Senha: ");
        String senhaAluno = scanner.next();
        scanner.nextLine();

        // Listar curso
        List<CursoEntity> curso = cursoRepository.listarTodas();
        System.out.println("\nCursos disponíveis:");
        for (CursoEntity cursos : curso) {
            System.out.println(cursos.getId() + " - " + cursos.getDescricaoCurso());
        }

        // Obter o ID do curso
        System.out.print("\nDigite o ID do Curso: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha deixada pelo nextLong()


        System.out.print("E-mail do aluno: ");
        String emailAluno = scanner.nextLine();

        try {
            AlunoEntity novoAluno = new AlunoEntity();

            novoAluno.setNome(nomeAluno);
            novoAluno.setRa(raAluno);
            novoAluno.setSenha(senhaAluno);
            novoAluno.setCursoId(idCurso);
            novoAluno.setEmail(emailAluno);

            alunoRepository.salvar(novoAluno);
            System.out.println("\nAluno cadastrado com sucesso!");

        } catch (PersistenceException e) {
            System.err.println("\nErro ao cadastrar aluno: " + e.getMessage());
            // Lógica para lidar com a exceção...
        } finally {
            em.close();
        }
    }
}
