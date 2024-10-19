package com.tiktok.task.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.tiktok.task.domain.TaskData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tiktok.common.annotation.Log;
import com.tiktok.common.core.controller.BaseController;
import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.enums.BusinessType;
import com.tiktok.task.domain.TkSpecialTask;
import com.tiktok.task.service.ITkSpecialTaskService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 特殊任务触发Controller
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@RestController
@RequestMapping("/task/specialTask")
public class TkSpecialTaskController extends BaseController
{
    @Autowired
    private ITkSpecialTaskService tkSpecialTaskService;

    /**
     * 查询特殊任务触发列表
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkSpecialTask tkSpecialTask)
    {
        startPage();
        List<TkSpecialTask> list = tkSpecialTaskService.selectTkSpecialTaskList(tkSpecialTask);
        return getDataTable(list);
    }

    /**
     * 查询特殊任务触发列表
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:list')")
    @GetMapping("/SpecialTask")
    public AjaxResult list(Long uid)
    {
        TkSpecialTask tkSpecialTask = new TkSpecialTask();
        tkSpecialTask.setUserId(uid);
        List<TkSpecialTask> list = tkSpecialTaskService.selectTkSpecialTaskList(tkSpecialTask);

        TaskData taskData = new TaskData();
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<TaskData.Task> objects1 = new ArrayList<>();
        if(list.size()!=0){

            objects.add(list.get(0).getUserId().toString());
            taskData.setUids(objects);

            for (int i = 0; i < list.size(); i++) {
                TaskData.Task task = new TaskData.Task();
                task.setCount(list.get(i).getTriggerCount().toString());
                task.setTaskId(list.get(i).getTaskId().toString());
                objects1.add(task);
            }
            taskData.setTaskList(objects1);
        }else {
            taskData.setUids(objects);
            taskData.setTaskList(objects1);
        }


        return AjaxResult.success(taskData);
    }

    /**
     * 导出特殊任务触发列表
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:export')")
    @Log(title = "特殊任务触发", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkSpecialTask tkSpecialTask)
    {
        List<TkSpecialTask> list = tkSpecialTaskService.selectTkSpecialTaskList(tkSpecialTask);
        ExcelUtil<TkSpecialTask> util = new ExcelUtil<TkSpecialTask>(TkSpecialTask.class);
        util.exportExcel(response, list, "特殊任务触发数据");
    }

    /**
     * 获取特殊任务触发详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkSpecialTaskService.selectTkSpecialTaskById(id));
    }

    /**
     * 新增特殊任务触发
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:add')")
    @Log(title = "特殊任务触发", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkSpecialTask tkSpecialTask)
    {
        return toAjax(tkSpecialTaskService.insertTkSpecialTask(tkSpecialTask));
    }

    /**
     * 修改特殊任务触发
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:edit')")
    @Log(title = "特殊任务触发", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkSpecialTask tkSpecialTask)
    {
        return toAjax(tkSpecialTaskService.updateTkSpecialTask(tkSpecialTask));
    }

    /**
     * 删除特殊任务触发
     */
    @PreAuthorize("@ss.hasPermi('task:specialTask:remove')")
    @Log(title = "特殊任务触发", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkSpecialTaskService.deleteTkSpecialTaskByIds(ids));
    }
}
