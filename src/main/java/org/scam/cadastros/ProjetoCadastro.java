package org.scam.cadastros;

import org.scam.classes.Projeto;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ProjetoCadastro {

    public Projeto cadastrarProjeto() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Projeto projeto = new Projeto();

        try {
            System.out.print("Digite o ID do projeto: ");
            projeto.setId(Integer.parseInt(scanner.nextLine()));

            System.out.print("Nome do projeto: ");
            projeto.setNomeDoProjeto(scanner.nextLine());

            System.out.print("Descrição do projeto: ");
            projeto.setDescricao(scanner.nextLine());

            System.out.print("Área de atuação: ");
            projeto.setAreaDeAtuacao(scanner.nextLine());

            System.out.print("Data de início do projeto (yyyy-MM-dd): ");
            projeto.setDataInicioProjeto(sdf.parse(scanner.nextLine()));

            System.out.print("Data de término do projeto (yyyy-MM-dd): ");
            projeto.setDataFinalProjeto(sdf.parse(scanner.nextLine()));

            System.out.print("Tamanho do grupo: ");
            projeto.setTamanhoDoGrupo(Integer.parseInt(scanner.nextLine()));

            System.out.print("Curso: ");
            projeto.setCurso(scanner.nextLine());

            System.out.print("Período: ");
            projeto.setPeriodo(scanner.nextLine());

            System.out.print("ID do aluno responsável (fk_aluno_id): ");
            projeto.setFk_aluno_id(Integer.parseInt(scanner.nextLine()));

            System.out.print("ID do mentor (fk_mentor_id): ");
            projeto.setFk_mentor_id(Integer.parseInt(scanner.nextLine()));

        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use o padrão yyyy-MM-dd.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número. Verifique os campos numéricos.");
        }

        return projeto;
    }
}