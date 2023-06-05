package check;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckJdk2 {
    public static String  checkJdkVersion(int type){
        //check jdk version
        Path path = Paths.get("");

        String[] cjv = {
                //check jdk version
                "CheckJdk  version is " + System.getProperty("java.version")
                //check jdk vendor
                ,"CheckJdk vendor is " + System.getProperty("java.vendor")
                //check jdk home
                ,"CheckJdk home is " + System.getProperty("java.home")
                //check os name
                ,"os name is " + System.getProperty("os.name")
                //check os version
                ,"os version is " + System.getProperty("os.version")
                //check os arch
                ,"os arch is " + System.getProperty("os.arch")
                //check user home
                ,"user home is " + System.getProperty("user.home")
                //check user dir
                ,"user dir is " + System.getProperty("user.dir")
                //check username
                ,"user name is " + System.getProperty("user.name")
                //check user home
                ,"user home is " + System.getProperty("user.home")
                //check this dir tree
                ,"this dir is " + path.toAbsolutePath()
                //jvm version
                ,"jvm version is " + System.getProperty("java.vm.version")
                //jvm vendor
                ,"jvm vendor is " + System.getProperty("java.vm.vendor")
                //jvm home
                ,"jvm home is " + System.getProperty("java.vm.home")
                //jvm info
                ,"jvm info is " + System.getProperty("java.vm.info")
                //jvm class path
                ,"jvm class path is " + System.getProperty("java.class.path")
                //java spec version
                ,"java spec version is " + System.getProperty("java.specification.version")
        };

        return cjv[type];
    }
}
