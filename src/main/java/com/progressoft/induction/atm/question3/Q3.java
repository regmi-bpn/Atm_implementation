package com.progressoft.induction.atm.question3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q3 {

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11};
        Result result = findMaxSumSubsequence(arr);
        System.out.println("The max sum of the subsequence is " + result.maxSum);
        System.out.println(Arrays.toString(result.elements.stream().mapToInt(Integer::intValue).toArray()));
    }

    private static Result findMaxSumSubsequence(int[] arr) {
        return findMax(0, arr, 0, null);
    }

    private static class Result {
        int maxSum;
        List<Integer> elements;

        public Result(int maxSum, List<Integer> elements) {
            this.maxSum = maxSum;
            this.elements = elements;
        }
    }

    private static Result findMax(int index, int[] arr, int sum, Integer lastAdded) {
        if (index == arr.length) return new Result(sum, new ArrayList<>());
        int elem = arr[index];

        Result withElem = new Result(0, new ArrayList<>());
        if (lastAdded == null || elem > lastAdded) {
            withElem = findMax(index + 1, arr, sum + elem, elem);
        }

        Result withoutElem = findMax(index + 1, arr, sum, lastAdded);

        if (withElem.maxSum > withoutElem.maxSum) {
            withElem.elements.add(0, elem);
            return withElem;
        }
        return withoutElem;
    }


}
