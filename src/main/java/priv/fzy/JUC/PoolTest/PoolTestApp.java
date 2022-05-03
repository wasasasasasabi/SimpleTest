package priv.fzy.JUC.PoolTest;

public class PoolTestApp {

    public static void main(String[] args) {
        SimpleHttpServer.setPort(8089);
        try {
            SimpleHttpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
