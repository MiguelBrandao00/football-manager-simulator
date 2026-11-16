package com.ppstudios.footballmanager.api.contracts.team;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
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
 * Representa um clube de futebol com informações básicas e gestão de jogadores.
 * <p>
 * Contém dados como nome, país, estádio, jogadores, estatísticas de jogo e funcionalidade
 * para exportação em JSON.
 */
public class Club implements IClub {
    private String name;
    private String code;
    private String Country;
    private int foundedYear;
    private String stadiumName;
    private String logo;
    private int points;
    private int numberOfGames;
    private int goalsScored;
    private int goalsConceded;

    protected IPlayer[] players;
    private int playerCount;


    /**
     * Construtor da classe Club.
     *
     * @param name        Nome do clube.
     * @param players     Array de jogadores do clube.
     * @param code        Código do clube (abreviação).
     * @param Country     País do clube.
     * @param foundedYear Ano de fundação.
     * @param stadiumName Nome do estádio.
     * @param logo        URL ou nome do ficheiro do logótipo.
     */
    public Club(String name, IPlayer[] players, String code, String Country, int foundedYear, String stadiumName, String logo) {
        this.name = name;
        this.players = players;
        this.code = code;
        this.Country = Country;
        this.foundedYear = foundedYear;
        this.stadiumName = stadiumName;
        this.logo = logo;
        this.playerCount = 0;
        this.points = 0;
        this.numberOfGames = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }

    /**
     * @return Nome do clube.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return Array de jogadores.
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
     * @return Código do clube.
     */
    @Override
    public String getCode() {
        return this.code;
    }


    /**
     * @return País do clube.
     */
    @Override
    public String getCountry() {
        return this.Country;
    }


    /**
     * @return Ano de fundação.
     */
    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    /**
     * @return Nome do estádio.
     */
    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }


    /**
     * @return Logótipo do clube.
     */
    @Override
    public String getLogo() {
        return this.logo;
    }

    /**
     * @return Número atual de jogadores no clube.
     */
    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }


    /**
     * @return Pontos atuais da equipa.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * @return Número de jogos disputados.
     */
    public int getNumberOfGames() {
        return this.numberOfGames;
    }

    /**
     * @return Total de golos marcados.
     */
    public int getGoalsScored() {
        return this.goalsScored;
    }

    /**
     * @return Total de golos sofridos.
     */
    public int getGoalsConceded() {
        return this.goalsConceded;
    }

    /**
     * Adiciona golos marcados ao total do clube.
     *
     * @param goalsScored Número de golos a adicionar.
     */
    public void addGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    /**
     * Adiciona golos sofridos ao total do clube.
     *
     * @param goalsConceded Número de golos sofridos a adicionar.
     */
    public void addGoalsConceded(int goalsConceded) {
        this.goalsConceded += goalsConceded;
    }

    /**
     * Incrementa o número de jogos disputados pelo clube.
     */
    public void incrementNumberOfGames() {
        this.numberOfGames++;
    }

    /**
     * Aumenta o tamanho do array de jogadores em 1.
     * Utilizado quando é necessário adicionar mais jogadores.
     */
    private void addSpace() {
        int newSize = this.players.length + 1;
        IPlayer[] newPlayers = new IPlayer[newSize];
        for (int i = 0; i < this.players.length; i++) {
            newPlayers[i] = this.players[i];
        }
        this.players = newPlayers;
    }

    /**
     * Adiciona um jogador ao clube. Caso o array de jogadores esteja cheio, aumenta o seu tamanho.
     *
     * @param var1 Jogador a adicionar.
     */
    @Override
    public void addPlayer(IPlayer var1) {
        if (this.playerCount == this.players.length) {
            addSpace();
        }
        this.players[this.playerCount++] = var1;
    }

    /**
     * Procura o índice de um jogador no array de jogadores.
     *
     * @param player Jogador a procurar.
     * @return Índice do jogador se encontrado, -1 caso contrário.
     */
    private int findPlayerIndex(IPlayer player) {
        for (int i = 0; i < this.playerCount; i++) {
            if (this.players[i] == player) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove um jogador do clube.
     *
     * @param var1 Jogador a remover.
     */
    @Override
    public void removePlayer(IPlayer var1) {
        int position = findPlayerIndex(var1);
        if (position != -1) {
            for (int i = position; i < this.playerCount - 1; i++) {
                this.players[i] = this.players[i + 1];
            }
            this.players[this.playerCount - 1] = null;
            this.playerCount--;
        }
    }

    /**
     * Verifica se um jogador pertence ao clube.
     *
     * @param var1 Jogador a verificar.
     * @return true se o jogador estiver no clube, false caso contrário.
     */
    @Override
    public boolean isPlayer(IPlayer var1) {
        return findPlayerIndex(var1) != -1;
    }

    /**
     * Seleciona um jogador com base num seletor e numa posição.
     *
     * @param selector Objeto seletor de jogadores.
     * @param position Posição desejada.
     * @return Jogador selecionado.
     */
    @Override
    public IPlayer selectPlayer(IPlayerSelector selector, IPlayerPosition position) {
        IPlayer player = selector.selectPlayer(this, position);
        return player;
    }

    /**
     * Verifica se o clube é válido.
     * Um clube é válido se tiver um array de jogadores não nulo, não vazio
     * e sem entradas nulas.
     *
     * @return true se o clube for válido, false caso contrário.
     */
    @Override
    public boolean isValid() {
        if (players == null) {
            return false;
        }
        if (players.length == 0) {
            return false;
        }
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Exporta os dados do clube para um ficheiro JSON com o nome "club<code>.json".
     *
     * @throws IOException Caso ocorra erro ao escrever o ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject clubJson = DataExporter.clubToJsonObject(this);

        try (FileWriter file = new FileWriter("club.json")) {
            file.write(clubJson.toJSONString());
        }
    }

}
