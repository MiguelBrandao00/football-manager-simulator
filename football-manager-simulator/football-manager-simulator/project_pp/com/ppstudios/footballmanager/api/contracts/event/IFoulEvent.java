package com.ppstudios.footballmanager.api.contracts.event;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * 'Interface' que representa uma falta num jogo de futebol.
 * <p>
 * Um defesa (defense) pode ocorrer apos o remate (Shoot) de um jogador da equipa adversaria.
 */
public interface IFoulEvent extends IEvent {

    /**
     * Retorna o jogador que cometeu a falta.
     *
     * @return Jogador responsavel pela falta.
     */
    IPlayer getPlayer();
}
