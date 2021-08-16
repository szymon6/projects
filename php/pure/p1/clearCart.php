<?php
session_start();
unset($_SESSION['koszyk']);
header('Location:index.php');
?>