import request from '@/utils/request'

// 查询企业清单列表
export function listCorporation(query) {
  return request({
    url: '/rbc/corporation/list',
    method: 'get',
    params: query
  })
}

// 查询企业清单详细
export function getCorporation(id) {
  return request({
    url: '/rbc/corporation/' + id,
    method: 'get'
  })
}

// 新增企业清单
export function addCorporation(data) {
  return request({
    url: '/rbc/corporation',
    method: 'post',
    data: data
  })
}

// 修改企业清单
export function updateCorporation(data) {
  return request({
    url: '/rbc/corporation',
    method: 'put',
    data: data
  })
}

// 删除企业清单
export function delCorporation(id) {
  return request({
    url: '/rbc/corporation/' + id,
    method: 'delete'
  })
}

// 导出企业清单
export function exportCorporation(query) {
  return request({
    url: '/rbc/corporation/export',
    method: 'get',
    params: query
  })
}