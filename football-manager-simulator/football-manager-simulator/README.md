# ⚽ Football Manager Simulator

[🇵🇹 Português](README.pt.md) · 🇬🇧 English

![Java](https://img.shields.io/badge/Java-OOP-orange?logo=openjdk&logoColor=white)
![Paradigm](https://img.shields.io/badge/Paradigm-Object--Oriented-blue)
![Type](https://img.shields.io/badge/Type-University%20Project-lightgrey)
![Status](https://img.shields.io/badge/Status-Prototype-yellow)

A console-based football league management and match simulation prototype written in **Java**. The user picks a club, browses the season calendar, chooses a tactical formation and simulates matchdays, while the engine generates match events such as shots, fouls, cards, corners, free kicks and penalties.

> 🎓 **Academic context** – Programming Paradigms course project (Object-Oriented Programming), BSc in Computer Engineering · March – June 2025.
> Built as a learning project to apply OOP principles to a realistic domain with many interacting entities.

---

## ✨ Features

- **League → Season → Schedule → Match** domain model, with standings entries per team.
- **5 tactical formations** (3-4-3, 4-3-3, 3-5-2, 4-4-2, 5-3-2) that define how many players are allowed per position.
- **Event-driven match engine** with dedicated event types: `Shoot`, `Goal`, `Defense`, `Foul`, `Corner`, `FreeKick`, `Penalty`, `GoalKick` and yellow/red cards.
- **Interactive CLI menu**: start a season, select a club, view the calendar, simulate *your* match, simulate *all* matches of the round, or finish the matchday.
- **JSON persistence**: leagues, seasons, clubs and players are loaded from and exported to JSON (`DataLoader` / `DataExporter`).
- **Rich player model**: position, preferred foot, shooting, passing, speed, stamina, height, weight, nationality, etc.
- **Custom exceptions** (`InvalidTeamOrPlayerException`, `PlayerNotInTeamException`) for domain validation.

## 🧠 What I practised

| Topic | How it shows up in the project |
|---|---|
| **Interfaces & contracts** | The code implements a set of provided API contracts (`IClub`, `ITeam`, `IPlayer`, `IMatch`, `ISeason`, `ISchedule`, `IEvent`…) packaged as a JAR, so the focus was on correct implementation against an existing specification. |
| **Strategy pattern** | `Simulate` implements `MatchSimulatorStrategy`, keeping the simulation logic decoupled from the match/season model. |
| **Encapsulation & modularity** | ~5,500 lines across ~35 classes, organised by domain package (`event`, `league`, `match`, `player`, `team`, `io`, `menu`, `simulation`). |
| **Enums with behaviour** | `Formation` and `PlayerPosition` carry their own rules (allowed players per position, display names). |
| **File I/O & JSON** | Serialisation/deserialisation of the full object graph with `json-simple`. |
| **Exception handling** | Domain-specific checked exceptions propagated through the simulation layer. |

## 🗂️ Project structure

```
project_pp/com/ppstudios/footballmanager/api/contracts/
├── Main.java                 # Entry point
├── menu/Menu.java            # Console UI
├── simulation/Simulate.java  # Match simulation engine
├── league/                   # League, Season, Schedule, Standing
├── match/Match.java
├── team/                     # Club, Team, Formation, PlayerSelector
├── player/                   # Player, PlayerPosition, PreferredFoot
├── event/                    # Goal, Shoot, Foul, Corner, FreeKick, Penalty, ...
├── io/                       # DataLoader, DataExporter (JSON)
└── Exceptions/
lib/                          # Third-party JARs + JSON data files
```

## 🚀 Getting started

**Requirements:** JDK 21+ (tested with 21).

```bash
git clone <repository-url>
cd football-manager-simulator
```

Compile and run (Linux/macOS – on Windows replace `:` with `;`):

```bash
javac -cp "lib/*" -d out $(find project_pp -name "*.java")
java  -cp "out:lib/*" com.ppstudios.footballmanager.api.contracts.Main
```

Run from the repository root (data is read from `lib/files/season.json`). Or simply open the folder in **IntelliJ IDEA**, add the JARs in `lib/` as libraries and run `Main`.

## 🎮 How to play

```
--- Main Menu ---            --- Season Menu ---         --- Simulate Journey ---
1 - Start New Season         1 - See Calendar            1 - Simulate My Game
2 - Exit                     2 - See Standings           2 - Simulate All Games
                             3 - Simulate Journey        3 - Finish Journey
                             4 - Back to Main Menu       4 - Back
```

## ⚠️ Current limitations & next steps

This is a learning prototype, and I prefer to be upfront about its scope:

- [ ] **Standings view** is not finished – the data model (`Standing`) exists, but the menu option is still disabled.
- [ ] Match outcomes are largely randomised; using player attributes (shooting, passing, stamina…) to weight probabilities would make the simulation more realistic.
- [ ] No automated tests yet → add JUnit tests for `Formation`, `Schedule` and the simulation rules.

## 👥 Authors

- **Maria Leonor Araújo** – [LinkedIn](https://www.linkedin.com/in/leonor-araújo-18155138b)
- **Miguel Ferreira**

## 📄 License

Educational project – no license specified. Please contact the authors before reusing the code.
