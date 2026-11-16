package com.ppstudios.footballmanager.api.contracts.simulation;

import com.ppstudios.footballmanager.api.contracts.Exceptions.InvalidTeamOrPlayerException;
import com.ppstudios.footballmanager.api.contracts.Exceptions.PlayerNotInTeamException;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.*;

import java.util.Random;
import java.util.Scanner;

/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

public class Simulate implements MatchSimulatorStrategy {
    private int teamFreekicks = 0;
    private int teamCorners = 0;
    private int teamPenalties = 0;
    private int teamGoals = 0;
    private int teamShots = 0;
    private int teamRedCards = 0;
    private int teamYellowCards = 0;
    private int teamNullCards = 0;
    private int teamFouls = 0;

    /**
     * Permite ao utilizador escolher a formação da equipa através da consola.
     *
     * @return Formação escolhida pelo utilizador.
     */
    private Formation userFormation() {
        Scanner scanner = new Scanner(System.in);
        Formation[] formations = Formation.values();

        System.out.println("Choose your formation:");
        for (int i = 0; i < formations.length; i++) {
            System.out.println(i + " - " + formations[i].getDisplayName());
        }

        int choice = -1;
        while (choice < 0 || choice >= formations.length) {
            System.out.print("Formation's Number: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Please write a valid number.");
            }
        }

        return formations[choice];
    }

    /**
     * Cria uma equipa com base num clube e numa formação escolhida.
     * Os jogadores são selecionados com base nas posições permitidas pela formação.
     *
     * @param club Clube base da equipa.
     * @param formation Formação tática escolhida.
     * @return Equipa criada.
     */
    private ITeam createUserTeam(IClub club, Formation formation) {
        IPlayerSelector selector = new PlayerSelector();
        IPlayer[] selectedPlayers = new IPlayer[11];
        int count = 0;

        for (IPlayerPosition position : PlayerPosition.values()) {
            int allowed = formation.getAllowedPosition(position);
            for (int i = 0; i < allowed; i++) {
                IPlayer player = selector.selectPlayer(club, position);
                if (player != null && !isAlreadySelected(selectedPlayers, player)) {
                    selectedPlayers[count++] = player;
                }
            }
        }

        IPlayer[] allPlayers = club.getPlayers();
        for (int i = 0; i < allPlayers.length && count < selectedPlayers.length; i++) {
            IPlayer candidate = allPlayers[i];
            if (candidate != null && !isAlreadySelected(selectedPlayers, candidate)) {
                selectedPlayers[count++] = candidate;
            }
        }

        return new Team(club, formation);
    }

    /**
     * Verifica se um jogador já foi selecionado para uma determinada lista.
     *
     * @param players Lista de jogadores já escolhidos.
     * @param player Jogador a verificar.
     * @return true se já tiver sido escolhido; false caso contrário.
     */
    private boolean isAlreadySelected(IPlayer[] players, IPlayer player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null && players[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simula a execução de um livre direto.
     *
     * @param team Equipa a que pertence o jogador.
     * @param player Jogador que executa o livre.
     * @throws PlayerNotInTeamException Caso o jogador não pertença à equipa.
     * @throws InvalidTeamOrPlayerException Caso a equipa ou o jogador sejam inválidos.
     */
    public void simulateFreeKick(ITeam team, IPlayer player) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        teamFreekicks++;
        Random rand = new Random();
        System.out.println(player);
        sb.append("🔔 Player ").append(player.getName()).append(" will attempt a shot to the goal.").append("\n");
        int successChance = rand.nextInt(100) + 1;
        if (player.getShooting() >= successChance) {
            teamGoals++;
            sb.append("⚽ GOAL! ").append(player.getName()).append(" scored a Free Kick.").append("\n");
        } else {
            sb.append("❌ ").append(player.getName()).append(" missed the shooting.").append("\n");
        }
        System.out.println(sb);
    }

    /**
     * Simula a execução de um canto.
     *
     * @param team Equipa a que pertence o jogador.
     * @param player Jogador que executa o canto.
     * @throws PlayerNotInTeamException Caso o jogador não pertença à equipa.
     * @throws InvalidTeamOrPlayerException Caso a equipa ou o jogador sejam inválidos.
     */
    public void simulateCorner(ITeam team, IPlayer player) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        teamCorners++;
        Random rand = new Random();

        sb.append("🔔 Player ").append(player.getName()).append(" will attempt a shot to the goal.").append("\n");
        int successChance = rand.nextInt(100) + 1;
        if (player.getShooting() >= successChance) {
            teamGoals++;
            sb.append("⚽ GOAL! ").append(player.getName()).append(" scored a corner.").append("\n");
        } else {
            sb.append("❌ ").append(player.getName()).append(" missed the corner.").append("\n");
        }
        System.out.println(sb);
    }

    /**
     * Simula a execução de um penálti.
     *
     * @param team Equipa a que pertence o jogador.
     * @param player Jogador que executa o penálti.
     * @throws PlayerNotInTeamException Caso o jogador não pertença à equipa.
     * @throws InvalidTeamOrPlayerException Caso a equipa ou o jogador sejam inválidos.
     */

    public void simulatePenalty(ITeam team, IPlayer player) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        teamPenalties++;
        Random rand = new Random();

        sb.append("🔔 Player ").append(player.getName()).append(" will attempt to score a Penalty.").append("\n");
        int successChance = rand.nextInt(50) + 1;
        if (player.getShooting() >= successChance) {
            teamGoals++;
            sb.append("⚽ GOAL! ").append(player.getName()).append(" scored the Penalty.").append("\n");
        } else {
            sb.append("❌ ").append(player.getName()).append(" misses the Penalty.").append("\n");
        }
        System.out.println(sb);
    }

    /**
     * Cria a classificação (standings) para uma equipa e jogador.
     * (Atualmente não implementado.)
     *
     * @param team Equipa envolvida.
     * @param player Jogador associado.
     */
    private void creatStandings(ITeam team, IPlayer player) {
        //Standing standing = new Standing(team, team.getClub());
    }

    /**
     * Simula um remate à baliza.
     *
     * @param team Equipa do jogador.
     * @param player Jogador que remata.
     * @throws PlayerNotInTeamException Se o jogador não pertence à equipa.
     * @throws InvalidTeamOrPlayerException Se a equipa ou o jogador forem inválidos.
     */
    public void simulateShot(ITeam team, IPlayer player) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        teamShots++;
        Random rand = new Random();

        sb.append("🔔 Player ").append(player.getName()).append(" will attempt a shot to the goal.").append("\n");
        int successChance = rand.nextInt(100) + 1;
        if (player.getShooting() >= successChance) {
            teamGoals++;
            sb.append("⚽ GOAL! ").append(player.getName()).append(" scored.").append("\n");
        } else {
            sb.append("❌ ").append(player.getName()).append(" misses the target.").append("\n");
        }
        System.out.println(sb);
    }

    /**
     * Simula um evento disciplinar (cartão), com base numa falta.
     * Pode originar simulação de livre ou penálti.
     *
     * @param team Equipa beneficiada.
     * @param player Jogador da equipa beneficiada.
     * @param badPlayer Jogador que cometeu a infração.
     * @throws PlayerNotInTeamException Se algum jogador não pertence à sua equipa.
     * @throws InvalidTeamOrPlayerException Se algum jogador ou equipa forem inválidos.
     */
    public void simulateCard(ITeam team, IPlayer player, IPlayer badPlayer) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        Random rand = new Random();
        int eventType = rand.nextInt(0, 2);
        switch (eventType) {
            case 0:
                simulateFreeKick(team, player);
                break;
            case 1:
                simulatePenalty(team, player);
                break;
        }
    }

    /**
     * Simula uma falta cometida por um jogador.
     * Pode resultar em cartão vermelho, amarelo ou aviso (null).
     *
     * @param team Equipa beneficiada pela falta.
     * @param player Jogador beneficiado.
     * @param badPlayer Jogador infrator.
     * @throws PlayerNotInTeamException Se algum jogador não pertence à sua equipa.
     * @throws InvalidTeamOrPlayerException Se algum jogador ou equipa forem inválidos.
     */
    public void simulateFoul(ITeam team, IPlayer player, IPlayer badPlayer) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        System.out.println("🚫 Foul\n");
        teamFouls++;
        Random rand = new Random();
        int eventType = rand.nextInt(0, 3);
        switch (eventType) {
            case 0:
                sb.append("🟥 RED CARD! ").append(badPlayer.getName()).append(" commited a serious foul!").append("\n");
                simulateCard(team, player, badPlayer);
                teamRedCards++;
                break;
            case 1:
                sb.append("🟨 Yellow card shown to ").append(badPlayer.getName()).append(" for unsporting behavior.").append("\n");
                simulateCard(team, player, badPlayer);
                teamYellowCards++;
                break;
            case 2:
                sb.append("📢 Null card attributed to: ").append(player.getName()).append("\n");
                simulateCard(team, player, badPlayer);
                teamNullCards++;
                break;
        }
        System.out.println(sb);
    }

    /**
     * Simula outros jogos (não controlados pelo utilizador), com base nos pontos das equipas.
     * Atualiza os golos marcados, sofridos e jogos disputados.
     *
     * @param match Jogo a simular.
     * @throws PlayerNotInTeamException Se um jogador não estiver na equipa.
     * @throws InvalidTeamOrPlayerException Se algum jogador ou equipa forem inválidos.
     */
    public void simulateOtherGames(IMatch match) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        Club club1 = (Club) match.getHomeClub();
        Club club2 = (Club) match.getAwayClub();

        int points1 = club1.getPoints();
        int points2 = club2.getPoints();
        int totalPoints = points1 + points2;
        if (totalPoints == 0) totalPoints = 1;
        double probabilityClub1 = (double) points1 / totalPoints;
        double chance = random.nextDouble();
        int goals1, goals2;

        if (chance < probabilityClub1) {
            goals1 = random.nextInt(2, 6);
            goals2 = random.nextInt(0, goals1);
        } else {
            goals2 = random.nextInt(2, 6);
            goals1 = random.nextInt(0, goals2);
        }

        club1.addGoalsScored(goals1);
        club1.addGoalsConceded(goals2);
        club2.addGoalsScored(goals2);
        club2.addGoalsConceded(goals1);
        club1.incrementNumberOfGames();
        club2.incrementNumberOfGames();

        if (goals1 == goals2) {
            sb.append("🤝 Draw\n");
        } else {
            String winner = (goals1 > goals2) ? club1.getName() : club2.getName();
            sb.append("🏆 Winner: ").append(winner).append("\n");
        }
        sb.append("📊 Final Score:\n").append(club1.getName()).append(" ").append(goals1).append(" - ").append(goals2).append(" ").append(club2.getName());
        System.out.println(sb);
    }


    /**
     * Método principal de simulação de jogo.
     * Cria equipas, simula eventos aleatórios (faltas, cantos, remates), e mostra estatísticas finais.
     *
     * @param iMatch Jogo a simular.
     */
    @Override
    public void simulate(IMatch iMatch) {
        long startTime = System.currentTimeMillis();
        long timePassed;
        int eventCount = 0;
        int maxEvents = 5;

        StringBuilder sb = new StringBuilder();
        System.out.println("🏁 The match begins!\n");
        Random rand = new Random();

        ITeam homeTeam = new Team(iMatch.getHomeClub(), Formation.FOUR_FOUR_TWO);
        for (int i = 0; i < iMatch.getHomeClub().getPlayers().length; i++) {
            homeTeam.addPlayer(iMatch.getHomeClub().getPlayers()[i]);
        }

        ITeam awayTeam = new Team(iMatch.getAwayClub(), Formation.FOUR_FOUR_TWO);
        for (int i = 0; i < iMatch.getAwayClub().getPlayers().length; i++) {
            awayTeam.addPlayer(iMatch.getAwayClub().getPlayers()[i]);
        }

        do {
            ITeam team = homeTeam;
            IPlayer[] players = homeTeam.getPlayers();
            IPlayer[] badPlayers = awayTeam.getPlayers();

            int rand1 = rand.nextInt(0, players.length);
            int rand2 = rand.nextInt(0, badPlayers.length);
            IPlayer player = players[rand1];
            IPlayer badPlayer = badPlayers[rand2];
            int eventType = rand.nextInt(0, 3);
            try {
                switch (eventType) {
                    case 0:
                        simulateFoul(team, player, badPlayer);
                        break;
                    case 1:
                        simulateCorner(team, player);
                        break;
                    case 2:
                        simulateShot(team, player);
                        break;
                }
                eventCount++;
            } catch (PlayerNotInTeamException | InvalidTeamOrPlayerException e) {
                throw new RuntimeException(e);
            }
            timePassed = (System.currentTimeMillis() - startTime) / 1000;
        } while (timePassed < 15 && eventCount < maxEvents);
        iMatch.isPlayed();
        sb.append("🏁 Final whistle! Match ended.\n");
        sb.append("📊 Stats:\n");
        sb.append(" Goals: ").append(teamGoals).append("\n");
        sb.append(" Shots: ").append(teamShots).append("\n");
        sb.append(" Fouls: ").append(teamFouls).append("\n");
        sb.append(" Red cards: ").append(teamRedCards).append("\n");
        sb.append(" Yellow cards: ").append(teamYellowCards).append("\n");
        sb.append(" Null cards: ").append(teamNullCards).append("\n");
        sb.append(" Penalties: ").append(teamPenalties).append("\n");
        sb.append(" Free Kicks: ").append(teamFreekicks).append("\n");
        sb.append(" Corners: ").append(teamCorners).append("\n");
        System.out.println(sb);
    }
}

