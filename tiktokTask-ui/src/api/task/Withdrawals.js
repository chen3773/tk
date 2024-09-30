import request from '@/utils/request'

// 查询提现记录列表
export function listWithdrawals(query) {
  return request({
    url: '/task/Withdrawals/list',
    method: 'get',
    params: query
  })
}

// 查询提现记录详细
export function getWithdrawals(id) {
  return request({
    url: '/task/Withdrawals/' + id,
    method: 'get'
  })
}

// 新增提现记录
export function addWithdrawals(data) {
  return request({
    url: '/task/Withdrawals',
    method: 'post',
    data: data
  })
}

// 修改提现记录
export function updateWithdrawals(data) {
  return request({
    url: '/task/Withdrawals',
    method: 'put',
    data: data
  })
}

// 删除提现记录
export function delWithdrawals(id) {
  return request({
    url: '/task/Withdrawals/' + id,
    method: 'delete'
  })
}
