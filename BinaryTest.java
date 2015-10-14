package com.competing.verify.train;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.competing.util.Contants;
public class BinaryTest {

	public static void main(String[] args) throws IOException {
		test(Contants.IMG1_PATH+"1_80.jpg","/Users/lixf/competImgtest/2.jpg");
	}
	
	public static void test(String readimg,String writeimg) throws IOException{
		File file = new File(readimg);
		BufferedImage bufferedImage = ImageIO.read(file);
		int h = bufferedImage.getHeight();
		int w = bufferedImage.getWidth();
 
		/**
		 * 
		 *  我们知道通过bufferedimage对象的getRGB（x，y）方法可以返回指定坐标的颜色int值 他可以通过
		 *  int R =(rgb & 0xff0000 ) >> 16 ;
		 *  int G= (rgb & 0xff00 ) >> 8 ;
		 *  int B= (rgb & 0xff );
		 *  转换成三个颜色分量
		 */
		
		int[][] gray = new int[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int argb = bufferedImage.getRGB(x, y);
				int r = (argb >> 16) & 0xFF;
				int g = (argb >> 8) & 0xFF;
				int b = (argb >> 0) & 0xFF;
			
				//平均值算法
//				int grayPixel = (r+g+b)/3;	
				int grayPixel = (int) ((b * 29 + g * 150 + r * 77 + 128) >> 8);
				int pixel = 255 << 24 | grayPixel << 16 | grayPixel << 8 | grayPixel;
				gray[x][y] = pixel;
				bufferedImage.setRGB(x, y, pixel);
			}
		}
		
		ImageIO.write(bufferedImage, "jpg", new File(  "/Users/lixf/competImgtest/2_1.jpg"));
	}
	
	 
}