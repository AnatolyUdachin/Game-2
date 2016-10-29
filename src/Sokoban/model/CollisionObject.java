package Sokoban.model;


import static Sokoban.model.Model.FIELD_SELL_SIZE;

/**
 * Created by Marina on 26.10.2016.
 */
public abstract class CollisionObject extends GameObject
{

    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int x=this.getX();
        int y=this.getY();
        if (direction==Direction.LEFT)
        {
            x=this.getX()-FIELD_SELL_SIZE;
        }

        if (direction==Direction.RIGHT)
        {
            x=this.getX()+FIELD_SELL_SIZE;
        }
        if (direction==Direction.UP)
        {
            y=this.getY()-FIELD_SELL_SIZE;
        }

        if (direction==Direction.DOWN)
        {
            y=this.getY()+FIELD_SELL_SIZE;
        }

        return (gameObject.getX()==x && gameObject.getY()==y);
    }
}
