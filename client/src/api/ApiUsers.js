import axios from 'axios'
import UserHandler from '@/utils/UserHandler.js'

const HOST = 'http://localhost:9000/'
const ApiUsers = {}

let token = {
  headers: {
    Authorization: `Bearer ${UserHandler.getToken()}`
  }
}

ApiUsers.refresh = async (newtoken) => {
  token = {
    headers: {
      Authorization: `Bearer ${newtoken}`
    }
  }
}

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

ApiUsers.editRequester = async (credentials) => {
  try {
    const req = await axios.post(`${HOST}` + 'editar', credentials, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getRequester = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'requisitante/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getReserved = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'reservados/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getReturned = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'devolvidos/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getRequested = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'requisitados/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getRequested = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'requisitados/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getBooks = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'livros/', token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getBook = async (idBook) => {
  try {
    const req = await axios.get(`${HOST}` + 'livro/' + idBook, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getProcesses = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'processos/requisitante/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.removeReservation = async (info) => {
  try {
    const req = await axios.post(`${HOST}` + 'cancelar/reserva', info, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.renewRequest = async (info) => {
  try {
    const req = await axios.post(`${HOST}` + 'renovar/reserva', info, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getAutores = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'Autores', token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getEditores = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'Editores', token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getNotifications = async (idRequistante) => {
  try {
    const req = await axios.get(`${HOST}` + 'notificacoes/requisitante/' + idRequistante, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.searchBook = async (title) => {
  try {
    const req = await axios.get(`${HOST}` + 'livros/' + title, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.librariesBook = async (isbn) => {
  try {
    const req = await axios.get(`${HOST}` + 'bibliotecas/livro/' + isbn, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.bookAvailability = async (info) => {
  try {
    const req = await axios.post(`${HOST}` + 'disponibilidade/reservar', info, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.requestBook = async (idRequisitante, process) => {
  try {
    const req = await axios.post(`${HOST}` + 'reservar/' + idRequisitante, process, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.filterBooks = async (filters) => {
  try {
    const req = await axios.post(`${HOST}` + 'livros/filtro', filters, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.getBibliotecas = async () => {
  try {
    const req = await axios.get(`${HOST}` + 'consulta/bibliotecas', token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

ApiUsers.searchLibrary = async (library) => {
  try {
    const req = await axios.get(`${HOST}` + 'search/bibliotecas/' + library, token)
    return req.data
  } catch (e) {
    console.error(e)
    return {
      success: false
    }
  }
}

export default ApiUsers
