import request from '@/utils/request'

export function searchUser(name) {
  return request({
    url: '/vue-element-admin/search/user',
    method: 'get',
    params: { name }
  })
}

export function transactionList(query) {
  /*
  return request({
    url: '/vue-element-admin/transaction/list',
    method: 'get',
    params: query
  })
  */
  return  {
    code: '000000',
    data: {
      total: 20,
      items: [{
        order_no: '12345678',
        timestamp: new Date(),
        username: '张三',
        price: '1000.00',
        status: '1'
      },
      {
        order_no: '12345678',
        timestamp: new Date(),
        username: '张三',
        price: '1000.00',
        status: '1'
      },
      {
        order_no: '12345678',
        timestamp: new Date(),
        username: '张三',
        price: '1000.00',
        status: '1'
      },
      {
        order_no: '12345678',
        timestamp: new Date(),
        username: '张三',
        price: '1000.00',
        status: '1'
      }]
    }
  }
}
