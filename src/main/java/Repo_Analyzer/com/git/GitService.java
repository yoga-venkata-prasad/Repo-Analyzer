package Repo_Analyzer.com.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class GitService {
    public File cloneRepository(String url) throws IOException, GitAPIException {
        File targetDir= Files.createTempDirectory("repo-analyzer-").toFile();
        Git.cloneRepository().
                setURI(url).
                setDirectory(targetDir).
                setDepth(1).
                call().
                close();
        return targetDir;
    }
}
