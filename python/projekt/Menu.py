import os
import msvcrt as m
import cursor as c
import numpy as np


def czysc():
    os.system("cls")




def wprowadzanie(w,k):
    c.show()
    m=np.array([[0 for x in range(k)] for y in range(w)], np.double)
    for i in range(w):
        for j in range(k):
            czysc()
            print("wprowadz element nr[%d][%d]" % (i, j))
            m[i][j] = np.double(float(input()))
    return m



def transpozycja(wy=np.array([])):
    c.show()
    czysc()
    print("Wybrano transpozycje maciezy")

    if (wy.size == 0):
        print("podaj ilosc kolumn  macierzy")
        k = int(input())
        print("podaj ilosc wierszy macierzy")
        w = int(input())

        if (w != k):
            c.hide()
            czysc()
            print("ilosc kolumn i wierszy nie moze byc rozna")
            print("<-- powrot")
            input()

            menu()

        print("wprowadz macierz")
        m = wprowadzanie(w, k)

    else:
        m=wy
        print(m)
        w=m.shape[0]
        k=m.shape[1]

    if(w!=k):
        c.hide()
        czysc()
        print("ilosc kolumn i wierszy nie moze byc rozna")
        print("<-- powrot")
        input()
        menu(wy)



    for i in range(w):
        for j in range(k):

            if (i <= j):
                continue


            a = m[i][j]

            m[i][j] = m[j][i]
            m[j][i] = a

    menu2(m)

def mnozenie(wy):
    c.show()
    czysc()
    print("Wybrano mnozenie maciezy")

    if (wy.size == 0):
        print("podaj ilosc kolumn pierwszej macierzy")
        k=int(input())
        print("podaj ilosc wierszy pierwszej macierzy")
        w = int(input())



        print("wprowadz pierwsza macierz")
        m1=wprowadzanie(w,k)
        czysc()
    else:
        m1=wy
        w=m1.shape[0]
        k=m1.shape[1]


    c.hide()
    print("pierwsza macierz:")
    print(m1)
    input()

    czysc()
    c.show()
    print("podaj ilosc kolumn drugiej macierzy")
    k2 = int(input())
    print("podaj ilosc wierszy drugiej macierzy")
    w2 = int(input())



    if (k != w2):
        c.hide()
        czysc()
        print("ilosc kolumn pierwszej maciezy musi byc rowna ilosci wierszy drugiej maciezy")
        print("<-- powrot")
        input()
        menu(wy)




    m2 = wprowadzanie(w2, k2)

    czysc()
    c.hide()
    print("druga macierz:")
    print(m2)
    input()


    wynik = np.array([[0 for x in range(k2)] for y in range(w)], np.double)

    sum=0
    for j in range(w):
        for j2 in range(k2):
            for i in range(k):

                a=m1[j][i]
                b=m2[i][j2]
                wyn=a * b
                sum += wyn

            wynik[j][j2]=sum
            sum=0


    menu2(wynik)

def mnozenie_skalar(wy):
    c.show()
    czysc()
    print("Wybrano mnozenie maciezy przez skalar")

    if (wy.size == 0):
        print("podaj ilosc kolumn macierzy")
        k=int(input())
        print("podaj ilosc wierszy macierzy")
        w = int(input())



        print("wprowadz  macierz")
        m1=wprowadzanie(w,k)
        czysc()
    else:
        m1=wy
        w=m1.shape[0]
        k=m1.shape[1]


    c.hide()
    print(" macierz:")
    print(m1)
    input()

    czysc()
    c.show()
    print("podaj skalar")
    s=np.double(float(input()))

    wynik = m1*s


    menu2(wynik)

def dzielenie_skalar(wy):
    c.show()
    czysc()
    print("Wybrano dzielenie maciezy przez skalar")

    if (wy.size == 0):
        print("podaj ilosc kolumn macierzy")
        k=int(input())
        print("podaj ilosc wierszy macierzy")
        w = int(input())



        print("wprowadz  macierz")
        m1=wprowadzanie(w,k)
        czysc()
    else:
        m1=wy
        w=m1.shape[0]
        k=m1.shape[1]


    c.hide()
    print(" macierz:")
    print(m1)
    input()

    czysc()
    c.show()
    print("podaj skalar")
    s=np.double(float(input()))

    wynik = m1/s


    menu2(wynik)

def dodawanie(wy):
    c.show()
    czysc()
    print("Wybrano dodawanie maciezy")

    if (wy.size == 0):
        print("podaj ilosc kolumn  macierzy")
        k=int(input())
        print("podaj ilosc wierszy macierzy")
        w = int(input())



        print("wprowadz pierwsza macierz")
        m1=wprowadzanie(w,k)
        czysc()
    else:
        m1=wy
        w=m1.shape[0]
        k=m1.shape[1]


    c.hide()
    print("pierwsza macierz:")
    print(m1)
    input()


    m2 = wprowadzanie(w, k)

    czysc()
    c.hide()
    print("druga macierz:")
    print(m2)
    input()

    wynik = m1+m2
    menu2(wynik)

def odejmowanie(wy):
    c.show()
    czysc()
    print("Wybrano odejmowanie maciezy")

    if (wy.size == 0):
        print("podaj ilosc kolumn  macierzy")
        k=int(input())
        print("podaj ilosc wierszy macierzy")
        w = int(input())



        print("wprowadz pierwsza macierz")
        m1=wprowadzanie(w,k)
        czysc()
    else:
        m1=wy
        w=m1.shape[0]
        k=m1.shape[1]


    c.hide()
    print("pierwsza macierz:")
    print(m1)
    input()


    m2 = wprowadzanie(w, k)

    czysc()
    c.hide()
    print("druga macierz:")
    print(m2)
    input()

    wynik = m1-m2
    menu2(wynik)


def menu2(wynik):
    czysc()
    print("wynik:")
    print(wynik)

    c.hide()
    print('\n')
    print("1 dzialanie na maciezy wynikowej")
    print("2 nowe dzialanie")
    print("3 wyjscie")

    while (0 == 0):

        opcja = int(m.getch())

        if (opcja == 1):
            menu(wynik)
            return

        if (opcja == 2):
            menu()
            return

        if (opcja == 3):
            exit()



def menu(wy=np.array([])):




    c.hide()
    czysc()

    if (wy.size != 0):
        print("dalsze dzialania na macierzy:")
        print(wy)
        print("\n")
        print("0 nowe dzialanie")

    print("1 transpozycja")
    print("2 dodawanie")
    print("3 odejmowanie")
    print("4 mnozenie")
    print("5 mnozenie ze skalarem")
    print("6 dzielenie ze skalarem")
    print("7 wyjscie")

    while(0==0):

        opcja = int(m.getch())

        if (opcja == 0 and wy.size != 0):
            menu()

        if (opcja == 1):
            transpozycja(wy)
            return

        if(opcja==2):
            dodawanie(wy)
            return

        if (opcja == 3):
            odejmowanie(wy)
            return

        if (opcja == 4):
            mnozenie(wy)
            return

        if (opcja == 5):
            mnozenie_skalar(wy)
            return

        if (opcja == 6):
            dzielenie_skalar(wy)
            return

        if (opcja == 7):
            exit()



menu()

































