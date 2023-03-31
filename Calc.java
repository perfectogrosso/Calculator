import java.io.IOException;
import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два целых числа, разделяя пробелами");
        String stringValue = scanner.nextLine(); //Вводить с пробелами, т.к. по ним работает split
        System.out.println(parse(stringValue));
    }

    public static String parse(String stringValue) throws Exception {
        String [] stringsFig = stringValue.split(" "); //Делим строку по пробелам, записываем значения в массив
        if (stringsFig.length > 3) {        // Проверяем, если введено больше трех значений = исключение
            throw new IOException("Вы ввели больше двух чисел");
        }

        String result;
        boolean isRim;
        int[] intArr = new int[2];
        //System.out.println(stringsFig[1]); // Отображаем оператор
        String operator = new String(stringsFig[1]);  // Добавляем его в отдельную строку

        //если оба числа римские
        if (Rim.isRim(stringsFig[0]) && Rim.isRim(stringsFig[2])) {
            //конвертируем оба числа в арабские для вычесления действия
            intArr[0] = Rim.convertToArabian(stringsFig[0]);
            intArr[1] = Rim.convertToArabian(stringsFig[2]);
            isRim = true;
        }
        //если оба числа арабские
        else if (!Rim.isRim(stringsFig[0]) && !Rim.isRim(stringsFig[2])) {
            intArr[0] = Integer.parseInt(stringsFig[0]);
            intArr[1] = Integer.parseInt(stringsFig[2]);
            isRim = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (intArr[0] > 10 || intArr[1] > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(intArr[0], intArr[1], operator);
        if (isRim) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            //конвертируем результат операции из арабского в римское
            result = Rim.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String operator) {
        if (operator.contains("+")) return "+";
        else if (operator.contains("-")) return "-";
        else if (operator.contains("*")) return "*";
        else if (operator.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String stringsFig) {

        if (stringsFig.equals("+")) return a + b;
        else if (stringsFig.equals("-")) return a - b;
        else if (stringsFig.equals("*")) return a * b;
        else return a / b;
    }

}

class Rim {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRim(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}

