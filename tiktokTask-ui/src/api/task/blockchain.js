import request from '@/utils/request'

// 查询区块链名称列表
export function listBlockchain(query) {
  return request({
    url: '/task/blockchain/list',
    method: 'get',
    params: query
  })
}

// 查询区块链名称详细
export function getBlockchain(id) {
  return request({
    url: '/task/blockchain/' + id,
    method: 'get'
  })
}

// 新增区块链名称
export function addBlockchain(data) {
  return request({
    url: '/task/blockchain',
    method: 'post',
    data: data
  })
}

// 修改区块链名称
export function updateBlockchain(data) {
  return request({
    url: '/task/blockchain',
    method: 'put',
    data: data
  })
}

// 删除区块链名称
export function delBlockchain(id) {
  return request({
    url: '/task/blockchain/' + id,
    method: 'delete'
  })
}
