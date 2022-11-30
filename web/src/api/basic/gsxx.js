import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/core/api/customer/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/core/api/customer/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/core/api/customer/getInfo',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/core/api/customer/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/core/api/customer/delete',
    method: 'post',
    data
  })
}

export function gsBox(data){
  return request({
    url: '/core/api/customer/comboBox',
    method: 'get',
    params: data
  })
}

export function gsBox2(data){
  return request({
    url: '/core/api/customer/comboBox2',
    method: 'get',
    params: data
  })
}