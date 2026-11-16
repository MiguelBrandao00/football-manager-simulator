package com.ppstudios.footballmanager.api.contracts.team;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;

/**
 * Classe responsável por selecionar ciclicamente jogadores de um clube com base na posição pretendida.
 *
 * <p>Implementa uma lógica de rotação para que a seleção de jogadores não seja sempre a mesma.</p>
 * <p>
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */
public class PlayerSelector implements IPlayerSelector {

    /**
     * Índice do último jogador selecionado.
     * Serve para garantir que a próxima seleção começa a partir do jogador seguinte.
     */
    private int lastSelectedIndex = -1;

    /**
     * Seleciona o próximo jogador disponível da posição especificada, de forma rotativa.
     *
     * @param iClub           Clube de onde selecionar o jogador.
     * @param iPlayerPosition Posição desejada do jogador.
     * @return Um jogador que corresponde à posição desejada, ou {@code null} se nenhum for encontrado.
     */
    @Override
    public IPlayer selectPlayer(IClub iClub, IPlayerPosition iPlayerPosition) {
        IPlayer[] players = iClub.getPlayers();
        int startIndex = (lastSelectedIndex + 1) % players.length;

        for (int i = 0; i < players.length; i++) {
            int idx = (startIndex + i) % players.length;
            IPlayer player = players[idx];
            if (player != null && player.getPosition().equals(iPlayerPosition)) {
                lastSelectedIndex = idx;
                return player;
            }
        }
        return null;
    }
}

