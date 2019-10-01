package org.dud;

import java.io.Console;

public class Main
{
    public static void main(String[] args) {
        UselessSet test = new UselessSet();
        test.Add(1,3,4,6);
        System.out.println(test.toString());
        test.AddNumbersInRange(30,50);
        test.Remove(17);
        System.out.println(test.toString());
        System.out.println("изи: " +test.GetOnlyPrimeNumbers());
        System.out.println("сложна: " +test.GetOnlyCompositeNumbers());
    }
}

