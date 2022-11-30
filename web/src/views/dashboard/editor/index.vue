<template>
  <div class="page-container">
      <el-row>
          <el-col :span="24">
              <div class="page-panel">
                <el-descriptions title="登录信息">
                  <el-descriptions-item label="登录账号">
                    <el-tag>{{ userinfo.userNo }}</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item label="用户姓名">
                    <el-tag type="success">{{ userinfo.userName }}</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item label="所属单位">
                      <el-tag type="warning">{{ userinfo.orgName}}</el-tag>
                  </el-descriptions-item>
                </el-descriptions>
              </div>
          </el-col>
      </el-row>
      <el-row style="margin-top: 15px;">
          <el-col>
              <div class="page-panel">
                  <div id="chart" style="width: 100%; height: calc(100vh - 280px);">

                  </div>
              </div>
          </el-col>
      </el-row>  
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { ndjhtj } from '@/api/oltp/cx'
export default {
  name: 'DashboardEditor',
  data() {
    return {
      chart: null
    }
  },
  computed: {
    ...mapGetters([
      'userinfo'
    ])
  },
  mounted() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
    init(){
      const now = new Date()
      ndjhtj({r:now}).then(res=>{
        const { data } = res
        this.initChart(data)
      })
    },
    initChart(chartData) {
      let x = []
      let c1 = []
      let c2 = []
      //let p1 = []
      //let p2 = []
      chartData.forEach((item,index)=>{
        x.push(item.m + '月')
        c1.push(item.cbm)
        //p1.push(item.pks)
        c2.push(item.cbm1)
        //p2.push(item.pks1)
      })
      const c = document.getElementById("chart")
      this.chart = echarts.init(c, 'macarons')
      this.chart.setOption({
        title: {
          text: '本年度进出仓统计表'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['进仓立方数', '出仓立方数']//,'进仓件数', '出仓件数'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: x
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '进仓立方数',
            type: 'line',
            data: c1
          },
          {
            name: '出仓立方数',
            type: 'line',
            data: c2
          }
          /*
          ,{
            name: '进仓件数',
            type: 'bar',
            data: p1
          },
          {
            name: '出仓件数',
            type: 'bar',
            data: p2
          }
          */
        ]
      })
    }
  }
}
</script>
<style lang="scss" src="@/styles/cust.scss" scoped></style>