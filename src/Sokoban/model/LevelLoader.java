package Sokoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static Sokoban.model.Model.FIELD_SELL_SIZE;


/**
 * Created by Marina on 26.10.2016.
 */
public class LevelLoader
{
    private Path levels;

    public int X;
    public int Y;
    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player=null;

        int levelToLoad = level%60==0?60:level%60;
        try(BufferedReader fileInputStream = new BufferedReader(new FileReader(levels.toFile())))
        {
            while (fileInputStream.ready())
            {
                String currentLine = fileInputStream.readLine();
                if (currentLine.equals("Maze: " + levelToLoad))
                {
                    fileInputStream.readLine();

                    String xSize = fileInputStream.readLine();
                    String ySize = fileInputStream.readLine();

                    fileInputStream.readLine();
                    fileInputStream.readLine();
                    fileInputStream.readLine();

                    X = Integer.parseInt(xSize.substring(8));
                    Y = Integer.parseInt(ySize.substring(8));

                    for(int i = 0; i<Y; i++)
                    {
                        String gameLine = fileInputStream.readLine();
                        for(int j = 0; j<gameLine.length();j++)
                        {
                            char c = gameLine.charAt(j);
                            if(c=='X')
                            {
                                walls.add(new Wall(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2));
                            }
                            else if(c=='@')
                            {
                                player = new Player(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2);
                            }
                            else if(c=='*')
                            {
                                boxes.add(new Box(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2));
                            }
                            else if(c=='.')
                            {
                                homes.add(new Home(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2));
                            }
                            else if(c=='&')
                            {
                                homes.add(new Home(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2));
                                boxes.add(new Box(j*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2, i*FIELD_SELL_SIZE+FIELD_SELL_SIZE/2));
                            }
                        }

                    }

                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        GameObjects gameObjects = new GameObjects(walls,boxes,homes,player);
        return gameObjects;
    }
}
