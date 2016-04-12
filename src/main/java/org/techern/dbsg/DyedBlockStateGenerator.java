package org.techern.dbsg;

import java.io.IOException;
import java.nio.file.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Logger;

/**
 * DyedBlockStateGenerator; A block state generator for dyed blocks
 *
 * @since 0.0.1
 */
public class DyedBlockStateGenerator {

    /**
     * The {@link Logger} used by {@link DyedBlockStateGenerator}
     *
     * @since 0.0.1
     */
    public static Logger LOGGER = Logger.getLogger("Generator");

    /**
     * Runs {@link DyedBlockStateGenerator}
     *
     * @param arguments The list of arguments
     * @since 0.0.1
     */
    public static void main(String... arguments) throws IOException {
        LOGGER.info("Starting the dyed block state generator...");

        Path rootPath = FileSystems.getDefault().getPath(".");

        Path templatePath = rootPath.resolve("templates");

        Path outputPath = rootPath.resolve("generated");

        LOGGER.info("Template path is " + templatePath.toString() + ", output path is " + outputPath.toString());

        if (Files.notExists(templatePath)) {
            Files.createDirectory(templatePath);
            LOGGER.warning("Template folder does not exist; Creating");
        }
        if (Files.notExists(outputPath)) {
            Files.createDirectory(outputPath);
            LOGGER.warning("Output folder does not exist; Creating");
        }

        Files.walkFileTree(templatePath, new SimpleFileVisitor<Path>() {

            /**
             * Invoked for a file in a directory.
             * <p/>
             * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
             * CONTINUE}.
             *
             * @param filePath The {@link Path} to the file
             * @param attrs The {@link BasicFileAttributes} of the file
             *
             * @since 0.0.1
             */
            @Override
            public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
                FileVisitResult result = super.visitFile(filePath, attrs);

                if (!result.equals(FileVisitResult.CONTINUE)) {
                    return result;
                }

                LOGGER.info("Found file: " + filePath.getFileName().toString());

                return result;
            }
        });



    }

}
