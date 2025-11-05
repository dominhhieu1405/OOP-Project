classDiagram
direction BT
class Ball {
  - Ball(int, int, int, int) 
  - boolean isFire
  - double speed
  - int velocityX
  - int velocityY
  - int RADIUS
  - Ball instance
  - collisionWithSideWall() boolean
  - collisionWithUpperWall() boolean
  + getCollisionSide(Entity) String
  + runBall() void
  + render(Graphics) void
  + update() void
  + reset() void
  - collisionWithPaddle() String
  + respawn() void
  - checkBlockCollision() void
   Ball instance
   boolean isFire
   double speed
   boolean isAlive
   int RADIUS
   boolean isRunning
   int velocityY
   int velocityX
}
class Block {
  + Block(int, int, int, int, int) 
  + Block(int, int, int) 
  + Block(int, int) 
  - int HP
  - int MAX_HP
  - boolean isAlive
  + decreaseHP(int) void
  + die() void
  + render(Graphics) void
   boolean isAlive
   int MAX_HP
   int HP
}
class BlockBedrock {
  + BlockBedrock(int, int, int, int) 
  + BlockBedrock(int, int) 
  + decreaseHP(int) void
}
class BlockBomb {
  + BlockBomb(int, int) 
  + BlockBomb(int, int, int, int, int, int) 
  + BlockBomb(int, int, int, int) 
  + BlockBomb(int, int, int, int, int, int, int) 
  - int damage
  - int radius
  + die() void
  - clamp(double, double, double) double
  + checkBombEffect(Block) boolean
  + exploded() void
  + render(Graphics) void
   int damage
   int radius
}
class BlockLucky {
  + BlockLucky(int, int, int, int, int) 
  + BlockLucky(int, int, int, int, int, int) 
  + BlockLucky(int, int, int, int) 
  + BlockLucky(int, int) 
  + die() void
}
class BlockManager {
  - BlockManager() 
  - BlockManager instance
  - ArrayList~Block~ blocks
  + String currentMap
  + reset() void
  + load(String) void
  + addBlock(Block) void
  + removeBlock(Block) void
  + render(Graphics) void
  + load(InputStream) void
  + checkWin() boolean
  + test() void
   BlockManager instance
   ArrayList~Block~ blocks
   String currentMap
}
class Constant {
  + Constant() 
  + createBtn(String) JButton
}
class Entity {
  + Entity(int, int, int, int) 
  # int y
  # int x
  # int width
  # int height
  + getCollisionSide(int, int, int, int) String
  + getCollisionSide(Entity) String
  + isCollision(Entity) boolean
   int y
   int x
   int height
   int width
}
class GameEngine {
  + GameEngine() 
  + run() void
  + stop() void
}
class GameOver {
  - GameOver() 
  - JButton playAgainButton
  - GameOver instance
  - JButton menuButton
  + addButtonsToPanel(JPanel) void
  + render(Graphics) void
   GameOver instance
   JButton playAgainButton
   JButton menuButton
}
class GamePanel {
  - GamePanel() 
  - GamePanel instance
  - Scene currentScene
  # paintComponent(Graphics) void
  + resetInstance() void
   Scene currentScene
   Scene scene
   GamePanel instance
}
class GameScene {
  - GameScene() 
  + GameScene instance
  + useMouse() boolean
  + useKeyboard() boolean
  + stopGame() void
  + continueGame() GameScene
  + paintComponent(Graphics) void
  + update() void
  + resetScene() GameScene
  + setupKeyBindings() void
  + startGame() void
  - removeAllButtons() void
   boolean gameStarted
   GameScene instance
   MouseListener mouseListener
   MouseMotionListener mouseMotionListener
}
class Map {
  + Map(int, String, String, boolean, JButton) 
  + unlock() void
}
class MapManager {
  + MapManager() 
  - int currentMap
  - MapManager instance
  - int page
  + load() void
  + save() void
  + hasNextMap() boolean
  + unlockNextMap() void
   MapManager instance
   Map lastUnlockedMap
   int page
   ArrayList~Map~ mapsByPage
   Map currentMap
   int totalPages
}
class MapScene {
  + MapScene() 
  + paintComponent(Graphics) void
  + useKeyboard() boolean
  + changePage(int) void
  + setupKeyBindings() void
}
class MenuScene {
  + MenuScene() 
  + setupKeyBindings() void
  + paintComponent(Graphics) void
  + useMouse() boolean
  + useKeyboard() boolean
}
class Paddle {
  - Paddle(int, int, int, int) 
  + double speed
  - boolean working
  - Paddle instance
  - boolean movingRight
  - boolean movingLeft
  + update() void
  + reset() void
  + render(Graphics) void
   Paddle instance
   boolean working
   double speed
   boolean movingLeft
   boolean movingRight
}
class Pause {
  - Pause() 
  - JButton playAgainButton
  - Pause instance
  - JButton menuButton
  - JButton resumeButton
  + addButtonsToPanel(JPanel) void
  + render(Graphics) void
   Pause instance
   JButton playAgainButton
   JButton resumeButton
   JButton menuButton
}
class PowerUp {
  + PowerUp(int, int, int, int, String) 
  # boolean isActive
  + update() void
  + activate() void
  + render(Graphics) void
  + deactivate() void
   boolean isActive
   boolean expired
}
class PowerUpBallExpand {
  + PowerUpBallExpand(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpBallExtraLife {
  + PowerUpBallExtraLife(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpBallFast {
  + PowerUpBallFast(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpBallFire {
  + PowerUpBallFire(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpBallShrink {
  + PowerUpBallShrink(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpBallSlow {
  + PowerUpBallSlow(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpCatchBall {
  + PowerUpCatchBall(int, int) 
  + deactivate() void
  + activate() void
}
class PowerUpManager {
  + PowerUpManager() 
  - PowerUpManager instance
  + update() void
  + render(Graphics) void
  + reset() void
  + addPowerUp(PowerUp) void
  + removePowerUp(PowerUp) void
   ArrayList~PowerUp~ powerUps
   PowerUpManager instance
}
class PowerUpPaddleExpand {
  + PowerUpPaddleExpand(int, int) 
  + activate() void
  + deactivate() void
}
class PowerUpPaddleFast {
  + PowerUpPaddleFast(int, int) 
  + activate() void
  + deactivate() void
}
class PowerUpPaddleShrink {
  + PowerUpPaddleShrink(int, int) 
  + activate() void
  + deactivate() void
}
class PowerUpPaddleSlow {
  + PowerUpPaddleSlow(int, int) 
  + activate() void
  + deactivate() void
}
class PowerUpRandom {
  + PowerUpRandom(int, int) 
  + activate() void
  + deactivate() void
  + toString() String
}
class Scene {
  + Scene() 
  + Scene(Image) 
  + useMouse() boolean
  + useKeyboard() boolean
  + update() void
  # paintComponent(Graphics) void
  + setupKeyBindings() void
   KeyListener keyListener
   MouseListener mouseListener
   MouseMotionListener mouseMotionListener
}
class SoundManager {
  + SoundManager() 
  + play(String) void
  + stop(String) void
  + init() void
  + loadSound(String, String) void
  + loop(String) void
}
class Tester {
  + Tester() 
  + testBallReset() void
  + testPaddleReset() void
  + testBallSetSpeed() void
  + testPaddleWorkingState() void
  + testBallSingleton() void
  + testBallHealthRespawn() void
  + testBallSetFire() void
  + testCollisionSideTop() void
  + testEntityCollisionFalse() void
  + testEntityCollisionTrue() void
  + testPaddleSingleton() void
  + testPaddleSetSpeed() void
}
class Win {
  - Win() 
  - Win instance
  - JButton playAgainButton
  - JButton nextLevelButton
  - JButton menuButton
  + addButtonsToPanel(JPanel) void
  + render(Graphics) void
  + renderButtons(Graphics) void
   JButton playAgainButton
   JButton menuButton
   Win instance
   JButton nextLevelButton
}
class main {
  + main() 
  + main(String[]) void
}

Ball  -->  Entity 
Block  -->  Entity 
BlockBedrock  -->  Block 
BlockBomb  -->  Block 
BlockLucky  -->  Block 
GameScene  -->  Scene 
MapManager  -->  Map 
MapScene  -->  Scene 
MenuScene  -->  Scene 
Paddle  -->  Entity 
PowerUp  -->  Entity 
PowerUpBallExpand  -->  PowerUp 
PowerUpBallExtraLife  -->  PowerUp 
PowerUpBallFast  -->  PowerUp 
PowerUpBallFire  -->  PowerUp 
PowerUpBallShrink  -->  PowerUp 
PowerUpBallSlow  -->  PowerUp 
PowerUpCatchBall  -->  PowerUp 
PowerUpPaddleExpand  -->  PowerUp 
PowerUpPaddleFast  -->  PowerUp 
PowerUpPaddleShrink  -->  PowerUp 
PowerUpPaddleSlow  -->  PowerUp 
PowerUpRandom  -->  PowerUp 
