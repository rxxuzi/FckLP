package check;

public class CheckJdk {
    public static void main(String[] args) {
        //check jdk version
        System.out.println("CheckJdk  version is " + System.getProperty("java.version"));
        //check jdk vendor
        System.out.println("CheckJdk vendor is " + System.getProperty("java.vendor"));
        //check jdk home
        System.out.println("CheckJdk home is " + System.getProperty("java.home"));
        //check os name
        System.out.println("os name is " + System.getProperty("os.name"));
        //check os version
        System.out.println("os version is " + System.getProperty("os.version"));
        //check os arch
        System.out.println("os arch is " + System.getProperty("os.arch"));
        //check user home
        System.out.println("user home is " + System.getProperty("user.home"));
        //check user dir
        System.out.println("user dir is " + System.getProperty("user.dir"));
        //check username
        System.out.println("user name is " + System.getProperty("user.name"));
        //check user home
        System.out.println("user home is " + System.getProperty("user.home"));
        //is os windows
        System.out.println("is Win" + System.getProperty("is.windows"));
        //java version

    }
}
