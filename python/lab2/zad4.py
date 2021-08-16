def jedenPrzezX(x):
    try:
        wynik=1/x
        return 0
    except ZeroDivisionError:
        print("dzielenie przez 0")
        return(-1)



print(jedenPrzezX(0))
print(jedenPrzezX(3))