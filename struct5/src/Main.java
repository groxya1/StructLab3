public class Main {

    public static void main(String[] args) {
        Item Item;
        int key;
        HashTable table = new HashTable();

        for (int j = 0; j < 204; j++){
            key = (int) (java.lang.Math.random()*42);//рандом
            Item = new Item(key);
            table.insert(Item);
        }
        table.displayTable();
        Item = table.find(2);
        if (Item != null)System.out.println("Во че есть " + 2);
        else System.out.println("Во чего нет " + 2);
    }
}