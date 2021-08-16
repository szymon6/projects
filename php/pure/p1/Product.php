<?php

class Product {

private $id;
private $author;
private $title;
private $price;
private $img_dir;
private $availableQuantity;





public function __construct($id, $author, $title, $price, $img_dir, $availableQuantity){
$this->id = $id;
$this->author = $author;
$this->title = $title;
$this->price = $price;
$this->img_dir = $img_dir;
$this->availableQuantity =$availableQuantity;

}

public function getId() {
    return $this->id;
}

public function getAuthor() {
    return $this->author;
}

public function getTitle() {
    return $this->title;
}

public function getPrice() {
    return $this->price;
}

public function getImg_dir() {
    return $this->img_dir;
}


public function getAvailableQuantity() {
    return $this->availableQuantity;
}








}
