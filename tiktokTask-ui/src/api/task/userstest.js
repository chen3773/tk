import request from '@/utils/request'

// 查询用户信息列表
export function listUserstest(query) {
  return request({
    url: '/task/userstest/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUserstest(uid) {
  return request({
    url: '/task/userstest/' + uid,
    method: 'get'
  })
}

// 新增用户信息
export function addUserstest(data) {
  return request({
    url: '/task/userstest',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUserstest(data) {
  return request({
    url: '/task/userstest',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUserstest(uid) {
  return request({
    url: '/task/userstest/' + uid,
    method: 'delete'
  })
}
