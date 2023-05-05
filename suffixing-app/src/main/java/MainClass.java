import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String []args)
    {
        Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(args[0]);
            //load properties from config file
            property.load(fis);
            String mode = property.getProperty("mode");
            String suffix = property.getProperty("suffix");
            String files = property.getProperty("files");
            if(!checkProperties(mode,suffix,files, logger))
                return;
            System.out.println(mode +suffix +files);
            //move or copy (depend on mode) given files
            for(String filePath: files.split(":")) {
                moveCopy(filePath, mode, suffix, logger);
            }

        } catch (IOException e) {
            logger.log(Level.WARNING, "No such file: " + Arrays.toString(args));

        }

    }
    private static boolean checkProperties(String mode, String suffix, String files, Logger logger)
    {
        //validation of params from args
        if(!(mode.equalsIgnoreCase("copy") || mode.equalsIgnoreCase("move")))
        {
            logger.log(Level.SEVERE, "Mode is not recognized: " + mode);

            return false;
        }
        if(suffix == null || suffix.equals(""))
        {
            logger.log(Level.SEVERE, "No suffix is configured");

            return false;
        }
        if(files == null || files.equals("")) {
            logger.log(Level.WARNING, "No files are configured to be copied/moved");

            return false;
        }
        return true;
    }
    private static void moveCopy(String sourcePath, String mode, String suffix, Logger logger)
    {

            String newFilePath = newName(sourcePath, suffix);
            System.out.println(newFilePath);
            if(mode.equalsIgnoreCase("move")) {
                //try to move files

                File from = new File(sourcePath);
                File to = new File(newFilePath);
                    //try to copy files
                try {
                        Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        logger.log(Level.INFO, sourcePath + " => " + newFilePath);
                        from.delete();
                } catch (IOException e) {
                    File newFile = new File(newFilePath);

                        logger.log(Level.SEVERE , "No such file: " +  sourcePath);

                }
            }
            else
            {
                File from = new File(sourcePath);
                File to = new File(newFilePath);
                //try to copy files
                try {
                    Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    logger.log(Level.INFO, sourcePath + " -> " + newFilePath);

                }
                catch (IOException ex) {
                    logger.log(Level.SEVERE , "No such file: " +  sourcePath);
                }
                System.out.println();
            }


    }
    private static String newName(String fileName, String suffix)
    {
        //returns a new path of file with suffix
        return (Arrays.stream(fileName.split("/")))
                .map(m -> m.contains(".") ?
                        m.substring(0, m.indexOf('.')) + suffix + m.substring(m.indexOf('.') ) : m)
                .collect(Collectors.joining("/"));
    }
}
