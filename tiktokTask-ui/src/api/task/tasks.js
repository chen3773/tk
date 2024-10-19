import request from '@/utils/request'

// 查询任务列列表
export function listTasks(query) {
  return request({
    url: '/task/tasks/list',
    method: 'get',
    params: query
  })
}

export function Sortinglist(query) {
  return request({
    url: '/task/tasks/Sortinglist',
    method: 'get',
    params: query
  })
}

// 查询任务列详细
export function getTasks(id) {
  return request({
    url: '/task/tasks/' + id,
    method: 'get'
  })
}

// 新增任务列
export function addTasks(data) {
  return request({
    url: '/task/tasks',
    method: 'post',
    data: data
  })
}

// 修改任务列
export function updateTasks(data) {
  return request({
    url: '/task/tasks',
    method: 'put',
    data: data
  })
}

// 删除任务列
export function delTasks(id) {
  return request({
    url: '/task/tasks/' + id,
    method: 'delete'
  })
}
