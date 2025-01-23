package utility;

public class IDgenerator {
    private static long id = 0;

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        IDgenerator.id = id;
    }

    public static long getNewId() {
        id++;
        return id;
    }
}
