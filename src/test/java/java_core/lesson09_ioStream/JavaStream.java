package java_core.lesson09_ioStream;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class JavaStream {

    public static void main(String[] args) throws IOException {
        JavaStream javaStream = new JavaStream();
       // javaStream.readDataFile();

        String dir = System.getProperty("user.dir");
        System.out.println(dir);

        // / / / / / /
        String dirPath = dir + File.separator +  "src/test/java/java_core/lession09_ioStream/folder1/folder2/fileName.csv";
        System.out.println(dirPath);
        File file = new File(dirPath);
        file.createNewFile();



        System.out.println("Exist: " + file.exists());
        System.out.println("Directory: " + file.isDirectory());
        System.out.println(file.getName());

        //JavaStream javaStream = new JavaStream();
        //javaStream.readDataFile();

        //javaStream.getAllFilesInFolder(new File("/Users/bgduy/workspace/auto/codegym/selenium-basic/src/test/java/java_core"));
        //javaStream.basicFunc();

        //javaStream.createDirectory();
    }

    File createNewFile() {
        File file = null;
        try {

            // Duong dan
            String filePath = System.getProperty("user.dir") + File.separator + "src/test/java/java_core/newFile" + System.currentTimeMillis() + ".txt";
            // File moi
            file = new File(filePath);

            // createNewFile -> Tao file trong folder
            if (file.createNewFile()) {
                System.out.println("File is created!: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    File writeToFile() {
        File file = createNewFile();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("You are an automation engineer!");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

    void readDataFile() {
        String filePath = "/Users/vincent/Work/workspace/source/testek/selenium-basic/src/test/java/java_core/lession09_ioStream/JavaStream.java";
        try {
            // File name -> Reader
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Line : " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void basicFunc() throws IOException {
        File file = createNewFile();
        File newFile = createNewFile();
        // Rename
        file.renameTo(newFile);


        // Copy
        System.out.println("Copy file in Java");
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new FileInputStream(writeToFile());
            Thread.sleep(1000);
            outStream = new FileOutputStream(createNewFile());

            int length;
            byte[] buffer = new byte[2048];     // Doc theo buffer

            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            System.out.println("File is copied successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            inStream.close();
            outStream.close();
        }

        // Append

    }

    public void getAllFilesInFolder(File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            System.out.println(fileOrDir.getAbsolutePath());

            File[] children = fileOrDir.listFiles();
            if (children == null) {
                return;
            }
            // sắp xếp file theo thứ tự tăng dần
            Arrays.sort(children, Comparator.comparing(File::getName));
            for (File each : children) {
                getAllFilesInFolder(each);
            }
        } else {
            System.out.println(fileOrDir.getAbsolutePath());
        }
    }

    void createDirectory() {
        String dirName = System.getProperty("user.dir") + File.separator + "src/test/java/java_core/folder01/sub_folder01";
        File d = new File(dirName);

        // Create directory now.
        d.mkdirs();
    }

}
