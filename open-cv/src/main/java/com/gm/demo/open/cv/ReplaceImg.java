package com.gm.demo.open.cv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * RepImg.
 *
 * @author ：Jason.lee
 * @version : 1.0
 * @date ：2020-3-23 12:33
 * @description : RepImg
 * @modified : -
 */
public class ReplaceImg {
    // 该值越大, 图片越小, 默认15
    public static double ratio = 8;
    // 该值越大, 往左边移的越多, 默认10
    public static int offset_left = 30;
    // 该值越大, 往上边移的越多, 默认5
    public static int offset_top = 10;

    public final static String root = "C:\\Users\\xiaok\\Desktop\\laboratory\\";

    public static void main(String[] args) throws IOException {
        File sourceFile = new File(root + "B.jpg");
        File faceFile = new File(root + "head\\a_source.jpg");
        eachReplace(sourceFile, faceFile, root+"repository\\B.jpg");
    }

    public static void eachReplace(File sourceFile, File faceFile, String path) throws IOException {
        // 原图
        BufferedImage source = ImageIO.read(sourceFile);

        // 头像原图
        BufferedImage sourceFace = ImageIO.read(faceFile);

        // 人脸识别
        List<Integer[]> faces = OpenCv.positions(sourceFile.getPath());

        // 压缩头像
        BufferedImage face = size(source, sourceFace);

        // 重新构图
        recompose(source, face, faces);

        // 保存新图
        if (faces.size() > 0)
            write(source, sourceFile.getName(), path);
    }

    public static void write(BufferedImage source, String filename, String path) throws IOException {
        int index = filename.lastIndexOf(".");
        String name = filename.substring(0, index);
        String suffix = filename.substring(index + 1);
        File file = new File(path);
        ImageIO.write(source, suffix, file);
    }


    public static void recompose(BufferedImage source, BufferedImage face, List<Integer[]> faces) throws IOException {
        // 将face写到source中指定的位置上去
        Graphics graphics = source.getGraphics();
        faces.forEach((Integer[] loc) -> {
            if(loc[0] > 100) {
                Integer x = loc[0] - offset_left;
                Integer y = loc[1] - offset_top;
                graphics.drawImage(face, x, y, null);
            }
        });
    }

    public static BufferedImage size(BufferedImage source, BufferedImage sourceFace) {
        int widthRaw = source.getWidth();
        int size = (int) (widthRaw / ratio);
        // 需要根据 指定大小压缩
        return zoomInImg(sourceFace, size, size);
    }

    private static BufferedImage zoomInImg(BufferedImage source, int maxWidth, int maxHeight) {
        BufferedImage newImage = new BufferedImage(maxWidth, maxHeight, source.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(source, 0, 0, maxWidth, maxHeight, null);
        g.dispose();
        return newImage;
    }


    /**
     * 将Mat类型转化成BufferedImage类型
     *
     * @param fileExtension 文件扩展名
     * @return
     */
    public static BufferedImage Mat2Img(Mat mat, String fileExtension) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, mat, mob);
        // convert the "matrix of bytes" into a byte array
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }

    /**
     * 将BufferedImage类型转换成Mat类型
     *
     * @param bfImg
     * @param imgType bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
     * @param matType 转换成mat的type 如 CvType.CV_8UC3
     * @return
     */
    public static Mat Img2Mat(BufferedImage bfImg, int imgType, int matType) {
        BufferedImage original = bfImg;
        int iType = imgType;
        int mType = matType;

        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }

        if (original.getType() != iType) {
            BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), iType);

            Graphics2D g = image.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(original, 0, 0, null);
            } finally {
                g.dispose();
            }
        }

        byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
        Mat mat = Mat.eye(original.getHeight(), original.getWidth(), mType);
        mat.put(0, 0, pixels);

        return mat;
    }
}
