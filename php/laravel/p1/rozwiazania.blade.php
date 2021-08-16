@extends('layouts/app')

@section('content')
<!DOCTYPE html>
<html>
<head>
    <style>
        #tabela {
            width: 100%;
            font-size: 30px;
            text-align: center;
        }

        #tabela td, #tabela th {
            padding: 8px;
        }

        #tabela th {
            padding-top: 12px;
            padding-bottom: 12px;
            color: white;
        }

        a{
            color: white;
            text-decoration: none;
        }
        a:hover{
            text-decoration: underline;
        }

    </style>

    <link href="/css/style.css" rel="stylesheet"/>

</head>
<body>


<div class='container'>

<table id="tabela">
    <tr>
        <th>Poprzednie rozwiązania:</th>
    </tr>

    @for ($i = 0; $i < count($dane); $i++)
    <tr>
        <td>  <a href="/rozwiazanie/{{$i}}">{{$dane[$i]->created_at}}</a> </td>
    </tr>
    @endfor
</table>

</div>

</body>
</html>
@endsection
