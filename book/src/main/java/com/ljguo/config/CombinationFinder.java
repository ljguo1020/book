package com.ljguo.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationFinder {

    public static List<List<Double>> findCombinations(double[] arr, double target) {
        List<List<Double>> result = new ArrayList<>();
        findCombinationsRecursive(arr, target, new ArrayList<>(), result, 0);
        return result;
    }

    private static void findCombinationsRecursive(double[] arr, double target, List<Double> current, List<List<Double>> result, int startIndex) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = startIndex; i < arr.length; i++) {
            current.add(arr[i]);
            findCombinationsRecursive(arr, target - arr[i], current, result, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static double calculateSum(Double[] numbers) {
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static Double[] convertToArray(List<Double> list) {
        Double[] array = new Double[list.size()];
        array = list.toArray(array);
        return array;
    }

    public static void main(String[] args) {

        System.out.println("开始计算！");

//        double[] arr = {
//                21237.60, 15228.00, 470, 940, 148.52, 5922, 5715.2, 10904, 7444.8,
//                1974, 5053.44, 5640, 11280, 5715.2, 5452, 3948, 8460, 2526.72, 8460,
//                11280, 4963.2, 7444.8, 5640, 11280, 5715.2, 1974, 11280, 5053.44
//        };

        double[] arr = {
                148.52, 5922, 5715.2, 10904, 7444.8,
                5053.44, 5640, 5715.2, 5452, 3948, 8460, 2526.72, 8460,
                11280, 4963.2, 7444.8, 5640, 11280, 5715.2, 1974, 11280, 5053.44
        };

//        double[] arr = {
//                11280, 5715.2, 148.52, 5922, 10904, 7444.8,
//                1974, 5053.44, 5640, 5715.2, 5452, 3948, 8460, 2526.72, 8460,
//                11280, 4963.2, 7444.8, 5640, 11280, 5715.2, 1974, 11280, 5053.44
//        };

        double target = 107143.08;

//        double[] arr = {1,2,3,4,5,5,6,7,8,9,10,9};
//
//        double target = 10;

        List<List<Double>> combinations = findCombinations(arr, target);

        Set<List<Double>> uniqueCombinations = new HashSet<>(combinations);
        combinations = new ArrayList<>(uniqueCombinations);
        for (List<Double> combination : combinations) {
//            Double[] array = convertToArray(combination);
//
//            double v = calculateSum(array);

//            if (!(combination.contains(15228.00) && combination.contains(21237.6))) {
                System.out.println(combination);
//            }


        }

        System.out.println("计算完成！");

        System.out.println(combinations.size());
    }
}
