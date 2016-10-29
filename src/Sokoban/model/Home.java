package Sokoban.model;

import java.awt.*;

/**
 * Created by Marina on 26.10.2016.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.red);
        graphics.drawOval(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
    }
}
