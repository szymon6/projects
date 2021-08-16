import '../style/Cart.css'

import { Link } from "react-router-dom";


const Cart = ({ cart }) => {

    cart.forEach(element => {
        console.log(element);
    });

    const cartList = cart.map(item => {
        return (

            <div className="item">
                <p key={item.id}>
                    {item.name}
                </p>

                <img src={item.photo}></img>

            </div>
        )
    })

    let price = 0;
    cart.forEach(item => price += item.price);

    return (<div>

        <p className="test1">Łączna cena: {price}</p>
        <Link to="/">Powrót</Link>
        {cartList}

    </div>);
}

export default Cart;