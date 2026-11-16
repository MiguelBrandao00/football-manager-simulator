package com.ppstudios.footballmanager.api.contracts.menu;

import com.ppstudios.footballmanager.api.contracts.Exceptions.InvalidTeamOrPlayerException;
import com.ppstudios.footballmanager.api.contracts.Exceptions.PlayerNotInTeamException;
import com.ppstudios.footballmanager.api.contracts.league.League;
import com.ppstudios.footballmanager.api.contracts.league.Schedule;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.Simulate;
import com.ppstudios.footballmanager.api.contracts.team.Formation;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import com.ppstudios.footballmanager.api.contracts.team.Team;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Classe que representa o menu principal do jogo de futebol.
 * Gere a navegacao pelos menus, selecao de clubes, simulacao de jogos e visualizacao de calendario.
 */
public class Menu {
    private Schedule schedule;
    private Simulate simulate;
    private IClub[] allClubs;
    private ITeam[] allTeams;
    private IPlayer[] allPlayers;
    private IClub userClub;
    private ITeam userTeam;
    private Scanner scanner = new Scanner(System.in);
    private boolean hasSeenCalendar = false;
    private boolean journeyFinished = false;

    /**
     * Construtor da classe Menu.
     *
     * @param simulate Instancia do simulador de jogos.
     * @param league   Instancia da liga que contem os clubes e calendario.
     */
    public Menu(Simulate simulate, League league) {

        this.schedule = (Schedule) league.getSeasons()[0].getSchedule();
        this.simulate = simulate;
        this.scanner = new Scanner(System.in);
        this.allClubs = league.getSeasons()[0].getCurrentClubs();
        this.allPlayers = new IPlayer[100];
        this.allTeams = new ITeam[allClubs.length];
        setTeams();
    }

    /**
     * Mostra o menu principal, permitindo iniciar nova epoca ou sair.
     *
     * @throws PlayerNotInTeamException     Excecao lancada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lancada se houver equipa ou jogador invalido.
     */

    public void showMainMenu() throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        int option = 0;
        do {
            try {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1 - Start New Season");
                System.out.println("2 - Exit");
                System.out.print("Choose an option: ");

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        showSeasonStartMenu();
                        break;
                    case 2:
                        System.out.println("Exiting game...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        } while (option != 2);
    }

    /**
     * Mostra o menu de inicio de epoca, permitindo a selecao dos clubes.
     *
     * @throws PlayerNotInTeamException     Excecao lancada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lanaada se houver equipa ou jogador invalido.
     */
    private void showSeasonStartMenu() throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        int option = 0;
        do {
            System.out.println("\n--- Season Start ---");
            System.out.println("1 - Select Clubs");
            System.out.println("2 - Back to Main Menu");
            System.out.print("Choose an option: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        showTeamsAvailable();
                        break;
                    case 2:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        } while (option != 2);
    }

    /**
     * Mostra a lista de clubes disponiveis para selecao.
     *
     * @throws PlayerNotInTeamException     Excecao lancada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lancada se houver equipa ou jogador invalido.
     */
    private void showTeamsAvailable() throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        System.out.println("\n--- Available Clubs ---");
        for (int i = 0; i < allClubs.length; i++) {
            System.out.println((i + 1) + " - " + allClubs[i].getName());
        }
        System.out.print("Select your clubs by number: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= allClubs.length) {
                userClub = allClubs[choice - 1];

                boolean foundTeam = false;
                int i = 0;
                do {
                    if (allTeams[i].getClub().getName().equals(userClub.getName())) {
                        foundTeam = true;
                        userTeam = allTeams[i];
                    }
                    i++;
                } while (!foundTeam && i < allClubs.length);

                showInSeasonMenu();
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
    }

    /**
     * Mostra o menu dentro da epoca, com opcoees para ver calendario, classificacao e simular jogos.
     *
     * @throws PlayerNotInTeamException     Excecao lancada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lancada se houver equipa ou jogador invalido.
     */

    private void showInSeasonMenu() throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Season Menu ---");
            System.out.println("1 - See Calendar");
            System.out.println("2 - See Standings");
            System.out.println("3 - Simulate Journey");
            System.out.println("4 - Back to Main Menu");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    seeCalendar();
                    hasSeenCalendar = true;
                    break;
                case 2:
                    seeStandings();
                    break;
                case 3:
                    showSimulationMenu();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 4);
    }

    /**
     * Mostra o menu para simulacao da jornada com opcoes para simular o jogo do utilizador, todos os jogos ou terminar a jornada.
     *
     * @throws PlayerNotInTeamException     Excecao lançada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lancada se houver equipa ou jogador invalido.
     */
    private void showSimulationMenu() throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Simulate Journey ---");
            System.out.println("1 - Simulate My Game");
            System.out.println("2 - Simulate All Games");
            System.out.println("3 - Finish Journey");
            System.out.println("4 - Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    simulateMyGame();
                    break;
                case 2:
                    if (hasSeenCalendar) {
                        simulateAllMatches(schedule);
                    } else {
                        System.out.println("You need to see the calendar first before simulating other games.");
                    }
                    break;
                case 3:
                    finishJourney();
                    option = 4;
                    break;
                case 4:
                    System.out.println("Returning to season menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 4);
    }


    /**
     * Gera e mostra o calendario de jogos.
     */
    private void seeCalendar() {
        schedule.generateSchedule(this.allClubs, allClubs.length);
    }

    /**
     * This method is commented because we didn't have time to finish implementing
     */
    private void seeStandings() {
        System.out.println("\nThis feature is not available because we didn't have time to finish implementing ");
        /*season.GenerateStandings();
        season.PrintStandings(season.getLeagueStandings());*/
    }


    /**
     * Permite ao utilizador simular um dos seus jogos selecionados.
     */
    private void simulateMyGame() {
        IMatch[] matches = schedule.getMatchesForTeam(userTeam);

        for (int i = 0; i < matches.length; i++) {
            System.out.println(i + 1 + " -> " + matches[i].getAwayClub().getName() + " vs " + matches[i].getHomeClub().getName());
        }
        System.out.print("Select your Match: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= matches.length) {
                IMatch match = matches[choice - 1];
                simulate.simulate(match);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
    }

    /**
     * Simula todos os jogos da epoca, ronda a ronda.
     *
     * @param schedule Calendario de jogos a simular.
     * @throws PlayerNotInTeamException     Excecao lancada se um jogador nao pertencer a uma equipa valida.
     * @throws InvalidTeamOrPlayerException Excecao lancada se houver equipa ou jogador invalido.
     */
    public void simulateAllMatches(Schedule schedule) throws PlayerNotInTeamException, InvalidTeamOrPlayerException {
        int totalRounds = schedule.getNumberOfRounds();
        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("🔄 Simulating Round " + round);
            IMatch[] matches = schedule.getMatchesForRound(round);
            boolean anyMatchSimulated = false;
            for (IMatch match : matches) {
                if (match != null) {
                    simulate.simulateOtherGames(match);
                    anyMatchSimulated = true;
                }
            }
            if (!anyMatchSimulated) {
                System.out.println("No matches to simulate this round.");
            }
        }
    }

    /**
     * Termina a jornada atual, garantindo que nao pode ser terminada mais do que uma vez.
     */
    private void finishJourney() {
        if (!journeyFinished) {
            System.out.println("Finishing journey...");
            journeyFinished = true;
        } else {
            System.out.println("Journey already finished.");
        }
    }


    /**
     * Inicializa as equipas a partir dos clubes existentes e preenche a lista de jogadores.
     */
    private void setTeams() {
        for (int i = 0; i < allClubs.length; i++) {
            Team team = new Team(allClubs[i], Formation.FOUR_FOUR_TWO);
            allTeams[i] = team;
            IPlayer[] tempPlayers = allClubs[i].getPlayers();
            for (int j = 0; j < tempPlayers.length; j++) {
                allPlayers[j] = tempPlayers[j];
            }
        }
    }

}
