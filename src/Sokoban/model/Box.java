package Sokoban.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marina on 26.10.2016.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        Image image = null;
        try
        {
            image = ImageIO.read(new File("resources/3-1.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        graphics.drawImage(image,getX()-20,getY()-20, 40, 40, null);
    }


    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }


}
