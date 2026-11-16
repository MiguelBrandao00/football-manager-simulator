package com.ppstudios.footballmanager.api.contracts.player;

import com.ppstudios.footballmanager.api.contracts.io.DataExporter;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
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
 * The {@code Player} class represents a player in a sports game.
 * It contains personal details, skills, and preferences for the player.
 * Implements {@link IPlayer} and {@link IPlayerPosition} interfaces.
 */
public class Player implements IPlayer {

    /**
     * The player's age.
     */
    private int age;

    /**
     * The player's birthdate.
     */
    private LocalDate birthDate;

    /**
     * The player's height in meters.
     */
    private float height;

    /**
     * The player's name.
     */
    private String name;

    /**
     * The player's nationality.
     */
    private String nationality;

    /**
     * The player's jersey number.
     */
    private int number;

    /**
     * The player's passing skill rating.
     */
    private int passing;

    /**
     * The player's photo (path).
     */
    private String photo;

    /**
     * The player's position on the field.
     */
    private IPlayerPosition position;

    /**
     * The player's shooting skill rating.
     */
    private int shooting;

    /**
     * The player's speed skill rating.
     */
    private int speed;

    /**
     * The player's stamina skill rating.
     */
    private int stamina;

    /**
     * The player's weight in kilograms.
     */
    private float weight;

    /**
     * The player's preferred foot for playing (left or right).
     */
    private PreferredFoot preferredFoot;

    /**
     * Constructs a {@code Player} object with the specified details.
     *
     * @param age           The player's age.
     * @param birthDate     The player's birthdate.
     * @param height        The player's height.
     * @param name          The player's name.
     * @param nationality   The player's nationality.
     * @param number        The player's jersey number.
     * @param passing       The player's passing skill rating.
     * @param photo         The player's photo (path or URL).
     * @param position      The player's position on the field.
     * @param shooting      The player's shooting skill rating.
     * @param speed         The player's speed skill rating.
     * @param stamina       The player's stamina skill rating.
     * @param weight        The player's weight.
     * @param preferredFoot The player's preferred foot.
     */
    public Player(int age, LocalDate birthDate, float height, String name, String nationality, int number, int passing,
                  String photo, IPlayerPosition position, int shooting, int speed, int stamina, float weight,
                  PreferredFoot preferredFoot) {
        this.age = age;
        this.birthDate = birthDate;
        this.height = height;
        this.name = name;
        this.nationality = nationality;
        this.number = number;
        this.passing = passing;
        this.photo = photo;
        this.position = position;
        this.shooting = shooting;
        this.speed = speed;
        this.stamina = stamina;
        this.weight = weight;
        this.preferredFoot = preferredFoot;
    }

    /**
     * @return The player's age.
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * @return The player's birthdate.
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * @return The player's height.
     */
    @Override
    public float getHeight() {
        return this.height;
    }

    /**
     * @return The player's name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return The player's nationality.
     */
    @Override
    public String getNationality() {
        return this.nationality;
    }

    /**
     * @return The player's jersey number.
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * @return The player's passing skill rating.
     */
    @Override
    public int getPassing() {
        return this.passing;
    }

    /**
     * @return The player's photo.
     */
    @Override
    public String getPhoto() {
        return this.photo;
    }

    /**
     * @return The player's position.
     */
    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    /**
     * @return The player's shooting skill rating.
     */
    @Override
    public int getShooting() {
        return this.shooting;
    }

    /**
     * @return The player's speed skill rating.
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @return The player's stamina skill rating.
     */
    @Override
    public int getStamina() {
        return this.stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * @return The player's weight.
     */
    @Override
    public float getWeight() {
        return this.weight;
    }

    /**
     * @param position The player's new position.
     */
    @Override
    public void setPosition(IPlayerPosition position) {
        this.position = position;
    }

    /**
     * @return The player's preferred foot.
     */
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }

    /**
     * @param preferredFoot The player's preferred foot.
     */
    public void setPreferredFoot(PreferredFoot preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public int getPlayerStrenght() {
        int strenght;
        strenght = passing + shooting + speed + stamina;
        return strenght;
    }

    /**
     * @return A string with the player's details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Number: ").append(number).append("\n");
        sb.append("Nationality: ").append(nationality).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("BirthDate: ").append(birthDate).append("\n");
        sb.append("Height: ").append(height).append("\n");
        sb.append("Weight: ").append(weight).append("\n");
        sb.append("Photo: ").append(photo).append("\n");
        sb.append("Position: ").append(position).append("\n");
        sb.append("Speed: ").append(speed).append("\n");
        sb.append("Stamina: ").append(stamina).append("\n");
        sb.append("Shooting").append(shooting).append("\n");
        sb.append("Passing: ").append(passing).append("\n");
        sb.append("PreferredFoot: ").append(preferredFoot).append("\n");
        return sb.toString();
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject playerJson = DataExporter.playerToJsonObject(this);

        try (FileWriter file = new FileWriter("player.json")) {
            file.write(playerJson.toJSONString());
        }
    }
}

