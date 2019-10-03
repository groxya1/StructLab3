package org.dud;

public class Main
{
    public static void main(String[] args) {
        Set test = new Set();
        test.Add(1,3,4,6);
        System.out.println(test.toString());
        test.AddNumbersInRange(30,50);
        test.Remove(1);
        System.out.println(test.toString());
        System.out.println("Простые числа: " +test.GetOnlyPrimeNumbers());
        System.out.println("Составные числа: " +test.GetOnlyCompositeNumbers());
    }
}

