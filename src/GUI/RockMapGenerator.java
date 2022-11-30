package GUI;
import java.io.FileOutputStream;
import java.io.IOException;

/** A utility class used to generate the rock map to be used in the program
 * @author Aaron Dichoso & Andrei Martin
 * @version 3.2
 * @since 30/11/2022
 */
public class RockMapGenerator {

    public static final String ROCK_MAP_FILE_NAME = "rocks.cfg";
    private static int[] rockIDs = {0, 4, 17, 25, 46, 33, 20, 22, 9};
    public static void main(String[] args) throws IOException
    {
        FileOutputStream out = null;

        //Try to generate the rock map config file
        try
        {
            out = new FileOutputStream(ROCK_MAP_FILE_NAME);

            for(int id : rockIDs)
                out.write(id);
        }
        finally
        {
            if(out != null)
                out.close();
        }
    }
}
