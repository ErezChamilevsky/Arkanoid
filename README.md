# Arkanoid Game - Java OOP Final Project

This repository contains an implementation of the classic Arkanoid (Breakout) game, developed in Java using object-oriented principles and key design patterns.

##  Overview

This project was built as part of an Object-Oriented Programming (OOP) course. It expands on a basic Arkanoid game engine to support:

- Multiple game levels
- Game pause/resume functionality
- Animated countdown before levels
- End screens (You Win / Game Over)
- Key press-controlled animations
- Smooth game flow between levels

Game resolution is fixed at **800 x 600 pixels**.

---

##  Features

-  **Pause Screen** - Press `P` to pause the game, and `SPACE` to resume.
-  **Countdown Animation** - Before each level, a 3-second countdown is displayed.
-  **Multiple Levels** - Three fully functional game levels with unique layouts and backgrounds.
-  **End Screens** - Win or lose, you’ll be presented with a final screen showing your score.
-  **Keyboard Input Handling** - Robust keyboard sensor system using the biuoop library.

---

##  Design Patterns & Principles

### 1. **Template Method (via Interface Simulation)**
- Introduced the `Animation` interface with `doOneFrame()` and `shouldStop()` methods.
- All animations follow this structure, enabling easy management and reuse.

### 2. **Composition over Inheritance**
- Avoided abstract base classes by using the `AnimationRunner` class, which runs any `Animation` implementation.
- Allows flexibility and better separation of concerns.

### 3. **Decorator Pattern**
- Implemented `KeyPressStoppableAnimation`, which wraps another animation and adds a keypress-based stop mechanism.
- Cleanly separates input behavior from visual content.

### 4. **Game Flow Controller**
- The `GameFlow` class manages the flow of multiple levels.
- Ensures clean transitions and persistent score handling across levels.

### 5. **Interface-Oriented Design**
- The `LevelInformation` interface allows creating any number of customizable levels with different parameters and styles.

## Screenshot

![ezgif com-video-to-gif](https://github.com/user-attachments/assets/a9f2fffc-d4ec-488a-8e7c-0b8255a43206)
