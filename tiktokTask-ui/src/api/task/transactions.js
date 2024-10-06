import request from '@/utils/request'

// 查询上下分日志列表
export function listTransactions(query) {
  return request({
    url: '/task/transactions/list',
    method: 'get',
    params: query
  })
}

// 查询上下分日志详细
export function getTransactions(id) {
  return request({
    url: '/task/transactions/' + id,
    method: 'get'
  })
}

// 新增上下分日志
export function addTransactions(data) {
  return request({
    url: '/task/transactions',
    method: 'post',
    data: data
  })
}

// 修改上下分日志
export function updateTransactions(data) {
  return request({
    url: '/task/transactions',
    method: 'put',
    data: data
  })
}

// 删除上下分日志
export function delTransactions(id) {
  return request({
    url: '/task/transactions/' + id,
    method: 'delete'
  })
}
