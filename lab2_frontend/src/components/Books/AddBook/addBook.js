import React from 'react';
import { useHistory } from 'react-router-dom';

const BookAdd = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: "",
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const author = formData.author;
        const availableCopies = formData.availableCopies;

        if(author!==null && author!=="" && category!==null && category!==""){
            props.onAddBook(name, category, author, availableCopies);
            history.push("/books");
        }else{
            alert("Please choose author and category!");
        }
    }

    return(
        <div className="row mt-5 d-flex justify-content-center">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group mb-2">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter name"
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-2">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="Available Copies"
                               required
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-2">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange} required>
                            <option selected={true}></option>
                            {props.authors.map((term) => {
                                return <option selected={null} value={term.id}>
                                    {term.name + " " + term.surname}</option>
                            })}
                        </select>
                    </div>

                    <div className="form-group mb-2">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange} required>
                            <option selected={true}></option>
                            {props.categories.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>

                    <button id="submit" type="submit" className="col-12 btn btn-success mt-2">Submit</button>
                </form>
            </div>
        </div>
    )

}

export default BookAdd;