package _base.toml_parse;

import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".");
        System.out.println(path.toFile().getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
        Path source = Paths.get("_0_base-learning/src/main/java/_base/toml_parse/res/file.toml");
        TomlParseResult result = Toml.parse(source);
        result.errors().forEach(error -> System.err.println(error.toString()));
        System.out.println(result.toJson());

        String value = result.getString("a. dotted . key");
        System.out.println(result.getString("datasets. subsets . image_dir"));
    }
}
