import './App.css';
import { useState } from 'react'
import producsts from './resources/products'
import Producs from './components/Products'
import ProductDetails from './components/ProductDetails'
import Cart from './components/Cart'

import { BrowserRouter, Route } from 'react-router-dom'



function App() {

  const [cart, setCart] = useState([]);

  const addToCart = (id) => {

    const product = producsts.find(item => item.id === id)
    let tmp = [...cart, product];
    setCart(tmp);

    console.log(tmp);
  }

  return (
    <BrowserRouter>

      <Route path="/" exact component={Producs} />
      <Route path="/details/:id" exact render={(props) => <ProductDetails {...props} addToCart={addToCart} />} />
      <Route path="/cart" exact render={() => <Cart cart={cart} />} />

    </BrowserRouter>
  );
}

export default App;
