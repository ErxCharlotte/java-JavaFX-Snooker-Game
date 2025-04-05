# 🎱 Snooker Game (JavaFX)

This is a JavaFX-based Snooker game project. It simulates core billiards mechanics such as ball movement, collision, scoring, difficulty levels, and user interaction (e.g., undo and cheat). It uses multiple design patterns to support clean and scalable architecture.

---

## 🕹️ Features

- ✅ Ball collisions and scoring system
- ✅ Realistic animation when clicking cue ball
- ✅ Supports game difficulty switching: Easy / Normal / Hard
- ✅ Timer and score display
- ✅ Undo previous move (Memento pattern)
- ✅ Cheat to remove all balls of a given color
- ✅ JSON-based config to customize ball setup, pockets, and level settings

---

## 🧱 Design Patterns Used

- **State Pattern** – Game level management (`EasyState`, `NormalState`, `HardState`)
- **Memento Pattern** – Undo last game state (score, time, ball position)
- **Strategy Pattern** – Ball scoring system per color

---
## 🚀 How to Run

```bash
cd
gradle build
gradle run

🎯 New Features Breakdown
Feature	How to Use
🎨 More ball colors	Set in JSON config
🧩 Difficulty switch	Click "Easy" / "Normal" / "Hard" buttons in-game
💣 Cheat mode	Click "Cheat on this colour" in-game
⏪ Undo	Click "Recover to last state" to undo the last move
⏱ Score & time	Displayed in UI

⚠️ Notes
All config is JSON-based and can be customized freely

Be careful with incorrect config files (may cause errors)
