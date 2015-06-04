package font;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义字体类
 * 加载字体文件
 * @author qianx
 *
 */
public class DIconFont {
	private static Font font = null;
	
	/**
	 * 获取自定义字体
	 * 
	 * @return Font 如果获取失败，返回微软雅黑字体
	 */
	public static Font getFont(){
		if (null == font){
	    	FileInputStream fi = null;
	    	BufferedInputStream fb = null;
	    	
			try {
				fi = new java.io.FileInputStream("src/IconFont.otf");
//				fi = new java.io.FileInputStream(ClientConstants.FONT_PATH);
				fb = new java.io.BufferedInputStream(fi);
				font = Font.createFont(Font.TRUETYPE_FONT, fb);
				font = font.deriveFont(Font.PLAIN, 12);
			} catch (Exception e) {
				e.printStackTrace();
//				Log.logError(e);
//				font = new Font("寰蒋闆呴粦", Font.PLAIN, ClientConstants.FONT_SIZE);
			} finally{
		        try {
					fb.close();
					fi.close();
				} catch (IOException e) {
					e.printStackTrace();
//					Log.logError(e);
				}
			}
		}
//		Log.logInfo("icon-font-name:"+font.getFontName()+" icon-font-size:"+font.getSize());
		return font;
	}
}
