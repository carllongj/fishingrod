package org.carl.rod.config.ctl;

/**
 * @author longjie
 * 2021/5/18
 */
public interface Document {

	/**
	 * 获取文档的原始字节数据内容
	 *
	 * @return 返回对应的原始字节数据内容
	 */
	byte[] getDocumentContent();

}
