package org;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Handle.class)
@PowerMockIgnore({"org.xml.*","javax.xml.*","javax.security.*","org.apache.hadoop.security.UserGroupInformation"})
public class HandleTest {
    @Test
    public void testMain() throws Exception {
        PowerMockito.mockStatic(Handle.class);
        PowerMockito.when(Handle.class,"main", Mockito.any()).thenCallRealMethod();
        PowerMockito.when(Handle.class,"loadJson",Mockito.any()).then(invocation -> {
            SparkSession spark = (SparkSession) invocation.getArguments()[0];
            return loadJson(spark);
        });
//
        Handle.main("1 2".split(" "));

//        PowerMockito.spy(Handle.class);
//        PowerMockito.doReturn("34").when(Handle.class,"test");
//        String d = Handle.callTest();
//        System.out.println(d);

    }

    private static SparkSession.Builder getBuilder(){
        return SparkSession.builder().master("local[2]");
    }

    private static Dataset<Row> loadJson(SparkSession spark){
        return spark.read().json("src/test/resources/people2.json");
    }
}