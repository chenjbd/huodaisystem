import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/inbound/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/inbound/insert',
    method: 'post',
    data
  })
}

export function getInfo(data) {
  return request({
    url: '/core/api/inbound/getInfo2',
    method: 'get',
    params: data
  })
}