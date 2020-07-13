import request from '@/utils/request'

// 查询企业清单列表
export function listCorporation(query) {
  return request({
    url: '/pbc/corporation/list',
    method: 'get',
    params: query
  })
}

// 查询企业清单详细
export function getCorporation(id) {
  return request({
    url: '/pbc/corporation/' + id,
    method: 'get'
  })
}

// 新增企业清单
export function addCorporation(data) {
  return request({
    url: '/pbc/corporation',
    method: 'post',
    data: data
  })
}

// 修改企业清单
export function updateCorporation(data) {
  return request({
    url: '/pbc/corporation',
    method: 'put',
    data: data
  })
}

// 删除企业清单
export function delCorporation(id) {
  return request({
    url: '/pbc/corporation/' + id,
    method: 'delete'
  })
}

// 导出企业清单
export function exportCorporation(query) {
  return request({
    url: '/pbc/corporation/export',
    method: 'get',
    params: query
  })
}