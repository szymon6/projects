<?php

namespace App\Http\Controllers;

use App\Models\Rozwiazanie;
use App\Models\Slownik;
use Illuminate\Http\Request;

class VocabTestController extends Controller
{

    public function __construct(){
        $this->middleware('auth');
    }




    function create()
    {
        function randomGen($min, $max, $quantity) {
            $numbers = range($min, $max);
            shuffle($numbers);
            return array_slice($numbers, 0, $quantity);
        }


        $numbers =  randomGen(1,10,4);

        $rozwiazanie = new Rozwiazanie();
        $rozwiazanie->id_slowa1 = $numbers[0];
        $rozwiazanie->id_slowa2 = $numbers[1];
        $rozwiazanie->id_slowa3 = $numbers[2];
        $rozwiazanie->id_slowa4 = $numbers[3];



        $rozwiazanie->save();

        foreach ($numbers as $value) {
        $dane[]=Slownik::find($value);
        }


        return view('vocabTest/test',compact('dane'));
    }


    function check()
    {


        $rozwiazanie = Rozwiazanie::latest()->first();

        $odpopiedzi=[];
        $odpowiedziUzytkownika=[];

        $odpowiedziUzytkownika[]=$rozwiazanie->odp1 = request("slowo1");
        $odpowiedziUzytkownika[]=$rozwiazanie->odp2 = request("slowo2");
        $odpowiedziUzytkownika[]=$rozwiazanie->odp3 = request("slowo3");
        $odpowiedziUzytkownika[]=$rozwiazanie->odp4 = request("slowo4");

        $rozwiazanie->save();

        $odpopiedzi[]=Slownik::find($rozwiazanie->id_slowa1);
        $odpopiedzi[]=Slownik::find($rozwiazanie->id_slowa2);
        $odpopiedzi[]=Slownik::find($rozwiazanie->id_slowa3);
        $odpopiedzi[]=Slownik::find($rozwiazanie->id_slowa4);



        $dane=[];
        $punkty=0;
        for ($i = 0; $i < 4; $i++) {
            $dane[]=[
                'pl'=>$odpopiedzi[$i]->pl,
                "odpUzytkownika"=>$odpowiedziUzytkownika[$i],
                "ang"=>$odpopiedzi[$i]->ang,
                "dobrze"=>$odpowiedziUzytkownika[$i] == $odpopiedzi[$i]->ang
            ];

            if($odpowiedziUzytkownika[$i] == $odpopiedzi[$i]->ang) $punkty++;
        }



        return view('vocabTest/sprawdzenie',compact('dane'),compact('punkty'));
    }


}
