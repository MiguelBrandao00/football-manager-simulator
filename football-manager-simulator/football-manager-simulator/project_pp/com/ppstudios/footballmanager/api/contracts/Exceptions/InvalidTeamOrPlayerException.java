package com.ppstudios.footballmanager.api.contracts.Exceptions;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Excecao lancada quando uma equipa ou jogador invalido e utilizado numa operacao.
 * Esta excecao pode ocorrer, por exemplo, quando se tenta adicionar um jogador inexistente a uma equipa
 * ou quando se faz referencia a uma equipa nula ou invalida durante o processamento de um evento.
 */
public class InvalidTeamOrPlayerException extends Exception {

    /**
     * Criou instancia de 'InvalidTeamOrPlayerException' com a mensagem de erro especificada.
     *
     * @param message Mensagem descritiva do erro.
     */
    public InvalidTeamOrPlayerException(String message) {
        super(message);
    }
}

