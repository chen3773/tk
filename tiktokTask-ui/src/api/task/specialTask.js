import request from '@/utils/request'

// 查询特殊任务触发列表
export function listSpecialTask(query) {
  return request({
    url: '/task/specialTask/list',
    method: 'get',
    params: query
  })
}

// 查询特殊任务触发详细
export function getSpecialTask(id) {
  return request({
    url: '/task/specialTask/' + id,
    method: 'get'
  })
}

// 新增特殊任务触发
export function addSpecialTask(data) {
  return request({
    url: '/task/specialTask',
    method: 'post',
    data: data
  })
}

// 修改特殊任务触发
export function updateSpecialTask(data) {
  return request({
    url: '/task/specialTask',
    method: 'put',
    data: data
  })
}

// 删除特殊任务触发
export function delSpecialTask(id) {
  return request({
    url: '/task/specialTask/' + id,
    method: 'delete'
  })
}
