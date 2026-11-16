# ⚽ Simulador de Gestão Desportiva (Football Manager)

🇵🇹 Português · [🇬🇧 English](README.md)

![Java](https://img.shields.io/badge/Java-POO-orange?logo=openjdk&logoColor=white)
![Paradigma](https://img.shields.io/badge/Paradigma-Orientado%20a%20Objetos-blue)
![Tipo](https://img.shields.io/badge/Tipo-Projeto%20Universit%C3%A1rio-lightgrey)
![Estado](https://img.shields.io/badge/Estado-Prot%C3%B3tipo-yellow)

Protótipo de simulação e gestão de uma liga de futebol em **Java**, executado na consola. O utilizador escolhe um clube, consulta o calendário da época, define a formação tática e simula jornadas, enquanto o motor gera eventos de jogo como remates, faltas, cartões, cantos, livres e penáltis.

> 🎓 **Contexto académico** – Projeto da unidade curricular de Paradigmas de Programação (Programação Orientada a Objetos), Licenciatura em Engenharia Informática · Março – Junho 2025.
> Desenvolvido como projeto de aprendizagem para aplicar princípios de POO num domínio realista com muitas entidades a interagir.

---

## ✨ Funcionalidades

- Modelo de domínio **Liga → Época → Calendário → Jogo**, com classificação por equipa.
- **5 formações táticas** (3-4-3, 4-3-3, 3-5-2, 4-4-2, 5-3-2) que definem o número de jogadores permitido por posição.
- **Motor de jogo orientado a eventos**, com tipos dedicados: `Shoot`, `Goal`, `Defense`, `Foul`, `Corner`, `FreeKick`, `Penalty`, `GoalKick` e cartões amarelos/vermelhos.
- **Menu interativo na consola**: iniciar época, escolher clube, ver calendário, simular *o meu* jogo, simular *todos* os jogos da jornada ou terminar a jornada.
- **Persistência em JSON**: ligas, épocas, clubes e jogadores são carregados e exportados em JSON (`DataLoader` / `DataExporter`).
- **Modelo de jogador completo**: posição, pé preferencial, remate, passe, velocidade, resistência, altura, peso, nacionalidade, etc.
- **Exceções próprias** (`InvalidTeamOrPlayerException`, `PlayerNotInTeamException`) para validação de domínio.

## 🧠 O que pratiquei

| Tema | Como aparece no projeto |
|---|---|
| **Interfaces e contratos** | O código implementa um conjunto de contratos de API fornecidos (`IClub`, `ITeam`, `IPlayer`, `IMatch`, `ISeason`, `ISchedule`, `IEvent`…) distribuídos num JAR, pelo que o foco esteve em implementar corretamente uma especificação existente. |
| **Padrão Strategy** | `Simulate` implementa `MatchSimulatorStrategy`, mantendo a lógica de simulação desacoplada do modelo de jogo/época. |
| **Encapsulamento e modularidade** | ~5 500 linhas em ~35 classes, organizadas por pacote de domínio (`event`, `league`, `match`, `player`, `team`, `io`, `menu`, `simulation`). |
| **Enums com comportamento** | `Formation` e `PlayerPosition` incluem as suas próprias regras (jogadores permitidos por posição, nomes de apresentação). |
| **Ficheiros e JSON** | Serialização/desserialização do grafo de objetos com `json-simple`. |
| **Tratamento de exceções** | Exceções verificadas específicas do domínio, propagadas pela camada de simulação. |

## 🗂️ Estrutura do projeto

```
project_pp/com/ppstudios/footballmanager/api/contracts/
├── Main.java                 # Ponto de entrada
├── menu/Menu.java            # Interface na consola
├── simulation/Simulate.java  # Motor de simulação de jogos
├── league/                   # League, Season, Schedule, Standing
├── match/Match.java
├── team/                     # Club, Team, Formation, PlayerSelector
├── player/                   # Player, PlayerPosition, PreferredFoot
├── event/                    # Goal, Shoot, Foul, Corner, FreeKick, Penalty, ...
├── io/                       # DataLoader, DataExporter (JSON)
└── Exceptions/
lib/                          # JARs de terceiros + ficheiros JSON de dados
```

## 🚀 Como executar

**Requisitos:** JDK 21+ (testado com 21).

```bash
git clone <url-do-repositório>
cd football-manager-simulator
```

Compila e executa (Linux/macOS – no Windows troca `:` por `;`):

```bash
javac -cp "lib/*" -d out $(find project_pp -name "*.java")
java  -cp "out:lib/*" com.ppstudios.footballmanager.api.contracts.Main
```

Executa a partir da raiz do repositório (os dados são lidos de `lib/files/season.json`). Em alternativa, abre a pasta no **IntelliJ IDEA**, adiciona os JARs de `lib/` como bibliotecas e executa o `Main`.

## 🎮 Como jogar

```
--- Main Menu ---            --- Season Menu ---         --- Simulate Journey ---
1 - Start New Season         1 - See Calendar            1 - Simulate My Game
2 - Exit                     2 - See Standings           2 - Simulate All Games
                             3 - Simulate Journey        3 - Finish Journey
                             4 - Back to Main Menu       4 - Back
```

## ⚠️ Limitações atuais e próximos passos

Trata-se de um protótipo de aprendizagem, e prefiro ser transparente quanto ao seu âmbito:

- [ ] **Vista de classificação** por concluir – o modelo de dados (`Standing`) existe, mas a opção do menu está desativada.
- [ ] Os resultados dos jogos são em grande parte aleatórios; usar os atributos dos jogadores (remate, passe, resistência…) para ponderar probabilidades tornaria a simulação mais realista.
- [ ] Sem testes automáticos → adicionar testes JUnit para `Formation`, `Schedule` e regras de simulação.

## 👥 Autores

- **Maria Leonor Araújo** – [LinkedIn](https://www.linkedin.com/in/leonor-araújo-18155138b)
- **Miguel Ferreira**

## 📄 Licença

Projeto educativo – sem licença definida. Contacta os autores antes de reutilizar o código.
