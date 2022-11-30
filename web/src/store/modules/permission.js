import { asyncRoutes, constantRoutes } from '@/router'
import Layout from '@/layout'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, menuData) {
    return new Promise(resolve => {
      let accessedRoutes = [];
      /*
      if (roles.includes('admin')) {
        accessedRoutes = asyncRoutes || []
      } else {
        accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      }
      */
      accessedRoutes = generateMenu(accessedRoutes, menuData)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

/**
 * 将后端菜单数据转换成路由对象格式
 * @param route 
 * @param menuData 
 */
export function generateMenu(route, menuData){
  menuData.forEach(item=>{
    const menu = {
      path: item.res_path,
      component: item.component === '#' ? Layout : loadView(item.component),
      hidden: item.zt == '0',//为0则隐藏
      redirect: item.redirect,
      children: [],
      name: item.res_no,
      meta: { title: item.res_name, icon: item.icon, affix: false }
    }
    if(item.child){
      generateMenu(menu.children, item.child)
    }
    route.push(menu)
  })
  return route;
}

/**
 * 静态路由懒加载
 * @param view 
 * @returns 
 */
export const loadView = (view)=>{
  return (resolve)=>require([`@/views/${view}.vue`], resolve)
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
