package org.carl.rod.config.ctl;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskOutputHandler {

    /**
     * 执行数据的输出处理
     *
     * @param target 输入数据的处理结果
     */
    void handleOutput(Object target) throws Exception;

}
