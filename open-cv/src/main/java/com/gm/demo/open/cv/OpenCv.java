package com.gm.demo.open.cv;

import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OpenCv.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class OpenCv {

    public static final String filename = "haarcascade_frontalface_alt_tree.xml";
    public static final String name = "/haarcascades_cuda/" + filename;
    public static final String path = OpenCv.class.getResource(name).getPath();
    public static CascadeClassifier cv;

    static {
        // 初始化设备
        environmentalInspection();
    }


    @Test
    public void testFace() {
        String img = "C:\\Users\\xiaok\\Desktop\\laboratory\\await\\A.jpg";
        List<Integer[]> positions = positions(img, What.RECT);
        positions.forEach(x -> System.out.println(Arrays.toString(x)));
    }


    public static List<Integer[]> contourId(List<Integer[]> positions, Mat img) {
        List<MatOfPoint> contours = findContours(img);
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint mop = contours.get(i);
            Rect rect = new Rect(new Point(595,556), mop.size());
            drawEffect(img, rect);
        }
        return positions;
    }
    public static List<Integer[]> contourIdMax(List<Integer[]> positions, Mat img) {
        List<MatOfPoint> contours = findContours(img);
        int maxSizeIndex = -1;
        int maxSize = Integer.MIN_VALUE;
        //找出轮廓面积最大的轮廓
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint mop = contours.get(i);
            int size = mop.width() * mop.height();
            if (size > maxSize) {
                maxSize = size;
                maxSizeIndex = i;
            }
        }
        if (maxSizeIndex != -1) {
            int minX=Integer.MAX_VALUE;
            int minY=Integer.MAX_VALUE;
            int maxX=Integer.MIN_VALUE;
            int maxY=Integer.MIN_VALUE;
            Integer[] position = new Integer[4];
            String dump = contours.get(maxSizeIndex).dump();
            String[] split = dump.substring(1,dump.length()-1).split(";");
            for (String s : split) {
                String[] l = s.split(",");
                int x = Integer.valueOf(l[0].trim());
                int y = Integer.valueOf(l[1].trim());
                minX = Math.min(x, minX);
                maxX = Math.max(x, maxX);
                minY = Math.min(y, minY);
                maxY = Math.max(y, maxY);
            }
            int width=maxX-minX;
            int height=maxY-minY;
            position[0] = minX;
            position[1] = minY;
            position[2] = width;
            position[3] = height;
            positions.add(position);
        }
        return positions;
    }


    public static List<MatOfPoint> findContours(Mat img) {
        // 边缘检测
        Imgproc.Canny(img, img, 20, 60, 3, false);
        // 膨胀边缘
        Imgproc.dilate(img, img, new Mat(), new Point(-1,-1), 3, 1, new Scalar(1));
        // 检测边缘
        Mat mat = new Mat();
        List<MatOfPoint> contours = new ArrayList();
        Imgproc.findContours(img, contours, mat, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println(String.format("There are %s cards", contours.size()));
        return contours;
    }


    public static List<Integer[]> positions(String img, What what) {
        // 存储位置
        List<Integer[]> positions = new ArrayList();
        if (!new File(img).exists()) {
            System.out.println("文件不存在!!!");
            return positions;
        }
        // 读取图片
        Mat image = Imgcodecs.imread(img, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        switch (what) {
            case FACE:
                faceId(positions, image);
                break;
            case RECT:
                contourId(positions, image);
                break;
            default:
                ;
                break;
        }
        // 返回位置
        return positions;
    }

    public static void faceId(List<Integer[]> positions, Mat image) {
        // 人脸识别
        MatOfRect faces = new MatOfRect();
        cv.detectMultiScale(image, faces, 1.1f, 1);
        System.out.println(String.format("There are %s cards", faces.toArray().length));
        // 记录位置
        for (Rect rect : faces.toArray()) {
            Integer[] position = new Integer[4];
            position[0] = rect.x;
            position[1] = rect.y;
            position[2] = rect.width;
            position[3] = rect.height;
            positions.add(position);
            // 画出效果
            drawEffect(image, rect);
        }
    }

    /**
     * 画出矩形效果
     *
     * @param image
     * @param rect
     */
    public static void drawEffect(Mat image, Rect rect) {
        Imgproc.rectangle(image,
                new Point(rect.x, rect.y),
                new Point(rect.x + rect.width, rect.y + rect.height),
                new Scalar(0, 255, 0));
        Imgcodecs.imwrite("effect.png", image);
    }

    /**
     * 图片预处理
     *
     * @param image 图片对象
     * @param sizeW 像素点加粗宽度
     * @param sizeH 像素点加粗高度
     * @return 处理后的对象
     */
    private static Mat preHandle(Mat image, int sizeW, int sizeH) {
        Mat desc = new Mat();
        //转为灰度
        Imgproc.cvtColor(image, desc, Imgproc.COLOR_BGR2GRAY);
        //图像反向二值化,去除噪点
        Imgproc.threshold(desc, desc, 187, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        //像素点加粗到sizeW*sizeH，让矩形闭合
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(sizeW, sizeH));
        Imgproc.morphologyEx(desc, desc, Imgproc.MORPH_GRADIENT, element);
        //去除边框的线条干扰
        int cutNum = 23;
        Mat subMat = desc.submat(cutNum, desc.rows() - cutNum, cutNum, desc.cols() - cutNum);
        //写入临时文件，读取后再删除，避免直接添加边框图片内容又恢复的问题
        Imgcodecs.imwrite("temp.jpg", subMat);
        Mat temp = Imgcodecs.imread("temp.jpg");
        try {
            Files.delete(Paths.get("temp.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //减去的边缘再填充回来，避免生成的坐标变位
        Core.copyMakeBorder(temp, temp, cutNum, cutNum, cutNum, cutNum, Core.BORDER_CONSTANT, new Scalar(0, 0, 0));
        return temp;
    }

    /**
     * openCv 环境检查
     */
    private static void environmentalInspection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cv = new CascadeClassifier(path.substring(1));
        System.out.println("openCv = " + Core.VERSION);
    }
}
