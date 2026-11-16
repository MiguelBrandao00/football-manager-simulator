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
 * Excecao lancada quando se tenta realizar uma operacao com um jogador
 * que nao pertence a equipa especificada.
 * Esta excecao pode ser utilizada, por exemplo, ao tentar substituir,
 * remover ou aceder a um jogador que nao faz parte da equipa atual.
 */
public class PlayerNotInTeamException extends Exception {
    /**
     * Cria uma instancia de {@code PlayerNotInTeamException}
     * com a mensagem de erro especificada.
     *
     * @param message Mensagem descritiva do erro.
     */
    public PlayerNotInTeamException(String message) {
        super(message);
    }
}