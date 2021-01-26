package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        int countFiles = 0;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                countFiles += countFilesInDirectory(path + "/" + f.getName());
            }
        } else {
            countFiles++;
        }
        return countFiles;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        int countDir = 0;
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                countDir += countDirsInDirectory(path + "/" + f.getName());
            }
            countDir++;
        }
        return countDir;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File filePathFromDir = new File(from);
        File filePathToDir = new File(to);
        File[] files = filePathFromDir.getParentFile().listFiles();
        if (!filePathToDir.exists()) {
            filePathToDir.getParentFile().mkdirs();
        }
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), filePathToDir.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    // Не проходит тест, но работает
    @Override
    public boolean createFile(String path, String name) {
        if (path == null || name == null) {
            return false;
        }
        Path pathDirectory = Paths.get(path);
        Path pathNewFile = Paths.get(path, name);
        try {
            if (!Files.exists(pathDirectory)) {
                Files.createDirectories(pathDirectory);
            }
            Files.deleteIfExists(pathNewFile);
            Files.createFile(pathNewFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Files.exists(pathNewFile);

    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\" + fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
