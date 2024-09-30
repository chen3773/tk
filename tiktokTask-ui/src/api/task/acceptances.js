import request from '@/utils/request'

// 查询用户接取任务列表
export function listAcceptances(query) {
  return request({
    url: '/task/acceptances/list',
    method: 'get',
    params: query
  })
}

// 查询用户接取任务详细
export function getAcceptances(id) {
  return request({
    url: '/task/acceptances/' + id,
    method: 'get'
  })
}

// 新增用户接取任务
export function addAcceptances(data) {
  return request({
    url: '/task/acceptances',
    method: 'post',
    data: data
  })
}

// 修改用户接取任务
export function updateAcceptances(data) {
  return request({
    url: '/task/acceptances',
    method: 'put',
    data: data
  })
}

// 删除用户接取任务
export function delAcceptances(id) {
  return request({
    url: '/task/acceptances/' + id,
    method: 'delete'
  })
}
