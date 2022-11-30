import request from '@/utils/request'

export function loadData(data) {
  return request({
    url: '/pms/api/role/list',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: '/pms/api/role/insert',
    method: 'post',
    data
  })
}

export function get(data) {
  return request({
    url: '/pms/api/role/get',
    method: 'get',
    params: data
  })
}

export function update(data) {
  return request({
    url: '/pms/api/role/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/pms/api/role/delete',
    method: 'get',
    params: data
  })
}

export function loadTree(data) {
  return request({
    url: '/pms/api/role/res-tree',
    method: 'get',
    params: data
  })
}

export function addRes(data){
  return request({
    url: '/pms/api/role/add-res',
    method: 'post',
    data
  })
}
