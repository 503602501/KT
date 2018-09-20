import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;



public class WeiXin {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//图片路径
        File file = new File("C:\\Users\\db2admin\\Desktop\\223.jpg");
//        File file = new File("C:\\Users\\db2admin\\Desktop\\11.png");
        //读取图片到缓冲区
        BufferedImage bufferedImage = ImageIO.read(file);
        //QRCode解码器
        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        /**
         *codeDecoder.decode(new MyQRCodeImage())
         *这里需要实现QRCodeImage接口，移步最后一段代码
         */
        //通过解析二维码获得信息
        try {
        	String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
        	System.out.println(result);
			
		} catch (DecodingFailedException e) {
			System.out.println("不是二维码");
		}
	}

}
