import axios from 'axios'
import UserHandler from '@/utils/UserHandler.js'

const HOST = 'http://localhost:9000/'
const ApiAdmin = {}

const token = {
  headers: {
    Authorization: `Bearer ${UserHandler.getToken()}`
  }
}

ApiAdmin.libraries = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'bibliotecas', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiAdmin.registerLibrary = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'registarBiblioteca', credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiAdmin.registerEmployee = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'registarResponsavel', credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

export default ApiAdmin
