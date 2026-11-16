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
 * 'Interface' que representa um evento de remate num jogo de futebol.
 */
public interface IShootEvent extends IEvent {

    /**
     * Retorna o jogador que vai realizar remate.
     *
     * @return Jogador responsavel pelo remate.
     */
    IPlayer getPlayer();
}
