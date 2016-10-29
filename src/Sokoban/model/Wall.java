package Sokoban.model;

import java.awt.*;

/**
 * Created by Marina on 26.10.2016.
 */
public class Wall extends CollisionObject
{

    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.blue);
        graphics.fill3DRect(getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight(),true);
    }


}
