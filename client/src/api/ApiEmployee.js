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

ApiEmployee.getRequested = async (email) => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/requisitados?email=' + email, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getReserved = async (email) => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/reservados?email=' + email, token)
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

ApiEmployee.getEditores = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/livros/editores', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.getAutores = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/livros/autores', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.filterBooks = async (filters) => {
  try {
    const req = await axios.post(`${HOST}` + 'biblioteca/livros/filtro', filters, token)
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
    const req = await axios.get(`${HOST}` + 'biblioteca/consultar', token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.searchBook = async (title) => {
  try {
    const req = await axios.get(`${HOST}` + 'biblioteca/livros/titulo/' + title, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.returnBook = async (idProcess) => {
  try {
    const req = await axios.post(`${HOST}` + 'biblioteca/devolve/' + idProcess, {}, token)
    console.log(req)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiEmployee.requisitionBook = async (idProcess) => {
  try {
    const req = await axios.post(`${HOST}` + 'biblioteca/requisita/' + idProcess, {}, token)
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
