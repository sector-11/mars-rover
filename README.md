# 🚀 mars-rover 🛰️

## Start your own space program today!

`mars-rover` is a project built as part of the Northcoders bootcamp to introduce MVC architecture and Test-Driven Development to a larger project. It allows you to set up an area on Mars, some rovers on that area, and give those rovers some instructions to move around. The project is quite simple but has taught me a lot about managing the creation of a larger program.

## Table of Contents
- [Features](#Features)
- [Installation](#Installation)
- [Usage](#Usage)
- [Architecture](#Architecture)
- [Bugs and Contributions](#Bugs-and-Contributions)
- [Testing](#Testing)
- [Future Plans](#Future-Plans)


## Features
You can define a plateau with a custom size, create as many rovers as you can fit on the plateau, name them, and give them instructions.
Currently, rovers can either move forwards in the direction they are facing, or turn 90 degrees either direction. The plateau is always an empty square in the current build.


## Installation
Prerequisites: Ensure Java 21 and Maven are installed on your machine.
1) Clone this repo with `https://github.com/sector-11/mars-rover.git` in to the directory of your choosing.
2) Open a terminal in the `/mars-rover` directory and run `mvn package`. If all goes well, you should see a line like `Building jar: /path/to/directory/mars-rover/target/mars-rover-VERSION.jar`. Please take note of the path and file mentioned here.
3) Move this .jar file to the directory of your choosing, and run it with the command `java -jar mars-rover-VERSION.jar`, replacing `VERSION` with whatever was shown by maven in the previous step.
4) You now have the program running! You can quit the program through the menu, or press `CTRL + C` in the terminal window the program is running in.


## Usage
The program has in-built instructions when you go through the menus to help guide users, but below is the usage when setting up a custom scenario.
1) Select start from the main menu
2) Input the size of your plateau (minimum size of 2x2)
3) Choose the number of rovers to add
4) Name your rovers
5) Set up each rover by giving it:
    1) A starting position
    2) The direction it should face
    3) Some instructions
6) Watch your scenario happen!


## Architecture 

This program is split into different layers with their own concerns:

- `Main` class - the point of entry for our program, which exists to start the view layer.
- `View` layer - gives the user a way to interact with the program, uses their input to give commands to the `Controller` and takes information from this to display to the user.
- `Controller` layer - Sets the Plateau and Rovers, then controls their execution.
- `Logic` layer - the 'business' side of the program. This contains the `Plateau` and `Rover`, which move entities around our space.
- `Input` layer - contains the modelling of our data and all logic to parse it into a standard format.


## Testing

This project was built with JUnit for testing. The test-driven development approach has left us with a solid suite of tests for the controller, input, and logic layers. This both allowed me to write my code to meet expectations instead of making my expectations change to fit my code, and to ensure that changes to any one part of the code won't break the rest of the program. 


## Bugs and Contributions

If you find any bugs, please create an issue on the issues page of this repo, and I'll see if I can find a fix.

As this project was made as part of a bootcamp, I'm not planning on accepting any contributions to the code at this time. Feel free to fork the project to play around with it if you wish though!


## Future Plans

I plan to add a step by step visualisation of movement - and possibly a map view. In the future, I want to use this project to learn to make a proper desktop UI in java to show the plateau and objects graphically. There is also potential to add obstacles on the plateau surface.