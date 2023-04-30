import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Categories from '../Categories/categories';
import Books from '../Books/ListBook/books';
import Header from '../Header/header';
import BookAdd from '../Books/AddBook/addBook';
import LibraryService from "../../repository/libraryRepository";
import BookEdit from "../Books/EditBook/editBook";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {},
            loggedInUser: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>

                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd categories={this.state.categories}
                                     authors={this.state.authors}
                                     onAddBook={this.addBook}/>}/>

                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit categories={this.state.categories}
                                      authors={this.state.authors}
                                      onEditBook={this.editBook}
                                      book={this.state.selectedBook}/>}/>

                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                   onDelete={this.deleteBook}
                                   onEdit={this.getBook}
                                   onReserve={this.markAsTaken}
                            />}
                        />
                        <Route path={"/"} exact render={() =>
                            <Books books={this.state.books}
                                   onDelete={this.deleteBook}
                                   onEdit={this.getBook}
                                   onReserve={this.markAsTaken}
                            />}
                        />

                        <Redirect to={"/"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadCategories();
        this.loadAuthors();
        this.loadBooks();
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    markAsTaken = (id) => {
        LibraryService.markAsTaken(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    loginUser = (username, password) => {
        LibraryService.loginUser(username, password)
            .then((data) => {
                this.setState({
                    loggedInUser: data.data
                })
            });
    }

    registerUser = (username, password, name, surname, role) => {
        LibraryService.registerUser(username, password, name, surname, role)
            .then((data) => {

            })
    }
}

export default App;