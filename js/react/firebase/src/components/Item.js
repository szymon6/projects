import firebase from '../firebase'
import React from 'react';

import DeleteIcon from '@material-ui/icons/Delete';
import styled from 'styled-components';

const MyDeleteIcon = styled(DeleteIcon)`
    color: red;
    cursor: pointer;
`;

const handleDelete = (item) => {
    firebase.database().ref(`people/${item.id}`).remove();
};

const Item = ({ item }) => {

    return (
        <tr>
            <td>{item.name}</td>
            <td>{item.surname}</td>
            <td>
                <MyDeleteIcon onClick={() => handleDelete(item)} />
            </td>
        </tr>
    );
};

export default Item;
