public class ChiffreDeVigenere {

    static String standartAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String KeyGen(String text){
        String key = "";
        int len = text.length();
        for (int i = 0; ;i++){
            if (len == i) i=0;
            if (key.length() == text.length()) break;
            key += text.charAt(i);
        }
        return key;
    }
    public static String Encript(String text, String key){
        return Encript(text, key, standartAlphabet);
    }
    public static String Encript(String text, String key, String alphabet){
        text = text.replace(" ", "");
        String result = "";
        for (int i = 0, j = 0; i < text.length();i++, j++) {
            if (j > key.length() - 1) j = 0;
            result += alphabet.charAt((text.charAt(i) + key.charAt(j)) % alphabet.length());
        }
        return result;
    }

    public static String Decript(String text, String key){
        return Decript(text, key, standartAlphabet);
    }
    public static String Decript(String text, String key, String alphabet){
        text=text.replace(" ", "");
        String result = "";
        for (int i = 0, j = 0; i<text.length(); i++, j++){
            if (j>key.length()-1) j=0;
            result += alphabet.charAt((text.charAt(i) - key.charAt(j) + alphabet.length()) % alphabet.length());
        }
        return result;
    }
}
