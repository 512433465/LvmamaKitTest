package com.lvmama.cn;


/**
 * 图片识别比对
 * @author zhang
 *
 */
public class AiLvmamaKit {
	public static void main(String[] args) {
		
		
		LvmamaKit kit = new LvmamaKit("C:\\Users\\Desktop\\pic\\ht\\mima.png","C:\\Users\\Desktop\\pic\\ht\\9.png");
		
		
		 kit.find2BElementPoint4Debug();
//		String ss = kit.find2BElementPoint();
//		
//		System.out.println(ss);
////
//		System.out.println("xxxx");
	}

}
