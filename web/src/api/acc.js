import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/pms/api/account/list',
    method: 'get',
    params: data
  })
}

export function get(data) {
  return request({
    url: '/pms/api/account/get',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/pms/api/account/insert',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/pms/api/account/update',
    method: 'post',
    data
  })
}

export function addAccRole(data) {
  return request({
    url: '/pms/api/account/add-role',
    method: 'post',
    data
  })
}

export function getAccRole(data) {
  return request({
    url: '/pms/api/account/acc-role',
    method: 'get',
    params: data
  })
}