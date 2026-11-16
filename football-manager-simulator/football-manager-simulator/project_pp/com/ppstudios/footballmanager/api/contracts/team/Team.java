package com.ppstudios.footballmanager.api.contracts.team;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.Player;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe que representa uma equipa associada a um clube, com uma formação tática e um conjunto de jogadores.
 * Permite a adição de jogadores, avaliação da força da equipa e exportação para JSON.
 *
 * <p>Inclui validações de posições em relação à formação e gestão interna dos jogadores.</p>
 * <p>
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Branda Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */
public class Team implements ITeam {

    /**
     * Clube associado à equipa.
     */
    private IClub club;

    /**
     * Formação atual da equipa.
     */
    private Formation formation;

    /**
     * Array de jogadores da equipa.
     */
    private IPlayer[] players;

    /**
     * Número de jogadores atualmente na equipa.
     */
    private int playerCount;

    /**
     * Construtor da equipa.
     *
     * @param club      Clube associado.
     * @param formation Formação inicial.
     */
    public Team(IClub club, Formation formation) {
        this.club = club;
        this.formation = formation;
        this.players = new IPlayer[0];
        this.playerCount = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IClub getClub() {
        return this.club;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IFormation getFormation() {
        return this.formation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] copy = new IPlayer[playerCount];
        for (int i = 0; i < playerCount; i++) {
            copy[i] = players[i];
        }
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(IPlayer var1) {
        if (var1 instanceof Player) {
            if (players.length == playerCount) {
                Player[] temp = new Player[players.length + 1];
                for (int i = 0; i < playerCount; i++) {
                    temp[i] = (Player) players[i];
                }
                players = temp;
            }
            Player player = (Player) var1;
            this.players[this.playerCount++] = player;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPositionCount(IPlayerPosition var1) {
        int positionCounter = 0;
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i].getPosition().equals(var1)) {
                positionCounter++;
            }
        }
        return positionCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidPositionForFormation(IPlayerPosition position) {
        int allowedCount = formation.getAllowedPosition(position);
        boolean isValid = allowedCount > 0;
        return isValid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTeamStrength() {
        int totalStrength = 0;
        int loss = 0;
        int taticalBonus = 0;

        for (int i = 0; i < this.players.length; i++) {
            IPlayer player = players[i];
            Player p = (Player) player;
            totalStrength += p.getPlayerStrenght();

            int allowedFormation = formation.getAllowedPosition(player.getPosition());
            if (allowedFormation == 0) {
                loss += 5;
            } else {
                taticalBonus += 5;
            }
        }

        taticalBonus = formation.getTacticalAdvantage(formation);
        return totalStrength - loss + taticalBonus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFormation(IFormation formation) {
        if (formation instanceof Formation) {
            this.formation = (Formation) formation;
        }
    }

    /**
     * Gera uma representação textual de todos os jogadores da equipa.
     *
     * @return String com a descrição dos jogadores.
     */
    public String TeamPlayersToString() {
        StringBuilder sb = new StringBuilder();
        if (playerCount > 0) {
            for (int i = 0; i < this.players.length - 1; i++) {
                IPlayer player = this.players[i];
                sb.append(player.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Retorna a representação textual da equipa, incluindo o clube, a formação e os jogadores.
     *
     * @return String com a descrição completa da equipa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Club: ").append(this.club).append("\n");
        sb.append("Formation: ").append(this.formation).append("\n");
        sb.append(TeamPlayersToString());
        return sb.toString();
    }

    /**
     * Exporta a equipa para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro durante a escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject teamJson = DataExporter.teamToJsonObject(this);

        try (FileWriter file = new FileWriter("team.json")) {
            file.write(teamJson.toJSONString());
        }
    }
}
