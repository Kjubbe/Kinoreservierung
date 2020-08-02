# Kinoreservierung
This software allows the user to reserve seats for a drive-in cinema.
The user chooses a movie, the desired time for the movie, seats and catering options.

The project is build on the mvc pattern and therefore consists of three main parts:
1. the view
2. the model
3. the controller
which can be found in packages of the same name.

the view consists of six different "tab-classes" containing various swing components.
all tab classes inherit from an abstract-tab-class, which contains the foundation for every tab.
each tab contains build and update functions, which are managed by the view.
building and updating varies between the tabs.

the model holds all data from the current user input and manages calculations.
other classes for the model contain more information, a few examples are:
1. movie, containing showtimes
2. showtime, containing seats
3. database, containing consistent data like caterings and movies
4. catering, containing primitive data types
5. file- and number managers for orders
6. order, saving data from the model
and various enums used by these classes, like a vocabulary storing strings.

the controller receives the input from the view and changes the model accordingly,
the view gets the new information from the model.

IMPORTANT:
- KinoMain.java is used to run the program
- KinoMain.java contains important TODOs and FIXMEs for the whole project
- Note that other comments can also contain important "TODO"s and "FIXME"s
