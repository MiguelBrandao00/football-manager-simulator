package com.ppstudios.footballmanager.api.contracts.league;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.match.Match;
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
 * Representa o calendario (schedule) de uma liga de futebol,
 * gerenciando os jogos distribuidos em rondas.
 */
public class Schedule implements ISchedule {
    private IMatch[][] rounds;
    private int maxTeams;
    private int maxRounds;

    /**
     * Construtor que define o numero maximo de times
     * e calcula o numero de rondas baseado nisso.
     *
     * @param maxTeams Numero maximo de equipas na liga.
     */
    public Schedule(int maxTeams) {
        this.maxTeams = maxTeams;
        if (maxTeams % 2 == 0) {
            this.maxRounds = (maxTeams - 1) * 2;
        } else {
            this.maxRounds = maxTeams * 2;
        }
        this.rounds = new IMatch[maxRounds][maxTeams / 2];
    }

    /**
     * Retorna o numero maximo de times da liga.
     */
    public int getMaxTeams() {
        return this.maxTeams;
    }

    /**
     * Retorna a matriz de jogos organizada por rondas.
     */
    public IMatch[][] getRounds() {
        return this.rounds;
    }

    /**
     * Define a matriz de jogos (rounds).
     */
    public void setRounds(IMatch[][] rounds) {
        this.rounds = rounds;
    }

    /**
     * Retorna todos os jogos da liga em um array linear,
     * percorrendo todas as rondas e os respetivos jogos.
     */
    @Override
    public IMatch[] getAllMatches() {
        int totalMatches = maxRounds * (maxTeams / 2);
        IMatch[] allInLine = new IMatch[totalMatches];
        int index = 0;

        for (int i = 0; i < maxRounds; i++) {
            for (int j = 0; j < rounds[i].length; j++) {
                allInLine[index++] = rounds[i][j];
            }
        }
        return allInLine;
    }

    /**
     * Retorna os jogos de uma ronda especifica.
     *
     * @param round Numero da ronda (1-based).
     * @return Array de jogos da ronda ou vazio se ronda inválida.
     */
    @Override
    public IMatch[] getMatchesForRound(int round) {
        if (round <= 0 || round > maxRounds) {
            return new IMatch[0];
        } else {
            return rounds[round - 1];
        }
    }

    /**
     * Conta quantos jogos uma equipa vai disputar no calendario.
     *
     * @param team equipa para contar os jogos.
     * @return Numero total de jogos da equipa.
     */
    public int getNumberOfMatchesForTeam(ITeam team) {
        int count = 0;
        for (int i = 0; i < maxRounds; i++) {
            for (IMatch match : rounds[i]) {
                if (match != null && (match.getHomeClub().equals(team.getClub()) || match.getAwayClub().equals(team.getClub()))) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Retorna todos os jogos de uma equipa percorrendo todas as rondas.
     *
     * @param team equipa cujos jogos serao retornados.
     * @return Array de jogos do equipa.
     */
    @Override
    public IMatch[] getMatchesForTeam(ITeam team) {
        int count = getNumberOfMatchesForTeam(team);
        IMatch[] teamMatches = new IMatch[count];
        int index = 0;
        for (int i = 0; i < maxRounds; i++) {
            for (IMatch match : rounds[i]) {
                if (match != null && (match.getHomeClub().equals(team.getClub()) || match.getAwayClub().equals(team.getClub()))) {
                    teamMatches[index++] = match;
                }
            }
        }
        return teamMatches;
    }

    /**
     * Retorna o numero total de rondas do calendario.
     */
    @Override
    public int getNumberOfRounds() {
        return this.maxRounds;
    }

    /**
     * Gera o calendario completo da liga, montando os jogos
     * entre os clubes fornecidos, incluindo jogos de ida e volta.
     * Se o numero de clubes for impar, adiciona um (null).
     *
     * @param clubs       Array de clubes participantes.
     * @param clubsNumber Numero de clubes na liga.
     */
    public void generateSchedule(IClub[] clubs, int clubsNumber) {
        boolean isOdd = (clubsNumber % 2 != 0);
        int actualClubNumber;
        if (isOdd) {
            actualClubNumber = clubsNumber + 1;
        } else {
            actualClubNumber = clubsNumber;
        }
        IClub[] extendedClubs = new IClub[actualClubNumber];
        for (int i = 0; i < clubsNumber; i++) {
            extendedClubs[i] = clubs[i];
        }
        if (isOdd) {
            extendedClubs[actualClubNumber - 1] = null;
        }

        int totalRounds = actualClubNumber - 1;
        rounds = new Match[totalRounds * 2][actualClubNumber / 2];

        for (int round = 0; round < totalRounds; round++) {
            System.out.println("Round " + (round + 1) + ":");
            for (int match = 0; match < actualClubNumber / 2; match++) {
                int home = (round + match) % (actualClubNumber - 1);
                int away = (actualClubNumber - 1 - match + round) % (actualClubNumber - 1);

                if (match == 0) {
                    away = actualClubNumber - 1;
                }

                IClub homeClub = extendedClubs[home];
                IClub awayClub = extendedClubs[away];
                if (homeClub == null || awayClub == null) {
                    continue;
                }
                Match firstLeg = new Match(homeClub, awayClub, round);
                rounds[round][match] = firstLeg;
                System.out.println("  " + homeClub.getName() + " vs " + awayClub.getName());
            }
        }

        for (int round = 0; round < totalRounds; round++) {
            System.out.println("Round " + (round + 1 + totalRounds) + ":");
            for (int match = 0; match < actualClubNumber / 2; match++) {
                Match firstLeg = (Match) rounds[round][match];
                if (firstLeg == null) {
                    continue;
                }
                IClub homeClub = firstLeg.getAwayClub();
                IClub awayClub = firstLeg.getHomeClub();

                Match secondLeg = new Match(homeClub, awayClub, round + totalRounds);
                rounds[round + totalRounds][match] = secondLeg;

                System.out.println("  " + homeClub.getName() + " vs " + awayClub.getName());
            }
        }
    }

    /**
     * Define um jogo especifico na matriz de rondas.
     *
     * @param roundIndex Indice da ronda (0-based).
     * @param matchIndex Indice do jogo dentro da ronda.
     * @param match      Objeto Match a ser colocado.
     */
    public void setMatch(int roundIndex, int matchIndex, Match match) {
        this.rounds[roundIndex][matchIndex] = match;
    }

    /**
     * Associa uma equipa a um jogo numa ronda especifica,
     * ajustando o objeto Match.
     *
     * @param team  Time a associar.
     * @param round Numero da ronda (0-based).
     */
    @Override
    public void setTeam(ITeam team, int round) {
        if (round < 0 || round >= maxRounds) {
            return;
        }

        for (IMatch match : rounds[round]) {
            if (match == null) continue;

            if (match.getHomeClub().equals(team.getClub()) || match.getAwayClub().equals(team.getClub())) {
                match.setTeam(team);
                break;
            }
        }
    }

    /**
     * Exporta o calendario completo para um arquivo JSON chamado "schedule.json".
     * Usa o DataExporter para converter o Schedule em JSON.
     *
     * @throws IOException Caso falhe ao escrever o arquivo.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject scheduleJson = DataExporter.scheduleToJsonObject(this);

        try (FileWriter file = new FileWriter("schedule.json")) {
            file.write(scheduleJson.toJSONString());
        }
    }
}
