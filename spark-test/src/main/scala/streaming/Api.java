package streaming;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * @author day.na
 * @date 2019/01/17
 */
public class Api {
    public static void main(String[] args) {
        SparkSession spark = initBuilder().getOrCreate();
        JavaStreamingContext streaming = new JavaStreamingContext(
                new StreamingContext(spark.sparkContext(), Durations.seconds(1)));
        streaming.sparkContext().setLogLevel("ERROR");

    }


    /**
     * 初始化Spark Builder
     * @return                      Builder
     */
    private static SparkSession.Builder initBuilder() {
        SparkSession.Builder sparkBuilder = SparkSession.builder()
                .appName("api-analysis");
        sparkBuilder.master("local[2]");
        return sparkBuilder;
    }
}
