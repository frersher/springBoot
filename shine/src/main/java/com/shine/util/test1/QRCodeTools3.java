package com.shine.util.test1;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QRCodeTools3 {
 
	/**
	 * 
	* @Title: deEncodeByPath 
	* @Description: 替换原图片里面的二维码 
	* @param @param filePath
	* @param @param newPath    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void deEncodeByPath(String filePath, String newPath) {
 
		// 原图里面二维码的url
		String originalURL = null;
		try {
			// 将远程文件转换为流
			BufferedImage readImage = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(readImage);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
 
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = null;
			result = new MultiFormatReader().decode(binaryBitmap, hints);
			originalURL = result.getText();
 
		}
 
		catch (IOException e) {
			log.error("资源读取失败" + e.getMessage());
			e.printStackTrace();
		}
		catch (NotFoundException e) {
			log.error("读取图片二维码坐标前发生异常:" + e.getMessage());
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		deEncodeByPath("/Users/chenbang/Downloads/1111.png", "F:\\image\\gzh.jpg");
	}
}