import axios from 'axios'

const HOST = 'http://localhost:9000/'
const ApiUsers = {}

ApiUsers.login = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'login', credentials)
    return {
      success: true,
      status: req.status,
      token: req.data
    }
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.register = async (user) => {
  try {
    const req = await axios.post(`${HOST}` + 'register', user)
    return {
      success: true,
      status: req.status,
      token: req.data
    }
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

export default ApiUsers
