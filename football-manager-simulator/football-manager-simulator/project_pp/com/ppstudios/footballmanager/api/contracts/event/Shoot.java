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
 * Representa um evento de remate (shoot) durante um jogo de futebol.
 * <p>
 * Esta classe e abstrata e implementa a 'interface' IShootEvent e serve como base para diferentes tipos de remates,
 * como penaltis, golos ou livres diretos. Armazena dados como a descricao, minuto do jogo,
 * se foi a baliza e o jogador que rematou.
 */
public abstract class Shoot implements IShootEvent {
    private String description;
    private int minute;
    private boolean isOnTarget;
    private IPlayer player;

    /**
     * Construtor para criar um evento de remate.
     *
     * @param description Descricao do remate (ex: "Remate forte de fora da area").
     * @param minute      Minuto em que o remate ocorreu.
     * @param isOnTarget  Indica se o remate foi a baliza 'true' ou não 'false'.
     * @param player      Jogador que efetuou o remate.
     */
    public Shoot(String description, int minute, boolean isOnTarget, IPlayer player) {
        this.description = description;
        this.minute = minute;
        this.isOnTarget = isOnTarget;
        this.player = player;
    }

    /**
     * Obtem a descricao do evento.
     *
     * @return A descricao do remate.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtem o minuto em que o remate foi feito.
     *
     * @return O minuto do remate.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Verifica se o remate foi a baliza.
     *
     * @return 'true' se foi a baliza, 'false' caso contrario.
     */
    public boolean isOnTarget() {
        return this.isOnTarget;
    }

    /**
     * Obtem o jogador que fez o remate.
     *
     * @return O jogador associado ao remate.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    /**
     * Retorna uma representacao textual do evento de remate.
     *
     * @return A 'string' descritiva do remate.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Remate de ").append(getPlayer().getName()).append(" aos ").append(getMinute()).append(" minutos. ");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro durante a escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject shootJson = DataExporter.shootToJsonObject(this);

        try (FileWriter file = new FileWriter("shoot_event.json")) {
            file.write(shootJson.toJSONString());
        }
    }
}

