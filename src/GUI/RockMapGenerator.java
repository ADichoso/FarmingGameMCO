package GUI;
import java.io.FileOutputStream;
import java.io.IOException;

public class RockMapGenerator {

    public static final String ROCK_MAP_FILE_NAME = "rocks.cfg";
    private static int[] rockIDs = {0, 4, 17, 25, 46, 33, 20};
    public static void main(String[] args) throws IOException
    {
        FileOutputStream out = null;

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
