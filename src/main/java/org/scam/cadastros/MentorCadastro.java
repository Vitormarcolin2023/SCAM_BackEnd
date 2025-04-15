package org.scam.cadastros;

import org.scam.classes.Mentor;
import org.scam.classes.TipoMentor;
import org.scam.entities.EnderecoEntity;
import org.scam.entities.MentorEntity;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;
import org.scam.utils.Sessao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class MentorCadastro {
    public void cadastrarMentor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== CADASTRO DE MENTOR ===");

        // 1. Coletar DADOS PESSOAIS
        System.out.println("\n[DADOS PESSOAIS]");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        // Persistência usando CustomizerFactory
        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository mentorRepo = new MentorRepository(em); // Instancia do repositório

        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();

        // Verifica se já existe CPF cadastrado
        if (mentorRepo.existePorCpf(cpf)) {
            System.out.println("❌ Já existe um mentor cadastrado com esse CPF.");
            em.close();
            return;
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        // Verifica se já existe e-mail cadastrado
        if (mentorRepo.existePorEmail(email)) {
            System.out.println("❌ Já existe um mentor cadastrado com esse e-mail.");
            em.close();
            return;
        }


        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        // Verifica se já existe telefone cadastrado
        if (mentorRepo.existePorTelefone(telefone)) {
            System.out.println("❌ Já existe um mentor cadastrado com esse telefone.");
            em.close();
            return;
        }

        // 2. Coletar INFORMAÇÕES PROFISSIONAIS
        System.out.println("\n[INFORMAÇÕES PROFISSIONAIS]");
        System.out.print("Tempo de experiência (ex: 5 anos): ");
        String tempoExperiencia = scanner.nextLine();

        System.out.print("Tipo de vínculo (ex: CLT, PJ): ");
        String tipoVinculo = scanner.nextLine();

        /*System.out.print("Área de atuação: ");
        String areaAtuacao = scanner.nextLine();*/

        System.out.println("\n[SELEÇÃO A ÁREA DE ATUAÇÃO]");
        AreaDeAtuacao[] tiposA = AreaDeAtuacao.values();
        for (int i = 0; i < tiposA.length; i++) {
            System.out.printf("%d - %s%n", i + 1, tiposA[i].name());
        }
        System.out.print("Digite o número correspondente ao tipo: ");

        int escolhaTipoA;
        AreaDeAtuacao areaDeAtuacao = null;
        try {
            escolhaTipoA = Integer.parseInt(scanner.nextLine());
            areaDeAtuacao = tiposA[escolhaTipoA - 1];
        } catch (Exception e) {
            System.out.println("Escolha inválida!.");
        }

        System.out.println("\n[SELEÇÃO DE TIPO DE MENTOR]");
        TipoMentor[] tipos = TipoMentor.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("%d - %s%n", i + 1, tipos[i].name());
        }
        System.out.print("Digite o número correspondente ao tipo: ");

        int escolhaTipo;
        TipoMentor tipoMentor;
        try {
            escolhaTipo = Integer.parseInt(scanner.nextLine());
            tipoMentor = tipos[escolhaTipo - 1];
        } catch (Exception e) {
            System.out.println("Escolha inválida! Usando padrão 'Formado'.");
            tipoMentor = TipoMentor.Formado;
        }

        // 3. Coletar ENDEREÇO
        System.out.println("\n[ENDEREÇO]");
        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        // Persistência usando CustomizerFactory
        EntityManager em2 = CustomizerFactory.getEntityManager();
        EntityTransaction tx = em2.getTransaction();

        try {
            tx.begin();

            // Criar e persistir endereço
            EnderecoEntity endereco = new EnderecoEntity();
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);
            em2.persist(endereco);

            // Criar e persistir mentor
            MentorEntity mentor = new MentorEntity();
            mentor.setNome(nome);
            mentor.setCpf(cpf);
            mentor.setEmail(email);
            mentor.setSenha(senha);
            mentor.setTipoDeUsuario(tipoMentor);
            mentor.setTelefone(telefone);
            mentor.setTempoExperiencia(tempoExperiencia);
            mentor.setTipoDeVinculo(tipoVinculo);
            mentor.setAreaDeAtuacao(areaDeAtuacao);
            mentor.setEndereco(endereco);
            em2.persist(mentor);

            tx.commit();

            System.out.println("\n✅ Mentor cadastrado com sucesso! ID: ");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("❌ Erro ao cadastrar mentor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em2 != null && em2.isOpen()) {
                em2.close();
            }
        }
    }

    public void editarMentor() {
        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository repository = new MentorRepository(em);
        Scanner scanner = new Scanner(System.in);

        // Recupera o mentor logado da sessão
        Mentor mentorLogado = Sessao.getMentorLogado();
        if (mentorLogado == null) {
            System.out.println("❌ Nenhum mentor logado.");
            return;
        }

        // Se o mentor está logado, você pode acessar as informações diretamente
        MentorEntity mentorEntity = new MentorEntity();
        mentorEntity.setNome(mentorLogado.getNome());
        mentorEntity.setCpf(mentorLogado.getCpf());
        mentorEntity.setEmail(mentorLogado.getEmail());
        mentorEntity.setSenha(mentorLogado.getSenha());
        mentorEntity.setTelefone(mentorLogado.getTelefone());
        mentorEntity.setTipoDeUsuario(mentorLogado.getTipoDeUsuario());
        mentorEntity.setTempoExperiencia(mentorLogado.getTempoDeExperiencia());
        mentorEntity.setTipoDeVinculo(mentorLogado.getTipoDeVinculo());
        mentorEntity.setAreaDeAtuacao(mentorLogado.getAreaDeAtuacao());
        mentorEntity.setEndereco(mentorLogado.getEndereco());

        // Agora você já tem os dados do mentor na variável mentorEntity e pode editá-los

        System.out.println("\n=== EDIÇÃO DE MENTOR ===");
        System.out.println("Deixe o campo em branco para manter o valor atual.\n");

        // Editar nome
        System.out.print("Nome atual: " + mentorEntity.getNome() + "\nNovo nome: ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            mentorEntity.setNome(novoNome);
        }

        // Editar telefone
        System.out.print("Telefone atual: " + mentorEntity.getTelefone() + "\nNovo telefone: ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.trim().isEmpty()) {
            mentorEntity.setTelefone(novoTelefone);
        }

        // Editar senha
        System.out.print("Senha atual: " + mentorEntity.getSenha() + "\nNova senha: ");
        String novaSenha = scanner.nextLine();
        if (!novaSenha.trim().isEmpty()) {
            mentorEntity.setSenha(novaSenha);
        }

        // Editar tempo de experiência
        System.out.print("Tempo de experiência atual: " + mentorEntity.getTempoDeExperiencia() + "\nNovo tempo: ");
        String novaExperiencia = scanner.nextLine();
        if (!novaExperiencia.trim().isEmpty()) {
            mentorEntity.setTempoExperiencia(novaExperiencia);
        }

        // Editar tipo de vínculo
        System.out.print("Tipo de vínculo atual: " + mentorEntity.getTipoDeVinculo() + "\nNovo vínculo: ");
        String novoVinculo = scanner.nextLine();
        if (!novoVinculo.trim().isEmpty()) {
            mentorEntity.setTipoDeVinculo(novoVinculo);
        }

        // Editar área de atuação
        System.out.println("Área de atuação atual: " + mentorEntity.getAreaDeAtuacao());

        System.out.println("\n[SELEÇÃO DA NOVA ÁREA DE ATUAÇÃO]");
        AreaDeAtuacao[] areas = AreaDeAtuacao.values();
        for (int i = 0; i < areas.length; i++) {
            System.out.printf("%d - %s%n", i + 1, areas[i].name());
        }
        System.out.print("Digite o número correspondente ou pressione Enter para manter: ");
        String escolha = scanner.nextLine();
        if (!escolha.trim().isEmpty()) {
            try {
                int indice = Integer.parseInt(escolha);
                AreaDeAtuacao novaAreaEnum = areas[indice - 1];
                mentorEntity.setAreaDeAtuacao(novaAreaEnum);
            } catch (Exception e) {
                System.out.println("❌ Escolha inválida. Área de atuação anterior mantida.");
            }
        }

        // Se o endereço do mentor também precisar ser editado, você pode fazer isso aqui
        EnderecoEntity endereco = mentorEntity.getEndereco();
        if (endereco != null) {
            System.out.println("\n--- Endereço ---");

            System.out.print("Rua atual: " + endereco.getRua() + "\nNova rua: ");
            String novaRua = scanner.nextLine();
            if (!novaRua.trim().isEmpty()) {
                endereco.setRua(novaRua);
            }

            System.out.print("Número atual: " + endereco.getNumero() + "\nNovo número: ");
            String novoNumeroStr = scanner.nextLine();
            if (!novoNumeroStr.trim().isEmpty()) {
                try {
                    int novoNumero = Integer.parseInt(novoNumeroStr);
                    endereco.setNumero(novoNumero);
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido. Valor anterior mantido.");
                }
            }

            System.out.print("Bairro atual: " + endereco.getBairro() + "\nNovo bairro: ");
            String novoBairro = scanner.nextLine();
            if (!novoBairro.trim().isEmpty()) {
                endereco.setBairro(novoBairro);
            }

            System.out.print("Cidade atual: " + endereco.getCidade() + "\nNova cidade: ");
            String novaCidade = scanner.nextLine();
            if (!novaCidade.trim().isEmpty()) {
                endereco.setCidade(novaCidade);
            }

            System.out.print("Estado atual: " + endereco.getEstado() + "\nNovo estado: ");
            String novoEstado = scanner.nextLine();
            if (!novoEstado.trim().isEmpty()) {
                endereco.setEstado(novoEstado);
            }

            System.out.print("CEP atual: " + endereco.getCep() + "\nNovo CEP: ");
            String novoCep = scanner.nextLine();
            if (!novoCep.trim().isEmpty()) {
                endereco.setCep(novoCep);
            }
        }

        // Atualizar os dados no repositório
        repository.editarMentor(mentorEntity);
        System.out.println("\n✅ Mentor atualizado com sucesso!");
    }

}