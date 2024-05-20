package info.utils;

public class Utilidades {

    public static void waitSeconds( int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
