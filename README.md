# Passenger Rush - a JavaFX Minigame

**In partial fulfillment of the requirements for CMSC 22 - Object-oriented Programming**

Passenger Rush is a simple two-player jeepney driving game built with JavaFX where players compete to transport the most passengers while navigating hazards and taking advantage of power-ups.

This minigame is the culmination of our lessons in object-oriented programming from the concept of software objects and the pillars of OOP up to concurrency and GUI creation.

Built by three second-year computer science students, this project would not have been possible without the support of our lab instructor CGA.

# Game

Passenger Rush is a simple Java Application exported to an executable file. Two players control two different jeepneys on the same computer.

## Game Overview

The goal is to transport passengers to the Junction Unloading Area and score higher than your opponent. Control your jeepney, pick up passengers, avoid obstacles, and utilize power-ups. The game only lasts a maximum of five minutes.

To play Passenger Rush:

1. Launch the executable `passenger-rush.exe`.
2. Navigate to "Start Game" from the main menu.
3. Choose your name.
4. Compete!

**Controls:**

* Player 1: WASD keys
* Player 2: Arrow keys

## Power-ups, Debuffs, and Disasters

* **Speed:** Increases jeepney speed.
* **Invincibility:** Provides temporary immunity to hazards.
* **Insurance:** Allows you to return to the starting point without losing passengers after encountering a disaster.
* **Oil Spill:** Causes the opponent to lose control.
* **Crack in the Road:** Reduces the opponent's speed.
* **Missile:** Damages the opponent's jeepney and reduces their passenger count.
* **Manhole:** Causes the jeepney to fall and lose control.

## Mechanics

The player who delivers the most passengers to the Junction Unloading Area within the time limit wins the game. The game ends when the time limit is reached. Results are then displayed and you will be able to return to the main menu.
