package exceptions.translate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

@SuppressWarnings("ALL")
public class ConfigurationLoader {

    public String loadConfiguration(String configFilePath) throws Exception {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(configFilePath)));
        }catch (NoSuchFileException e){
            throw new InstallationException(e);
        } catch (IOException e){
            throw new ConfigurationException(e);
        }
    }
}
