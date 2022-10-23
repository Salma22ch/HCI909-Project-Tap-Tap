#Tap-Tap
#Group 14: CHANA Salma, HARO Alegria




# Outline:
Project description:
1. Project-specific features:
2. Interaction style:
3. Limitations and possible improvement
Code:
1. Architecture, separation of concerns:
2. Main problems and challenges:
Conclusion:

# General Info:

### Link to download the apk:
https://wetransfer.com/downloads/e8a549b3f883f5bd20bf16fc2d73bdc520221022213531/bd313deb20190c0a6edb68970088962d20221022213551/102da2
Or https://drive.google.com/file/d/1JsRywlcYx3OMTpuz91fa3ECTu5y8H4ps/view?usp=sharing

### Link to demo:
https://drive.google.com/file/d/1IFW37J75y5tNWdpQj_LKa8N4MwKRpBQS/view?usp=sharing

### Github repository:
https://github.com/Salma22ch/HCI909-Project-Tap-Tap

### Supported Android Version:
The game can only be run on an android phone: Android 6.0 (API level 23) and higher.


## Project description
The main goal of this project was to develop an application that allows users to interact with music. Our initial idea was to make a game similar to GuitarHero, but as development progressed, we made it look like a PianoTiles game.
We think the latter offers a richer interactive experience (the ability to click directly on the musical note to score points instead of clicking on the bottom buttons).

## Project-specific features:
Choose a game mode, we offer three slightly different game modes (easy, medium, expert), and the difference between them is: animation speed + the number of musical notes.
Tap on the animated music note to score points.
Synchronize the animation of the music note with the music.
Visual feedback on the user's actions (disappearance of notes).
Progress bar to indicate the progress of the music and the time passed.
Pause/Resume/Restart the game. 
Save the best score in each mode in an xml file using shared preferences.


## Interaction style:
Tap : Navigate in the game.
Choose the preferred mode.
Score points.
Pause/Resume/Restart the game.


## Limitations and possible improvement
###Add more interactions style:
Long press: Long press on a note to multiply the score.
Swipe to the left/right: change the game mode (easy => medium => expert => easy)
Double-click: to pause/resume the game.

### Add more feedback styles to the user's action:
The game gives one type of feedback (visual), if the user taps in the right place, the note will disappear and the score will be incremented, otherwise the layout will be red and the score will be decremented.
But we think that having another type of feedback would be great, for example:
Haptic feedback: vibrate each time a note is missed by the player.
###Implement more functionalities:
Have multiple songs to play (we wanted to implement this but because of the time-consuming task of synchronization and also because of the copyright issue, we couldn't do it).
Have more differences between difficulty modes (currently the only difference between modes is the animation speed + the number of music notes).



# Code

Architecture, separation of concerns:
Our game is developed using the MVC model:
Explanations:  (In the following, we will focus on the game activity architecture (EasyActivity, MediumActivity, ExpertActivity) because it is the most important part of our project:
### View: To build the view we used AndroidUIBuilder, the general hierarchy of the layout is as follows:
Our game will be running on mobile devices, so we need to make sure it will be responsive and adapt to all screens. To achieve this behavior, we used LinearLayout(Horizontal, Vertical) + LayoutWeight.
As shown in the figure above, our application supports all screen sizes.
### Controller: Composed of three main parts:
	OnCreate(): this method will be called once the activity is created, and it will hold the declaration of the views and add listeners to them.
	OnStart(): as its name suggest it will be called once the activity is started  In this method, we will have a welcome countdown (3,2,1,0) before starting the game, once finished it will call the second countdown (synchronized with the music playing in the background + responsible for updating the progress bar). As well as adding the music notes progressively using a loop. The synchronization task is accomplished by calling a runnable inside the loop, this runnable will be executed with a delay corresponding to the time of appearance of each note of the music data structure given by the GameModel class.
StateManagement:  we have four states:
-StartedState : when the music is played and the animation of the musical notes is launched.
- PausedState : when the user clicks on the pauseButton.
- ResumeState : when the user resumes the game by clicking on the resumeButton.
- FinishedState : when the game is over.
### Model: GameModel class:
It stores the four game’s states.
It is responsible for the transcription task (so far one the most difficult task in this game). 


## Main problems and challenges:
The main problem of our project was time management, as we wasted the first three weeks developing features that are not relevant for the end users (like having a different sequence of music notes every time the user starts a new game [the user will not be able to progress and make better scores], playing the music when the user taps in the right place [this will be frustrating for the end user as the music will be stopped frequently].

We were able to overcome these challenges through the discussions we had during the course, at this point, we would like to thank you for putting us on the right track with your advice.

# Conclusion:
At the end of this project we weren’t able to implement all the features we want, but we learned many things:

Design interaction and feedback in a game.
Implement the MVC model in a mobile application.
Learn how to use runnable in java for synchronization tasks.


