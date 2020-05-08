package _base.lambda.stream._operator.statistics;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> listInt = Arrays.asList(new Integer[]{2,7,3});
        IntSummaryStatistics collect = listInt.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println(collect);

    }
}
