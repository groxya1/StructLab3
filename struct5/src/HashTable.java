public class HashTable {
    private Item[] hashArray;

    private int size = 255;

    private Item bufferItem; //Вальгалла

    public HashTable() {
        hashArray = new Item[size];
        bufferItem = new Item(-1); //ключ удаленного элемента -1
    }

    public void displayTable() {
        System.out.print("Таблица (почти): ");
        for (int j = 0; j < size; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("#");//ну как бы нет
        }
        System.out.println("");
    }

    public int Hash(int key) {
        return key % size;
    }

    public void insert(Item item){
        int key = item.getKey();
        int hashVal = Hash(key); //KC & The Sunshine Band - Shake, shake, shake
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {//до черноты души кофе или -1
            ++hashVal;
            hashVal %= size;
        }
        hashArray[hashVal] = item;
    }

    public Item delete(int key) {
        int hashVal = Hash(key);

        while (hashArray[hashVal] != null) //только до черноты души кофе
        {
            if (hashArray[hashVal].getKey() == key) {//temp через вальгаллу
                Item temp = hashArray[hashVal];
                hashArray[hashVal] = bufferItem;
                return temp;
            }
            ++hashVal;
            hashVal %= size;
        }
        return null; //а вдруг его нет
    }

    public Item find(int key) //поиск по ключу
    {
        int hashVal = Hash(key); //♪hash hash hash, hash your body♪

        while (hashArray[hashVal] != null)
        {
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];
            ++hashVal; // go to next cell
            hashVal %= size;
        }
        return null;
    }
}