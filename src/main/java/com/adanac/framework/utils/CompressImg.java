package com.adanac.framework.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

public class CompressImg {
	/**
	 *  通过 com.sun.image.codec.jpeg.JPEGCodec提供的编码器来实现图像压缩
	 * @param image
	 * @param quality
	 * @return
	 */
	private static byte[] newCompressImage(BufferedImage image, float quality) {
		// 如果图片空，返回空
		if (image == null) {
			return null;
		}

		// 得到指定Format图片的writer
		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");// 得到迭代器
		ImageWriter writer = (ImageWriter) iter.next(); // 得到writer

		// 得到指定writer的输出参数设置(ImageWriteParam )
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
		iwp.setCompressionQuality(quality); // 设置压缩质量参数

		iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		iwp.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		// 开始打包图片，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		IIOImage iIamge = new IIOImage(image, null, null);
		try {
			// 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
			// 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
			writer.write(null, iIamge, iwp);
		} catch (IOException e) {
			System.out.println("write errro");
			e.printStackTrace();
		}

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 
	 * 功能描述：压缩图片
	 * 输入参数：<按照参数定义顺序> 
	 * @param 参数说明
	 * 返回值:  类型 <说明> 
	 * @return 返回值
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	public static byte[] compressImg(byte[] imagedata, int width, int height) throws AWTException {
		Robot rb = new Robot();
		Rectangle rt = new Rectangle(0, 0, width, height);
		BufferedImage image = rb.createScreenCapture(rt);
		return newCompressImage(image, 0.2f);
	}
}
