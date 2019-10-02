public class HashItem {

    private String key, value;

    public String getKey(){//если не понятно то в Java нет св-в как в # и такие Bean св-ва норма
        return key;
    }
    private void setKey(String key) {
        this.key = key;
    }

    public String getValue(){
        return value;
    }
    private void setValue(String value){
        this.value=value;
    }

    public HashItem(String key,String value)
    {
        setKey(key);
        setValue(value);
    }

    @Override
    public String toString()
    {
        return key;
    }
}
