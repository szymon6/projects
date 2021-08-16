@extends('layouts/app')

@section('content')

<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link href="/css/style.css" rel="stylesheet"/>

</head>
<body>





<div class='container'>

    <h1>TEST SŁÓW</h1>

    <form action="/test" method="post">
        @csrf


        <div class="wiersze">
            @for ($i = 0; $i < 4; $i++)
            <div class="wiersz">
                <div class="slowo">
                    {{$dane[$i]["pl"]}}
                </div>
                <div class="miejsce">
                    <input type="text"  name="slowo{{$i+1}}">
                </div>

            </div>
            @endfor




        </div>

        <input type="submit" value="sprawdź">

    </form>

</div>



</body>
</html>
@endsection
