<?php
session_start();
require_once("Product.php");
require_once("Cart.php");

$ilosc = $_POST["ilosc"];
$produkt = unserialize($_SESSION["produkt"]);
unset($_SESSION["produkt"]);

if ($ilosc == "" || !((int)$ilosc == $ilosc && (int)$ilosc > 0)) {
  $_SESSION["blad"] = "<div id=blad>Bląd</div>";
  header("Location:stronaProduktu.php?id=" . $produkt->getId());
  exit();
} else if ((int)$ilosc > $produkt->getAvailableQuantity()) {

  $_SESSION["blad"] = "<div id=blad>Zbyt mała ilość</div>";
  header("Location:stronaProduktu.php?id=" . $produkt->getId());
  exit();
}


$koszyk;
if (isset($_SESSION['koszyk']))
  $koszyk = unserialize($_SESSION['koszyk']);
else
  $koszyk = new Cart();




$koszyk->addToCart($produkt, $ilosc);

$_SESSION['koszyk'] = serialize($koszyk);

header('Location:index.php');
echo ($produkt->getAuthor());
