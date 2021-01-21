package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        String endResult = base.replace(remove, "");
        return endResult;
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        boolean isQuestion = false;
        if (text.endsWith("?")) {
            isQuestion = true;
        }
        return isQuestion;
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder endResult = new StringBuilder();
        for (String s : elements) {
            endResult.append(s);
        }
        return endResult.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        char[] charArray = text.toCharArray();
        int counter = 1;
        for (int i = 0; i < charArray.length; i++) {
            if (counter % 2 != 0) {
                charArray[i] = Character.toLowerCase(charArray[i]);
                counter++;
            } else {
                charArray[i] = Character.toUpperCase(charArray[i]);
                counter++;
            }
        }
        return String.valueOf(charArray);
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if (string.length() == 0) {
            return false;
        }
        string = string.toLowerCase().replace(" ", "");
        for (int i = 0; i < string.length() / 2; i++) {
            int firstIndex = i;
            int lastIndex = string.length() - 1 - i;
            if (string.charAt(firstIndex) != string.charAt(lastIndex)) {
                return false;
            }
        }
        return true;
    }
}
