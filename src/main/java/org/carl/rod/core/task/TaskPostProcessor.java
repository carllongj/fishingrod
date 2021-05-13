package org.carl.rod.core.task;

import org.carl.rod.config.task.Task;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskPostProcessor {

    /**
     * 处理任务配置项信息
     *
     * @param task 任务配置
     */
    default Task postProcess(Task task) {
        return task;
    }
}
