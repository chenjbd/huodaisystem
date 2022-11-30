import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/box/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/box/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/core/api/box/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/core/api/box/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/core/api/box/delete',
    method: 'post',
    data
  })
}

export function take(data) {
  return request({
    url: '/core/api/box/take',
    method: 'post',
    data
  })
}

export function zx(data) {
  return request({
    url: '/core/api/box/zx',
    method: 'post',
    data
  })
}

//装箱货物
export function loadZxhw(data) {
  return request({
    url: '/core/api/box/list-zxhw',
    method: 'get',
    params: data
  })
}