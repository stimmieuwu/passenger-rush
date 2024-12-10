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
3. Choose your name and skin.
4. Compete!

**Controls:**

* Player 1: WASD keys, F for powerup
* Player 2: Arrow keys, L for powerup

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

## References

**Code Reference**
* [JavaFX CSS Reference Guide](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)
* [AABB Collision Response: Push back colliding objects](https://stackoverflow.com/questions/29195951/aabb-collision-responsepush-back-colliding-objects)
* [How to make something happen every N seconds in game?](https://gamedev.stackexchange.com/questions/64633/how-to-make-something-happen-every-n-seconds-in-game)
* [What is the preferred way of getting the frame rate of a JavaFX application?](https://stackoverflow.com/questions/28287398/what-is-the-preferred-way-of-getting-the-frame-rate-of-a-javafx-application)
* [How to Eclipse with Github](https://github.com/maxkratz/How-to-Eclipse-with-Github)
* [OpenJFX Documentation](https://openjfx.io/openjfx-docs/)
* [Drawing Tiles](https://www.youtube.com/watch?v=ugzxCcpoSdE)
* [Collision detection](https://www.youtube.com/watch?v=oPzPpUcDiYY&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=9)
* [Sound in Java 2D games](https://www.youtube.com/watch?v=nUHh_J2Acy8&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=11)
* Some of the course materials, like Mario for collision

**Music**
* [Bonetrousle](https://www.youtube.com/watch?v=zdeZwAk6ULE)
* [Tem Shop](https://www.youtube.com/watch?v=y_qHuDjE3CQ)

