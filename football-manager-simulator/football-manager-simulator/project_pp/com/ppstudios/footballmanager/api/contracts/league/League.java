package com.ppstudios.footballmanager.api.contracts.league;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
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
 * Representa uma liga de futebol composta por várias épocas (seasons).
 * Permite criar, remover e obter epocas, alem de exportar os dados da liga para um ficheiro JSON.
 */
public class League implements ILeague {
    private final static int MAX_NUMBER_OF_SEASONS = 10;
    private final static int INCREASE_FACTOR = 2;

    private String leagueName;
    private ISeason[] seasons;
    private int countSeasons;

    /**
     * Construtor da classe League.
     *
     * @param leagueName Nome da liga.
     */
    public League(String leagueName) {
        this.leagueName = leagueName;
        this.seasons = new ISeason[MAX_NUMBER_OF_SEASONS];
        this.countSeasons = 0;
    }

    /**
     * Aumenta o tamanho do array de epocas quando esta cheio.
     */
    private void increaseArray() {
        ISeason[] newSeasons = new ISeason[seasons.length * INCREASE_FACTOR];
        for (int i = 0; i < seasons.length; i++) {
            newSeasons[i] = seasons[i];
        }
        seasons = newSeasons;
    }

    /**
     * Cria uma nova epoca na liga, se ainda nao existir.
     *
     * @param season Epoca a adicionar.
     * @return true se a epoca foi adicionada, false se ja existia.
     */
    @Override
    public boolean createSeason(ISeason season) {
        if (findSeason(season) != -1) {
            return false;
        }
        if (countSeasons == seasons.length) {
            increaseArray();
        }
        seasons[countSeasons++] = season;
        return true;
    }

    /**
     * Procura uma epoca no array.
     *
     * @param season Epoca a procurar.
     * @return Indice da epoca se encontrada, -1 caso contrario.
     */
    private int findSeason(ISeason season) {
        for (int i = 0; i < countSeasons; i++) {
            if (seasons[i].equals(season)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Procura uma epoca com base no ano.
     *
     * @param year Ano da epoca.
     * @return Indice da epoca se encontrada, -1 caso contrario.
     */
    private int findSeasonByYear(int year) {
        for (int i = 0; i < countSeasons; i++) {
            if (seasons[i].getYear() == year) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Devolve o nome da liga.
     *
     * @return Nome da liga.
     */
    @Override
    public String getName() {
        return this.leagueName;
    }

    /**
     * Obtem uma epoca especifica pelo ano.
     *
     * @param year Ano da epoca.
     * @return A epoca correspondente, ou null se nao existir.
     */
    @Override
    public ISeason getSeason(int year) {
        for (int i = 0; i < countSeasons; i++) {
            if (seasons[i].getYear() == year) {
                return seasons[i];
            }
        }
        return null;
    }

    /**
     * Devolve todas as epocas da liga.
     *
     * @return Array com todas as epocas.
     */
    @Override
    public ISeason[] getSeasons() {
        ISeason[] copy = new ISeason[countSeasons];
        for (int i = 0; i < countSeasons; i++) {
            copy[i] = seasons[i];
        }
        return copy;
    }

    /**
     * Remove uma epoca com base no ano.
     *
     * @param year Ano da epoca a remover.
     * @return A epoca removida ou null se não existir.
     */
    @Override
    public ISeason removeSeason(int year) {
        int index = findSeasonByYear(year);
        if (index == -1) {
            return null;
        }
        ISeason removed = seasons[index];
        for (int i = index; i < countSeasons - 1; i++) {
            seasons[i] = seasons[i + 1];
        }
        seasons[--countSeasons] = null;
        return removed;
    }

    /**
     * Exporta os dados da liga para um ficheiro JSON.
     *
     * @throws IOException Caso ocorra erro ao escrever no ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject leagueJson = DataExporter.leagueToJsonObject(this);

        try (FileWriter file = new FileWriter("C:\\Users\\Huawei\\Documents\\PP\\PP_8240331_8240332\\lib\\files\\season.json")) {
            file.write(leagueJson.toJSONString());
        }
    }
}
