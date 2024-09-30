import request from '@/utils/request'

// 查询系统默认配置列表
export function listUserDefault(query) {
  return request({
    url: '/task/userDefault/list',
    method: 'get',
    params: query
  })
}

// 查询系统默认配置详细
export function getUserDefault(id) {
  return request({
    url: '/task/userDefault/' + id,
    method: 'get'
  })
}

// 新增系统默认配置
export function addUserDefault(data) {
  return request({
    url: '/task/userDefault',
    method: 'post',
    data: data
  })
}

// 修改系统默认配置
export function updateUserDefault(data) {
  return request({
    url: '/task/userDefault',
    method: 'put',
    data: data
  })
}

// 删除系统默认配置
export function delUserDefault(id) {
  return request({
    url: '/task/userDefault/' + id,
    method: 'delete'
  })
}
