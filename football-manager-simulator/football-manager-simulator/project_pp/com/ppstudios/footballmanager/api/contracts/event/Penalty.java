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
 * Representa um evento de penalti num jogo de futebol.
 * <p>
 * Um penalti e um tipo especial de remate (herda da classe 'Shoot'), causado por uma 'Foul'.
 * Esta classe permite associar a falta que originou o penalti e realizar o remate,
 * podendo resultar num 'Goal' ou numa 'Defense'.
 */
public class Penalty extends Shoot implements IPenaltyEvent {
    private Foul foul;

    /**
     * Construtor de um penalti.
     *
     * @param description Descrição do remate.
     * @param player      Jogador que executa o penalti.
     * @param foul        A falta que originou o penalti.
     */
    public Penalty(String description, IPlayer player, Foul foul) {
        super(description, foul.getMinute(), true, player);
        this.foul = foul;
    }

    /**
     * Retorna a falta que originou o penalti.
     *
     * @return A instancia de 'Foul'.
     */
    public Foul getFoul() {
        return foul;
    }

    /**
     * Retorna o jogador que vai bater o penalti.
     *
     * @return O jogador que executa o penalti.
     */
    @Override
    public IPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * Retorna o minuto em que a falta ocorreu (e por consequencia, o penalti).
     *
     * @return Minuto do penalti.
     */
    @Override
    public int getMinute() {
        return foul.getMinute();
    }

    /**
     * Retorna a descricao associada a falta que causou o penalti.
     *
     * @return Descricao do evento.
     */
    @Override
    public String getDescription() {
        return foul.getDescription();
    }

    /**
     * Executa o penalti e retorna o resultado do remate.
     *
     * @param goalkeeper Guarda-redes adversario.
     * @param isGoal     Indica se o penalti foi convertido em golo.
     * @return Um objeto 'Goal' se for golo, ou 'Defense' caso contrario.
     */
    public IEvent takeShoot(IPlayer goalkeeper, boolean isGoal) {
        String shotDescription = "Penalty";

        if (isGoal) {
            return new Goal(shotDescription, getMinute(), getPlayer());
        } else {
            return new Defense(shotDescription, getMinute(), goalkeeper);
        }
    }

    /**
     * Retorna uma 'string' descritiva do penalti.
     *
     * @return Representação textual do evento.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Penalty shot by ").append(getPlayer().getName())
                .append(" at ").append(getMinute()).append(" minutes. Will it score?");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro ao escrever o ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject penaltyJson = DataExporter.penaltyToJsonObject(this);

        try (FileWriter file = new FileWriter("penalty_event.json")) {
            file.write(penaltyJson.toJSONString());
        }
    }
}
