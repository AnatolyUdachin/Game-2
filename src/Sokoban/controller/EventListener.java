package Sokoban.controller;


import Sokoban.model.Direction;
import Sokoban.model.Model;

/**
 * Created by Marina on 26.10.2016.
 */
public interface EventListener
{
    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);

    Model getModel();
}
