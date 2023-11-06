# Dungeon Adventures

## Description

Dungeon Adventures is a text-based adventure game where you can explore a dungeon and fight monsters.
The game ends when you die or when you exit the game.
You can use potions to heal yourself and you can find them in the dungeon killing the monsters.
You win the game when you kill 15 monsters in a row.

The game is written in Java and it uses the Java Socket API to communicate between the client and the server.

## Installation

To install Dungeon Adventures, you can download the source code from the [Github Repository](https://github.com/lorenzobandini/Dungeon_Adventures).
You will need to have Java installed in your PC to compile and play the game.

### Mac or Linux

After that just open a terminal in the directory you just download and give the execution permission to the shell script (only the first time) by typing:

```sh
chmod +x play.sh
```

and then start the game by typing:

```sh
./play.sh
```

that you can use to start the game every time you want.

### Windows

To start the game on Windows you have to open the terminal in the repository folder and type:

```batch
.\play.bat
```

---

Alternatively, you can open the terminal in the repository folder and type:

```sh
javac Server.java
```

and then:

```sh
java Server
```

to start the server.

Then open another terminal and type:

```sh
javac Client.java
```

and then:

```sh
java Client
```

## User Instructions

To play the game you can ran the script `play.sh` or you can run the commands described in the installation section.

When you start the game you will be asked to insert your name and then your character will be created.
Your character will have a random amount of health points between 50 and 100 and a random amount of potions between 1 and 3.
You will be able to use the potions to heal yourself during the game.
After inserting your name, the game will start and you will be able to play.
Now you are in the dungeon and you do things by typing the number of the action you want to do between the ones that are displayed.
Out of combat, the actions you can do are:

- **Fight a Monster**
- **Use a Potion**
- **Exit the Game**

If you choose to fight a monster, you will enter in combat with a monster that has a random amount of health points between 10 and 100.
The combat is turn-based and you can choose between two actions:

- **Attack**
- **Use a Potion**

If you choose to attack, you will attack the monster and you will deal a random amount of damage between 1 and your max health points.
If you choose to use a potion, you will use a potion and you will heal yourself for a random amount of health points between 1 and 50.
After you choose your action, the monster will attack you and it will deal a random amount of damage between 1 and its max health points.
The combat will end when you or the monster die.
If you die, the game will end and you will lose.
If you kill the monster, you will gain 1 point and you will be able to fight another monster.
The monsters will drop a number of potions between 0 and (its max health points)/25.

The game ends when you die or when you win 15 combats in a row.
