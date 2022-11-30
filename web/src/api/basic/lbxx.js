import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/dic/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/dic/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/core/api/dic/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/core/api/dic/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/core/api/dic/delete',
    method: 'post',
    data
  })
}

export function listDic(data) {
  return request({
    url: '/core/api/dic/queryDic',
    method: 'get',
    params: data
  })
}
