import request from '@/utils/request'

// 查询文章列列表
export function listNews(query) {
  return request({
    url: '/crm/news/list',
    method: 'get',
    params: query
  })
}

// 查询文章列详细
export function getNews(id) {
  return request({
    url: '/crm/news/' + id,
    method: 'get'
  })
}

// 新增文章列
export function addNews(data) {
  return request({
    url: '/crm/news',
    method: 'post',
    data: data
  })
}

// 修改文章列
export function updateNews(data) {
  return request({
    url: '/crm/news',
    method: 'put',
    data: data
  })
}

// 删除文章列
export function delNews(id) {
  return request({
    url: '/crm/news/' + id,
    method: 'delete'
  })
}

// 导出文章列
export function exportNews(query) {
  return request({
    url: '/crm/news/export',
    method: 'get',
    params: query
  })
}