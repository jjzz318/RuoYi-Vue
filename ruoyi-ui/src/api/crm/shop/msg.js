import request from '@/utils/request'

// 获取验证码
export function sendVerifyCode(query) {
    return request({
      url: '/crm/ApplyOnline/sendVerifyCode',
      method: 'get',
      params: query
    })
  }