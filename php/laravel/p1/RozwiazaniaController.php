<?php

namespace App\Http\Controllers;

use App\Models\Rozwiazanie;
use App\Models\Slownik;
use Illuminate\Http\Request;

class RozwiazaniaController extends Controller
{


    public function __construct(){
        $this->middleware('auth');
    }


    function rozwiazania()
    {
        $dane = Rozwiazanie::all();
        return view('vocabTest/rozwiazania',compact('dane'));
    }

    function rozwiazanie($id){


        $rozwiazania = Rozwiazanie::all();
        $rozwiazanie = $rozwiazania[$id];


        $odpopiedzi=[];
        $odpowiedziUzytkownika=[];

        $odpowiedziUzytkownika[]=$rozwiazanie->odp1;
        $odpowiedziUzytkownika[]=$rozwiazanie->odp2;
        $odpowiedziUzytkownika[]=$rozwiazanie->odp3;
        $odpowiedziUzytkownika[]=$rozwiazanie->odp4;

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
