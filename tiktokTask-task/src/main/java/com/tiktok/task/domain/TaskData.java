package com.tiktok.task.domain;

import java.util.List;

public class TaskData {
        private List<String> uids;
        private List<Task> taskList;

        // Getters and Setters
        public List<String> getUids() {
            return uids;
        }

        public void setUids(List<String> uids) {
            this.uids = uids;
        }

        public List<Task> getTaskList() {
            return taskList;
        }

        public void setTaskList(List<Task> taskList) {
            this.taskList = taskList;
        }

        public static class Task {
            private String taskId;
            private String count;

            // Getters and Setters
            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }


}
