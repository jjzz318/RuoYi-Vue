import request from '@/utils/request'

// 登录方法
export function apply(data) {
    return request({
        url: '/dev-api/openApi/user/login2',
        method: 'post',
        data: data
    })
}