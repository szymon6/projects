import React from 'react';
import { Table } from 'react-bootstrap';
import Item from './Item';



const DataTable = ({ data }) => {
    const rows = data.map((item) => (
        <Item key={item.id} item={item} />
    ));

    return (
        <div>
            <Table bordered hover>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>{rows}</tbody>
            </Table>
        </div>
    );
};

export default DataTable;
