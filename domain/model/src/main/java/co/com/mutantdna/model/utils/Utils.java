package co.com.mutantdna.model.utils;

public class Utils {

    public static String convertArrayToSimpleText(String[] dna){
        String id = "";
        for (String s : dna) {
            id = id.concat(s);
        }

        return id;
    }
}
