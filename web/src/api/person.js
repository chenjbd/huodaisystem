import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/person/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/person/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/core/api/person/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/core/api/person/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/core/api/person/delete',
    method: 'post',
    data
  })
}
