package com.ppstudios.footballmanager.api.contracts.event;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
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
 * Representa um evento de defesa (defense) num jogo de futebol.
 * <p>
 * Esta classe implementa a ‘interface’ IDefenseEvent e modela uma defesa
 * realizada por um jogador especifico, num determinado minuto, com uma descricao.
 * Tambem permite exportar os dados do evento para um ficheiro JSON.
 */
public class Defense implements IDefenseEvent {
    private String description;
    private int minute;
    private IPlayer goalKeeper;

    /**
     * Cria uma instancia do evento de canto.
     *
     * @param description Descricao do evento.
     * @param minute      Minuto em que o canto ocorre.
     * @param goalKeeper  Jogador responsavel por cobrar o canto.
     */
    public Defense(String description, int minute, IPlayer goalKeeper) {
        this.description = description;
        this.minute = minute;
        this.goalKeeper = goalKeeper;
    }

    /**
     * Obtem a descricao do evento.
     *
     * @return A descricao da defesa.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtem o minuto em que a defesa foi feita.
     *
     * @return O minuto da defesa.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Obtem o guarda-redes que cobrou o canto.
     *
     * @return O guarda-redes associado ao canto.
     */
    @Override
    public IPlayer getGoalKeeper() {
        return this.goalKeeper;
    }

    /**
     * Retorna uma representacao textual do evento de defesa.
     *
     * @return A 'string' descritiva do canto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Defesa de ").append(getGoalKeeper().getName()).append(" aos ").append(getMinute()).append(" minutos. ");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro durante a escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject defenseJson = DataExporter.defenseToJsonObject(this);

        try (FileWriter file = new FileWriter("defense_event.json")) {
            file.write(defenseJson.toJSONString());
        }
    }

}
