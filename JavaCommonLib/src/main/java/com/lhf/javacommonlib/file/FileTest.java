package com.lhf.javacommonlib.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Joshua on 2020/9/14 0:12
 */
class FileTest {
    private static File rootPath = new File("D:\\lhf");
    ;

    public static void main(String[] args) {
//        testNewFile();

//        System.out.println("testGetFileInfo  传的是文件夹路径");
//        testGetFileInfo(rootPath);// 传的是文件夹路径
//        System.out.println("testGetFileInfo  传的是文件相对路径");
//        testGetFileInfo(new File("README.md"));// 传的是文件相对路径
//        System.out.println("testGetFileInfo  传的是文件绝对路径");
//        testGetFileInfo(new File("D:\\lhf\\test.txt"));// 传的是文件绝对路径
//        System.out.println("testGetFileInfo  传的是不存在的路径");
//        testGetFileInfo(new File("D:\\lhf\\notExist.txt"));// 传的是文件绝对路径

//        testCreateFile();
//        testCreateDirs();
//        testDeleteFile();
        testListFile();
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
            boolean b = file.createNewFile();// 创建文件是否成功
            System.out.println("FileTest.testCreatFile: b = [" + b + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
