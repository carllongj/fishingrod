package org.carl.rod.config.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractCtlTask extends AbstractTask implements TaskCtl {

    /**
     * 数据过滤器集合
     */
    private List<UrlPathFilter> filterList;

    /**
     * 数据输入处理器集合
     */
    private TaskInputHandler taskInputHandler;

    /**
     * 数据输入处理器集合
     */
    private List<TaskOutputHandler> taskOutputHandlers;

    public AbstractCtlTask() {
        // TODO: 2021/5/13 使用名称生成器来生成对应的名称
        this("");
    }

    public AbstractCtlTask(String taskName) {
        super(taskName);
        this.filterList = new ArrayList<>();
        this.taskOutputHandlers = new ArrayList<>();
    }

    @Override
    public List<UrlPathFilter> getUrlPathFilters() {
        return this.filterList;
    }

    @Override
    public void addUrlPathFilter(UrlPathFilter urlPathFilter) {
        Objects.requireNonNull(urlPathFilter);
        this.filterList.add(urlPathFilter);
    }

    @Override
    public TaskInputHandler getTaskInput() {
        return taskInputHandler;
    }

    @Override
    public void setTaskInput(TaskInputHandler taskInput) {
        this.taskInputHandler = taskInput;
    }

    @Override
    public List<TaskOutputHandler> getTaskOutputHandler() {
        return taskOutputHandlers;
    }

    @Override
    public void addTaskOutputHandler(TaskOutputHandler taskOutputHandler) {
        this.taskOutputHandlers.add(taskOutputHandler);
    }
}
