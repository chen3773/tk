import request from '@/utils/request'

// 查询svip默认配置列表
export function listSvipSetting(query) {
  return request({
    url: '/task/svipSetting/list',
    method: 'get',
    params: query
  })
}

// 查询svip默认配置详细
export function getSvipSetting(id) {
  return request({
    url: '/task/svipSetting/' + id,
    method: 'get'
  })
}

// 新增svip默认配置
export function addSvipSetting(data) {
  return request({
    url: '/task/svipSetting',
    method: 'post',
    data: data
  })
}

// 修改svip默认配置
export function updateSvipSetting(data) {
  return request({
    url: '/task/svipSetting',
    method: 'put',
    data: data
  })
}

// 删除svip默认配置
export function delSvipSetting(id) {
  return request({
    url: '/task/svipSetting/' + id,
    method: 'delete'
  })
}
