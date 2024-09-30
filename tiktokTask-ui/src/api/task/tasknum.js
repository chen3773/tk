import request from '@/utils/request'

// 查询任务数量列表
export function listTasknum(query) {
  return request({
    url: '/task/tasknum/list',
    method: 'get',
    params: query
  })
}

// 查询任务数量详细
export function getTasknum(id) {
  return request({
    url: '/task/tasknum/' + id,
    method: 'get'
  })
}

// 新增任务数量
export function addTasknum(data) {
  return request({
    url: '/task/tasknum',
    method: 'post',
    data: data
  })
}

// 修改任务数量
export function updateTasknum(data) {
  return request({
    url: '/task/tasknum',
    method: 'put',
    data: data
  })
}

// 删除任务数量
export function delTasknum(id) {
  return request({
    url: '/task/tasknum/' + id,
    method: 'delete'
  })
}
