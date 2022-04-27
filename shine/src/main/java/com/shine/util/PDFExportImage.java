//package com.shine.util;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.awt.image.CropImageFilter;
//import java.awt.image.FilteredImageSource;
//import java.awt.image.ImageFilter;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by haber on 2017/4/11.
// */
//public class PDFExportImage {
//
//
//    private static final List excludePage = Arrays.asList(0);
//    private static final boolean isCut = true;
//
//    public static void setup(String filePath, String outDirPath) throws IOException {
//
//
//        try {
//            File file = new File(filePath);
//            PDDocument document = PDDocument.load(file);
//            PDFRenderer renderer = new PDFRenderer(document);
//
//            int pageTotal = document.getNumberOfPages();
//            System.out.println("页数：" + pageTotal);
//
//
//            File outDir = new File(outDirPath);
//            if (!outDir.exists()) {
//                outDir.mkdirs();
//            }
//
//            if (!outDir.isDirectory()) {
//                System.err.println("请填写正确的输出路径");
//
//                System.exit(0);
//            }
//            int pageName = 0;
//            for (int pageIndex = 0; pageIndex < pageTotal; pageIndex++) {
//                System.out.println("正在转换第 " + pageIndex + " 页");
//
//                BufferedImage image = renderer.renderImageWithDPI(pageIndex, 100, ImageType.RGB);
//
//                String fileName = outDir + "/" + file.getName() + "-" + (pageName++) + ".jpg";
//                ImageIO.write(image, "jpg", new File(fileName));
//
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                ImageIO.write(image, "jpg", out);
//                byte[] bytes = out.toByteArray();
//            }
//            document.close();
//            System.gc();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public final static void cut(BufferedImage bufferedImage, String result,
//                                 int x, int y, int width, int height) {
//        try {
//            // 读取源图像
//            int srcWidth = bufferedImage.getWidth(); // 源图宽度
//            int srcHeight = bufferedImage.getHeight(); // 源图高度
//            if (srcWidth > 0 && srcHeight > 0) {
//                Image image = bufferedImage.getScaledInstance(srcWidth, srcHeight,
//                        Image.SCALE_DEFAULT);
//                // 四个参数分别为图像起点坐标和宽高
//                // 即: CropImageFilter(int x,int y,int width,int height)
//                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
//                Image img = Toolkit.getDefaultToolkit().createImage(
//                        new FilteredImageSource(image.getSource(),
//                                cropFilter));
//                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//                Graphics g = tag.getGraphics();
//                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
//                g.dispose();
//                // 输出为文件
//                ImageIO.write(tag, "jpg", new File(result));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        String outDirPath = "/Users/chenbang/Desktop/picture/7";
//        String filePath = "/Users/chenbang/Desktop/有赞和有赞文化-202001201 碧寻.pdf";
//
//
////        String filePath = "/Users/haber/Documents/church/礼仪周刊/乙年-2018/圣家节/";
////        String[] fileNames = new String[]{filePath+"20171231圣家节周刊曲线版.pdf-9.jpg",filePath+"20171231圣家节周刊曲线版.pdf-10.jpg"};
////        mergeImage(fileNames, 1, filePath + "20171231圣家节周刊曲线版.pdf-9-0.jpg");
//
//        setup(filePath, outDirPath);
//
//
////        BufferedImage bi = ImageIO.read(new File("/Users/haber/Downloads/test.jpg"));
////        cut(bi, outDir + "/test-1.jpg",0 , 0, bi.getWidth()/2, bi.getHeight());
////        cut(bi, outDir + "/test-2.jpg",bi.getWidth()/2 , 0, bi.getWidth()/2, bi.getHeight());
//    }
//}