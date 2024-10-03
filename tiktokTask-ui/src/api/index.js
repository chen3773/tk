import request from '@/utils/request'

// 查询三级分佣百分比列表
export function getHomePage() {
  return request({
    url: '/admin/getHomePage',
    method: 'get'
  })
}
