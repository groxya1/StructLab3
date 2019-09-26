using System;
using System.Collections.Generic;
using System.Linq;
namespace ConsoleApp17
{
    class Program
    {
        static void Main(string[] args)
        {
            BinaryTree<int> binTree = new BinaryTree<int>(7);
            binTree.Add(9, 5, 2, 3, 6, 1, 8, 4);
            Console.WriteLine(binTree.ToPrint());
            Console.WriteLine("идём направо: " + binTree.Right.ToPrint());
            Console.WriteLine("идём налево: " + binTree.Left.ToPrint());
            Console.WriteLine("идём как я вчера: " + binTree.Left.Right.ToPrint());
            Tree<int> tree = new Tree<int>(5);
            tree.Add(3, 2, 1);
            tree.Add(tree.Root.GetChild(0),8,7);
            tree.Add(tree.Root.GetChild(0).GetChild(1), 6, 4, 9);
            Console.WriteLine("это дерево с корнем 5:" + tree.ToString());
            Console.WriteLine("это 1-ое поддерево дерева с корнем 5: " + tree.Print(tree.Root.GetChild(0)));
            Console.WriteLine("это бинарное дерево, полученное из 1-го поддерева дерева с корнем 5: " + tree.ConvertToBinaryTree(tree.Root.GetChild(0)).ToPrint());
            Console.WriteLine("это правое поддерево бинарного дерева, полученного из 1-го поддерева дерева с корнем 5: " + tree.ConvertToBinaryTree(tree.Root.GetChild(0)).Right.ToPrint());
            Console.WriteLine("это левое поддерево правого поддерева бинарного дерева, полученного из 1-го поддерева дерева с корнем 5: " + tree.ConvertToBinaryTree(tree.Root.GetChild(0)).Right.Left.ToPrint());
            Console.ReadKey();
        }
    }

    public class Tree<T> where T: IComparable<T>
    {
        private Node<T> root;

        public Tree(T value)
        {
            this.root = new Node<T>(value);
        }
        public Node<T> Root { get { return this.root; } }

        public void Add(params T[] values)
        {
            Add(root, values);
            return;
        }
        public void Add(Node<T> parent,params T[] values)
        {
            foreach (T value in values)
                parent.AddChild(new Node<T>(value));
            return;
        }


        public BinaryTree<T> ConvertToBinaryTree(Node<T> rootNode = null)
        {
            if (rootNode == null) rootNode = this.root;
            outputList.Clear();
            WriteDataToList(rootNode);
            BinaryTree<T> binaryTree = new BinaryTree<T>(rootNode.Data,null);
            outputList.RemoveAt(0);
            binaryTree.Add(outputList.ToArray());
            return binaryTree;
        }

        List<T> outputList = new List<T>();
        public void WriteDataToList(Node<T> node,bool isFirstIteration = true)
        {
            if (node == null) return;
            if (isFirstIteration) outputList.Add(node.Data);
            foreach (Node<T> child in node.childs)
            {
                outputList.Add(child.Data);
                if (child.ChildsCount != 0) WriteDataToList(child,false);
            }
            return;
        }

        public string Print(Node<T> node)
        {
            if (node == null) return string.Empty;
            outputList.Clear();
            WriteDataToList(node, true);
            string output = string.Empty;
            foreach (T value in outputList)
                output += value + " ";
            return output.Remove(output.Length-1);
        }

        public override string ToString() => Print(this.root);
    }
    public class Node<T>
    {
        private T data;
        public List<Node<T>> childs;
        public Node(T value)
        {
            this.data = value;
            this.childs = new List<Node<T>>();
        }
        public T Data { get { return this.data; } set { this.data = value; } }
        public Node<T> GetChild(int index) { return this.childs[index]; }
        public int ChildsCount { get { return this.childs.Count; } }
        public void AddChild(Node<T> child)
        {
            this.childs.Add(child);
            return;
        }
    }

    public class BinaryTree<T> where T : IComparable<T> //страшно вырубай
    {
        private BinaryTree<T> parent, left, right;
        private T data;

        public BinaryTree<T> Left
        { get { return left; }}
        public BinaryTree<T> Right
        { get { return right; } }

        public BinaryTree(T value, BinaryTree<T> parent=null)
        {
            this.data = value;
            this.parent = parent;
        }

        public void Add(T value)
        {
            if (value.CompareTo(this.data) < 0)
            {
                if (this.left == null) this.left = new BinaryTree<T>(value, this);
                else if (this.left != null) this.left.Add(value);
            }
            else
            {
                if (this.right == null) this.right = new BinaryTree<T>(value, this);
                else if (this.right != null) this.right.Add(value);
            }
        }

        public void Add(params T[] values)//было лень поэтому подумал
        {
            foreach (T value in values)
                Add(value);
            return;
        }

        public bool Remove(T value)//насилие над детьми
        {
            BinaryTree<T> tree = search(value);//а был ли мальчик?
            if (tree == null) return false;
            BinaryTree<T> currentTree;
            if (tree == this) //мальчик становится бесполым
            {
                if (tree.right != null) currentTree = tree.right;
                else currentTree = tree.left;
                while (currentTree.left != null)
                    currentTree = currentTree.left;
                T temp = currentTree.data;
                this.Remove(temp);
                tree.data = temp;
                return true;
            }

            if (tree.left == null && tree.right == null && tree.parent != null)//мальчик лишается пальцев
            {
                if (tree == tree.parent.left) tree.parent.left = null;
                else tree.parent.right = null;
                return true;
            }

            if (tree.left != null && tree.right == null)//мальчик левша без правой руки
            {
                tree.left.parent = tree.parent;//своим родителям он не нужен, поэтому на коляске в детдом
                if (tree == tree.parent.left) tree.parent.left = tree.left;
                else if (tree == tree.parent.right) tree.parent.right = tree.left;
                return true;
            }

            if (tree.left == null && tree.right != null)//история та же, но отзеркалена
            {
                tree.right.parent = tree.parent;
                if (tree == tree.parent.left) tree.parent.left = tree.right;
                else if (tree == tree.parent.right) tree.parent.right = tree.right;
                return true;
            }

            if (tree.right != null && tree.left != null)//нормальный был парень
            {
                currentTree = tree.right;
                while (currentTree.left != null)
                    currentTree = currentTree.left;
                if (currentTree.parent == tree)//самый левый элемент является первым потомком
                {
                    currentTree.left = tree.left;
                    tree.left.parent = currentTree;
                    currentTree.parent = tree.parent;
                    if (tree == tree.parent.left) tree.parent.left = currentTree;
                    else if (tree == tree.parent.right) tree.parent.right = currentTree;
                    return true;
                }
                else//самый левый элемент не является первым потомком
                {
                    if (currentTree.right != null) currentTree.right.parent = currentTree.parent;
                    currentTree.parent.left = currentTree.right;
                    currentTree.right = tree.right;
                    currentTree.left = tree.left;
                    tree.left.parent = currentTree;
                    tree.right.parent = currentTree;
                    currentTree.parent = tree.parent;
                    if (tree == tree.parent.left) tree.parent.left = currentTree;
                    else if (tree == tree.parent.right) tree.parent.right = currentTree;
                    return true;
                }
            }
            return false;
        }

        public BinaryTree<T> search(T value)
        {
            return _search(this, value);
        }
        private BinaryTree<T> _search(BinaryTree<T> tree, T value)//проверяем мальчика
        {
            if (tree == null) return null;
            switch (value.CompareTo(tree.data))//причина почему так страшно в начале
            {
                case 1: return _search(tree.right, value);
                case -1: return _search(tree.left, value);
                case 0: return tree;
                default: return null;
            }
        }


        List<T> outputList = new List<T>();
        string outputString = "";
        private void _print(BinaryTree<T> node)
        {
            if (node == null) return;
            _print(node.left);
            outputList.Add(node.data);
            outputString += node + " ";
            if (node.right != null)
                _print(node.right);
            return;
        }

        public string ToPrint()
        {
            outputList.Clear();
            _print(this);
            return outputString;
        }

        public override string ToString() => data.ToString();
    }
}
