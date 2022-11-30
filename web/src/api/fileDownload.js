import request from '@/utils/request'
import jsFileDownload from 'js-file-download'

export function fileDownload (url, data, fileName) {
    request({
        method: 'post',
        url: url,
        timeout: 600000, // 请求超时时间，数据量多后台响应慢的情况可以调大点，没生效的话可能是vue.config.js里的配置影响了
        responseType: 'blob', // 返回类型为数据流
        data: data //参数
        }).then(res => {
            if (res && res.data) {
                // 调用js-file-download下载文件，第一个参数是数据流，第二个参数是文件名，我这后端返回时把文件名放到响应的header的filename字段中，所以用这种方式取出
                jsFileDownload(res.data, fileName)//decodeURIComponent(res.headers['filename'])
            }
        })
}