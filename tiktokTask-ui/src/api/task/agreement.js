import request from '@/utils/request'

// 查询文本列表
export function listAgreement(query) {
  return request({
    url: '/task/agreement/list',
    method: 'get',
    params: query
  })
}

// 查询文本详细
export function getAgreement(id) {
  return request({
    url: '/task/agreement/' + id,
    method: 'get'
  })
}

// 新增文本
export function addAgreement(data) {
  return request({
    url: '/task/agreement',
    method: 'post',
    data: data
  })
}

// 修改文本
export function updateAgreement(data) {
  return request({
    url: '/task/agreement',
    method: 'put',
    data: data
  })
}

// 删除文本
export function delAgreement(id) {
  return request({
    url: '/task/agreement/' + id,
    method: 'delete'
  })
}
