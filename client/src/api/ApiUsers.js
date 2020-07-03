import axios from 'axios'
import UserHandler from '@/utils/UserHandler.js'

const HOST = 'http://localhost:9000/'
const ApiUsers = {}

const token = {
  headers: {
    Authorization: `Bearer ${UserHandler.getToken()}`
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

export default ApiUsers
