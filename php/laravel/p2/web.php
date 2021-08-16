<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return redirect("/uczniowie");
});


Route::get('/uczniowie',"SzkolaController@uczniowie");
Route::get('/nauczyciele',"SzkolaController@nauczyciele");

Route::get('/dodaj',"SzkolaController@createDodaj");
Route::post('/dodaj',"SzkolaController@saveDodaj");
