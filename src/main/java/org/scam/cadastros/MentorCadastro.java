package org.scam.cadastros;

import org.scam.classes.TipoMentor;
import org.scam.entities.EnderecoEntity;
import org.scam.entities.MentorEntity;
import org.scam.repository.CustomizerFactory;
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

        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        // 2. Coletar INFORMAÇÕES PROFISSIONAIS
        System.out.println("\n[INFORMAÇÕES PROFISSIONAIS]");
        System.out.print("Tempo de experiência (ex: 5 anos): ");
        String tempoExperiencia = scanner.nextLine();

        System.out.print("Tipo de vínculo (ex: CLT, PJ): ");
        String tipoVinculo = scanner.nextLine();

        System.out.print("Área de atuação: ");
        String areaAtuacao = scanner.nextLine();

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
        EntityManager em = CustomizerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

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
            em.persist(endereco);

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
            mentor.setAreaDeAtuacao(areaAtuacao);
            mentor.setEndereco(endereco);
            em.persist(mentor);

            tx.commit();

            System.out.println("\n✅ Mentor cadastrado com sucesso! ID: ");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("❌ Erro ao cadastrar mentor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}