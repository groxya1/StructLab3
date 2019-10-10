public class Main {

    public static void main(String[] args) {
        System.out.println(ChiffreDeVigenere.Decript("VDOKR RVVZK OTUII MNUUV RGFQK TOGNX VHOPG RPEVW VZYYO WKMOC ZMBR","VIGENERE"));
        System.out.println(ChiffreDeVigenere.Encript("AVIGENERECIPHERISMORECOMPLICATEDTHANCAESARSUBSTITUTION","VIGENERE"));
        System.out.println(ChiffreDeVigenere.Encript("AVIGENERECIPHERISMORECOMPLICATEDTHANCAESARSUBSTITUTION",ChiffreDeVigenere.KeyGen("AVIGENERECIPHERISMORECOMPLICATEDTHANCAESARSUBSTITUTION")));
    }
}
