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
 * Enumeracao que representa as varias posicoes de um jogador de futebol.
 * Cada posicao tem metodos que indicam se e defensiva, medio ou avancada,
 * bem como uma descricao textual da funcao da posicao.
 */
public enum PlayerPosition implements IPlayerPosition {

    /**
     * Guarda-redes: defende a baliza para evitar golos.
     */
    GOALKEEPER {
        public boolean isDefencive() {
            return true;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Goalkeeper: Stops the opposing team from scoring by defending the goal.";
        }
    },

    /**
     * Central defensivo: defende a zona central, bloqueando remates e marcando atacantes.
     */
    CENTRE_BACK {
        public boolean isDefencive() {
            return true;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Centre-back: Defends the central area, blocking shots and marking attackers.";
        }
    },

    /**
     * Lateral: defende as alas e apoia ataques pelos flancos.
     */
    FULL_BACK {
        public boolean isDefencive() {
            return true;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Full-back: Defends the wide areas and supports attacks on the flanks.";
        }
    },

    /**
     * Lateral ofensivo: joga nas alas, tanto a defender como a atacar, oferecendo cruzamentos.
     */
    WING_BACK {
        public boolean isDefencive() {
            return true;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Wing-back: Plays wide in defense and attack, providing crosses and defensive cover.";
        }
    },

    /**
     * Medio defensivo: protege a defesa e interrompe ataques adversarios.
     */
    DEFENSIVE_MIDFIELDER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return true;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Defensive midfielder: Protects the defense and disrupts the opponent's attacks.";
        }
    },

    /**
     * Medio centro: controla o ritmo de jogo e liga defesa ao ataque.
     */
    CENTRAL_MIDFIELDER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return true;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Central midfielder: Controls the tempo of the game and links defense with attack.";
        }
    },

    /**
     * Medio ofensivo: cria oportunidades de golo e fornece assistencias.
     */
    ATTACKING_MIDFIELDER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return true;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Attacking midfielder: Creates goal-scoring opportunities and provides assists.";
        }
    },

    /**
     * Avancado (ponta de lanca): principal marcador de golos, posicionado perto da baliza adversaria.
     */
    STRIKER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return true;
        }

        @Override
        public String getDescription() {
            return "Striker: Main goal-scorer, positioned near the opponent’s goal.";
        }
    },

    /**
     * Avancado centro: lidera o ataque e segura a bola para criar jogo para os colegas.
     */
    CENTER_FORWARD {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return true;
        }

        @Override
        public String getDescription() {
            return "Center forward: Leads the attack and holds the ball up to bring teammates into play.";
        }
    },
    /**
     * Avancado de apoio: apoia o ponta de lanca principal, ajudando a criar e marcar golos.
     */
    SUPPORT_STRIKER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return true;
        }

        @Override
        public String getDescription() {
            return "Support striker: Supports the main striker, assisting in scoring and creating chances.";
        }
    },

    /**
     * Extremo esquerdo: ataca pela ala esquerda, faz cruzamentos e corta para dentro.
     */
    LEFT_WINGER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return true;
        }

        @Override
        public String getDescription() {
            return "Left winger: Attacks from the left flank, delivering crosses and cutting inside.";
        }
    },

    /**
     * Extremo direito: ataca pela ala direita, faz cruzamentos e cria oportunidades de golo.
     */
    RIGHT_WINGER {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return true;
        }

        @Override
        public String getDescription() {
            return "Right winger: Attacks from the right flank, delivering crosses and creating scoring chances.";
        }
    },

    /**
     * Posicao desconhecida.
     */
    UNKNOWN {
        public boolean isDefencive() {
            return false;
        }

        public boolean isMidfielder() {
            return false;
        }

        public boolean isForward() {
            return false;
        }

        @Override
        public String getDescription() {
            return "Unknown position.";
        }
    };

    /**
     * Indica se a posicao é defensiva.
     *
     * @return true se for uma posicao defensiva, false caso contrario.
     */
    public abstract boolean isDefencive();

    /**
     * Indica se a posicao e de medio.
     *
     * @return true se for medio, false caso contrario.
     */
    public abstract boolean isMidfielder();

    /**
     * Indica se a posicao é de avancado.
     *
     * @return true se for avancado, false caso contrario.
     */
    public abstract boolean isForward();
}
