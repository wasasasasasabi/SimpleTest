package priv.fzy.JUC;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class ConcurrentHashMapTest {

    /**
     * hashMap并发 引起死循环 导致CPU利用率飙升
     * https://www.baidu.com/link?url=L4t4tiMOwRBJkmlfHx7hlD_Kdj7DimL0EFZuFNDikIdiFwScMr_pngQo0OG4Fgzpc_yjZ5zkun4qoKAxJAeg-91T8QySJ2QkGv8Buzoa-Dm&wd=&eqid=dc5f5e8c0009ccbf0000000661e2be46
     */
    private static Map<String, String> hashMap = new HashMap();

    private static Map<String,String> hashTable = new Hashtable<>();
    @Test
    public void test01() {
        for (int i = 0; i < 80; i++) {
            hashMap.put(i+"",i+"");
            hashTable.put(i+"",i+"");
        }

    }
}
