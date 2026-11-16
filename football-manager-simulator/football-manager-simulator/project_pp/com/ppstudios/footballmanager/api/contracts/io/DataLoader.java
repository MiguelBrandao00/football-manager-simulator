package com.ppstudios.footballmanager.api.contracts.io;

import com.ppstudios.footballmanager.api.contracts.league.League;
import com.ppstudios.footballmanager.api.contracts.league.Schedule;
import com.ppstudios.footballmanager.api.contracts.league.Season;
import com.ppstudios.footballmanager.api.contracts.league.Standing;
import com.ppstudios.footballmanager.api.contracts.match.Match;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.Player;
import com.ppstudios.footballmanager.api.contracts.player.PlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import com.ppstudios.footballmanager.api.contracts.team.Club;
import com.ppstudios.footballmanager.api.contracts.team.Formation;
import com.ppstudios.footballmanager.api.contracts.team.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDate;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Classe utilitaria que converte objetos Json em objetos, do modelo de dominio do
 * "Football Manager", para importacao ou comunicacao.
 */
public class DataLoader {

    /**
     * Loads a League from a JSON file located at the given file path.
     *
     * @param filePath the path to the JSON file
     * @return the loaded League object
     * @throws Exception if the file cannot be read or parsed
     */
    public static League loadLeagueFromJsonFile(String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject leagueJson = (JSONObject) parser.parse(new FileReader(filePath));
        return loadLeagueFromJson(leagueJson);
    }

    /**
     * Loads a League from a JSON object.
     *
     * @param leagueJsonObject the JSON object representing the league
     * @return the League object
     * @throws Exception if any parsing or conversion fails
     */
    public static League loadLeagueFromJson(JSONObject leagueJsonObject) {
        String leagueName = (String) leagueJsonObject.get("leagueName");
        League league = new League(leagueName);

        JSONArray seasonsArray = (JSONArray) leagueJsonObject.get("seasons");
        if (seasonsArray != null) {
            for (Object seasonObj : seasonsArray) {
                JSONObject seasonJson = (JSONObject) seasonObj;
                Season season = loadSeasonFromJson(seasonJson);
                league.createSeason(season);
            }
        }
        return league;
    }

    /**
     * Loads a Season object from its corresponding JSON representation.
     *
     * @param seasonJson the JSON object representing the season
     * @return the Season object
     */
    public static Season loadSeasonFromJson(JSONObject seasonJson) {
        String name = (String) seasonJson.get("name");
        int year = ((Number) seasonJson.get("year")).intValue();
        int maxTeams = ((Number) seasonJson.get("maxTeams")).intValue();
        int pointsPerWin = ((Number) seasonJson.get("pointsPerWin")).intValue();
        int pointsPerLoss = ((Number) seasonJson.get("pointsPerLoss")).intValue();
        int pointsPerDraw = ((Number) seasonJson.get("pointsPerDraw")).intValue();
        int currentTeams = ((Number) seasonJson.get("currentTeams")).intValue();
        int currentMatches = ((Number) seasonJson.get("currentMatches")).intValue();

        Season season = new Season(name, year, maxTeams, pointsPerWin, pointsPerLoss, pointsPerDraw);
        season.setCurrentMatches(currentMatches);

        JSONArray clubsJsonArray = (JSONArray) seasonJson.get("clubs");
        if (clubsJsonArray != null) {
            for (Object c : clubsJsonArray) {
                JSONObject clubJson = (JSONObject) c;
                Club club = loadClubFromJson(clubJson);
                season.addClub(club);
            }
        }


        JSONObject scheduleJson = (JSONObject) seasonJson.get("schedule");
        if (scheduleJson != null) {
            Schedule schedule = loadScheduleFromJson(scheduleJson);
            season.setSchedule(schedule);
        }

        JSONArray standingsJsonArray = (JSONArray) seasonJson.get("standings");
        if (standingsJsonArray != null) {
            Standing[] standings = new Standing[standingsJsonArray.size()];
            for (Object c : standingsJsonArray) {
               /*
               JSONObject standingJson = (JSONObject) c;
               Standing standing = loadStandingFromJsonObject(standingJson);
               standings[standingsJsonArray.indexOf(c)] = standing;
                */
            }
            season.setStandings(standings);
        }

        return season;
    }

    /**
     * Loads a Club from its JSON representation.
     *
     * @param clubJson the JSON object representing the club
     * @return the Club object
     */
    public static Club loadClubFromJson(JSONObject clubJson) {
        String name = (String) clubJson.get("name");
        String stadiumName = (String) clubJson.get("stadiumName");
        String country = (String) clubJson.get("country");
        String code = (String) clubJson.get("code");
        String logo = (String) clubJson.get("logo");
        int foundedYear = ((Long) clubJson.get("foundedYear")).intValue();

        Player[] players = new Player[0];
        Club club = new Club(name, players, code, country, foundedYear, stadiumName, logo);


        JSONArray playersJsonArray = (JSONArray) clubJson.get("players");
        if (playersJsonArray != null) {
            for (Object p : playersJsonArray) {
                JSONObject playerJson = (JSONObject) p;
                Player player = loadPlayerFromJson(playerJson);
                club.addPlayer(player);
            }
        }

        return club;
    }

    /**
     * Loads a Schedule object from its JSON representation.
     *
     * @param scheduleJson the JSON object representing the schedule
     * @return the Schedule object
     */
    public static Schedule loadScheduleFromJson(JSONObject scheduleJson) {
        int maxTeams = ((Long) scheduleJson.get("maxTeams")).intValue();
        Schedule schedule = new Schedule(maxTeams);

        JSONArray roundsArray = (JSONArray) scheduleJson.get("matches");

        if (roundsArray != null) {

            for (int i = 0; i < roundsArray.size(); i++) {

                JSONArray matchesArray = (JSONArray) roundsArray.get(i);
                if (matchesArray != null) {
                    for (int j = 0; j < matchesArray.size(); j++) {
                        JSONObject matchJson = (JSONObject) matchesArray.get(j);

                        if (matchJson != null) {
                            Match match = loadMatchFromJson(matchJson);
                            schedule.setMatch(i, j, match);
                        }
                    }
                }
            }
        }
        return schedule;
    }

    /**
     * Loads a Match from its JSON representation.
     *
     * @param matchJson the JSON object representing the match
     * @return the Match object
     */
    public static Match loadMatchFromJson(JSONObject matchJson) {

        int round = ((Number) matchJson.get("round")).intValue();
        boolean isPlayed = (boolean) matchJson.get("played");

        JSONObject homeClubJson = (JSONObject) matchJson.get("homeClub");
        JSONObject awayClubJson = (JSONObject) matchJson.get("awayClub");

        Club homeClub = loadClubFromJson(homeClubJson);
        Club awayClub = loadClubFromJson(awayClubJson);

        JSONObject homeTeamJson = (JSONObject) matchJson.get("homeTeam");
        JSONObject awayTeamJson = (JSONObject) matchJson.get("awayTeam");

        Match match = new Match(homeClub, awayClub, round);

        if (homeTeamJson != null) {
            Team homeTeam = loadTeamFromJson(homeTeamJson);
            match.setTeam(homeTeam);
        }
        if (awayTeamJson != null) {
            Team awayTeam = loadTeamFromJson(awayTeamJson);
            match.setTeam(awayTeam);
        }

        if (isPlayed) {
            match.setPlayed();
        }

        return match;
    }

    /**
     * Loads a Team from its JSON representation.
     *
     * @param teamJson the JSON object representing the team
     * @return the Team object
     */
    public static Team loadTeamFromJson(JSONObject teamJson) {
        if (teamJson == null) {
            return null;
        }
        JSONObject clubJson = (JSONObject) teamJson.get("club");
        Club club = loadClubFromJson(clubJson);

        JSONObject formationJson = (JSONObject) teamJson.get("formation");
        Formation formation = loadFormationFromJson(formationJson);

        Team team = new Team(club, formation);

        JSONArray playersArray = (JSONArray) teamJson.get("players");
        if (playersArray != null) {
            for (Object obj : playersArray) {
                JSONObject playerJson = (JSONObject) obj;
                Player player = loadPlayerFromJson(playerJson);
                team.addPlayer(player);
            }
        }
        return team;
    }

    /**
     * Loads a Formation from its JSON representation.
     * Currently only returns a default value (FOUR_FOUR_TWO).
     *
     * @param formationJson the JSON object representing the formation
     * @return the Formation enum value
     */
    public static Formation loadFormationFromJson(JSONObject formationJson) {
        return Formation.FOUR_FOUR_TWO;
    }

    /**
     * Loads a Player from its JSON representation.
     *
     * @param playerJson the JSON object representing the player
     * @return the Player object
     */
    public static Player loadPlayerFromJson(JSONObject playerJson) {
        String name = (String) playerJson.get("name");
        int number = ((Number) playerJson.get("number")).intValue();
        String nationality = (String) playerJson.get("nationality");
        int age = ((Number) playerJson.get("age")).intValue();
        LocalDate birthDate = LocalDate.parse((String) playerJson.get("birthDate"));
        float height = ((Double) playerJson.get("height")).floatValue();
        float weight = ((Double) playerJson.get("weight")).floatValue();
        String photo = (String) playerJson.get("photo");
        String positionStr = (String) playerJson.get("position");
        IPlayerPosition position;
        try {
            position = PlayerPosition.valueOf(positionStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            position = PlayerPosition.UNKNOWN;
        }
        int speed = ((Long) playerJson.get("speed")).intValue();
        int stamina = ((Long) playerJson.get("stamina")).intValue();
        int shooting = ((Long) playerJson.get("shooting")).intValue();
        int passing = ((Long) playerJson.get("passing")).intValue();
        String preferredFootStr = (String) playerJson.get("preferredFoot");
        PreferredFoot preferredFoot;
        try {
            preferredFoot = PreferredFoot.valueOf(preferredFootStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            preferredFoot = PreferredFoot.UNKNOWN;
        }

        return new Player(age, birthDate, height, name, nationality, number, passing, photo, position, shooting, speed, stamina, weight, preferredFoot);
    }

    /*  public static Standing loadStandingFromJsonObject(JSONObject standingJson) {

        Standing standing = new Standing();

        standing.setWins(((Number) standingJson.get("wins")).intValue());
        standing.setDraws(((Number) standingJson.get("draws")).intValue());
        standing.setLosses(((Number) standingJson.get("losses")).intValue());
        standing.setGoalsScored(((Number) standingJson.get("goalsScored")).intValue());
        standing.setGoalsConceded(((Number) standingJson.get("goalsConceded")).intValue());
        standing.setPoints(((Number) standingJson.get("points")).intValue());

        return standing;
    }*/
}



