package util;

@SuppressWarnings("javadoc")
public class OutputDisplay {

    public void showText(final String someText) {
        final long time = System.currentTimeMillis();
        System.out.println("time: " + time + " - " + someText);
    }
}
