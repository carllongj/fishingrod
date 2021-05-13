package org.carl.rod.core.task;

import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;

import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskFactory {

    /**
     * 创建任务
     *
     * @param configuration 所有的配置项
     * @return 返回创建完成的所有task集合
     */
    List<Task> createTask(RodBaseConfiguration configuration);

    /**
     * 添加任务处理器
     *
     * @param processor 添加任务处理器
     */
    void addTaskPostProcessor(TaskPostProcessor processor);
}
