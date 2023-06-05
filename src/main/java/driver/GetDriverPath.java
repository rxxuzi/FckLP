package driver;

import java.io.File;

public class GetDriverPath {
    public static void main(String[] args) {
        String p1  = "./opt";
        String p2  = p1;
        String path = "0";
        File opt = new File(p1);
        var dir = opt.listFiles();

        if(dir != null){
            for (File value : dir) {
                if (value.isDirectory()) {
                    p2 = value.getPath();
                }
            }
        }

        var folder = new File(p2);
        var dir2 = folder.listFiles();

        if(dir2 != null) {
            for (File file : dir2) {
                String ext = file.getName();
                if (ext.endsWith(".exe")) {
                    path = file.getPath();
                }
            }
        }
        System.out.println(path);
    }
}
