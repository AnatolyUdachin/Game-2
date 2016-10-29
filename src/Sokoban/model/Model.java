package Sokoban.model;


import Sokoban.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by Marina on 26.10.2016.
 */
public class Model
{
    public static final int FIELD_SELL_SIZE = 40;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    String filePath = "resources/levels.txt";
    private LevelLoader levelLoader = new LevelLoader(Paths.get(filePath));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction)
    {
        if(checkWallCollision(gameObjects.getPlayer(),direction)) return;
        if(checkBoxCollision(direction)) return;
        if(direction==Direction.LEFT) gameObjects.getPlayer().move(-FIELD_SELL_SIZE,0);
        else if(direction==Direction.RIGHT) gameObjects.getPlayer().move(FIELD_SELL_SIZE,0);
        else if(direction==Direction.UP) gameObjects.getPlayer().move(0,-FIELD_SELL_SIZE);
        else if(direction==Direction.DOWN) gameObjects.getPlayer().move(0,FIELD_SELL_SIZE);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        boolean result = false;

        Set<Wall> wallSet = gameObjects.getWalls();

        for(Wall wall : wallSet)
            if(gameObject.isCollision(wall,direction))
            {
                result = true;
                break;
            }
        return result;
    }

    public boolean checkBoxCollision(Direction direction){
        boolean result = false;
        Set<Box> boxes = gameObjects.getBoxes();
        for(Box box : boxes)
        if(gameObjects.getPlayer().isCollision(box, direction))
        {
            if (checkWallCollision(box, direction))
            {
                result=true;
                break;
            }
            else
                for (Box box1 : boxes)
                {
                    if (box1 != box && box.isCollision(box1, direction))
                    {
                        result = true;
                        break;
                    }
                }

            if (!result)
            {
                if(direction==Direction.LEFT)box.move(-FIELD_SELL_SIZE,0);
                else if(direction==Direction.RIGHT)box.move(FIELD_SELL_SIZE,0);
                else if(direction==Direction.UP)box.move(0,-FIELD_SELL_SIZE);
                else if(direction==Direction.DOWN)box.move(0,FIELD_SELL_SIZE);
            }
        }
        return result;
    }

    public void checkCompletion()
    {
        boolean result = false;
        Set<Home> homes = gameObjects.getHomes();
        Set<Box> boxes = gameObjects.getBoxes();
        for (Home home : homes)
        {
            result=false;
            for(Box box : boxes)
            {
                if(box.getY()==home.getY()&&box.getX()==home.getX())
                    result=true;
            }
            if (!result) break;
        }
        if(result) eventListener.levelCompleted(currentLevel);
    }

    public LevelLoader getLevelLoader()
    {return levelLoader;}
}
