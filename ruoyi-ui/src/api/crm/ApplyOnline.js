import request from '@/utils/request'

// 查询在线申请清单详细
export function apiGetApplyOnline(id) {
  return request({
    url: '/crm/ApplyOnline/api/' + id,
    method: 'get'
  })
}
// 查询在线申请清单列表
export function listApplyOnline(query) {
  return request({
    url: '/crm/ApplyOnline/list',
    method: 'get',
    params: query
  })
}

// 查询在线申请清单详细
export function getApplyOnline(id) {
  return request({
    url: '/crm/ApplyOnline/' + id,
    method: 'get'
  })
}

// 新增在线申请清单
export function addApplyOnline(data) {
  return request({
    url: '/crm/ApplyOnline',
    method: 'post',
    data: data
  })
}

// 修改在线申请清单
export function updateApplyOnline(data) {
  return request({
    url: '/crm/ApplyOnline',
    method: 'put',
    data: data
  })
}

// 删除在线申请清单
export function delApplyOnline(id) {
  return request({
    url: '/crm/ApplyOnline/' + id,
    method: 'delete'
  })
}

// 导出在线申请清单
export function exportApplyOnline(query) {
  return request({
    url: '/crm/ApplyOnline/export',
    method: 'get',
    params: query
  })
}