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
 * 'Interface' que representa um evento de penalti num jogo de futebol.
 */
public interface IPenaltyEvent extends IEvent {

    /**
     * Retorna o jogador que vai cobrar o penalti.
     *
     * @return Jogador responsavel pelo penalti.
     */
    IPlayer getPlayer();
}
