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
 * 'Interface' que representa um evento de defesa num jogo de futebol.
 * <p>
 * Um defesa (defense) pode ocorrer apos o remate (shoot) de um jogador da equipa adversaria.
 */
public interface IDefenseEvent extends IEvent {

    /**
     * Retorna o guarda-redes que realizou a defesa.
     *
     * @return Guarda-redes responsavel pela defesa.
     */
    IPlayer getGoalKeeper();
}
