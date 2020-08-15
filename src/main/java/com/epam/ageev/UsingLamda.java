package com.epam.ageev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsingLamda {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        List<Integer> list1 = new ArrayList<>();
        fillArray(arr1);
        fillList(list1);


        arr1 = Arrays.stream(arr1).map(a -> a * 2).toArray();
        list1 = list1.stream().map(a -> a * 2).collect(Collectors.toList());

        arr1 = Arrays.stream(arr1).map(a -> 3).toArray();
        arr1 = Arrays.stream(arr1).map(a -> a + 1).toArray();

        //filter

        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();

        fillArray(arr2);
        fillList(list2);

        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
        list2 = list2.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());

        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList();

        fillList(list3);
        fillArray(arr3);

        int sum1 = Arrays.stream(arr3).reduce((accum, currentElem) -> accum + currentElem).getAsInt();
        int sum2 = list3.stream().reduce(1, (acc, b) -> acc * b);

        int[] arr4 = new int[10];
        List<Integer> list4 = new ArrayList();

        fillList(list4);
        fillArray(arr4);
        System.out.println(list4);
        list4 = list4.stream().filter(a -> a % 2 != 0).map(a -> a * 2).collect(Collectors.toList());

        System.out.println(list4);
    }

    private static void fillArray(int[] arr) {
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
    }

    private static void fillList(List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }
}
