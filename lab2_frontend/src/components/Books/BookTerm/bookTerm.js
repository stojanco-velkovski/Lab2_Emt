import React from 'react';
import {Link} from 'react-router-dom';


const BookTerm = (props) => {
    console.log(props.term)
    return (
        <tr key={props.term.id}>
            <td>{props.term.name}</td>
            <td>{props.term.author.name + " " + props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td>{props.term.category}</td>
            <td className={"text-right"}>
                <Link className={"btn btn-info"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>

                <a href="/" title={"Delete"} className={"btn btn-secondary m-2"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>

                <Link className={"btn btn-success"}
                      onClick={() => props.onReserve(props.term.id)}
                      to={'/books'}>
                    Mark as taken
                </Link>

            </td>
        </tr>
    )
}

export default BookTerm;