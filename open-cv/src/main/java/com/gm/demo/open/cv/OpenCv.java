package com.gm.demo.open.cv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

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

    public static final String name = "/lbpcascades"+"/lbpcascade_frontalface_improved"+".xml";
    public static final String path = OpenCv.class.getResource(name).getPath();
    public static CascadeClassifier cv;
    static {
        // 初始化设备
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cv = new CascadeClassifier(path.substring(1));
    }


    /**
     * 人脸识别案例
     * @param args
     */
    public static void main(String[] args) {
        // 读取图片
        List<Integer[]> positions = positions("C:\\Users\\xiaok\\Desktop\\实验室\\face.png");
        positions.forEach(x -> System.out.println(Arrays.toString(x)));
    }





    public static List<Integer[]> positions(String img){
        // 存储位置
        List<Integer[]> positions = new ArrayList();
        // 读取图片
        Mat image = Imgcodecs.imread(img);
        MatOfRect faces = new MatOfRect();

        Size minSize = new Size(250, 250);
        Size maxSize = new Size(800, 800);

//        cv.detectMultiScale(image, faces, 1.1, 4, 0,  minSize, maxSize);
        cv.detectMultiScale(image, faces);
        System.out.println(String.format("Detected %s faces", faces.toArray().length));
        // ------------------------------------------
        for (Rect rect : faces.toArray()) {
            Integer[] position = new Integer[4];
            Imgproc.rectangle(image,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            position[0] = rect.x;
            position[1] = rect.y;
            position[2] = rect.width;
            position[3] = rect.height;
            positions.add(position);
            // 画出效果
            writeEffect(img, image);
        }
        // ------------------------------------------
        return positions;
    }

    public static void writeEffect(String img, Mat image) {
        String name = img.substring(img.lastIndexOf("\\") + 1);
        String filename = "C:\\Users\\xiaok\\Desktop\\实验室\\效果\\" + name;
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}
