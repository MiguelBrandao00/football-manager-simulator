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
 * 'Interface' que representa um evento de pontape de baliza num jogo de futebol.
 */
public interface IGoalKickEvent extends IEvent {

    /**
     * Retorna o guarda-redes que vai realizar o pontape de baliza.
     *
     * @return Jogador responsavel pelo livre.
     */
    IPlayer getGoalKeeper();
}
