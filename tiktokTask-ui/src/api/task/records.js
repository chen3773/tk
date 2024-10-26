import request from '@/utils/request'

// 查询理财分红记录列表
export function listRecords(query) {
  return request({
    url: '/task/records/list',
    method: 'get',
    params: query
  })
}

// 查询理财分红记录详细
export function getRecords(id) {
  return request({
    url: '/task/records/' + id,
    method: 'get'
  })
}

// 新增理财分红记录
export function addRecords(data) {
  return request({
    url: '/task/records',
    method: 'post',
    data: data
  })
}

// 修改理财分红记录
export function updateRecords(data) {
  return request({
    url: '/task/records',
    method: 'put',
    data: data
  })
}

// 删除理财分红记录
export function delRecords(id) {
  return request({
    url: '/task/records/' + id,
    method: 'delete'
  })
}
