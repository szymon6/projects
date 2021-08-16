<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Person;
use PhpParser\Node\Expr\ShellExec;

class SzkolaController extends Controller
{
    function uczniowie()
    {

        $dane = Person::select('*')->where('status','=','uczen')->get();
        return view("szkola/uczniowie", compact('dane'));


    }


    function nauczyciele()
    {

        $dane = Person::select('*')->where('status','=','nauczyciel')->get();
        return view("szkola/nauczyciele", compact('dane'));


    }


    function createDodaj()
    {
        return view("szkola/dodaj");
    }

    function saveDodaj()
    {
        $person = new Person();

        $person->imie=request('imie');
        $person->nazwisko=request('nazwisko');
        $person->status=request('status');

        $person->save();

        if($person->status=="uczen")
        return redirect("/uczniowie");
        else
            return redirect("/nauczyciele");

    }





}
