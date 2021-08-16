<?php

class Cart
{

    private $products;

    public function __construct()
    {
        $this->products = [];
    }



    public function getProduct()
    {
        return $this->products;
    }

    public function setProducts($products)
    {
        $this->products = $products;
    }






    public function addToCart($product, $number)
    {
        for ($i = 0; $i < $number; $i++) {
            $this->products[] = $product;
        }
    }


    public function numberOfItems()
    {
        return  sizeof($this->products);
    }

    public function priceOfItems()
    {

        $price = 0;
        foreach ($this->products as $key => $value) {
            $price += $value->getPrice();
        }

        return $price;
    }
}
