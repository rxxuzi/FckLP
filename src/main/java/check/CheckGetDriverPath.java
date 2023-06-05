package check;

import java.io.File;

public class CheckGetDriverPath {
    public static void main(String[] args) {
        String path  = "./opt";
//        String p2  = p1;

        System.out.println("path is" + driverPath(path));
    }
    private static String driverPath(String p){
        var f = new File(p);
        var dir = f.listFiles();
        String DirPath = null;
        String filePath = "";
        boolean dp = false;
        boolean fn = false;

        for (int i = 0; i < dir.length; i++) {
            String ext = dir[i].getName();
            if (ext.endsWith(".exe")) {
                filePath = dir[i].getPath();
                fn = true; 
                break;
            }
            if (dir[i].isDirectory()) {
                DirPath = dir[i].getPath();
                dp = true;
            }
        }
        
        if(fn){
            return filePath; 
        }else if(dp){
            return driverPath(DirPath);
        }else{
            return "null";
        }
    }
}
