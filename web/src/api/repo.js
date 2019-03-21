import axios from '@/libs/api.request'

export const getRepo = () => {
  return axios.request({
    url: '/v1/repo',
    method: 'get'
  })
}

export const getRepoSite = () => {
  return axios.request({
    url: '/v1/repoSite',
    method: 'get'
  })
}
