# 🏰 dwaslashe-Lobby

Autorski plugin serwerowy dedykowany dla sektora **Lobby / Hub** w sieci serwerów **hotmc.pl || pvpplay.pl || wywrotkamc.pl || lotmc.pl**. Odpowiada za obsługę interakcji na lobby, przekierowywanie graczy na inne tryby gry, mechaniki rozrywkowe oraz narzędzia administracyjne.

---

## 🌟 Główne Funkcje & Mechaniki

### 🌐 Przekierowywanie na Sektory / Tryby
Wbudowane komendy do szybkiego łączenia graczy z poszczególnymi serwerami w sieci:
* **BoxPvP** (`/boxpvp`)
* **Practice** (`/practice`)
* **Practice 2** (`/practice2`)
* **Survival** (`/survival`)

### 🎮 Mechaniki Lobby & Minigry
* **LaunchPady:** Wyrzutnie blokowe do szybkiego przemieszczania się po mapie lobby (`LaunchPadListener`).
* **Teleport Bow:** Łuk łapiący/teleportujący gracza w miejsce wystrzału strzały (`PlayerTeleportBowListener`).
* **Sword PvP:** Wyznaczone strefy lub mechaniki do dołączania do walki mieczem na lobby (`SwordPvPListener`).
* **Ochrona Lobby:** Kontrola dołączania, wychodzenia, spawardu oraz ograniczeń w interakcjach graczy (`PlayerHubListener`, `PlayerJoinListener`, `PlayerQuitListener`).

### 🛠️ Administracja & Narzędzia Gracza
* **Wiadomości prywatne:** System `/msg` oraz odpisywania `/reply` (`MsgCommand`, `ReplyCommand`).
* **Zarządzanie czasem i pogodą:** Prawa administracyjne do zmiany stanu świata (`DayCommand`, `NightCommand`, `SunCommand`, `StormCommand`).
* **Statystyki i Informacje:** Wyświetlanie pingu, czasu działania serwera oraz linków do sociali/strony (`PingCommand`, `UpTimeCommand`, `WebsiteCommand`, `DiscordCommand`, `MediaCommand`).
* **Pask boczny & Ekwipunek:** Zarządzanie widocznością Scoreboarda oraz podgląd ekwipunku graczy (`SidebarCommand`, `InvseeCommand`).

---

## 📂 Struktura Projektu

```text
xyz.dwaslashe.lobby
├── commands/           # Komendy systemowe i operacyjne
│   ├── managers/       # System zarzadzania i rejestracji komend (CommandManager)
│   └── servers/        # Komendy przekierowujace na sektory (BoxPvP, Practice, Survival)
├── configs/            # Konfiguracja pluginu (PluginConfig)
├── helpers/            # Helpery ekwipunku i refleksji (InventoryHelper, ReflectionHelper)
├── listeners/          # Obsluga zdarzen (LaunchPad, TeleportBow, SwordPvP, Hub, Chat)
└── utils/              # API pomocnicze (Api, ChatApi, ItemApi, LicenseApi, TimerApi)
