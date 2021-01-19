package com.epam.suffixingapp;

import com.epam.suffixingapp.beans.RenamingConfigs;
import com.epam.suffixingapp.beans.RenamingResult;
import com.epam.suffixingapp.configgetters.ConfigGetter;
import com.epam.suffixingapp.configgetters.ConfigGetterFactory;
import com.epam.suffixingapp.output.ResultOutputManager;
import com.epam.suffixingapp.services.FileChecker;
import com.epam.suffixingapp.services.Renamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

import static com.epam.suffixingapp.messages.UserMessages.LOG_APP_FINISHED;
import static com.epam.suffixingapp.messages.UserMessages.LOG_APP_STARTED;
import static com.epam.suffixingapp.messages.UserMessages.NOT_ALL_FILES_MESSAGE;
import static com.epam.suffixingapp.messages.UserMessages.NO_ARGUMENT_MESSAGE;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private App() {
    }

    public static void main(String[] args) {
        LOGGER.debug(LOG_APP_STARTED);
        checkArgs(args);
        String configFilePath = args[0];
        ConfigGetter configGetter = ConfigGetterFactory.createConfigGetter(configFilePath);
        RenamingConfigs configs = configGetter.getConfigs(configFilePath);
        checkFiles(configs.getFiles());
        List<RenamingResult> renamingResults = Renamer.addSuffix(configs);
        ResultOutputManager.outputResult(renamingResults, configFilePath, configs.getOutput());
        LOGGER.debug(LOG_APP_FINISHED);
    }

    private static void checkArgs(String[] args) {
        if (args.length < 1) {
            LOGGER.error(NO_ARGUMENT_MESSAGE);
            throw new IllegalArgumentException(NO_ARGUMENT_MESSAGE);
        }
    }

    private static void checkFiles(List<Path> files) {
        if (!FileChecker.checkFilesExistence(files)) {
            System.err.println(NOT_ALL_FILES_MESSAGE);
            LOGGER.error(NOT_ALL_FILES_MESSAGE);
            System.exit(1);
        }
    }
}
