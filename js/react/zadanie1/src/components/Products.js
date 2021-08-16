import products from '../resources/products';
import '../style/ProductList.css'

import { Link } from "react-router-dom";






const Producs = () => {

    const productList = products.map(p => {

        return (

            <div key={p.id} className="product">
                <Link to={`/details/${p.id}`}><img src={p.photo}></img></Link>
                <p> {p.name}</p>
                <h3>Cena: {p.price}</h3>
            </div>
        )

    });


    return (
        <div className="products">
            {productList}
        </div>
    )

}


export default Producs

