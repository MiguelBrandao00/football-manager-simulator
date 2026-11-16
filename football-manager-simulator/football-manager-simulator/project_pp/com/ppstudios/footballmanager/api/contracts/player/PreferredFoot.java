package com.ppstudios.footballmanager.api.contracts.player;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Enumeracao que representa o pe preferido de um jogador.
 */
public enum PreferredFoot {
    /**
     * Pe esquerdo.
     */
    LEFT,

    /**
     * Pe direito.
     */
    RIGHT,

    /**
     * Usa ambos os pes.
     */
    BOTH,

    /**
     * Pe preferido desconhecido.
     */
    UNKNOWN;

    /**
     * Construtor padrao.
     */
    private PreferredFoot() {
    }

    /**
     * Obtem o nome do pe preferido como 'String'.
     *
     * @return o nome do pe preferido.
     */
    public String getPreferredFoot() {
        return this.name();
    }

    /**
     * Converte uma 'string' para o enum correspondente.
     *
     * @param foot 'String' com o pe preferido.
     * @return o valor do enum correspondente.
     * @throws IllegalArgumentException se nao existir valor correspondente.
     */
    public static PreferredFoot fromString(String foot) {
        for (PreferredFoot p : values()) {
            if (p.name().equalsIgnoreCase(foot)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No constant with text " + foot + " found");
    }

    /**
     * Obtem a descricao do pe preferido a partir de uma 'string'.
     *
     * @param foot 'String' com o pe preferido.
     * @return descricao do pe preferido.
     * @throws IllegalArgumentException se nao existir valor correspondente.
     */
    public static String getFootDescription(String foot) {
        for (PreferredFoot p : values()) {
            if (p.name().equalsIgnoreCase(foot)) {
                return p.name();
            }
        }
        throw new IllegalArgumentException("No constant with text " + foot + " found");
    }
}

