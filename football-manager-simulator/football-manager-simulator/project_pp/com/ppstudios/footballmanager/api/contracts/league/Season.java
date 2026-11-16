package com.ppstudios.footballmanager.api.contracts.league;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
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
 * Representa uma temporada (Season) numa liga de futebol,
 * controlando equipas, partidas, calendario, classificacao e regras de pontuacao.
 */
public class Season implements ISeason {

    private int pointsPerWin;
    private int pointsPerLoss;
    private int pointsPerDraw;

    private String name;
    private int year;
    private int maxTeams;

    private IClub myClub;
    private IClub[] clubs;
    private int contClubs;

    private MatchSimulatorStrategy matchSimulator;
    private IMatch[] matches;
    private int contMatches;

    private ISchedule schedule;

    private IStanding[] standings;
    private int contStandings;

    private boolean seasonComplete;

    /**
     * Construtor cria uma temporada com os parametros basicos.
     * Inicializa arrays para equipas, jogos e classificacao.
     */
    public Season(String name, int year, int maxTeams, int pointsPerWin, int pointsPerLoss, int pointsPerDraw) {
        this.name = name;
        this.year = year;
        this.maxTeams = maxTeams;
        this.pointsPerWin = pointsPerWin;
        this.pointsPerDraw = pointsPerDraw;
        this.pointsPerLoss = pointsPerLoss;

        this.clubs = new IClub[maxTeams];
        this.contClubs = 0;
        this.matches = new IMatch[maxTeams * (maxTeams - 1)];
        this.contMatches = 0;
        this.standings = new IStanding[maxTeams];
        this.seasonComplete = false;
    }

    /**
     * Aumenta o array de clubes em 2 posicoes caso precise de mais espaco.
     */
    public void increaseClubsArray() {
        int newSize = this.clubs.length + 2;
        IClub[] newPlayers = new IClub[newSize];
        for (int i = 0; i < this.clubs.length; i++) {
            newPlayers[i] = this.clubs[i];
        }
        this.clubs = newPlayers;
    }

    /**
     * Tenta adicionar um clube a temporada.
     * Retorna false se ja existir se ultrapassar máxim, ou se houver erro.
     */
    @Override
    public boolean addClub(IClub club) {
        if (findClub(club) != -1) {
            return false; // Já está na lista
        }
        if (contClubs >= maxTeams) {
            return false; // Limite atingido
        }
        if (contClubs >= clubs.length) {
            increaseClubsArray();
        }
        clubs[contClubs++] = club;
        return true;
    }

    /**
     * Procura indice do clube no array. Retorna menos 1 se nao encontrar.
     */
    private int findClub(IClub club) {
        for (int i = 0; i < contClubs; i++) {
            if (clubs[i].equals(club)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Monta uma 'string' simples com o resultado de um jogo.
     * Se nao foi jogado informa isso.
     */
    @Override
    public String displayMatchResult(IMatch match) {
        StringBuilder sb = new StringBuilder();
        if (!match.isPlayed()) {
            sb.append("Match not played.");
            return sb.toString();
        } else {
            int homeGoals = match.getTotalByEvent(com.ppstudios.footballmanager.api.contracts.event.Goal.class, match.getHomeClub());
            int awayGoals = match.getTotalByEvent(com.ppstudios.footballmanager.api.contracts.event.Goal.class, match.getAwayClub());
            sb.append("Resultado final: ")
                    .append(match.getHomeClub().getName())
                    .append(" (").append(homeGoals).append(")")
                    .append(" - ")
                    .append("(").append(awayGoals).append(") ")
                    .append(match.getAwayClub().getName());
            return sb.toString();
        }
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Gera o calendario da temporada com base nos clubes.
     * Se ja existir um calendario, substituio.
     */
    @Override
    public void generateSchedule() {
        if (schedule == null) {
            schedule = new Schedule(maxTeams);
        }
        if (schedule instanceof Schedule) {
            ((Schedule) schedule).generateSchedule(clubs, contClubs);
            this.matches = schedule.getAllMatches();
            this.contMatches = this.matches.length;
        }
    }

    /**
     * Retorna uma copia dos clubes atualmente na temporada.
     */
    @Override
    public IClub[] getCurrentClubs() {
        IClub[] copy = new IClub[contClubs];
        for (int i = 0; i < contClubs; i++) {
            copy[i] = clubs[i];
        }
        return copy;
    }

    public void setNumberOfCurrentTeams(int contClubs) {
        this.contClubs = contClubs;
    }

    public void setCurrentMatches(int contMatches) {
        this.contMatches = contMatches;
    }

    @Override
    public int getCurrentMatches() {
        return this.contMatches;
    }

    @Override
    public int getCurrentRound() {
        return this.schedule.getNumberOfRounds();
    }

    @Override
    public IStanding[] getLeagueStandings() {
        return this.standings;
    }

    @Override
    public IMatch[] getMatches() {
        return this.matches;
    }

    public void setMatches(IMatch[] matches) {
        this.matches = matches;
    }

    @Override
    public IMatch[] getMatches(int round) {
        return this.schedule.getMatchesForRound(round);
    }

    @Override
    public int getMaxRounds() {
        return (maxTeams - 1) * 2;
    }

    @Override
    public int getMaxTeams() {
        return this.maxTeams;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfCurrentTeams() {
        return this.contClubs;
    }

    @Override
    public int getPointsPerDraw() {
        return this.pointsPerDraw;
    }

    @Override
    public int getPointsPerLoss() {
        return this.pointsPerLoss;
    }

    @Override
    public int getPointsPerWin() {
        return this.pointsPerWin;
    }

    @Override
    public ISchedule getSchedule() {
        return this.schedule;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public boolean isSeasonComplete() {
        return this.seasonComplete;
    }

    public void setSeasonComplete(boolean seasonComplete) {
        this.seasonComplete = seasonComplete;
    }

    /**
     * Remove um clube da temporada.
     * Retorna false se o clube nao existir.
     */
    @Override
    public boolean removeClub(IClub club) {
        int index = findClub(club);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < contClubs - 1; i++) {
            clubs[i] = clubs[i + 1];
        }
        clubs[--contClubs] = null;
        return true;
    }

    /**
     * Recomeca a temporada para estado inicial.
     */
    @Override
    public void resetSeason() {
        this.contMatches = 0;
        this.schedule = null;
        this.standings = new IStanding[maxTeams];
        this.seasonComplete = false;
    }

    /**
     * Define a estrategia para simulacao de partidas.
     */
    @Override
    public void setMatchSimulator(MatchSimulatorStrategy matchSimulator) {
        this.matchSimulator = matchSimulator;
    }

    /**
     * Metodos para simular rodadas e a temporada inteira nao implementados.
     * Lancam excecao para avisar.
     */
    @Override
    public void simulateRound() {
        throw new UnsupportedOperationException("Simulation not implemented");
    }

    @Override
    public void simulateSeason() {
        throw new UnsupportedOperationException("Simulation not implemented");
    }

    public void setStandings(Standing[] standings) {
        this.standings = standings;
    }

    /**
     * Adiciona uma classificacao na lista de standings.
     * Retorna false se ja estiver cheia.
     */
    public boolean addStanding(IStanding standing) {
        if (contStandings >= maxTeams) {
            return false;
        }
        standings[contStandings++] = standing;
        return true;
    }

    /**
     * Exporta os dados da temporada para um arquivo JSON.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject seasonJson = DataExporter.seasonToJsonObject(this);

        try (FileWriter file = new FileWriter("season_" + year + ".json")) {
            file.write(seasonJson.toJSONString());
        }
    }
}
