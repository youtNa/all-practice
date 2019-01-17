package org;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 用于mock测试，mock【getBuilder】
 */
public class Handle {
    public static void main(String[] args){
        SparkSession.Builder builder = SparkSession.builder().master("local[2]");
        SparkSession spark = builder.getOrCreate();
        loadJson(spark).show();
//        spark.read().json("src/test/resources/people.json").show();
    }

    private static SparkSession.Builder getBuilder(){
        return SparkSession.builder();
    }

    public static String callTest(){
        return test();
    }

    private static String test(){
        return "12";
    }

    private static Dataset<Row> loadJson(SparkSession spark){
        return spark.read().json("src/test/resources/people.json");
    }
}
