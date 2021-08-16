<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>


    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <!-- folder public-->
    <link href="/css/style.css" rel="stylesheet"/>


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

    @yield('zawartosc')




    <table class="tabela">
        <tr>

            <th>Imie</th>
            <th>Nazwisko</th>

        </tr>


        @foreach($dane as $item)

            <tr>
                <td> {{$item->imie}} </td>
                <td> {{$item->nazwisko}} </td>
            </tr>

        @endforeach

    </table>
    </div>




</body>
</html>
