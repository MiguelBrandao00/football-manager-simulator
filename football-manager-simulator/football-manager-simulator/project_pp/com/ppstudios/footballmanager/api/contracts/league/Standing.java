package com.ppstudios.footballmanager.api.contracts.league;

import com.ppstudios.footballmanager.api.contracts.team.ITeam;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Representa a classificacao (standing) de uma equipa na liga,
 * guardando estatisticas como vitorias, empates, derrotas,
 * golos marcados, golos sofridos e pontos.
 */
public class Standing implements IStanding {
    private ITeam currentTeam;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;
    private int points;

    /**
     * Cria uma classificacao para uma equipa, iniciando tudo a zero.
     *
     * @param currentTeam equipa associado a esta classificacao.
     */
    public Standing(ITeam currentTeam) {
        this.currentTeam = currentTeam;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.points = 0;
    }

    /**
     * Regista um empate e adiciona os pontos correspondentes.
     */
    @Override
    public void addDraw(int pointsPerDraw) {
        this.draws++;
        this.points += pointsPerDraw;
    }

    /**
     * Regista uma derrota e adiciona os pontos correspondentes.
     */
    @Override
    public void addLoss(int pointsPerLoss) {
        this.losses++;
        this.points += pointsPerLoss;
    }

    /**
     * Adiciona pontos diretamente.
     */
    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Regista uma vitoria e adiciona os pontos correspondentes.
     */
    @Override
    public void addWin(int pointsPerWin) {
        this.wins++;
        this.points += pointsPerWin;
    }

    /**
     * Retorna o total de empates do time.
     */
    @Override
    public int getDraws() {
        return this.draws;
    }

    /**
     * Retorna a diferenca de golo (golos marcados - golos sofridos).
     */
    @Override
    public int getGoalDifference() {
        return this.goalsScored - this.goalsConceded;
    }

    /**
     * Retorna o total de golos sofridos.
     */
    @Override
    public int getGoalsConceded() {
        return this.goalsConceded;
    }

    /**
     * Retorna o total de golos marcados.
     */
    @Override
    public int getGoalScored() {
        return this.goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    /**
     * Retorna o total de derrotas.
     */
    @Override
    public int getLosses() {
        return this.losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Retorna os pontos acumulados.
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retorna o equipa associado a esta classificacao.
     */
    @Override
    public ITeam getTeam() {
        return this.currentTeam;
    }

    /**
     * Retorna o numero total de jogos disputados (vitorias + empates + derrotas).
     */
    @Override
    public int getTotalMatches() {
        return this.wins + this.draws + this.losses;
    }

    /**
     * Retorna o numero de vitorias.
     */
    @Override
    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Adiciona golos marcados ao total.
     */
    public void addGoalsScored(int goals) {
        this.goalsScored += goals;
    }

    /**
     * Adiciona golos sofridos ao total.
     */
    public void addGoalsConceded(int goals) {
        this.goalsConceded += goals;
    }
}
