import request from '@/utils/request'

// 查询自动审核列表
export function listAudit(query) {
  return request({
    url: '/task/audit/list',
    method: 'get',
    params: query
  })
}

// 查询自动审核详细
export function getAudit(id) {
  return request({
    url: '/task/audit/' + id,
    method: 'get'
  })
}

// 新增自动审核
export function addAudit(data) {
  return request({
    url: '/task/audit',
    method: 'post',
    data: data
  })
}

// 修改自动审核
export function updateAudit(data) {
  return request({
    url: '/task/audit',
    method: 'put',
    data: data
  })
}

// 删除自动审核
export function delAudit(id) {
  return request({
    url: '/task/audit/' + id,
    method: 'delete'
  })
}
