<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>


    <link href="/css/style.css" rel="stylesheet"/>

    <style>
        .container{
            width: 300px;
        }

    </style>


</head>
<body>


<nav id="menu">

    <ul>
        <li><a href="/uczniowie">Uczniowie</a></li>
        <li><a href="/nauczyciele">Nauczyciele</a></li>
        <li><a href="/dodaj">Dodaj</a></li>


    </ul>

</nav>

<div class="container">

<h1>Dodaj</h1>
    <form action="/dodaj" method="post">
        @csrf

        <label>Imie:</label><br>
        <input type="text" name="imie"><br>

        <label>Nazwisko:</label><br>
        <input type="text" name="nazwisko"><br>


        <label>Status:</label><br>
        <select name="status">
            <option value="uczen">Uczeń</option>
            <option value="nauczyciel">Nauczyciel</option>
        </select>



        <input type="submit" value="Dodaj">
    </form>



</div>





</body>
</html>
