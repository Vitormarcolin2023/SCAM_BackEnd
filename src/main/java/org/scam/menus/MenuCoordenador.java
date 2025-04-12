package org.scam.menus;

import org.scam.classes.Coordenador;

public class MenuCoordenador {

    private Coordenador coordenador;

    public MenuCoordenador(Coordenador coordenador){
        this.coordenador = coordenador;
    }

    public void exibirMenu(){

        System.out.println("Deu certo, usuário: " + coordenador.getNome());
        // lógica menu
    }

}
