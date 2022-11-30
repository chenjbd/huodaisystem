import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/driver/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/driver/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/core/api/driver/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/core/api/driver/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/core/api/driver/delete',
    method: 'post',
    data
  })
}

export function sjBox(data){
  return request({
    url: '/core/api/driver/comboBox',
    method: 'get',
    params: data
  })
}