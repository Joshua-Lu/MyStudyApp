package com.lhf.javacommonlib.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Joshua on 2020/9/14 0:12
 */
class FileTest {
    private static File rootPath = new File("JavaCommonLib\\src\\main\\java\\com\\lhf\\javacommonlib\\file");
    ;

    public static void main(String[] args) {
//        testNewFile();

        // 获取文件的基本信息
//        System.out.println("testGetFileInfo  传的是文件夹路径");
//        testGetFileInfo(rootPath);// 传的是文件夹路径
//        System.out.println("testGetFileInfo  传的是文件相对路径");
//        testGetFileInfo(new File("README.md"));// 传的是文件相对路径
//        System.out.println("testGetFileInfo  传的是文件绝对路径");
//        testGetFileInfo(new File("D:\\lhf\\test.txt"));// 传的是文件绝对路径
//        System.out.println("testGetFileInfo  传的是不存在的路径");
//        testGetFileInfo(new File("D:\\lhf\\notExist.txt"));// 传的是文件绝对路径

        // 创建、删除、遍历文件
//        testCreateFile();
//        testCreateDirs();
//        testDeleteFile();
//        testListFile();
//        testListAllFiles(rootPath);

        // 过滤文件
//        System.out.println("使用FilenameFilter过滤");
//        testListFilesWithFilter(rootPath, filenameFilter);
//        System.out.println("使用FileFilter过滤");
//        testListFilesWithFilter(rootPath, fileFilter);

        // 字节流：InputStream OutputStream
//        testWriteToFile();
//        testAppendToFile();
//        testReadFromFile();
//        testCopyFile();

        // 字符流：Reader Writer
//        testReader();
//        testWriter();

        // Properties：结合了IO操作的集合
//        testPropertiesStore();
//        testPropertiesLoad();

        // 字节缓冲流
//        testBufferedOutputStream();
//        testBufferedInputStream();

        // 字符缓冲流
//        testBufferedWriter();
//        testBufferedReader();

        // 转换流
        testOutputStreamWriter();
        testInputStreamReader();
    }

    /**
     * InputStreamReader 可以指定读文件的解码格式
     */
    private static void testInputStreamReader() {
        InputStreamReader isr = null;
        try {
            // 设置charset为GBK，读取UTF-8格式文件乱码：utf-8 鏍煎紡鏂囦欢鍐呭
//            isr = new InputStreamReader(new FileInputStream(new File(rootPath, "utf8.txt")), "GBK");
            isr = new InputStreamReader(new FileInputStream(new File(rootPath, "utf8.txt")));
            int read;
            while ((read = isr.read()) != -1) {
                System.out.print((char) read);
            }
            System.out.println();

            // IDE默认是UTF-8格式，不指定charset时，读GBK格式文件乱码：GBK ��ʽ�ļ�����
//            isr = new InputStreamReader(new FileInputStream(new File(rootPath, "gbk.txt")));
            isr = new InputStreamReader(new FileInputStream(new File(rootPath, "gbk.txt")), "GBK");
            while ((read = isr.read()) != -1) {
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * OutputStreamWriter 可以指定写文件的编码格式
     */
    private static void testOutputStreamWriter() {
        OutputStreamWriter osw = null;
        try {
            // 不设charset默认为UTF-8，与下面这行等效
            osw = new OutputStreamWriter(new FileOutputStream(new File(rootPath, "utf8.txt")));
//            osw = new OutputStreamWriter(new FileOutputStream(new File(rootPath, "utf8.txt")), StandardCharsets.UTF_8);
            osw.write("utf-8 格式文件内容");
            osw.flush();

            osw = new OutputStreamWriter(new FileOutputStream(new File(rootPath, "gbk.txt")), "GBK");
            osw.write("GBK 格式文件内容");
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字符缓冲输入流 BufferedReader
     * 增加了缓冲区，提高效率
     */
    private static void testBufferedReader() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(rootPath, "buffered-char.txt")));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("FileTest.testBufferedReader: line = [" + line + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字符缓冲输出流 BufferedWriter
     * 增加了缓冲区，提高效率
     */
    private static void testBufferedWriter() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(rootPath, "buffered-char.txt")));
            for (int i = 0; i < 3; i++) {
                bw.write("test BufferedWriter " + i);
                bw.newLine();// 换行
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 字节缓冲输入流 BufferedInputStream
     * 增加了缓冲区，提高效率
     */
    private static void testBufferedInputStream() {
        BufferedInputStream bis = null;
        try {
            FileInputStream fis = new FileInputStream(new File(rootPath, "buffered.txt"));
            bis = new BufferedInputStream(fis);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = bis.read(bytes)) != -1) {
                System.out.println("FileTest.testBufferedInputStream: new String(bytes) = [" + new String(bytes) + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节缓冲输出流 BufferedOutputStream
     * 增加了缓冲区，提高效率
     */
    private static void testBufferedOutputStream() {
        BufferedOutputStream bos = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File(rootPath, "buffered.txt"));
            bos = new BufferedOutputStream(fos);
            bos.write("test BufferedOutputStream".getBytes());
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过Properties的load()方法，将键值对从磁盘中读取到内存
     */
    private static void testPropertiesLoad() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(rootPath, "prop.txt")));
            Set<String> propertyNames = properties.stringPropertyNames();
            for (String key : propertyNames) {
                String value = properties.getProperty(key);
                System.out.println("MyClass.testPropertiesLoad: key = [" + key + "], value = [" + value + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Properties的store()方法，将键值对存到磁盘中
     */
    private static void testPropertiesStore() {
        Properties properties = new Properties();
        properties.setProperty("p1", "v1");
        properties.setProperty("p2", "v2");
        properties.setProperty("p3", "v3");
        properties.setProperty("烽爷", "27");
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String key : propertyNames) {
            String value = properties.getProperty(key);
            System.out.println("MyClass.testProperties: key = [" + key + "], value = [" + value + "]");
        }

        try {
            properties.store(new FileWriter(new File(rootPath, "prop.txt")), "store comments");
            // 使用FileOutputStream，properties中的中文会乱码
//            properties.store(new FileOutputStream(new File(rootPath, "prop2.txt")), "store comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符输出流FileWriter读取文件
     */
    private static void testWriter() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(rootPath, "writer.txt"));

            // 不同于字节输出流直接写到文件，字符输出流会先将字符写到内存并转换成字节
            fw.write(97);
            char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
            fw.write(chars);
            fw.write(chars, 1, 3);
            String s = "JoshuaLu";
            fw.write(s);
            fw.write(s, 6, 2);

            // flush 将内存中的字节写到磁盘中
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close 释放资源前，也会有相当于flush的操作
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 字符输入流FileReader读取文件
     */
    private static void testReader() {
        File file = new File(rootPath, "a.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);

            // 方式一 read() 读取单个字符
//            int read;
//            while ((read = fr.read()) != -1) {
//                System.out.println("FileTest.testReader: read = [" + read + "]");
//                System.out.println("FileTest.testReader: read = [" + (char) read + "]");
//            }

            // 方式二 read(char b[]) 读取字符数组
            char[] chars = new char[10];
            int len;
            while ((len = fr.read(chars)) != -1) {
                System.out.println("FileTest.testReader: chars = [" + Arrays.toString(chars) + "]");
                System.out.println("FileTest.testReader: chars = [" + new String(chars, 0, len) + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件拷贝
     * 本质是输入输出流的读写
     */
    private static void testCopyFile() {
        File file = new File(rootPath, "testWrite.txt");
        System.out.println("FileTest.testCopyFile: file = [" + file + "]");
        File copyFile = new File(rootPath, "testWrite_copy.txt");
        System.out.println("FileTest.testCopyFile: copyFile = [" + copyFile + "]");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(copyFile);
            byte[] bytes = new byte[1024];// 1kb
            int readLength;
            while ((readLength = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, readLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过字节输入流FileInputStream读文件
     */
    private static void testReadFromFile() {
        File file = new File(rootPath, "testWrite.txt");
        System.out.println("FileTest.testReadFromFile: file = [" + file + "]");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            // 方法一：read()读取单个字节
            // 返回读取到的字节，若返回-1，说明读到末尾了
//            int read;
//            while ((read = fis.read()) != -1) {
//                System.out.println("FileTest.testReadFromFile: read = [" + read + "]");
//            }
//            System.out.println("FileTest.testReadFromFile: read = [" + read + "]");

            // 方法二：read(byte b[]) 读取指定大小的字节数组，起到缓冲的作用
            // 返回读取到的字节数组大小，若返回-1，说明读到末尾了
            byte[] bytes = new byte[10];
            int readLength;
            while ((readLength = fis.read(bytes)) != -1) {
                System.out.println("FileTest.testReadFromFile: readLength = [" + readLength + "]");
                System.out.println("FileTest.testReadFromFile: bytes after = [" + Arrays.toString(bytes) + "]");
                System.out.println("FileTest.testReadFromFile: bytes after = [" + new String(bytes, 0, readLength) + "]");
            }
            System.out.println("FileTest.testReadFromFile: readLength = [" + readLength + "]");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过字节输出流FileOutputStream写文件（追加到文件末尾）
     */
    private static void testAppendToFile() {
        FileOutputStream fos = null;
        try {
            // 构造方法中传append参数为true，表示该文件是追加写入
            File file = new File(rootPath, "testAppend.txt");
            System.out.println("FileTest.testAppendToFile: file = [" + file.getAbsolutePath() + "]");

            fos = new FileOutputStream(file, true);
            for (int i = 0; i < 3; i++) {
                fos.write(("text to append " + i).getBytes());
                fos.write("\r\n".getBytes());// 换行符
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();// 流使用完后，必须关闭，释放资源
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过字节输出流FileOutputStream写文件
     */
    private static void testWriteToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(rootPath, "testWrite.txt"));
            fos.write(97);// a
            byte[] bytes = new byte[]{97, 98, 99};
            fos.write(bytes);
            fos.write(bytes, 1, 2);
            byte[] bytes1 = "hello".getBytes();// [104, 101, 108, 108, 111]
            System.out.println("FileTest.testWriteToFile: bytes1 = [" + Arrays.toString(bytes1) + "]");
            fos.write(bytes1);
            byte[] bytes2 = "你好".getBytes();// [-28, -67, -96, -27, -91, -67] UTF-8编码，一个中文=3个字节
            System.out.println("FileTest.testWriteToFile: bytes2 = [" + Arrays.toString(bytes2) + "]");
            fos.write(bytes2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static FilenameFilter filenameFilter = new FilenameFilter() {
        /**
         * 可以同时对文件夹和文件名进行过滤
         *
         * @param dir  【文件或文件夹】所在的文件夹
         * @param name 【文件或文件夹】的名称
         * @return
         */
        @Override
        public boolean accept(File dir, String name) {
            // 为了遍历所有的子文件夹
            if (new File(dir, name).isDirectory()) {
                return true;
            }
            return dir.toString().endsWith("CIBN认证不过")
                    && name.endsWith(".log");
        }
    };

    static FileFilter fileFilter = new FileFilter() {
        /**
         * 对完整路径进行过滤
         * @param pathname 文件的绝对路径
         * @return
         */
        @Override
        public boolean accept(File pathname) {
            // 为了遍历所有的子文件夹
            if (pathname.isDirectory()) {
                return true;
            }
            return pathname.toString().endsWith(".log");
        }
    };

    /**
     * 使用FileFilter过滤
     *
     * @param dir
     * @param fileFilter
     */
    private static void testListFilesWithFilter(File dir, FileFilter fileFilter) {
        File[] files = dir.listFiles(fileFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                testListFilesWithFilter(file, fileFilter);
            } else {
                System.out.println(file);
            }
        }
    }

    /**
     * 使用FilenameFilter过滤
     *
     * @param dir
     * @param filenameFilter
     */
    private static void testListFilesWithFilter(File dir, FilenameFilter filenameFilter) {
        File[] files = dir.listFiles(filenameFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                testListFilesWithFilter(file, filenameFilter);
            } else {
                System.out.println(file);
            }
        }
    }

    private static void testFilter(File file) {
        boolean accept = false;
//        accept = isAcceptFileFilter(file);
        accept = isAcceptFilenameFilter(file);
        if (accept) {
            System.out.println(file);
        }
    }

    private static boolean isAcceptFilenameFilter(File file) {
        return filenameFilter.accept(file, file.getName());
    }

    private static boolean isAcceptFileFilter(File file) {
        return fileFilter.accept(file);
    }

    /**
     * 递归遍历文件夹
     *
     * @param dir
     */
    private static void testListAllFiles(File dir) {
        System.out.println(dir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                testListAllFiles(file);
            } else {
                System.out.println(file);
            }
        }
    }

    /**
     * 遍历文件夹
     */
    private static void testListFile() {
        String[] list = rootPath.list();
        System.out.println("FileTest.testListFile: list = [" + Arrays.toString(list) + "]");
        File[] files = rootPath.listFiles();
        System.out.println("FileTest.testListFile: files = [" + Arrays.toString(files) + "]");
    }

    /**
     * 删除文件或文件夹
     */
    private static void testDeleteFile() {
        File file = new File(rootPath, "a.txt");
        System.out.println("FileTest.testDeleteFile: file.delete() = [" + file.delete() + "]");

        File dir = new File(rootPath, "dir");
        System.out.println("FileTest.testDeleteFile: dir.delete() = [" + dir.delete() + "]");

        // delete() 删除的是文件夹的话，只能删除空的文件夹
        File dirs = new File(rootPath, "dir1");
        System.out.println("FileTest.testDeleteFile: dirs.delete() = [" + dirs.delete() + "]");
    }

    /**
     * 创建文件夹
     */
    private static void testCreateDirs() {
        File dir = new File(rootPath, "dir");
        // mkdir() 只能创建单级文件夹
        System.out.println("FileTest.testCreateDirs: dir.mkdir() = [" + dir.mkdir() + "]");

        File dirs = new File(rootPath, "dir1\\dir2");
        System.out.println("FileTest.testCreateDirs: dirs.mkdir() = [" + dirs.mkdir() + "]");
        // mkdirs() 可以创建多级文件夹
        System.out.println("FileTest.testCreateDirs: dirs.mkdirs() = [" + dirs.mkdirs() + "]");
    }

    /**
     * 创建文件
     */
    private static void testCreateFile() {
        try {
            File file = new File(rootPath, "a.txt");
            System.out.println("FileTest.testCreateFile: file.getAbsolutePath() = [" + file.getAbsolutePath() + "]");
            if (!file.getParentFile().exists()) {// 判断文件路径是否存在
                boolean mkdirs = file.getParentFile().mkdirs();// 路径不存在，先创建路径
                System.out.println("FileTest.testCreateFile: mkdirs = [" + mkdirs + "]");
            }
            boolean b = file.createNewFile();// 创建文件是否成功
            System.out.println("FileTest.testCreatFile: b = [" + b + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取文件信息
     *
     * @param file
     */
    private static void testGetFileInfo(File file) {
        System.out.println("FileTest.testGetFileInfo: file = [" + file + "]");// 相当于 getPath
        // getAbsolutePath() 绝对路径，完整路径
        System.out.println("FileTest.testGetFileInfo: file.getAbsolutePath() = [" + file.getAbsolutePath() + "]");
        // getPath() 构造方法中传递的路径
        System.out.println("FileTest.testGetFileInfo: file.getPath() = [" + file.getPath() + "]");
        // getName() 路径的结尾，文件夹或文件的名称
        System.out.println("FileTest.testGetFileInfo: file.getName() = [" + file.getName() + "]");
        // length() 文件大小（文件夹没有大小，返回0）
        System.out.println("FileTest.testGetFileInfo: file.length() = [" + file.length() + "]");
        // exists() 文件或文件夹是否存在
        System.out.println("FileTest.testGetFileInfo: file = [" + file.exists() + "]");
        // isDirectory() 是否是文件夹
        System.out.println("FileTest.testGetFileInfo: file.isDirectory() = [" + file.isDirectory() + "]");
        // isFile() 是否是文件
        System.out.println("FileTest.testGetFileInfo: file.isFile() = [" + file.isFile() + "]");
        System.out.println("=========================================");
    }

    /**
     * 创建文件对象
     */
    private static void testNewFile() {
        File file = new File("D:\\lhf");// 使用绝对路径
        System.out.println("FileTest.testNewFile: file = [" + file.getAbsolutePath() + "]");
        File file3 = new File("lhf");// 使用相对路径，相对项目的跟路径D:\GitHub\MyStudyApp
        System.out.println("FileTest.testNewFile: file3 = [" + file3.getAbsolutePath() + "]");
        File file1 = new File("D:", "lhf");
        System.out.println("FileTest.testNewFile: file1 = [" + file1 + "]");
        File file2 = new File(file, "test.txt");
        System.out.println("FileTest.testNewFile: file2 = [" + file2 + "]");
    }
}
