import java.util.LinkedList;

public class Element {
    private String name;
    private int weight;
    private LinkedList data;

    public String getName() {
        return name;
    }
    public void setName(String name, Object... data) {
        this.name = name;
        for (Object value:data)
            this.data.add(value);
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getData(int index){
        return data.get(index);
    }

    public Element(String name, int weight){
        this.name = name;
        this.weight = weight;
    }
    public Element(String name, int weight, Object... data){
        this.name = name;
        this.weight = weight;
        if (data.length != 0) {
            for (Object value:data)
                this.data.add(data);
        }
    }

    @Override
    public String toString(){
        return this.name;
    }
}
