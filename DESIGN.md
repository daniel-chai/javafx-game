# Design

## High-level Design Goals
- Design Battle and BossBattle so that they are separate classes for better separation of concerns.
- Implement SimpleBattle as an abstract class to be the superclass of Battle and BossBattle so
that the two classes don't have any duplicated code.
- Encapsulate characters and items into their own classes when needed to make public only the
necessary methods (also limits duplicated code).
- Delegate commonly-used methods to separate classes (i.e. UIGenerator and SceneManager).
- Design and implement Interface when appropriate to promote consistency across the classes.
 
## How to Add New Features
The two major features in this game are the Scenes and the characters/items. Examples of Scenes are 
Battle, BossBattle, Menu, GameOver, GameWon, etc. Examples of characters/items are Player, Laser,
Enemy, Potion, etc.
 
### Adding a New Scene
- Each Scene requires its own class that implements SceneInterface. The SceneInterface defines the init
method, which returns a new Scene when called. 
- The SceneManager class controls scene switching (i.e. clicking on a button on the Menu Scene leads
to the Instructions Scene). The same SceneManager object must be used throughout the game, so this 
one SceneManager object is passed to every Scene class. Therefore, this new Scene class must take in
the currently used SceneManager as a parameter in the constructor. Then, whenever a scene switch is
desired, the SceneManager's methods can be used.
- Specific characters/items can be instantiated to be used in the Scene. 
- If the Scene is a "battle scene," then it should extend the abstract class SimpleBattle. SimpleBattle
contains attributes and methods that are appropriate for any battle. The attributes include the player
and the player's lasers. The methods include functionality to display common text, initialize the
player correctly, move the player's lasers around, and check for intersections. 
- Many times, Scenes will need to incorporate UI components such as Text and Button. These UI components
can be instantiated with the UIGenerator class. The goal of the UIGenerator class is to remove as
much duplicated code as possible when making UI components.
 
### Adding a New Character / Item
- Generally, each major character/item should have its own class to encapsulate behavior. Its constants
(such as height, width, etc.) should be declared as public static constants so that other classes (such
as the Scenes) can access these values.
- Much like how Scenes use Text and Button, characters/items are often based on Rectangle. Rectangles
can also be generated using the UIGenerator.
 
## Major Design Choices and Trade-offs
- I decided to design SimpleBattle as an abstract class and the superclass for Battle and BossBattle.
The reason behind the abstract class is that SimpleBattle is never meant to be instantiated. Instead,
it is used to factor out common functionality from Battle and BossBattle. This way, once Battle and
BossBattle extend SimpleBattle, a lot of the code is already written. Another option that I considered
was using delegation instead of inheritance. That would mean creating a new class to call functions 
from instead of extending any class. This would be cleaner in the sense that not everything from
SimpleBattle has to be inherited, and only the methods that are needed can be called. But I decided
to use inheritance because of the is-a relationship. Battle is-a SimpleBattle and BossBattle is-a 
SimpleBattle. It just makes more sense conceptually, and I think inheritance makes the code cleaner
as well.
- I decided to use the SceneManager as the way to switch between Scenes. The SceneManager has public
methods that can be called to switch to the Scene that is desired. The downside to using the
SceneManager is that the SceneManager object has to be passed around a lot between the classes that 
use it in order to keep consistent state. The alternative was to just pass the Stage around and
not use a SceneManager. This perhaps would've been easier to implement, but I decided against it
because I thought passing the Stage around is worse than passing the SceneManager around. Also, the
SceneManager provides consistency in how the scene changes are handled.
- I decided to encapsulate a lot of the characters/items into their own classes. For example, 
Laser and Player each have their own class and are also instantiated in SimpleBattle. The downside
to this is that objects cannot be displayed on the Scene. Instead, the actual Rectangle is needed.
Because of this, I need to store both the actual objects (to call the methods) and the associated
Rectangles (to display on the Scene) as instance variables. The major disadvantage with this is 
the potential inconsistency and the need to update one whenever the other is changed. However, the
alternative was to just not create the Player/Laser classes and do everything within the SimpleBattle
class. I thought it would be much worse to not encapsulate the character/item behavior because
they are separable components that need to be re-used throughout the game. So because of that, I 
figured it was better to limit duplicated code.
 
## Assumptions Made
- I assume that the player won't press two buttons at once. For example, the player can only either
be moving or shooting the laser at any given moment. This was an assumption made to simplify the 
design and implementation of the game.
- I assume that the player wants to play the levels only in order and not jump around. This 
simplification made it much easier to design the Battle class, as the constructor can just take
in the level as a parameter.