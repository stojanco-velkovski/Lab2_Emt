import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks: () => {
        return axios.get('/books');
    },
    fetchCategories: () => {
        return axios.get('/categories');
    },
    fetchAuthors: () => {
        return axios.get('/authors');
    },
    getBook: (id) => {
        return axios.get(`/${id}`);
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    markAsTaken: (id) => {
        return axios.post(`/mark/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post('/add', {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/delete/${id}`);
    },
    loginUser: (username, password) => {
        return axios.post("/login", {
            "username": username,
            "password": password
        })
    },
    registerUser: (username, password, name, surname, role) => {
        return axios.post("/register", {
            "username": username,
            "password": password,
            "name": name,
            "surname": surname,
            "role": role
        })
    }
}

export default LibraryService;