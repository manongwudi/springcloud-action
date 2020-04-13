import axios from 'axios'

let base = '/api'
// 登录接口
export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data) }
// 获取实验信息列表接口
export const getExpInfosListPage = params => { return axios.get(`${base}/expInfo/listpage`, { params: params }) }
