import request from '@/utils/request'

// 登录方法
export function apply(data) {
    return request({
        url: 'crm/ApplyOnline/applyLoan',
        method: 'post',
        data: data
    })
}