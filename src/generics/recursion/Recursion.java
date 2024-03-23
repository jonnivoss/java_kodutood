package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Recursion {

    public List<String> getParts(Path path) {
        List<String> results = new ArrayList<>();
        Path run = path;
        while (run != null) {
            results.addFirst(run.getFileName().toString());
            run = run.getParent();
        }

        return results;
    }

    public List<String> getParts2(Path path) {
        if(path == null){
            return null;
        }

        getParts2(path.getParent());
        System.out.println(path.getFileName());
        return null;
    }

    public List<String> getParts3(Path path, List<String> parts) {
        if(path == null){
            return null;
        }

        getParts3(path.getParent(),parts);

        parts.add(path.getFileName().toString());
        return null;
    }

    public List<String> getParts4(Path path) {
        if(path == null){
            return new ArrayList<>();
        }
        List<String> parts = getParts4(path.getParent());
        parts.add(path.getFileName().toString());

        return parts;
    }
}
