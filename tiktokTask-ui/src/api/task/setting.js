import request from '@/utils/request'

// 查询三级分佣百分比列表
export function listSetting(query) {
  return request({
    url: '/task/setting/list',
    method: 'get',
    params: query
  })
}

// 查询三级分佣百分比详细
export function getSetting(id) {
  return request({
    url: '/task/setting/' + id,
    method: 'get'
  })
}

// 新增三级分佣百分比
export function addSetting(data) {
  return request({
    url: '/task/setting',
    method: 'post',
    data: data
  })
}

// 修改三级分佣百分比
export function updateSetting(data) {
  return request({
    url: '/task/setting',
    method: 'put',
    data: data
  })
}

// 删除三级分佣百分比
export function delSetting(id) {
  return request({
    url: '/task/setting/' + id,
    method: 'delete'
  })
}
