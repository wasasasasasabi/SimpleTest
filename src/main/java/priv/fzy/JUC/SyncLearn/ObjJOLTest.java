package priv.fzy.JUC.SyncLearn;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * JOL:java Object LayOut 对象在内存中的视图
 */
public class ObjJOLTest {
    private int col;
    public static void main(String[] args) {
        ObjJOLTest o = new ObjJOLTest();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized(o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
