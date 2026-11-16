package com.ppstudios.footballmanager.api.contracts.event;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Enumeracao que representa os tipos de cartao possiveis num jogo de futebol.
 */
public enum CardType {
    /**
     * Nenhum cartao foi atribuido.
     */
    NONE,

    /**
     * Cartao amarelo.
     */
    YELLOW,

    /**
     * Cartao vermelho.
     */
    RED;

    /**
     * Converte um valor de 'CardType' para uma representacao textual.
     *
     * @param type Tipo de cartao.
     * @return 'String' descritiva do cartao.
     */
    public static String cardTypeToString(CardType type) {
        switch (type) {
            case YELLOW:
                return "The player had a Yellow Card";
            case RED:
                return "he player had a Red Card";
            default:
                return "he player had no Card";
        }
    }
}
