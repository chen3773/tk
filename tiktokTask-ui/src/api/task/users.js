import request from '@/utils/request'

// 查询用户信息列表
export function listUsers(query) {
  return request({
    url: '/task/users/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUsers(uid) {
  return request({
    url: '/task/users/' + uid,
    method: 'get'
  })
}

// 新增用户信息
export function addUsers(data) {
  return request({
    url: '/task/users',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUsers(data) {
  return request({
    url: '/task/users',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUsers(uid) {
  return request({
    url: '/task/users/' + uid,
    method: 'delete'
  })
}

export function UpgradeSvip(query) {
  return request({
    url: '/task/svipSetting/UpgradeSvip',
    method: 'get',
    params: query
  })
}

export function addSpecialTask(data) {
  return request({
    url: '/task/users/addSpecialTask',
    method: 'post',
    data: data
  })
}

export function getSpecialTask(query) {
  return request({
    url: '/task/specialTask/SpecialTask',
    method: 'get',
    params: query
  })
}