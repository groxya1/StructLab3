import java.util.LinkedList;

public class Node<T>//мы встроили дерево тебе в дерево чтобы ты мог перебирать дерево пока перебираешь дерево
{
    private T data;
    private String name = null;
    private Node<T> parent = null;
    public LinkedList<Node<T>> childs;
    public Node(T value)
    {
        this.data = value;
        this.childs = new LinkedList<Node<T>>();
    }

    public Node(T value,Node<T> parent)
    {
        this.data = value;
        this.parent = parent;
        this.childs = new LinkedList<Node<T>>();
    }
    public Node(T value,Node<T> parent,String name)
    {
        this.name = name;
        this.data = value;
        this.parent = parent;
        this.childs = new LinkedList<Node<T>>();
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Node<T> getChild(int index) {
        return this.childs.get(index);
    }
    public int childsCount() {
        return this.childs.size();
    }

    public void addChild(Node<T> child) {
        this.childs.add(child);
    }

    public boolean isThatLikeParentExist(T data, String name){
        if (this.name== name && this.data == data) return true;
        if (this.parent == null) return false;
        else return this.parent.isThatLikeParentExist(data, name);
    }

    public int getWeight(int CurrentWeight,String names) {//Let me introduce you
        if (parent==null) {
            System.out.println("у нас были: " + names +"\n и весили они " + CurrentWeight);
            return CurrentWeight;
        }
        names +=this.name + ", ";
        return this.parent.getWeight(CurrentWeight+((int)data),names);
    }
}