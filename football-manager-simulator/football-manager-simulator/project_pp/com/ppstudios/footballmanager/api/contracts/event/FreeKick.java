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
 * Representa um evento de livre direto num jogo de futebol.
 * <p>
 * Esta classe estende 'Shoot' e esta associada a uma 'Foul',
 * que originou a cobranca do livre. Permite simular o remate e exportar
 * o evento para JSON.
 */
public class FreeKick extends Shoot implements IFreeKickEvent {
    private Foul foul;

    /**
     * Construtor para um evento de livre direto.
     *
     * @param description Descricaoo do remate.
     * @param player      Jogador que vai cobrar o livre.
     * @param foul        A falta que originou o livre.
     */
    public FreeKick(String description, IPlayer player, Foul foul) {
        super(description, foul.getMinute(), true, player);
        this.foul = foul;
    }

    /**
     * Retorna a falta que originou o livre direto.
     *
     * @return A instancia de 'Foul'.
     */
    public Foul getFoul() {
        return foul;
    }

    /**
     * Retorna o jogador que vai bater o livre.
     *
     * @return Jogador responsavel pela cobranca.
     */
    @Override
    public IPlayer getPlayer() {
        return super.getPlayer();
    }

    /**
     * Retorna o minuto em que o livre sera batido.
     *
     * @return Minuto do evento.
     */
    @Override
    public int getMinute() {
        return super.getMinute();
    }

    /**
     * Retorna a descricao do remate.
     *
     * @return Descricao textual do livre.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Simula a cobranca do livre e retorna o resultado.
     *
     * @param goalkeeper Guarda-redes adversario.
     * @param isGoal     Indica se o remate resultou em golo.
     * @return Um 'Goal' se for golo, ou 'Defense' caso contrario.
     */
    public IEvent takeShoot(IPlayer goalkeeper, boolean isGoal) {
        String shotDescription = "Shoot after Free Kick";

        if (isGoal) {
            return new Goal(shotDescription, getMinute(), getPlayer());
        } else {
            return new Defense(shotDescription, getMinute(), goalkeeper);
        }
    }

    /**
     * Retorna uma representacao textual do evento.
     *
     * @return String descritiva do livre direto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Free kick done by: ")
                .append(getPlayer().getName())
                .append(" at ").append(getMinute()).append(" minutes. Will it score?");
        return sb.toString();
    }

    /**
     * Exporta os dados do evento para um ficheiro JSON.
     *
     * @throws IOException Se ocorrer um erro na escrita do ficheiro.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject freeKickJson = DataExporter.freeKickToJsonObject(this);

        try (FileWriter file = new FileWriter("freeKick_event.json")) {
            file.write(freeKickJson.toJSONString());
        }
    }
}


