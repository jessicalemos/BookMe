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

ApiLibraries.registerEmployee = async (idLibrary, credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'registarResponsavel/' + idLibrary, credentials, token)
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

ApiLibraries.getProcessLibrary = async (idProcess) => {
  try {
    const req = await axios.get(`${HOST}` + 'processo/biblioteca/' + idProcess, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.removeLibrary = async (idLibrary) => {
  try {
    const req = await axios.post(`${HOST}` + 'delete/biblioteca/' + idLibrary, {}, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.searchLibrary = async (library) => {
  try {
    const req = await axios.get(`${HOST}` + 'bibliotecas/' + library, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.registerResponsible = async (idResponsible, credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'responsavel/registarFunc/' + idResponsible, credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.getEmployees = async (idResponsible) => {
  try {
    const req = await axios.get(`${HOST}` + 'responsavel/consultarFunc/' + idResponsible, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiLibraries.removeEmployee = async (idEmployee) => {
  try {
    const req = await axios.post(`${HOST}` + 'responsavel/removerFunc/' + idEmployee, {}, token)
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
