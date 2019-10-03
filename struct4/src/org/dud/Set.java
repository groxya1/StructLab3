package org.dud;

import java.util.LinkedList;

public class Set {//целый бесполезный класс
    private LinkedList<Integer> values = new LinkedList<>() {
    };
    /*public T value(){
        return value();
    }*/

    public LinkedList GetValues() {
        return values;
    }
    public int Count () {
        return values.size();
    }
    public void Add(int value){
        if (!values.contains(value)) values.add(value);
    }
    public void Add(int... values)//если лень
    {
        for (int value:values) {
            if (!this.values.contains(value)) this.values.add(value);
        }
    }

    public void AddNumbersInRange(int startValue,int endValue)
    {
        if (startValue==endValue) Add(startValue);
        else
        {
            for (int i=startValue;i<=endValue;i++)
            {
                if (!this.values.contains(i)) this.values.add(i);
            }
        }
    }


    public void Remove(int value){
        if (values.contains(value))values.remove(value);
    }


    public Set Union(Set set1, Set set2) {
        if (set1.Count() == 0) return set2;
        else if (set2.Count() == 0) return set1;
        Set result = new Set();

        for (int value : set1.values)
            result.Add(value);
        for (int value : set2.values)
            result.Add(value);
        return result;
    }


    public static boolean isPrime(int value)//6k+1 с вики
    {
        if (value<=3)return true;
        else if (value%2==0 || value%3==0)return false;
        int i = 5;
        while (i*i<=value)
        {
            if (value%i==0||value%(i+2)==0) return false;
            i+=6;
        }
        return true;
    }
    public Set GetOnlyCompositeNumbers()
    {
        if (this.Count()==0) return this;
        Set compositeNums = new Set();
        for (int value:this.values)
            if (!isPrime(value)) compositeNums.Add(value);
        return compositeNums;
    }
    public Set GetOnlyPrimeNumbers()
    {
        if (this.Count()==0) return this;
        Set primeNums = new Set();
        for (int value:this.values)
            if (isPrime(value)) primeNums.Add(value);
        return primeNums;
    }
    @Override
    public String toString()
    {
        StringBuffer result = new StringBuffer("");
        for (int value:values)
            result.append(value +", ");
        return result.replace(result.length()-2,result.length()-1,"").toString();
    }
}
