# Kinoreservierung
The project contains three main parts:
1. the view
2. the model
3. the controller

those can be found in their packages of the same name

1. the view:
the view consists of different tab-classes containing components and information,
each tab is build differently and managed by the KinoView.java

2. the model:
the model (KinoModel.java) contains all important data for the current user input, aswell as consistent data, like movies and catering options,
other classes make up the complete "Kino":

each movie has showtimes,
each showtime has seats

enums found in the enums package are used to build these classes,
the Vocabulary class contains all important strings for building the view,
an order is saved in a list in KinoModel, saved as an Order object

3. the controller:
the controller manages input from the view and changes the model and view accordingly,
there is only one class - KinoController - in the controller package

IMPORTANT:
- Main.java is used to run the program
- Main.java contains important TODOs and FIXMEs for the whole project
- Other comments containing "TODO" and "FIXME" are important on what changes and additions need to be added, please use them!
