import getSortedKeys from './JSON'
import axios from 'axios'
import qs from 'qs'
const md5 = require('js-md5')

const generateSignature = (jsonData, signType = 'md5') => {
  let sortedKeys = getSortedKeys(jsonData)
  let str = String()
  sortedKeys.forEach(function (key) {
    if (key !== 'sign') {
      str = str + key + jsonData[key]
    }
  })
  return md5.base64(str).toUpperCase()
}

const checkLogin = () => {
  let localStorage = window.localStorage
  let username = localStorage.getItem('username')
  let token = localStorage.getItem('userToken')
  if (token !== undefined) {
    return new Promise((resolve, reject) => {
      axios.post('/user/check_login',
        qs.stringify(
          {
            username: username,
            userToken: token
          }
        )
      ).then(response => {
        let code = response.data.code
        resolve(code === 10005 || code === 10006)
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  } else {
    return false
  }
}

export { checkLogin }
export { generateSignature }
