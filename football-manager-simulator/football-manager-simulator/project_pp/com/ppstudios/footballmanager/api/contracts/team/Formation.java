package com.ppstudios.footballmanager.api.contracts.team;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PlayerPosition;

/**
 * Enumeração que representa várias formações táticas no futebol.
 * Cada formação define um nome de exibição, uma vantagem tática e os números permitidos para cada posição.
 * <p>
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */
public enum Formation implements IFormation {

    /**
     * Formação 3-4-3: três defesas, quatro médios e três atacantes.
     */
    THREE_FOUR_THREE {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "3-4-3";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTacticalAdvantage(IFormation formation) {
            return 56;
        }

        /**
         * {@inheritDoc}
         */
        public int getAllowedPosition(IPlayerPosition position) {
            if (position == PlayerPosition.GOALKEEPER) return 1;
            if (position == PlayerPosition.CENTRE_BACK) return 3;
            if (position == PlayerPosition.WING_BACK || position == PlayerPosition.FULL_BACK) return 2;
            if (position == PlayerPosition.DEFENSIVE_MIDFIELDER) return 1;
            if (position == PlayerPosition.CENTRAL_MIDFIELDER) return 1;
            if (position == PlayerPosition.ATTACKING_MIDFIELDER) return 1;
            if (position == PlayerPosition.STRIKER || position == PlayerPosition.CENTER_FORWARD) return 2;
            return 0;
        }
    },

    /**
     * Formação 4-3-3: quatro defesas, três médios e três atacantes (incluindo extremos).
     */
    FOUR_THREE_THREE {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "4-3-3";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTacticalAdvantage(IFormation formation) {
            return 50;
        }

        /**
         * {@inheritDoc}
         */
        public int getAllowedPosition(IPlayerPosition position) {
            if (position == PlayerPosition.GOALKEEPER) return 1;
            if (position == PlayerPosition.CENTRE_BACK) return 2;
            if (position == PlayerPosition.FULL_BACK) return 2;
            if (position == PlayerPosition.DEFENSIVE_MIDFIELDER) return 1;
            if (position == PlayerPosition.CENTRAL_MIDFIELDER) return 1;
            if (position == PlayerPosition.ATTACKING_MIDFIELDER) return 1;
            if (position == PlayerPosition.STRIKER) return 1;
            if (position == PlayerPosition.LEFT_WINGER) return 1;
            if (position == PlayerPosition.RIGHT_WINGER) return 1;
            return 0;
        }
    },

    /**
     * Formação 3-5-2: três defesas, cinco médios e dois atacantes.
     */
    THREE_FIVE_TWO {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "3-5-2";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTacticalAdvantage(IFormation formation) {
            return 45;
        }

        /**
         * {@inheritDoc}
         */
        public int getAllowedPosition(IPlayerPosition position) {
            if (position == PlayerPosition.GOALKEEPER) return 1;
            if (position == PlayerPosition.CENTRE_BACK) return 3;
            if (position == PlayerPosition.WING_BACK) return 2;
            if (position == PlayerPosition.DEFENSIVE_MIDFIELDER) return 1;
            if (position == PlayerPosition.CENTRAL_MIDFIELDER) return 1;
            if (position == PlayerPosition.STRIKER) return 1;
            if (position == PlayerPosition.CENTER_FORWARD) return 1;
            return 0;
        }
    },

    /**
     * Formação 4-4-2: quatro defesas, quatro médios e dois atacantes.
     */
    FOUR_FOUR_TWO {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "4-4-2";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTacticalAdvantage(IFormation formation) {
            return 40;
        }

        /**
         * {@inheritDoc}
         */
        public int getAllowedPosition(IPlayerPosition position) {
            if (position == PlayerPosition.GOALKEEPER) return 1;
            if (position == PlayerPosition.CENTRE_BACK) return 2;
            if (position == PlayerPosition.FULL_BACK) return 2;
            if (position == PlayerPosition.CENTRAL_MIDFIELDER) return 2;
            if (position == PlayerPosition.LEFT_WINGER) return 1;
            if (position == PlayerPosition.RIGHT_WINGER) return 1;
            if (position == PlayerPosition.STRIKER) return 1;
            if (position == PlayerPosition.CENTER_FORWARD) return 1;
            return 0;
        }
    },

    /**
     * Formação 5-3-2: cinco defesas, três médios e dois atacantes.
     */
    FIVE_THREE_TWO {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "5-3-2";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTacticalAdvantage(IFormation formation) {
            return 32;
        }

        /**
         * {@inheritDoc}
         */
        public int getAllowedPosition(IPlayerPosition position) {
            if (position == PlayerPosition.GOALKEEPER) return 1;
            if (position == PlayerPosition.CENTRE_BACK) return 3;
            if (position == PlayerPosition.WING_BACK) return 2;
            if (position == PlayerPosition.DEFENSIVE_MIDFIELDER) return 1;
            if (position == PlayerPosition.CENTRAL_MIDFIELDER) return 1;
            if (position == PlayerPosition.ATTACKING_MIDFIELDER) return 1;
            if (position == PlayerPosition.STRIKER) return 1;
            if (position == PlayerPosition.CENTER_FORWARD) return 1;
            return 0;
        }
    };

    /**
     * Devolve o número máximo de jogadores permitidos numa determinada posição para esta formação.
     *
     * @param position A posição de jogador a verificar.
     * @return Número máximo permitido para essa posição.
     */
    public abstract int getAllowedPosition(IPlayerPosition position);
}
