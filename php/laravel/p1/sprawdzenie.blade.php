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

    <h1>WYNIK:{{$punkty . "/" . 4}}</h1>


        @csrf


        <div class="wiersze">
            @for ($i = 0; $i < 4; $i++)
            <div class="wiersz">
                <div class="slowo">
                    {{$dane[$i]['pl']}}
                </div>

                @if ($dane[$i]['dobrze']==true)
                <div class="miejsce miejsceSprawdzenie">
                    <input style="color: green" type="text" disabled="disabled" value="{{$dane[$i]['odpUzytkownika']}}">
                </div>
                @else
                    <div class="miejsce miejsceSprawdzenie">
                        <input style="color: red" type="text" disabled="disabled" value="{{$dane[$i]['odpUzytkownika']}}">
                    </div>

                @endif


            </div>
            @endfor




        </div>

    <a href="/test"> <input type="submit" value="nowy test"> </a>



</div>


</body>
</html>
@endsection
