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
 * Representa o evento golo num jogo de futebol.
 * <p>
 * Esta classe estende 'Goal' e implementa 'IGoalEvent',
 * sendo usada para registrar remates que resultaram num golo.
 * Os dados do evento podem ser exportados para um ficheiro JSON.
 */
public class Goal extends Shoot implements IGoalEvent {

    /**
     * Construtor do evento de golo.
     *
     * @param description Descricao do remate que resultou em golo.
     * @param minute      Minuto em que o golo foi marcado.
     * @param player      Jogador que marcou o golo.
     */
    public Goal(String description, int minute, IPlayer player) {
        super(description, minute, true, player);
    }

    /**
     * Retorna a descricao do golo.
     *
     * @return Descricao textual.
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Retorna o minuto do golo.
     *
     * @return Minuto do evento.
     */
    public int getMinute() {
        return super.getMinute();
    }

    /**
     * Retorna o jogador que marcou o golo.
     *
     * @return O jogador que realizou o remate.
     */
    public IPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * Representacao textual do evento de golo.
     *
     * @return 'String' com informacao do golo.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gole by ").append(getPlayer().getName()).append(" at ").append(getMinute()).append(" minutes. ");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro na escrita do ficheiro.
     */
    public void exportToJson() throws IOException {
        JSONObject goalJson = DataExporter.goalToJsonObject(this);

        try (FileWriter file = new FileWriter("goal_event.json")) {
            file.write(goalJson.toJSONString());
        }
    }
}
