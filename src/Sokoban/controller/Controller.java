package Sokoban.controller;

import Sokoban.model.Model;
import Sokoban.model.Direction;
import Sokoban.model.GameObjects;
import Sokoban.view.View;

/**
 * Created by Marina on 26.10.2016.
 */
public class Controller implements EventListener
{
    private View view;
    private Model model;

    public Controller()
    {
        this.model=new Model();
        this.view=new View(this);
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public static void main(String[] args)
    {
        Controller controller = new Controller();
    }

    public GameObjects getGameObjects()
    {
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction)
    {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart()
    {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel()
    {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level)
    {
        view.completed(level);
    }

    public Model getModel()
    {
        return model;
    }
}
