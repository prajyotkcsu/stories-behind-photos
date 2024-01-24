package org.example;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.common.functions.FlatMapFunction;

public class WordCount {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env
                = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Integer> amounts = env.fromElements(1, 29, 40, 50);
        System.out.println(amounts.count());
    }
}