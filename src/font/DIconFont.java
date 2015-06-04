package font;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * �Զ���������
 * ���������ļ�
 * @author qianx
 *
 */
public class DIconFont {
	private static Font font = null;
	
	/**
	 * ��ȡ�Զ�������
	 * 
	 * @return Font �����ȡʧ�ܣ�����΢���ź�����
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
//				font = new Font("微软雅黑", Font.PLAIN, ClientConstants.FONT_SIZE);
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
