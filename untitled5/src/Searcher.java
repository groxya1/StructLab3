import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Searcher {
    public static void BasedOnInput(){
        int maxWeight;
        int iterations;
        LinkedList<Element> elements = new LinkedList<Element>();

        Scanner sc = new Scanner(System.in);
        System.out.println("максимально допустимый суммарный вес элементов: ");
        maxWeight = sc.nextInt();
        System.out.println("число попыток: ");
        iterations = sc.nextInt();

        int tempWeight;
        String tempName;
        do{
            System.out.println("\nимя элемента: ");
            sc.nextLine();
            tempName = sc.nextLine();
            if (tempName.equals("") || tempName.equals("\n") || tempName.equals(" ")) break;
            System.out.println("вес: ");
            tempWeight = sc.nextInt();
            elements.add(new Element(tempName, tempWeight));
        }while (true);
        System.out.println("\n" + RandomSearch(iterations,maxWeight,null, elements));
    }


    public static String RandomSearch(int iterations, int maxWeight, Element[] currentMostOptimalDecission, LinkedList<Element> elements){
        LinkedList<Element> firstDecission = new LinkedList<Element>();
        LinkedList<Element> secondDecission = new LinkedList<Element>();
        LinkedList<Element> temp = new LinkedList<Element>();
        LinkedList<Element> tempElements = new LinkedList<Element>();
        int secondDecissionWeight = 0;
        int firstDecissionWeight = 0;
        int tempWeight = 0;

        tempElements.addAll(elements);
        if (currentMostOptimalDecission!=null) {
            for (Element element:currentMostOptimalDecission) {
                temp.add(element);
                tempWeight+=element.getWeight();
            }
        }

        Random rnd = new Random();
        for (int i = 0; i < iterations; i++){
            firstDecission.clear();
            secondDecission.clear();
            firstDecissionWeight=0;
            secondDecissionWeight=0;
            tempElements = SomeRandomFluctations(tempElements);
            for (Element element:tempElements){
                if (rnd.nextBoolean()&&maxWeight>=firstDecissionWeight+element.getWeight()) {
                    firstDecission.add(element);
                    firstDecissionWeight+=element.getWeight();
                }
                else if (maxWeight>=secondDecissionWeight+element.getWeight()) {
                    secondDecission.add(element);
                    secondDecissionWeight+=element.getWeight();
                }
            }
            if (secondDecission.size()>firstDecission.size()&&secondDecission.size()>temp.size())
                temp = secondDecission;
            else if (firstDecission.size()>=secondDecission.size()&&firstDecission.size()>temp.size())
                temp = firstDecission;
        }
        String tempString = "";
        for (Element element:temp) {
            tempString += element.toString()+ ", ";
        }
        return tempString;
    }

    public static LinkedList<Element> SomeRandomFluctations(LinkedList<Element> notSoRandomList){
        Random rnd = new Random();
        Element temp;
        int n;
        for (int i = 0; i < notSoRandomList.size(); i++){
            n = rnd.nextInt(notSoRandomList.size()-1);
            temp = notSoRandomList.get(n);
            notSoRandomList.remove(n);
            notSoRandomList.add(temp);
        }
        return notSoRandomList;
    }
}
