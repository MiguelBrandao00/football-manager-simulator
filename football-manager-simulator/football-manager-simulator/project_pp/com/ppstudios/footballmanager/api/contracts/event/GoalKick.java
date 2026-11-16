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
 * Representa um evento de pontape de baliza num jogo de futebol.
 * <p>
 * Um pontape de baliza e realizado pelo guarda-redes e reinicia o jogo apos a bola sair pela linha de fundo.
 * Esta classe permite registar o evento e exportalo para JSON.
 */
public class GoalKick implements IGoalKickEvent {
    private String description;
    private int minute;
    private IPlayer goalkeeper;

    /**
     * Construtor da classe GoalKick.
     *
     * @param description Descricao do evento.
     * @param minute      Minuto em que ocorreu o pontape de baliza.
     * @param goalkeeper  Guarda-redes que executou o pontape.
     */
    public GoalKick(String description, int minute, IPlayer goalkeeper) {
        this.description = description;
        this.minute = minute;
        this.goalkeeper = goalkeeper;
    }

    /**
     * Retorna a descricao do evento.
     *
     * @return String descritiva do evento.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Retorna o minuto do evento.
     *
     * @return Minuto do pontape de baliza.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Retorna o guarda-redes que executou o pontape de baliza.
     *
     * @return o guarda-redes que realizou o pontape de baliza.
     */
    @Override
    public IPlayer getGoalKeeper() {
        return this.goalkeeper;
    }

    /**
     * Retorna uma representacao textual do evento.
     *
     * @return 'String' com detalhes do pontape de baliza.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Goal Kick ")
                .append(getGoalKeeper().getName())
                .append(" at ")
                .append(getMinute())
                .append(" minutes.");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Caso ocorra erro ao escrever o ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject goalKickJson = DataExporter.goalKickToJsonObject(this);

        try (FileWriter file = new FileWriter("goalKick_event.json")) {
            file.write(goalKickJson.toJSONString());
        }
    }
}

