package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData(){
        String textFile = "";
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/sensitive_data.txt"))){
            while (scanner.hasNextLine()) {
                textFile = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile(
                "(?<=(?:[^\\d]|^))(\\d{4}[ \\t])(?:\\d{4}[ \\t]){2}(\\d{4})(?=(?:[^\\d]|$))",
                Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(textFile);
        return matcher.replaceAll("$1**** **** $2");
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String textFile = "";
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/sensitive_data.txt"))){
            while (scanner.hasNextLine()) {
                textFile = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Pattern paymentPattern = Pattern.compile("(\\$\\{payment_amount})");
        Pattern balancePattern = Pattern.compile("(\\$\\{balance})");
        textFile = textFile.replaceAll(paymentPattern.pattern(),String.valueOf((int)paymentAmount))
                .replaceAll(balancePattern.pattern(), String.valueOf((int)balance));
        return textFile;
    }
}
