package Sokoban.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Sokoban.model.Model.FIELD_SELL_SIZE;

/**
 * Created by Marina on 26.10.2016.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        Image image = null;
        try
        {
            image = ImageIO.read(new File("resources/3.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        graphics.drawImage(image,getX()-FIELD_SELL_SIZE/2,getY()-FIELD_SELL_SIZE/2, FIELD_SELL_SIZE, FIELD_SELL_SIZE, null);
    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }


}
