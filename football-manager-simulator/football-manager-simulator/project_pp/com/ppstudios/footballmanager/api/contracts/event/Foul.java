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
 * Representa um evento de falta durante um jogo de futebol.
 * <p>
 * Esta classe implementa a 'interface' IFoulEvent e modela uma falta cometida por um jogador
 * num minuto especifico, podendo estar associada a um tipo de cartao (amarelo/vermelho).
 * Os dados do evento podem ser exportados para um ficheiro JSON.
 */
public class Foul implements IFoulEvent {
    private String description;
    private int minute;
    private IPlayer player;
    private CardType card;

    /**
     * Construtor para criar um evento de falta.
     *
     * @param description Descricao da falta.
     * @param minute      Minuto em que a falta ocorreu (0 a 120).
     * @param player      Jogador que cometeu a falta.
     * @param type        Tipo de cartao associado a falta (pode ser 'null').
     * @throws IllegalArgumentException Se a descrição for nula/vazia ou o minuto invalido.
     */
    public Foul(String description, int minute, IPlayer player, CardType type) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("The description cannot be empty");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute");
        }
        this.description = description;
        this.minute = minute;
        this.player = player;
        this.card = type;
    }

    /**
     * Obtem a descricaoo da falta.
     *
     * @return A descricaoo textual da falta.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtem o minuto em que a falta foi cometida.
     *
     * @return Minuto do evento de falta.
     */

    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Obtem o jogador que cometeu a falta.
     *
     * @return O jogador associado a falta.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    /**
     * Retorna uma representacaoo textual do evento de falta.
     *
     * @return A 'string' descritiva da falta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Foul ").append(player.getName()).append(" at ").append(minute).append(" minutes. ").append(CardType.cardTypeToString(card));
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro durante a escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject foulJson = DataExporter.foulToJsonObject(this);

        try (FileWriter file = new FileWriter("foul_event.json")) {
            file.write(foulJson.toJSONString());
        }
    }
}
