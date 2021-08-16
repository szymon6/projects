<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateRozwiazaniaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('rozwiazania', function (Blueprint $table) {
            $table->id();
            $table->integer('id_slowa1');
            $table->string('odp1')->nullable();;

            $table->integer('id_slowa2');
            $table->string('odp2')->nullable();;

            $table->integer('id_slowa3');
            $table->string('odp3')->nullable();

            $table->integer('id_slowa4');
            $table->string('odp4')->nullable();;


            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('rozwiazania');
    }
}
