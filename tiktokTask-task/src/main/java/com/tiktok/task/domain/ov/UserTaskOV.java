package com.tiktok.task.domain.ov;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tiktok.task.domain.TkTaskAcceptances;
import com.tiktok.task.domain.TkTasks;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTaskOV {
    private TkTasks tkTasks;
    private TkTaskAcceptances tkTaskAcceptances;

    public TkTasks getTkTasks() {
        return tkTasks;
    }

    public void setTkTasks(TkTasks tkTasks) {
        this.tkTasks = tkTasks;
    }

    public TkTaskAcceptances getTkTaskAcceptances() {
        return tkTaskAcceptances;
    }

    public void setTkTaskAcceptances(TkTaskAcceptances tkTaskAcceptances) {
        this.tkTaskAcceptances = tkTaskAcceptances;
    }
}
