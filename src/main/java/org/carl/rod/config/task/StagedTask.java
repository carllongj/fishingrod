package org.carl.rod.config.task;

import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
public interface StagedTask extends Task {

    /**
     * 获取当前的步骤任务
     *
     * @return 返回当前任务集合
     */
    List<Task> getStagedTasks();
}
