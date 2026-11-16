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
 * Representa um evento de canto (corner) num jogo de futebol.
 * <p>
 * Esta classe implementa a 'interface' ICornerEvent e modela um canto
 * cobrado por um jogador especifico, num determinado minuto, com uma descricao.
 * Também permite exportar os dados do evento para um ficheiro JSON.
 */
public class Corner implements ICornerEvent {
    private String description;
    private int minute;
    private IPlayer player;

    /**
     * Cria uma instancia do evento de canto.
     *
     * @param description Descricao do evento.
     * @param minute      Minuto em que o canto ocorre.
     * @param player      Jogador responsavel por cobrar o canto.
     */
    public Corner(String description, int minute, IPlayer player) {
        this.description = description;
        this.minute = minute;
        this.player = player;
    }

    /**
     * Obtém a descricao do evento.
     *
     * @return A descricao do canto.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtem o minuto em que o canto foi cobrado.
     *
     * @return O minuto do canto.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Obtém o jogador que cobrou o canto.
     *
     * @return O jogador associado ao canto.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    /**
     * Retorna uma representacao textual do evento de canto.
     *
     * @return A 'string' descritiva do canto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Canto a ser cobrado por ").append(getPlayer().getName()).append(" aos ").append(getMinute()).append(" minutos!");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro durante a escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject cornerJson = DataExporter.cornerToJsonObject(this);

        try (FileWriter file = new FileWriter("corner_event.json")) {
            file.write(cornerJson.toJSONString());
        }
    }
}

