package org.carl.rod.config.page;

import org.carl.rod.config.ctl.AbstractHttpParameterTask;
import org.carl.rod.config.task.PageInfo;

/**
 * @author longjie
 * 2021/5/13
 */
public class DefaultPageRequestTask extends AbstractHttpParameterTask implements HttpPageRequestTask {

    /**
     * 分页查询请求参数信息
     */
    private PageInfo pageInfo;

    public DefaultPageRequestTask() {
    }

    public DefaultPageRequestTask(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public PageStrategy getPageStrategy() {
        if (null != pageInfo) {
            return new PageFormatStrategy(pageInfo);
        }
        return null;
    }
}
