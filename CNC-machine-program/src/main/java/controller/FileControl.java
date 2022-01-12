package controller;

import javafx.stage.FileChooser;
import model.GcodeElement;
import model.InputFile;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class FileControl {

    public static File file;

    public static void configureFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("IN", "*.in"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
    }

    public static void convertToGcode(File file){
        FileControl.file = file;

        InputFile inputFile = FileHandler.readFromFile(file.getPath());

        List<GcodeElement> gCodeList = FileToGcode.convert(inputFile);

        FileHandler.writeGcode(gCodeList, FilenameUtils.removeExtension(file.getName()));

    }
}
