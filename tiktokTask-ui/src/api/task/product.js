import request from '@/utils/request'

// 查询众筹产品列表
export function listProduct(query) {
  return request({
    url: '/task/product/list',
    method: 'get',
    params: query
  })
}

// 查询众筹产品详细
export function getProduct(productId) {
  return request({
    url: '/task/product/' + productId,
    method: 'get'
  })
}

// 新增众筹产品
export function addProduct(data) {
  return request({
    url: '/task/product',
    method: 'post',
    data: data
  })
}

// 修改众筹产品
export function updateProduct(data) {
  return request({
    url: '/task/product',
    method: 'put',
    data: data
  })
}

// 删除众筹产品
export function delProduct(productId) {
  return request({
    url: '/task/product/' + productId,
    method: 'delete'
  })
}
