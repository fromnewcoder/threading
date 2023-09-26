package org.example.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("joey");
        list.add("jim");
        list.add("tina");

        ListIterator<String> stringListIterator = list.listIterator();
        stringListIterator.forEachRemaining(System.out::println);

        Iterator<String> iterator = list.iterator();



        while (stringListIterator.hasPrevious()){
            System.out.println(stringListIterator.previous());
        }

        while (iterator.hasNext()){
            System.out.println(iterator.next());
            //Exception in thread "main" java.util.ConcurrentModificationException
            //list.remove(0);
        }
        while (iterator.hasNext()){
            //no element printed as the iterator has gone to the end last time
            System.out.println(iterator.next());
        }

        Iterator<String> iterator2 = list.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
            //cam remove successfully from iterator
            iterator2.remove();
        }
        System.out.println(list.size());



    }
}
