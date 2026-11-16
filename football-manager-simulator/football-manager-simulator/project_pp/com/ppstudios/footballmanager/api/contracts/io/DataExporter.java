package com.ppstudios.footballmanager.api.contracts.io;

import com.ppstudios.footballmanager.api.contracts.event.*;
import com.ppstudios.footballmanager.api.contracts.league.*;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.Player;
import com.ppstudios.footballmanager.api.contracts.team.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Classe utilitaria que converte objetos do modelo de dominio do
 * "Football Manager" em objetos JSON, para exportacao ou comunicacao.
 */
public class DataExporter {

    /**
     * Converte uma liga (ILeague) em um JSONObject.
     * Inclui o nome da liga e todas as suas temporadas convertidas para JSON.
     */
    public static JSONObject leagueToJsonObject(ILeague league) {
        JSONObject leagueJson = new JSONObject();
        leagueJson.put("leagueName", league.getName());

        JSONArray seasonsArray = new JSONArray();
        ISeason[] seasons = league.getSeasons();
        for (ISeason season : seasons) {
            if (season != null) {
                seasonsArray.add(seasonToJsonObject(season));
            }
        }
        leagueJson.put("seasons", seasonsArray);

        return leagueJson;
    }

    /**
     * Converte uma temporada (ISeason) em JSONObject.
     * Inclui dados basicos da temporada, clubes, partidas, tabela de classificacao e calendario.
     */
    public static JSONObject seasonToJsonObject(ISeason season) {
        JSONObject seasonJson = new JSONObject();
        seasonJson.put("name", season.getName());
        seasonJson.put("year", season.getYear());
        seasonJson.put("maxTeams", season.getMaxTeams());
        seasonJson.put("pointsPerWin", season.getPointsPerWin());
        seasonJson.put("pointsPerDraw", season.getPointsPerDraw());
        seasonJson.put("pointsPerLoss", season.getPointsPerLoss());
        seasonJson.put("currentTeams", season.getNumberOfCurrentTeams());
        seasonJson.put("currentMatches", season.getCurrentMatches());

        JSONArray clubsArray = new JSONArray();
        IClub[] clubs = season.getCurrentClubs();
        for (IClub club : clubs) {
            if (club != null && club instanceof Club) {
                clubsArray.add(clubToJsonObject((Club) club));
            }
        }
        seasonJson.put("clubs", clubsArray);

        JSONArray matchesArray = new JSONArray();
        IMatch[] matches = season.getMatches();
        for (IMatch match : matches) {
            if (match != null) {
                matchesArray.add(matchToJsonObject(match));
            } else {
                matchesArray.add(null);
            }
        }
        seasonJson.put("matches", matchesArray);

        ISchedule schedule = season.getSchedule();
        if (schedule instanceof Schedule) {
            seasonJson.put("schedule", scheduleToJsonObject((Schedule) schedule));
        } else {
            seasonJson.put("schedule", null);
        }

        JSONArray standingsArray = new JSONArray();
        for (IStanding standing : season.getLeagueStandings()) {
            if (standing != null && standing instanceof Standing) {
                standingsArray.add(standingToJsonObject((Standing) standing));
            } else {
                standingsArray.add(null);
            }
        }
        seasonJson.put("standings", standingsArray);

        return seasonJson;
    }

    /**
     * Converte o calendario (Schedule) da temporada em JSONObject,
     * listando as partidas organizadas por rounds.
     */
    public static JSONObject scheduleToJsonObject(Schedule schedule) {
        JSONObject scheduleJson = new JSONObject();
        JSONArray roundsArray = new JSONArray();

        for (int i = 0; i < schedule.getNumberOfRounds(); i++) {
            JSONArray matchesArray = new JSONArray();
            IMatch[] matches = schedule.getMatchesForRound(i);

            for (IMatch match : matches) {
                if (match != null) {
                    matchesArray.add(DataExporter.matchToJsonObject(match));
                } else {
                    matchesArray.add(null);
                }
            }

            roundsArray.add(matchesArray);
        }

        scheduleJson.put("matches", roundsArray);
        scheduleJson.put("maxRounds", schedule.getNumberOfRounds());
        scheduleJson.put("maxTeams", schedule.getMaxTeams());

        return scheduleJson;
    }

    /**
     * Converte a classificacao (IStanding) de um clube em JSONObject.
     * Contém estatisticas como vitorias, empates, derrotas, golos e pontos.
     */
    public static JSONObject standingToJsonObject(IStanding standing) {
        JSONObject standingJson = new JSONObject();

        //standingJson.put("clubName", standing.getClub().getName());
        // standingJson.put("teamName", standing.getTeam() != null ? standing.getTeam().getClub().getName() : null);

        standingJson.put("wins", standing.getWins());
        standingJson.put("draws", standing.getDraws());
        standingJson.put("losses", standing.getLosses());
        standingJson.put("goalsScored", standing.getGoalScored());
        standingJson.put("goalsConceded", standing.getGoalsConceded());
        standingJson.put("goalDifference", standing.getGoalDifference());
        standingJson.put("points", standing.getPoints());
        standingJson.put("totalMatches", standing.getTotalMatches());

        return standingJson;
    }

    /**
     * Converte um clube (Club) em JSONObject.
     * Inclui informacoes do clube e os seus jogadores.
     */
    public static JSONObject clubToJsonObject(Club club) {
        JSONObject clubJson = new JSONObject();

        clubJson.put("name", club.getName());
        clubJson.put("code", club.getCode());
        clubJson.put("country", club.getCountry());
        clubJson.put("foundedYear", club.getFoundedYear());
        clubJson.put("stadiumName", club.getStadiumName());
        clubJson.put("logo", club.getLogo());
        clubJson.put("playerCount", club.getPlayerCount());
        clubJson.put("points", club.getPoints());
        clubJson.put("numberOfGames", club.getNumberOfGames());
        clubJson.put("goalsScored", club.getGoalsScored());
        clubJson.put("goalsConceded", club.getGoalsConceded());

        JSONArray playersArray = new JSONArray();
        IPlayer[] players = club.getPlayers();
        for (IPlayer player : players) {
            if (player != null) {
                playersArray.add(playerToJsonObject(player));
            } else {
                playersArray.add(null);
            }
        }
        clubJson.put("players", playersArray);

        return clubJson;
    }

    /**
     * Converte um jogador (IPlayer) em JSONObject.
     * Inclui informacoes pessoais e atributos tecnicos.
     */
    public static JSONObject teamToJsonObject(ITeam team) {
        JSONObject teamJson = new JSONObject();

        if (team.getClub() != null && team.getClub() instanceof Club) {
            teamJson.put("club", clubToJsonObject((Club) team.getClub()));
        } else {
            teamJson.put("club", null);
        }

        if (team.getFormation() instanceof Formation) {
            teamJson.put("formation", formationToJsonObject((Formation) team.getFormation()));
        } else {
            teamJson.put("formation", null);
        }

        JSONArray playersArray = new JSONArray();
        IPlayer[] players = team.getPlayers();
        for (int i = 0; i < players.length; i++) {
            IPlayer player = players[i];
            if (player != null && player instanceof Player) {
                playersArray.add(playerToJsonObject((Player) player));
            } else {
                playersArray.add(null);
            }
        }
        teamJson.put("players", playersArray);

        return teamJson;
    }

    /**
     * Converte um jogador (IPlayer) em JSONObject.
     * Inclui informacoes pessoais e atributos tecnicos.
     */
    public static JSONObject playerToJsonObject(IPlayer player) {
        JSONObject playerJson = new JSONObject();

        playerJson.put("name", player.getName());
        playerJson.put("number", player.getNumber());
        playerJson.put("nationality", player.getNationality());
        playerJson.put("age", player.getAge());
        playerJson.put("birthDate", player.getBirthDate().toString());
        playerJson.put("height", player.getHeight());
        playerJson.put("weight", player.getWeight());
        playerJson.put("photo", player.getPhoto());
        playerJson.put("position", player.getPosition().toString());
        playerJson.put("speed", player.getSpeed());
        playerJson.put("stamina", player.getStamina());
        playerJson.put("shooting", player.getShooting());
        playerJson.put("passing", player.getPassing());
        playerJson.put("preferredFoot", player.getPreferredFoot().toString());

        return playerJson;
    }

    /**
     * Converte uma formacao tatica (IFormation) em JSONObject,
     * incluindo o nome e a vantagem tatica calculada.
     */
    public static JSONObject formationToJsonObject(IFormation formation) {
        JSONObject formationJson = new JSONObject();
        formationJson.put("displayName", formation.getDisplayName());
        formationJson.put("tacticalAdvantage", formation.getTacticalAdvantage(formation));
        return formationJson;
    }

    /**
     * Converte um jogo (IMatch) em JSONObject,
     * com informacoees sobre clubes, equipas, round e estado da partida.
     */
    public static JSONObject matchToJsonObject(IMatch match) {
        JSONObject matchJson = new JSONObject();

        if (match.getHomeClub() instanceof Club) {
            matchJson.put("homeClub", (clubToJsonObject((Club) match.getHomeClub())));
        } else {
            matchJson.put("homeClub", null);
        }

        if (match.getAwayClub() instanceof Club) {
            matchJson.put("awayClub", (clubToJsonObject((Club) match.getAwayClub())));
        } else {
            matchJson.put("awayClub", null);
        }

        if (match.getHomeTeam() instanceof Team) {
            matchJson.put("homeTeam", (teamToJsonObject((Team) match.getHomeTeam())));
        } else {
            matchJson.put("homeTeam", null);
        }

        if (match.getAwayTeam() instanceof Team) {
            matchJson.put("awayTeam", (teamToJsonObject((Team) match.getAwayTeam())));
        } else {
            matchJson.put("awayTeam", null);
        }

        matchJson.put("round", match.getRound());
        matchJson.put("played", match.isPlayed());

        return matchJson;
    }

    /**
     * Converte um conjunto de eventos (IEventManager) em um JSONArray.
     * Cada evento e convertido para o seu tipo JSON especifico.
     */
    public static JSONArray eventsToJsonArray(IEventManager eventManager) {
        JSONArray eventsArray = new JSONArray();

        IEvent[] events = eventManager.getEvents();
        int count = eventManager.getEventCount();

        for (int i = 0; i < count; i++) {
            IEvent event = events[i];
            if (event == null) {
                eventsArray.add(null);
                continue;
            }
            if (event instanceof Corner) {
                eventsArray.add(cornerToJsonObject((Corner) event));
            } else if (event instanceof Defense) {
                eventsArray.add(defenseToJsonObject((Defense) event));
            } else if (event instanceof Foul) {
                eventsArray.add(foulToJsonObject((Foul) event));
            } else if (event instanceof FreeKick) {
                eventsArray.add(freeKickToJsonObject((FreeKick) event));
            } else if (event instanceof Goal) {
                eventsArray.add(goalToJsonObject((Goal) event));
            } else if (event instanceof GoalKick) {
                eventsArray.add(goalKickToJsonObject((GoalKick) event));
            } else if (event instanceof Penalty) {
                eventsArray.add(penaltyToJsonObject((Penalty) event));
            } else if (event instanceof Shoot) {
                eventsArray.add(shootToJsonObject((Shoot) event));
            } else {
                eventsArray.add(null);
            }
        }
        return eventsArray;
    }

    /**
     * Converte um evento de canto (ICornerEvent) em JSONObject.
     * Inclui descricao, minuto e jogador envolvido.
     */
    public static JSONObject cornerToJsonObject(ICornerEvent corner) {
        JSONObject cornerJson = new JSONObject();

        cornerJson.put("description", corner.getDescription());
        cornerJson.put("minute", corner.getMinute());

        if (corner.getPlayer() instanceof Player) {
            cornerJson.put("player", playerToJsonObject((Player) corner.getPlayer()));
        } else {
            cornerJson.put("player", null);
        }

        return cornerJson;
    }

    /**
     * Converte um evento de defesa (IDefenseEvent) em JSONObject.
     * Inclui descricao, minuto e guarda-redes.
     */
    public static JSONObject defenseToJsonObject(IDefenseEvent defense) {
        JSONObject defenseJson = new JSONObject();

        defenseJson.put("description", defense.getDescription());
        defenseJson.put("minute", defense.getMinute());

        if (defense.getGoalKeeper() instanceof Player) {
            defenseJson.put("player", playerToJsonObject((Player) defense.getGoalKeeper()));
        } else {
            defenseJson.put("player", null);
        }
        return defenseJson;
    }

    /**
     * Converte um evento de falta (IFoulEvent) em JSONObject.
     * Inclui descricao, minuto, tipo de cartao e jogador.
     */
    public static JSONObject foulToJsonObject(IFoulEvent foul) {
        JSONObject foulJson = new JSONObject();

        foulJson.put("description", foul.getDescription());
        foulJson.put("minute", foul.getMinute());
        foulJson.put("cardType", foul.toString());

        if (foul.getPlayer() instanceof Player) {
            foulJson.put("player", playerToJsonObject((Player) foul.getPlayer()));
        } else {
            foulJson.put("player", null);
        }

        return foulJson;
    }

    /**
     * Converte um evento de livre direto (FreeKick) em JSONObject,
     * incluindo os dados do chute e possivel falta associada.
     */
    public static JSONObject freeKickToJsonObject(FreeKick freeKick) {
        JSONObject freeKickJson = shootToJsonObject(freeKick);

        if (freeKick.getFoul() != null) {
            freeKickJson.put("foul", foulToJsonObject(freeKick.getFoul()));
        } else {
            freeKickJson.put("foul", null);
        }

        return freeKickJson;
    }

    /**
     * Converte um evento de golo (Goal) em JSONObject.
     * Basicamente usa os dados do evento de chute.
     */
    public static JSONObject goalToJsonObject(Goal goal) {
        return shootToJsonObject(goal);
    }

    /**
     * Converte um evento de pontape de baliza (IGoalKickEvent) em JSONObject.
     * Inclui descricao, minuto e guarda-redes envolvido.
     */
    public static JSONObject goalKickToJsonObject(IGoalKickEvent goalKick) {
        JSONObject goalKickJson = new JSONObject();

        goalKickJson.put("description", goalKick.getDescription());
        goalKickJson.put("minute", goalKick.getMinute());

        if (goalKick.getGoalKeeper() instanceof Player) {
            goalKickJson.put("player", playerToJsonObject(goalKick.getGoalKeeper()));
        } else {
            goalKickJson.put("player", null);
        }

        return goalKickJson;
    }

    /**
     * Converte um evento de penalti (Penalty) em JSONObject,
     * incluindo os dados do chute e possivel falta associada.
     */
    public static JSONObject penaltyToJsonObject(Penalty penalty) {
        JSONObject penaltyJson = shootToJsonObject(penalty);

        if (penalty.getFoul() != null) {
            penaltyJson.put("foul", foulToJsonObject(penalty.getFoul()));
        } else {
            penaltyJson.put("foul", null);
        }

        return penaltyJson;
    }

    /**
     * Converte um evento de chute (IShootEvent) em JSONObject.
     * Inclui descricao, minuto e jogador que efetuou o chute.
     */
    public static JSONObject shootToJsonObject(IShootEvent shoot) {
        JSONObject shootJson = new JSONObject();

        shootJson.put("description", shoot.getDescription());
        shootJson.put("minute", shoot.getMinute());

        if (shoot.getPlayer() instanceof Player) {
            shootJson.put("player", playerToJsonObject((Player) shoot.getPlayer()));
        } else {
            shootJson.put("player", null);
        }

        return shootJson;
    }
}
