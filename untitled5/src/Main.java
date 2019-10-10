import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Searcher.BasedOnInput();//это для подсчета по вводу

        Object[][] data = new Object[][] {{"сейсмометр","уголковый отражатель","коллектор солнечного ветра","лунный грунт","флаг","камера","мусор","печеньки","кремниевый диск","посадочный модуль","Эдвин","Нил","Скафандры"},{2,1,1,10,3,5,10,1,1,1200,98,87,40}};
        LinkedList<Element> listedData = new LinkedList<>();
        for(int i = 0;i < data[0].length-1;i++){
            listedData.add(new Element((String)data[0][i],(int)data[1][i]));
        }
        String result = Searcher.RandomSearch(1000,250,null,listedData);
        System.out.println("Вместе с орлом вернуться: " + result.substring(0,result.length()-2));
        if (!result.contains("Нил")||!result.contains("Эдвин")) System.out.println("похоже это будет долгий день");
    }
}
