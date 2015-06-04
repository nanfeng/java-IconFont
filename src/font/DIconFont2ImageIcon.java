package font;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class DIconFont2ImageIcon {
	public static void main(String[] args) {
		JFrame jf = new JFrame("img icon");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setSize(500, 500);

		ImageIcon im1 = new ImageIcon();
		im1.setImage(DIconFont2ImageIcon.String2Img24X24("\ue666"));
		
		ImageIcon im2 = new ImageIcon();
//		String2Img(String str, Color strColor, Color bgColor, float fontSize, /*String ouImgPath,*/ int imgWidth, int imgHeight, float transparency)
		im2.setImage(DIconFont2ImageIcon.String2Img24X24("\ue667"));

//		ImageIcon im3 = new ImageIcon();
//		im3 = new ImageIcon();
//		im3.setImage(DIconFont2ImageIcon.WaterImgPP("d:/1.png", "d:/sy.png", "d:/waterimgpp.png", 0.2, 0.2));
//
//		ImageIcon im4 = new ImageIcon();
//		im4 = new ImageIcon();
//		im4.setImage(DIconFont2ImageIcon.WaterImgPS("d:/1.png", "\ue668", Color.ORANGE, null, "d:/waterimgps.png", 0.1, 0.1));

//		ImageIcon im5 = new ImageIcon();
//		im5 = new ImageIcon();
//		im5.setImage(DIconFont2ImageIcon.WaterImgSP("\ue669", Color.YELLOW, 50, 50, null, "d:/sy.png", /*"d:/waterimgsp.png",*/ 0.2, 0.2));
		
		JLabel j1 = new JLabel(im1);
		j1.setBorder(new LineBorder(Color.RED));
		j1.setSize(40, 40);
		
		JLabel j2 = new JLabel(im2);
		j2.setBorder(new LineBorder(Color.RED));
		j2.setSize(40, 40);
		
//		JLabel j3 = new JLabel(im3);
//		j3.setBorder(new LineBorder(Color.RED));
//		j3.setSize(40, 40);
//		
//		JLabel j4 = new JLabel(im4);
//		j4.setBorder(new LineBorder(Color.RED));
//		j4.setSize(40, 40);
//
//		JLabel j5 = new JLabel(im5);
//		j5.setBorder(new LineBorder(Color.RED));
//		j5.setSize(100, 100);
		
		JPanel jp = new JPanel(new FlowLayout());
		jp.add(j1);
		jp.add(j2);
//		jp.add(j3);
//		jp.add(j4);
//		jp.add(j5);
		
		jf.add(jp);
		jf.setVisible(true);
	}
	
	/**
	 * 字符串转图片
	 * @param str		字符串
	 * @param strColor	字符串颜色
	 * @param bgColor	字符串背景颜色
	 * @param imgWidth	图片宽
	 * @param imgHeight 图片高
	 * @param transparency str 透明度
	 * @return
	 */
	public static BufferedImage String2Img(String str, Color strColor, Color bgColor, float fontSize, /*String ouImgPath,*/ int imgWidth, int imgHeight, float transparency){
		BufferedImage image = null;
//		String str = "\ue666\ue666\ue666\ue666\ue666\ue666";
		Font font = DIconFont.getFont();
		int strWidth = 0;
		int strHeight = 0;
		FontMetrics metrics = null;

		// Create the image using the  
		image = new BufferedImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);  

		// Get the images graphics.  
		Graphics2D g = image.createGraphics();  

		//消除锯齿
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(hints);
        
		// Set the graphics composite to Alpha.  
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));  
		
		// set font
		font = font.deriveFont(fontSize);
		g.setFont(font);
		
		//font width and height
		metrics = g.getFontMetrics(font);
		strWidth = metrics.stringWidth(str);
//		strHeight = metrics.getHeight();
		strHeight = font.getSize();

		// Draw the passed image into the new transparent image.  
		g.drawImage(image, null, 0, 0);
		
		// set background color
		if (null != bgColor){
			g.setColor(bgColor);
			g.fillRect(0, 0, imgWidth, imgHeight);
		}
		
		//set font color
		if (null == strColor)
			g.setColor(Color.BLACK);
		else
			g.setColor(strColor);
		
		// draw string in center
		g.drawString(str, (imgWidth-strWidth)/2, (imgHeight+strHeight)/2);
		
		// Free all system resources.  
		g.dispose(); 

		//output file
/* warn:  have problem
		if (null != ouImgPath){
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(ouImgPath);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(image);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					if (null != out)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		return image;
	}
	
	/**
	 * 绘制24X24大小，颜色为strColor, font为 font的图片
	 * @param str
	 * @param strColor
	 * @param font
	 * @return
	 */
	public static BufferedImage String2Img24X24(String str, Color strColor, float fontSize){
		return DIconFont2ImageIcon.String2Img(str, strColor, null, fontSize, 24, 24, 1.0f);
	}
	
	/**
	 * 绘制24X24大小，颜色为strColor的图片
	 * @param str
	 * @param strColor
	 * @return
	 */
	public static BufferedImage String2Img24X24(String str, Color strColor){
		return DIconFont2ImageIcon.String2Img(str, strColor, null, 16.0f, 24, 24, 1.0f);
	}
	
	/**
	 * 绘制24X24大小，颜色为黑色的图片
	 * @param str
	 * @return
	 */
	public static BufferedImage String2Img24X24(String str){
		return DIconFont2ImageIcon.String2Img(str, null, null, 16.0f, 24, 24, 1.0f);
	}
	
	/**
	 * 绘制24X24大小，颜色为黑色的图片
	 * @param str
	 * @return
	 */
	public static ImageIcon String2ImgIcon24X24(String str){
		return new ImageIcon(DIconFont2ImageIcon.String2Img(str, new Color(43,87,154), null, 16.0f, 24, 24, 1.0f));
	}
	
	/**
	 * 绘制24X24大小，颜色为黑色的图片
	 * @param str
	 * @return
	 */
	public static ImageIcon String2ImgIcon24X24(String str, Color strColor){
		return new ImageIcon(DIconFont2ImageIcon.String2Img(str, strColor, null, 16.0f, 24, 24, 1.0f));
	}
	
	/**
	 * 绘制24X24大小，颜色为黑色的图片
	 * @param str
	 * @return
	 */
	public static ImageIcon String2ImgIcon18X18(String str, Color strColor){
		return new ImageIcon(DIconFont2ImageIcon.String2Img(str, strColor, null, 16.0f, 18, 18, 1.0f));
	}
	
	/**
	 * 两幅图片叠加
	 * @param imgPath		图片路径
	 * @param waterImgPath	水印图片路径
	 * @param outImgPath	输出图片路径
	 * @param xOffset		范围0-1.0 x偏移，值越大越往左偏
	 * @param yOffset		范围0-1.0 y偏移，值越大越往上偏
	 * @return
	 */
	public static BufferedImage WaterImgPP(String imgPath, String waterImgPath, String outImgPath, double xOffset, double yOffset){
		BufferedImage image = null;
		try {
			//原始图片
			Image formerImage = ImageIO.read(new File(imgPath));
			int width = formerImage.getWidth(null);
			int height = formerImage.getHeight(null);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(formerImage, 0, 0, width, height, null);
			
			//水印图片
			Image waterMarkImage = ImageIO.read(new File(waterImgPath));
			int widthWMI = waterMarkImage.getWidth(null);
			int heightWMI = waterMarkImage.getHeight(null);
			int x = (int) (width * xOffset); // "0.5"小数越大，水印越向左移动。
			int y = (int) (height * yOffset); // "0.5"小数越大，水印越向上移动。
			g.drawImage(waterMarkImage, width - widthWMI - x, height - heightWMI - y, widthWMI, heightWMI, null);
			
			g.dispose();
			
			if (null != outImgPath){
		        String formatName = outImgPath.substring(outImgPath.lastIndexOf(".") + 1);
		        ImageIO.write(image, /*"GIF"*/ formatName /* format desired */ , new File(outImgPath) /* target */ );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * 在图片上添加字符串
	 * @param imgPath	图片路径
	 * @param str		字符串
	 * @param strColor	字符串颜色
	 * @param font		字体
	 * @param outImgPath图片输出路径
	 * @param xOffset	范围0-1.0 x越大越往右偏移
	 * @param yOffset	范围0-1.0 y越大越往下偏移
	 * @return
	 */
	public static BufferedImage WaterImgPS(String imgPath, String str, Color strColor, Font font, String outImgPath, double xOffset, double yOffset){
		BufferedImage image = null;
		
		try {
			//原始图片
			Image formerImage = ImageIO.read(new File(imgPath));
			int width = formerImage.getWidth(null);
			int height = formerImage.getHeight(null);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(formerImage, 0, 0, width, height, null);
			
			//消除锯齿
	        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setRenderingHints(hints);
	        
			//字符串处理
			FontMetrics metrics = null;
			int strWidth = 0;
			int strHeight = 0;
			
			// set font
			if (null == font)
				font = DIconFont.getFont();

			g.setFont(font);
			
			//font width and height
			metrics = g.getFontMetrics(font);
			strWidth = metrics.stringWidth(str);
//			strHeight = metrics.getHeight();
			strHeight = font.getSize();

			// Draw the passed image into the new transparent image.  
			g.drawImage(image, null, 0, 0);
			
			//set font color
			if (null == strColor)
				g.setColor(Color.BLACK);
			else
				g.setColor(strColor);
			
			// draw string
			g.drawString(str, (float)((width-strWidth)*xOffset), (float)((height+strHeight)*yOffset));
			
			g.dispose();
			
			//保存文件
			if (null != outImgPath){
		        String formatName = outImgPath.substring(outImgPath.lastIndexOf(".") + 1);
		        ImageIO.write(image, /*"GIF"*/ formatName /* format desired */ , new File(outImgPath) /* target */ );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * 在字符串上添加图片
	 * @param str		字符串
	 * @param strColor	字符串颜色
	 * @param imgWidth	图片宽度
	 * @param imgHeight	图片高度
	 * @param font		字符串字体
	 * @param imgPath	图片路径
	 * @param xOffset	范围0-1.0 x越大越往右偏移
	 * @param yOffset	范围0-1.0 y越大越往下偏移
	 * @return
	 */
	public static BufferedImage WaterImgSP(String str, Color strColor, int imgWidth, int imgHeight, Font font, String imgPath, /*String outImgPath,*/ double xOffset, double yOffset){
		BufferedImage image = null;
		
		try {
			//原始图片
			Image formerImage = ImageIO.read(new File(imgPath));
			int width = formerImage.getWidth(null);
			int height = formerImage.getHeight(null);
			image = new BufferedImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
			Graphics2D g = image.createGraphics();
			g.drawImage(formerImage, (int)(imgWidth*xOffset), (int)(imgHeight*yOffset), width, height, null);
			
			//消除锯齿
	        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setRenderingHints(hints);
	        
			//字符串处理
			FontMetrics metrics = null;
			int strWidth = 0;
			int strHeight = 0;
			
			// set font
			if (null == font)
				font = DIconFont.getFont();

			g.setFont(font);
			
			//font width and height
			metrics = g.getFontMetrics(font);
			strWidth = metrics.stringWidth(str);
//			strHeight = metrics.getHeight();
			strHeight = font.getSize();

			// Draw the passed image into the new transparent image.  
			g.drawImage(image, null, 0, 0);
			
			//set font color
			if (null == strColor)
				g.setColor(Color.BLACK);
			else
				g.setColor(strColor);
			
			// draw string in center
			g.drawString(str, (imgWidth-strWidth)/2, (imgHeight+strHeight)/2);
			
			g.dispose();
			
			//保存文件
/*have problem with Transparency.TRANSLUCENT
			if (null != outImgPath){
				FileOutputStream out = new FileOutputStream(outImgPath);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(image);
				out.close();
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * 两个字符叠加，可以设置第二个字符的偏移位置
	 * @param str1			字符1
	 * @param str1Color		字符1颜色
	 * @param str1FontSize	字符1大小
	 * @param str2			字符2
	 * @param str2Color		字符2颜色
	 * @param str2FontSize	字符2大小
	 * @param imgWidth		生成图片后的宽度
	 * @param imgHeight		生成图片后的高度
	 * @param xOffset		字符2相对于字符1x偏移（0-1.0）
	 * @param yOffset		字符2相对于字符1y偏移（0-1.0）
	 * @return
	 */
	public static BufferedImage WaterImgSS(String str1, Color str1Color, float str1FontSize, String str2, Color str2Color, float str2FontSize, int imgWidth, int imgHeight, double xOffset, double yOffset){
		BufferedImage image = null;
		Font str1Font = null;
		Font str2Font = null;
		try {
			image = new BufferedImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
			Graphics2D g = image.createGraphics();
			
			//消除锯齿
	        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setRenderingHints(hints);
	        
			//字符串处理
			FontMetrics metrics = null;
			int strWidth = 0;
			int strHeight = 0;
			str1Font = DIconFont.getFont().deriveFont(str1FontSize);
			g.setFont(str1Font);
			
			//font width and height
			metrics = g.getFontMetrics(str1Font);
			strWidth = metrics.stringWidth(str1);
//			strHeight = metrics.getHeight();
			strHeight = str1Font.getSize();

			// Draw the passed image into the new transparent image.  
			g.drawImage(image, null, 0, 0);
			
			g.setColor(str1Color);
			
			// draw string in center
			g.drawString(str1, (imgWidth-strWidth)/2, (imgHeight+strHeight)/2);
			
			//str2
			//字符串处理
			metrics = null;
			strWidth = 0;
			strHeight = 0;
			str2Font = DIconFont.getFont().deriveFont(str2FontSize);
			g.setFont(str2Font);
			//font width and height
			metrics = g.getFontMetrics(str2Font);
			strWidth = metrics.stringWidth(str2);
//			strHeight = metrics.getHeight();
			strHeight = str2Font.getSize();
			g.setColor(str2Color);
			
			// draw string in center
			g.drawString(str2, (int)((imgWidth-strWidth)*xOffset), (int)((imgHeight+strHeight)*yOffset));
			g.dispose();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * 获取错误图标
	 * @param fontSize 字体大小
	 * @return
	 */
	public static ImageIcon GetErrorIcon(float fontSize){
		return new ImageIcon(DIconFont2ImageIcon.WaterImgSS("\uEA19", Color.RED, fontSize, "\uEA1A", Color.WHITE, fontSize, (int)fontSize, (int)fontSize, 0.5, 0.5));
	}
	
	/**
	 * 获取告警图标
	 * @param fontSize
	 * @return
	 */
	public static ImageIcon GetWarnIcon(float fontSize){
		return new ImageIcon(DIconFont2ImageIcon.WaterImgSS("\uEA19", new Color(237,183,2), fontSize, "\uEA1B", Color.WHITE, fontSize, (int)fontSize, (int)fontSize, 0.5, 0.5));
	}
	
	/**
	 * 获取信息图标
	 * @param fontSize
	 * @return
	 */
	public static ImageIcon GetInfoIcon(float fontSize){
		return new ImageIcon(DIconFont2ImageIcon.WaterImgSS("\uEA19", Color.RED, fontSize, "\uEA1C", Color.WHITE, fontSize, (int)fontSize, (int)fontSize, 0.5, 0.5));
	}


	
	//待调整
	public static ImageIcon SS2ImgIcon18X18(String str1, String str2){
		return new ImageIcon(DIconFont2ImageIcon.WaterImgSS(str1, new Color(43,87,154), 16.0f, str2, Color.RED, 12.0f, 18, 18, 0.7, 0.6));
	}


}
