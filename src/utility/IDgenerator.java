package utility;

public class IDgenerator {
    private static long id = 0;

    public static long getNewId() {
        id++;
        return id;
    }
}
