import products from "../resources/products";
import style from "../style/ProductDetails.module.css"

import { Link } from "react-router-dom";

const ProductDetails = ({ match, addToCart }) => {

    const product = products.find(p => p.id == match.params.id)


    return (
        <div className={style.container}>

            <Link to="/cart" className={style.button}>Koszyk</Link>
            <Link to="/" className={style.button}>Powrót</Link>

            <div className={style.details}>
                <img src={product.photo} />
                <div>
                    <h4>Nazwa: {product.name}</h4>
                    <p>Cena: {product.price}</p>
                    <p>Opis: {product.description}</p>
                    <p className={style.button} onClick={() => addToCart(product.id)}> Dodaj do koszyka</p>

                </div>
            </div>
            <div className="elo1"></div>
        </div>
    )
}

export default ProductDetails;