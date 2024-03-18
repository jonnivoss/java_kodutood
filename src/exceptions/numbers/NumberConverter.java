package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SuppressWarnings("ALL")
public class NumberConverter {

    public static void main(String[] args) {
        NumberConverter nb = new NumberConverter("es");
        System.out.println(nb.numberInWords(1));
    }

    private Properties properties;
    public NumberConverter(String lang) {
        String filePath = "src/exceptions/numbers/numbers_%s.properties".formatted(lang);

        properties = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);

            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.ISO_8859_1);

            properties.load(reader);
        }catch (IllegalArgumentException e){
            throw new BrokenLanguageFileException(lang,e);
        }
        catch (IOException e) {
            throw new MissingLanguageFileException(lang,e);
        } finally {
            close(is);
        }
    }


    public String numberInWords(Integer number) {
        if (!properties.containsKey(String.valueOf(number%10))){
            throw new MissingTranslationException(number.toString());
        }
        //zero?
        if(number == 0){
            return properties.getProperty(String.valueOf(0));
        }

        String word = "";
        if(number >= 1000000000){
            word += properties.getProperty(String.valueOf(1));
            word += properties.getProperty("billion-before-delimiter");
            word += properties.getProperty("billion-singular");
        }

        //milion
        if(number >= 1000000 && number < 1000000000){
            int thousands = number/ 1000000;
            int hundred = thousands%1000;
            word += hundredMaker(hundred);
            System.out.println(hundred);
            word += properties.getProperty("million-before-delimiter");
            if(hundred == 1){
                word += properties.getProperty("million-singular");
            }else {
                word += properties.getProperty("million-plural");
            }
            if(number%1000000 != 0){
                word += properties.getProperty("million-after-delimiter");
            }
            number %= 1000000;
        }

        //thousand
        if(number >= 1000 && number < 1000000){
            int thousands = number/ 1000;
            int hundred = thousands%1000;
            word += hundredMaker(hundred);
            word += properties.getProperty("thousand-before-delimiter");
            word += properties.getProperty("thousand");
            if(number%1000 != 0){
                word += properties.getProperty("thousand-after-delimiter");
            }
        }

        //hundred
        int hundreds = number%1000;
        word += hundredMaker(hundreds);
        return word;
    }

    private String hundredMaker(Integer number){
        String word = "";
        int hundreds = number;
        if(hundreds >= 100){
            hundreds /= 100;
            word += properties.getProperty(String.valueOf(hundreds));
            word += properties.getProperty("hundred-before-delimiter");
            word += properties.getProperty("hundred");
            if(number%100 != 0){
                word += properties.getProperty("hundred-after-delimiter");
            }
        }

        int tens = number%100;
        //teens and tens
        if (tens >= 20 && tens < 100 || tens == 10){
            word += checkSecondExceptions(tens);
            if(tens%10 != 0){
                word += properties.getProperty("tens-after-delimiter");
            }
        }
        if(tens < 20 && tens > 10){
            word += checkTeenExceptions(tens);
        }
        else if (tens >= 20 || tens < 10) {
            word += checkOnes(tens);
        }
        return word;
    }

    private String checkOnes(Integer number){
        if (number%10 == 0){
            return "";
        }

        return properties.getProperty(String.valueOf(number%10));
    }


    private String checkSecondExceptions(Integer number){
        if(properties.containsKey(String.valueOf(number - (number%10)))){
            return properties.getProperty(String.valueOf(number - (number%10)));
        }else {
            return properties.getProperty(String.valueOf(number / 10))
                    + properties.getProperty("tens-suffix");
        }
    }

    private String checkTeenExceptions(Integer number){
        if(properties.containsKey(String.valueOf(number))){
            return properties.getProperty(String.valueOf(number));
        }else {
            return properties.getProperty(String.valueOf(number % 10))
                    + properties.getProperty("teen");
        }
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {}
    }
}
