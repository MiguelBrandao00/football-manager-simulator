package com.ppstudios.footballmanager.api.contracts.match;

import com.ppstudios.footballmanager.api.contracts.event.EventManager;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Representa um jogo entre dois clubes numa determinada ronda.
 * Guarda o estado do jogo, eventos ocorridos e equipa envolvidas.
 */
public class Match implements IMatch {

    /**
     * Clube da casa.
     */
    private IClub homeClub;

    /**
     * Clube visitante.
     */
    private IClub awayClub;

    /**
     * Equipa da casa (pode conter os jogadores).
     */
    private ITeam homeTeam;

    /**
     * Equipa visitante.
     */
    private ITeam awayTeam;

    /**
     * Numero da ronda onde este jogo ocorre.
     */
    private int round;

    /**
     * Estado do jogo: true se ja foi jogado, false caso contrario.
     */
    private boolean played;

    /**
     * 'Array' de eventos do jogo (golos, cartoes, etc).
     */
    private EventManager events;

    /**
     * Cria um jogo entre dois clubes numa ronda especifica.
     *
     * @param homeClub clube da casa
     * @param awayClub clube visitante
     * @param round    ronda do jogo
     */
    public Match(IClub homeClub, IClub awayClub, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.played = false;
        this.events = new EventManager();
    }

    /**
     * Obtem o clube visitante.
     *
     * @return clube visitante
     */
    @Override
    public IClub getAwayClub() {
        return this.awayClub;
    }

    /**
     * Obtem a equipa visitante.
     *
     * @return equipa visitante
     */
    @Override
    public ITeam getAwayTeam() {
        return this.awayTeam;
    }

    /**
     * Obtem o clube da casa.
     *
     * @return clube da casa
     */
    @Override
    public IClub getHomeClub() {
        return this.homeClub;
    }

    /**
     * Obtem a equipa da casa.
     *
     * @return equipa da casa
     */
    @Override
    public ITeam getHomeTeam() {
        return this.homeTeam;
    }

    /**
     * Obtem o numero da ronda em que o jogo ocorre.
     *
     * @return número da ronda
     */
    @Override
    public int getRound() {
        return this.round;
    }

    /**
     * Conta quantos eventos do tipo especificado ocorreram para um clube.
     *
     * @param eventClass classe do evento a contar (ex: Goal.class)
     * @param club       clube para contar eventos
     * @return numero de eventos do tipo para o clube
     */
    @Override
    public int getTotalByEvent(Class eventClass, IClub club) {
        int count = 0;
        for (int i = 0; i < events.getEventCount(); i++) {
            if (eventClass.isInstance(events.getEvents()[i]) && homeClub.equals(club)) {
                count++;
            }
            if (eventClass.isInstance(events.getEvents()[i]) && awayClub.equals(club)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Obtem a equipa vencedora do jogo.
     * Retorna null em caso de empate.
     *
     * @return equipa vencedora ou null se empate
     */
    @Override
    public ITeam getWinner() {
        int homeGoals = getTotalByEvent(com.ppstudios.footballmanager.api.contracts.event.Goal.class, homeClub);
        int awayGoals = getTotalByEvent(com.ppstudios.footballmanager.api.contracts.event.Goal.class, awayClub);

        if (homeGoals > awayGoals) {
            return homeTeam;
        } else if (awayGoals > homeGoals) {
            return awayTeam;
        } else {
            return null;
        }
    }

    /**
     * Verifica se o jogo ja foi jogado.
     *
     * @return true se jogado, false caso contrario
     */
    @Override
    public boolean isPlayed() {
        return this.played;
    }

    /**
     * Verifica se o jogo e valido (equipas definidas).
     *
     * @return true se as duas equipas estiverem definidas, false caso contrario
     */
    @Override
    public boolean isValid() {
        return homeTeam != null && awayTeam != null;
    }

    /**
     * Marca o jogo como jogado.
     */
    @Override
    public void setPlayed() {
        this.played = true;
    }

    /**
     * Define a equipa (casa ou visitante) conforme o clube da equipa.
     *
     * @param team equipa a definir
     */
    @Override
    public void setTeam(ITeam team) {
        if (team.getClub().equals(homeClub)) {
            this.homeTeam = team;
        } else if (team.getClub().equals(awayClub)) {
            this.awayTeam = team;
        }
    }

    /**
     * Adiciona um evento ao jogo (ex: golo, cartão).
     *
     * @param event evento a adicionar
     */
    public void addEvent(IEvent event) {
        this.events.addEvent(event);
    }

    /**
     * Obtem todos os eventos do jogo.
     *
     * @return array com os eventos
     */
    @Override
    public IEvent[] getEvents() {
        return this.events.getEvents();
    }

    /**
     * Obtem o array de eventos do jogo.
     *
     * @return EventManager com todos os eventos
     */
    public EventManager getEventManager() {
        return this.events;
    }

    /**
     * Obtem o numero total de eventos do jogo.
     *
     * @return quantidade de eventos
     */
    @Override
    public int getEventCount() {
        return this.events.getEventCount();
    }

    /**
     * Exporta os dados do jogo para um ficheiro JSON.
     *
     * @throws IOException em caso de erro na escrita do ficheiro
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject matchJson = DataExporter.matchToJsonObject(this);

        try (FileWriter file = new FileWriter("match_round_" + round + ".json")) {
            file.write(matchJson.toJSONString());
        }
    }
}
