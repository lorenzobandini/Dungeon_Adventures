# Dungeon Adventures

## Description

Dungeon Adventures is a text-based adventure game where you can explore a dungeon and fight monsters.
The game ends when you die or when you exit the game.
You can use potions to heal yourself and you can find them in the dungeon killing the monsters.
You win the game when you kill 15 monsters in a row.

The game is written in Java and it uses the Java Socket API to communicate between the client and the server.

## Installation

To install Dungeon Adventures, you can download the source code from the [Github Repository](https://github.com/lorenzobandini/Dungeon_Adventures).
After that just give the execution permission to the shell script (only the first time) by typing:

```sh
chmod +x play.sh
```

and then start the game by typing:

```sh
./play.sh
```

that you can use to start the game every time you want.

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

## Usage

To play the game, you need to start the server and the client (or the script) and you will play on the client terminal.
When you start the client, you will be asked to enter your name. After that you will be able to play the game.
The action you can do are:

- **Fight a monster**
- **Use a potion**
- **Exit the game**
