import axios from 'axios'
import UserHandler from '@/utils/UserHandler.js'

const HOST = 'http://localhost:9000/'
const ApiEmployee = {}

const token = {
  headers: {
    Authorization: `Bearer ${UserHandler.getToken()}`
  }
}

ApiEmployee.getEmployee = async (idEmployee) => {
  try {
    const req = await axios.get(`${HOST}` + 'funcionario/verPerfil/' + idEmployee, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.editEmployee = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'funcionario/editarPerfil', credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getBooks = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/livros', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.registerBooks = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'biblioteca/registaLivro', credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getRequested = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/requisitados', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getReserved = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/reservados', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getLibrary = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.editLibrary = async (idEmployee, credentials) => {
  try {
    const req = await axios.get(`${HOST}` + 'editarBiblioteca/' + idEmployee, credentials, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

export default ApiEmployee
