import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/pms/api/account/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/pms/api/account/userinfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/pms/api/account/logout',
    method: 'post'
  })
}
