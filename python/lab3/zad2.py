import numpy as np

def silnia(x):
    silnia1=1

    for i in range(1,x+1):
        silnia1*=i

    return silnia1

def tsin(x,n):
    suma=0
    for i in range(n+1):
        w=(-1)**i
        w/=silnia(2*i+1)
        w*=x**(2*i+1)
        suma+=w
    return(suma)

def tcos(x, n):
    suma = 0
    for i in range(n + 1):
        w = (-1)**i
        w /= silnia(2*i)
        w *= x ** (2 * i)
        suma += w
    return suma

def texp(x, n):
    suma = 0
    for i in range(n + 1):
        w = x**i
        w /= silnia(i)
        suma += w
    return suma

def ttan(x, n):
    wynik = tsin(x,n)/tcos(x,n)
    return wynik


def blad(x,xd, p):
    w = abs(x-xd)
    b = w/xd
    print("blad wzgedny:%f, blad bezwzgedny:%f, dla x: %f " % (w,xd,p))






def zakresS(xp,xk,krok, n):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp
    for i in range(ilosc_krokow):
        a += krok
        blad(tsin(a, n), np.sin(a), a)

def zakresC(xp,xk,krok, n):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp
    for i in range(ilosc_krokow):
        a += krok
        blad(tcos(a, n), np.cos(a), a)

def zakresE(xp,xk,krok, n):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp
    for i in range(ilosc_krokow):
        a += krok
        blad(texp(a, n), np.exp(a), a)

def zakresT(xp,xk,krok, n):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp
    for i in range(ilosc_krokow):
        a += krok
        blad(ttan(a, n), np.tan(a), a)






n=10
krok = 0.5

print("dla sin:")
zakresS(-3*np.pi,0,krok,n)
print("\n")
zakresS(0,np.pi/4,krok,n)
print("\n")
zakresS(-3*np.pi,3*np.pi,krok,n)

print("dla cos:")
zakresC(-3*np.pi,0,krok,n)
print("\n")
zakresC(0,np.pi/4,krok,n)
print("\n")
zakresC(-3*np.pi,3*np.pi,krok,n)

print("dla exp:")
zakresE(0,20,krok,n)
print("\n")
zakresE(-np.pi,2,krok,n)

print("dla tan:")
zakresT(-np.pi/2,0,krok,n)
print("\n")
zakresT(0,np.pi/2,krok,n)
print("\n")
zakresT(-np.pi/2,np.pi/2,krok,n)



















