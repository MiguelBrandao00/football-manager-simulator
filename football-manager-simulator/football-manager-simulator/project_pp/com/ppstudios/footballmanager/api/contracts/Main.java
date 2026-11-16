package com.ppstudios.footballmanager.api.contracts;

import com.ppstudios.footballmanager.api.contracts.io.DataLoader;
import com.ppstudios.footballmanager.api.contracts.league.League;
import com.ppstudios.footballmanager.api.contracts.menu.Menu;
import com.ppstudios.footballmanager.api.contracts.simulation.Simulate;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Classe principal que inicia a aplicacao Football Manager.
 *
 * <p>Responsavel por carregar a liga a partir de um ficheiro JSON,
 * configurar a simulacao e apresentar o menu principal ao utilizador.</p>
 *
 * <p>Este e o ponto de entrada do programa.</p>
 */
public class Main {

    /**
     * metodo main que executa o menu.
     *
     * @param args argumentos da linha de comando (nao utilizados)
     * @throws Exception lança excecoes relacionadas com o carregamento da liga ou inicializacao da aplicacao
     */
    public static void main(String[] args) throws Exception {
        League league = DataLoader.loadLeagueFromJsonFile("lib/files/season.json");
        Simulate simulate = new Simulate();

        Menu menu = new Menu(simulate, league);
        menu.showMainMenu();
    }
}
