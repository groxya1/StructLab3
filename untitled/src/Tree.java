import java.util.LinkedList;
import java.util.Random;

public class Tree<T> {
    private Node<T> root;

    public Tree(T value) {
        this.root = new Node<T>(value);
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void add(T... values) {
        for (T value:values) {
            add(this.root,value);
        }
    }

    public void add(Node<T> parent,T... values) {
        for (T value:values) {
            parent.addChild(new Node<T>(value,parent));
        }
    }

    LinkedList<T> outputList = new LinkedList<T>();

    public void WriteDataToList(Node <T> node) {
        WriteDataToList(node,true);
    }
    public void WriteDataToList(Node<T> node,boolean isFirstIteration)
    {
        if (node == null) return;
        if (isFirstIteration) outputList.add(node.getData());
        for(Node<T>child:node.childs)
        {
            outputList.add(child.getData());
            if (child.childsCount() != 0) WriteDataToList(child,false);
        }
        return;
    }

    public String Print(Node<T> node)
    {
        if (node == null) return "";
        outputList.clear();
        WriteDataToList(node);
        String output = "";
        for (T value:outputList) {
            output += value + " ";
        }
        return output.substring(0,output.length()-2);
    }

    @Override
    public String toString(){
        return this.root.toString();
    }

    public String SearchAlgorithmStarter(int maxVolume,LinkedList<T> values)//страшно вырубай
    {
        LinkedList<T> valuesBuffer = values;
        Random rnd = new Random();
        while (true) {
            int n = rnd.nextInt(valuesBuffer.size()-1);
            Tree<T> treeItSelf = new Tree<T>(valuesBuffer.get(n));
            valuesBuffer.remove(n);
            return SearchAlgorithm(maxVolume, valuesBuffer, treeItSelf.root);
        }
        //return SearchAlgorithmStarter(maxVolume,values);
    }

    private int SearchAlgorithm(int maxVolume, LinkedList<T> values,Node<T> node)
    {
        Random ngrnd = new Random();
        int n = ngrnd.nextInt(values.size()-1);
        getRandomChild(node).addChild(new Node<T>(values.get(n),node));
        if (values.size()==1) {
            return node.getWeight(0,"");
        }
        values.remove(n);
        return SearchAlgorithm(maxVolume, values, node);
    }

    private Node<T> getRandomChild(Node<T> startNode)//сука я сделал Node ещё 1 деревом
    {
        if (startNode.childs.size()==0) return startNode;
        Random rnd = new Random();
        return getRandomChild(startNode.getChild(rnd.nextInt(startNode.childsCount()-1)));
    }
}