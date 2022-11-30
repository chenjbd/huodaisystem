import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/outbound/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/outbound/insert',
    method: 'post',
    data
  })
}

export function getInfo(data) {
  return request({
    url: '/core/api/outbound/getInfo',
    method: 'get',
    params: data
  })
}
