package org.scam.cadastros;


import org.scam.entities.ProjetoEntity;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;
import org.scam.repository.ProjetoRepository;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Scanner;

public class ProjetoCadastro {

    public void cadastrarProjeto() {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository mentorRepository = new MentorRepository(em);
        ProjetoRepository projetoRepository = new ProjetoRepository(em);

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Projeto projeto = new Projeto();*/

        //Curso tipoCurso;
        //AreaDeAtuacao tipoAreaDeAtucao;

        String tipoCursoString = "";
        String tipoArea = "";

            /*System.out.print("Digite o ID do projeto: ");
            projeto.setId(Integer.parseInt(scanner.nextLine()));*/

        System.out.print("Nome do projeto: ");
        String nomeProjeto = scanner.nextLine();

        System.out.print("Descrição do projeto: ");
        String descricaoProjeto = scanner.nextLine();

        exibirMenuAreaDeAtuacao();

        System.out.print("Escolha a área de atuação: ");
        int opAreaDeAtuacao = scanner.nextInt();

        switch (opAreaDeAtuacao){
            case 1:
                tipoArea = "AGRÁRIAS_E_BIOLOGIA";
                break;
            case 2:
                tipoArea = "EDUCAÇÃO";
                break;
            case 3:
                tipoArea = "ENGENHARIA_E_ARQUITETURA";
                break;
            case 4:
                tipoArea = "GESTÃO";
                break;
            case 5:
                tipoArea = "SAÚDE";
                break;
            case 6:
                tipoArea = "SOCIAIS";
                break;
            case 7:
                tipoArea = "TECNOLOGIA";
                break;
            default:
                System.out.println("Área de atuação inválido.");
        }

        System.out.print("Data de início do projeto (yyyy-MM-dd): ");
        String dataInicio = scanner.next();

        System.out.print("Data de término do projeto (yyyy-MM-dd): ");
        String dataFinal = scanner.next();

        System.out.print("Quantidade de integrantes: ");
        int qtdParticipante = scanner.nextInt();

        exibirMenuCursos();

        System.out.print("Escolha o curso: ");
        int opCurso = scanner.nextInt();

        switch (opCurso) {
            case 1:
                tipoCursoString =  "ADMINISTRADOR";
                break;
            case 2:
                tipoCursoString =  "AGRONOMIA";
                break;
            case 3:
                tipoCursoString =  "ANÁLISE_E_DESENVOLVIMENTO_DE_SISTEMAS";
                break;
            case 4:
                tipoCursoString =  "ARQUITETURA_E_URBANISMO";
                break;
            case 5:
                tipoCursoString =  "BIOMEDICINA";
                break;
            case 6:
                tipoCursoString =  "CIÊNCIAS_BIOLÓGICAS";
                break;
            case 7:
                tipoCursoString =  "CIÊNCIAS_CONTÁBEIS";
                break;
            case 8:
                tipoCursoString =  "DESIGN_GRÁFICA_DIGITAL";
                break;
            case 9:
                tipoCursoString =  "DIREITO";
                break;
            case 10:
                tipoCursoString =  "EDUCAÇÃO_FÍSICA";
                break;
            case 11:
                tipoCursoString =  "ENFERMAGEM";
                break;
            case 12:
                tipoCursoString =  "ENGENHARIA_CIVIL";
                break;
            case 13:
                tipoCursoString =  "ENGENHARIA_DE_SOFTWARE";
                break;
            case 14:
                tipoCursoString =  "ENGENHARIA_ELÉTRICA";
                break;
            case 15:
                tipoCursoString =  "ENGENHARIA_MECÂNICA";
                break;
            case 16:
                tipoCursoString =  "FARMÁCIA";
                break;
            case 17:
                tipoCursoString =  "FISIOTERAPIA";
                break;
            case 18:
                tipoCursoString =  "MEDICINA_VETERINÁRIA";
                break;
            case 19:
                tipoCursoString =  "NUTRIÇÃO";
                break;
            case 20:
                tipoCursoString =  "PEDAGOGIA";
                break;
            case 21:
                tipoCursoString =  "PSICOLOGIA";
                break;
            case 22:
                tipoCursoString =  "PUBLICIDADE_E_PROPAGANDA";
                break;
            default:
                System.out.println("Curso inválido.");
        }

        System.out.print("Período: ");
        String periodo = scanner.nextLine();

        System.out.print("RA do aluno responsável: ");
        int raAluno = scanner.nextInt();

            /*List<MentorEntity> allPacientes = mentorRepository.buscarTodos();

            for (MentorEntity x : allPacientes){
                System.out.println("Id: "+ x.getIdMentor() + " Nome :"+x.getNome());
            }*/

        System.out.print("ID do mentor (fk_mentor_id): ");
        int mentor = scanner.nextInt();

        ProjetoEntity novoProjeto = new ProjetoEntity();
        novoProjeto.setNomeDoProjeto(nomeProjeto);
        novoProjeto.setDescricao(descricaoProjeto);
        novoProjeto.setAreaDeAtuacao(tipoArea);
        novoProjeto.setDataInicioProjeto(LocalDate.parse(dataInicio));
        novoProjeto.setDataFinalProjeto(LocalDate.parse(dataFinal));
        novoProjeto.setTamanhoDoGrupo(qtdParticipante);
        novoProjeto.setCurso(tipoCursoString);
        novoProjeto.setPeriodo(periodo);
        novoProjeto.setRaAluno(raAluno);
        novoProjeto.setIdMentor(mentor);


        projetoRepository.salvar(novoProjeto);
    }


    private void exibirMenuCursos() {
        System.out.println("===================  ESCOLHA O CURSO  ===================");
        System.out.println("= [1]  - ADMINISTRADOR                                  =");
        System.out.println("= [2]  - AGRONOMIA                                      =");
        System.out.println("= [3]  - ANÁLISE E DESENVOLVIMENTO DE SISTEMAS          =");
        System.out.println("= [4]  - ARQUITETURA E URBANISMO                        =");
        System.out.println("= [5]  - BIOMEDICINA                                    =");
        System.out.println("= [6]  - CIÊNCIAS BIOLÓGICAS                            =");
        System.out.println("= [7]  - CIÊNCIAS CONTÁBEIS                             =");
        System.out.println("= [8]  - DESIGN GRÁFICA DIGITAL                         =");
        System.out.println("= [9]  - DIREITO                                        =");
        System.out.println("= [10] - EDUCAÇÃO FÍSICA                                =");
        System.out.println("= [11] - ENFERMAGEM                                     =");
        System.out.println("= [12] - ENGENHARIA CIVIL                               =");
        System.out.println("= [13] - ENGENHARIA DE SOFTWARE                         =");
        System.out.println("= [14] - ENGENHARIA ELÉTRICA                            =");
        System.out.println("= [15] - ENGENHARIA MECÂNICA                            =");
        System.out.println("= [16] - FARMÁCIA                                       =");
        System.out.println("= [17] - FISIOTERAPIA                                   =");
        System.out.println("= [18] - MEDICINA VETERINÁRIA                           =");
        System.out.println("= [19] - NUTRIÇÃO                                       =");
        System.out.println("= [20] - PEDAGOGIA                                      =");
        System.out.println("= [21] - PSICOLOGIA                                     =");
        System.out.println("= [22] - PUBLICIDADE E PROPAGANDA                       =");
        System.out.println("=========================================================\n");
    }

    private void exibirMenuAreaDeAtuacao() {
        System.out.println("============== ESCOLHA A ÁREA DE ATUAÇÃO ==============");
        System.out.println("= [1]  - AGRÁRIAS E BIOLOGIA                          =");
        System.out.println("= [2]  - EDUCAÇÃO                                     =");
        System.out.println("= [3]  - ENGENHARIA E ARQUITETURA                     =");
        System.out.println("= [4]  - GESTÃO                                       =");
        System.out.println("= [5]  - SAÚDE                                        =");
        System.out.println("= [6]  - SOCIAIS                                      =");
        System.out.println("= [7]  - TECNOLOGIA                                   =");
        System.out.println("=======================================================\n");
    }




}