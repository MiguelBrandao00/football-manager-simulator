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
 * 'Interface' que representa um evento de canto num jogo de futebol.
 */
public interface ICornerEvent extends IEvent {

    /**
     * Retorna o jogador que vai executar o canto.
     *
     * @return Jogador responsavel pelo canto.
     */
    IPlayer getPlayer();
}

