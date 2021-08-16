<?php

require_once("connect.php");
require_once("Product.php");
session_start();

$polaczenie = new mysqli($host, $db_user, $db_password, $db_name);


$id = $_GET['id'];
$sql = "select * from produkt where id='" . $id . "'";
$result = $polaczenie->query($sql);

$row = $result->fetch_assoc();
$produkt = new Product($row['id'], $row['author'], $row['title'], $row['price'], $row['img_dir'], $row['availableQuantity']);



?>


<!DOCTYPE html>
<html lang="pl">

<head>
    <meta charset="utf-8" />
    <title> </title>

    <link rel="stylesheet" href="styleStronaProduktu.css">


</head>


<body>





    <div class="produkt">

        <div id="powrot">
            <a href="index.php">
                <h3> POWRÓT </h3>
            </a>
        </div>


        <?php

        $_SESSION["produkt"] = serialize($produkt);

        echo "
        <div class=\"zdjecie\">
        <img src=\"" . $produkt->getImg_dir() . " \">
        </div>

        <div class=\"info\">
            <h2>" . $produkt->getAuthor() . "</h2>
            <p>" . $produkt->getTitle() . "</p></br>



            <form action=\"addToCart.php\" method=\"post\">
            <p> cena: " . $produkt->getPrice() . " zł </p> 
            <p> ilość dostępnych sztuk: " . $produkt->getAvailableQuantity() . " </p> 

            <input type=\"text\" id=\"ilosc\" name=\"ilosc\" placeholder=\"ilość\">
            
            <input type=\"submit\" value=\"dodaj do koszyka\">
            </form>
        </div>
        ";
        if (isset($_SESSION['blad']))    echo $_SESSION['blad'];
        unset($_SESSION["blad"]);
        ?>


    </div>












    </div>


</body>

</html>