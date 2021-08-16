<?php

session_start();
require_once("connect.php");
require_once("Product.php");
require_once("Cart.php");

$polaczenie = new mysqli($host, $db_user, $db_password, $db_name);

$sql = "select * from produkt";

$koszyk;

if (isset($_SESSION['koszyk']))
    $koszyk = unserialize($_SESSION['koszyk']);
else
    $koszyk = new Cart();



?>


<!DOCTYPE html>
<html lang="pl">

<head>
    <meta charset="utf-8" />
    <title> </title>

    <link rel="stylesheet" href="style.css">


</head>


<body>



    <div id="koszyk">

        <div> ilość płyt: <?php echo $koszyk->numberOfItems() ?></div>
        <div>cena: <?php echo $koszyk->priceOfItems() ?> zł</div>

        <input type="button" value="wyczysc koszyk" onclick="location='clearCart.php'">


    </div>


    <?php
    $result = $polaczenie->query($sql);

    $i = 0;
    while ($row = $result->fetch_assoc()) {


    echo "
    <div class=\"produkt\">
    
        <div class=\"zdjecie\">
        <img src=\"" . $row["img_dir"] . " \">
        </div>

        <div class=\"info\">
            <h2>" . $row['author'] . "</h2>
            <p>" . $row['title'] . "</p>
            <input type=\"button\" value=\"zobacz\" onclick=\"location='stronaProduktu.php?id=" . $row["id"] . "'\">
        </div>
   
    </div>
    
    ";
    }
    ?>


</body>

</html>