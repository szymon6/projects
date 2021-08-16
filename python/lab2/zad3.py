import numpy as np

def suma(xp,xk,k):

    ilosc=xk/k
    ilosc = int(ilosc)

    sum=xp
    for i in range(ilosc):
        sum+=k
    return sum


def iloczyn(xp,xk):
    suma=0
    for i in range(xk):
        suma+=xp
    return suma


def jedenPrzezX(x):
    if x!=0:
        return 1/x

print(suma(-3.14,3.14,0.01))

print(iloczyn(2,4))

print(suma(jedenPrzezX(4),5,0.01))
print(iloczyn(jedenPrzezX(3),6))

print(suma(np.sin(7),3,0.01))
print(iloczyn(np.sin(23),9))


