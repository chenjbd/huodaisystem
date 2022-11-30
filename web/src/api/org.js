import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/pms/api/org/list',
    method: 'get',
    params: data
  })
}

export function loadTree(data) {
  return request({
    url: '/pms/api/org/getOrgTree',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/pms/api/org/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/pms/api/org/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/pms/api/org/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/pms/api/org/delete',
    method: 'post',
    data
  })
}
