package org.scam.menus;

import org.scam.cadastros.MentorCadastro;
import org.scam.classes.Mentor;

import java.util.Scanner;

public class MenuMentor {
    private Mentor mentor;
    public MenuMentor(Mentor mentor){this.mentor = mentor;}

    public MenuMentor(){}

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int continuar = 0;
       do{
           System.out.println("======== MENU DO MENTOR =======");
           System.out.println("= [1] - Login                 =");
           System.out.println("= [2] - Cadastre-se           =");
           System.out.println("= [3] - Sair                  =");
           System.out.println("=============================\n");
           System.out.println("- Selecione a opção desejada: ");
           continuar = sc.nextInt();
           sc.nextLine();

           switch (continuar){
               case 1:
                   return;
               case 2:
                   new MentorCadastro().cadastrarMentor();
                   break;
               default:
                   System.out.println("Opção inválida!");
           }
       }while (continuar != 3);
    }

    public void exibirMenu() {
        System.out.println("teste");
    }
}
