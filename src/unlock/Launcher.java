package unlock;

public class Launcher {
   
    /** Default constructor for avoiding documentation warning */
    private Launcher() {}

    public static void main(String[] args) {

        UnlockPlay unlock = new UnlockPlay();
        unlock.setUndecorated(true);
        unlock.setVisible(true);
    }
}