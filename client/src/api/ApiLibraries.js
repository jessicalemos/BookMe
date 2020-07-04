import axios from 'axios'
import UserHandler from '@/utils/UserHandler.js'

const HOST = 'http://localhost:9000/'
const ApiLibraries = {}

const token = {
  headers: {
    Authorization: `Bearer ${UserHandler.getToken()}`
  }
}

ApiLibraries.libraries = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'bibliotecas', token)
    console.log(token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.registerLibrary = async (credentials) => {
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

ApiLibraries.registerEmployee = async (credentials) => {
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

ApiLibraries.responsibleInfo = async (idBiblioteca) => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/' + idBiblioteca + '/responsavel', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.editResponsible = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'editarResponsavel', credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

export default ApiLibraries
