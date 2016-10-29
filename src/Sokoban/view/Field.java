package Sokoban.view;


import Sokoban.controller.EventListener;
import Sokoban.model.Direction;
import Sokoban.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

import static Sokoban.model.Model.FIELD_SELL_SIZE;

/**
 * Created by Marina on 26.10.2016.
 */
public class Field extends JPanel
{
    private EventListener eventListener;
    private View view;

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g)
    {
        int x= eventListener.getModel().getLevelLoader().X;
        int y = eventListener.getModel().getLevelLoader().Y;
        view.setSize(x*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2,y*FIELD_SELL_SIZE+FIELD_SELL_SIZE);
        g.setColor(Color.black);
        g.fillRect(0,0,view.getWidth(),view.getHeight());
        Set<GameObject> gameObjects = view.getGameObjects().getAll();
        for (GameObject gameObject : gameObjects)
            gameObject.draw(g);
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT: eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT: eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP: eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN: eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R: eventListener.restart();
            }

        }
    }

}
