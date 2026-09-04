package Repo_Analyzer.com.git;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileTreeWalker {
    private static final Set<String> IGNORED_DIR_VALUES = Set.of(".git", "node_modules", "target",
            "build", "dist",
            ".idea", ".vscode", "venv", "__pycache__");
     public List<String> walk (File rootDir)  throws IOException {
         Path rootpath = rootDir.toPath();
         try(Stream<Path>stream = Files.walk(rootpath)){
             return stream.filter(path -> !isIgnoredDir(rootpath,path))
                     .filter(Files::isRegularFile).map(path -> rootpath.relativize(path).toString())
                     .collect(Collectors.toList());
         }
     }
     public boolean isIgnoredDir(Path rootpath, Path path){
             Path realtive = rootpath.relativize(path);
             for(Path part:realtive){
                 if(IGNORED_DIR_VALUES.contains(part.toString()))return true;
             }
             return false;
     }
}
