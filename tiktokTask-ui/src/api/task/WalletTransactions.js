import request from '@/utils/request'

// 查询用户钱包交易记录列表
export function listWalletTransactions(query) {
  return request({
    url: '/task/WalletTransactions/list',
    method: 'get',
    params: query
  })
}

// 查询用户钱包交易记录详细
export function getWalletTransactions(id) {
  return request({
    url: '/task/WalletTransactions/' + id,
    method: 'get'
  })
}

// 新增用户钱包交易记录
export function addWalletTransactions(data) {
  return request({
    url: '/task/WalletTransactions',
    method: 'post',
    data: data
  })
}

// 修改用户钱包交易记录
export function updateWalletTransactions(data) {
  return request({
    url: '/task/WalletTransactions',
    method: 'put',
    data: data
  })
}

// 删除用户钱包交易记录
export function delWalletTransactions(id) {
  return request({
    url: '/task/WalletTransactions/' + id,
    method: 'delete'
  })
}
