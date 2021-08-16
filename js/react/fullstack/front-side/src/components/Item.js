import axios from 'axios';
import React from 'react';

import DeleteIcon from '@material-ui/icons/Delete';
import styled from 'styled-components';

const MyDeleteIcon = styled(DeleteIcon)`
    color: red;
    cursor: pointer;
`;

const handleDelete = (item) => {
    axios.delete(`http://localhost:5000/people/${item.id}`);
};

const Item = ({ item }) => {

    return (
        <tr>
            <td>{item.id}</td>
            <td>{item.name}</td>
            <td>{item.surname}</td>
            <td>
                <MyDeleteIcon onClick={() => handleDelete(item)} />
            </td>
        </tr>
    );
};

export default Item;
