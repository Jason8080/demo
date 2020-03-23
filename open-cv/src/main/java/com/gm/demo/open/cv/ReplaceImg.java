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

    public final static String root = "C:\\Users\\xiaok\\Desktop\\实验室\\";

    public static void main(String[] args) throws IOException {
        File sourceFile = new File(root + "face.jpg");
        File faceFile = new File(root + "头像\\a_source.jpg");
        int i=100;
        while (i-->0) {
            eachReplace(sourceFile, faceFile);
        }
    }

    public static void eachReplace(File sourceFile, File faceFile) throws IOException {
        // 原图
        BufferedImage source = ImageIO.read(sourceFile);

        // 头像原图
        BufferedImage sourceFace = ImageIO.read(faceFile);

        // 人脸识别
        List<Integer[]> faces = OpenCv.positions(sourceFile.getPath());

        // 压缩头像
        BufferedImage face = size(sourceFace, faces);

        // 重新构图
        recompose(source, face, faces);

        // 保存新图
        if(faces.size()>0)
            write(source, sourceFile.getName(), root + "仓库\\");
    }

    public static void write(BufferedImage source, String filename, String root) throws IOException {
        File file = new File(root + UUID.randomUUID().toString() + "." + filename);
        int index = filename.lastIndexOf(".");
        ImageIO.write(source, filename.substring(index + 1), file);
    }


    public static void recompose(BufferedImage source, BufferedImage target, List<Integer[]> faces) throws IOException {
        // 将target写到source中指定的位置上去
        Graphics graphics = source.getGraphics();
        faces.forEach((Integer[] loc) -> graphics.drawImage(target, loc[2], loc[3], null));
    }

    public static BufferedImage size(BufferedImage source, List<Integer[]> faces) {
        // 需要根据 指定大小压缩
        if (faces.size() > 0) {
            Integer[] loc = faces.get(0);
            Integer width = loc[2];
            Integer height = loc[3];
            return source.getSubimage(0, 0, width, height);
        }
        return source.getSubimage(0, 0, source.getWidth(), source.getHeight());
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
