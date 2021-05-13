package org.carl.rod.config.page;

import org.carl.rod.config.task.HttpRequestTask;

/**
 * @author longjie
 * 2021/5/13
 */
public interface HttpPageRequestTask extends HttpRequestTask {
    /**
     * 获取当前的Http分页抓取URL链接处理
     *
     * @return 返回当前的分页处理逻辑
     */
    PageStrategy getPageStrategy();
}
