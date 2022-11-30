import request from '@/utils/request'
import { fileDownload } from '@/api/fileDownload'

export function ckxxcx(data) {
  return request({
    url: '/core/api/cxtj/ckxxcx',
    method: 'get',
    params: data
  })
}

export function sjlhtj(data) {
  return request({
    url: '/core/api/cxtj/sjlhtj',
    method: 'get',
    params: data
  })
}

export function zxgzhtj(data) {
  return request({
    url: '/core/api/cxtj/zxgzhtj',
    method: 'get',
    params: data
  })
}

export function jcgstj(data) {
  return request({
    url: '/core/api/cxtj/jcgstj',
    method: 'get',
    params: data
  })
}

export function tcfytj(data) {
  return request({
    url: '/core/api/cxtj/tcfytj',
    method: 'get',
    params: data
  })
}

export function zxgzhtjExcel(data, fileName){
  fileDownload('/core/api/cxtj/zxgzhtj-excel', data, fileName);
}

export function zxqdExcel(data, fileName){
  fileDownload('/core/api/cxtj/zxqd-excel', data, fileName);
}

//年度进货统计，首页使用
export function ndjhtj(data) {
  return request({
    url: '/core/api/cxtj/ndjhtj',
    method: 'get',
    params: data
  })
}