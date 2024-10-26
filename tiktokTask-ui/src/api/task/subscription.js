import request from '@/utils/request'

// 查询认购列表
export function listSubscription(query) {
  return request({
    url: '/task/subscription/list',
    method: 'get',
    params: query
  })
}

// 查询认购详细
export function getSubscription(id) {
  return request({
    url: '/task/subscription/' + id,
    method: 'get'
  })
}

// 新增认购
export function addSubscription(data) {
  return request({
    url: '/task/subscription',
    method: 'post',
    data: data
  })
}

// 修改认购
export function updateSubscription(data) {
  return request({
    url: '/task/subscription',
    method: 'put',
    data: data
  })
}

// 删除认购
export function delSubscription(id) {
  return request({
    url: '/task/subscription/' + id,
    method: 'delete'
  })
}
