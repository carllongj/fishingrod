package org.carl.rod.config.ctl;

import org.carl.rod.config.task.Task;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractTask implements Task {

    private String taskName;

    public AbstractTask() {
        // TODO: 2021/5/13 生成指定的名称
        this.taskName = "";
    }

    public AbstractTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
