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






def zakres(xp,xk,krok, n ,f1, f2):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp

    for i in range(ilosc_krokow):
        a += krok
        blad(f1(a, n), f2(a), a)



def odchylenie(xp,xk,krok, n ,f1, f2):

    ilosc_krokow = abs(xp - xk) / krok
    ilosc_krokow = int(np.ceil(ilosc_krokow))

    a = xp
    suma =0
    for i in range(ilosc_krokow):
        a += krok
        w = abs(f1(a, n)- f2(a))
        suma+=w
    g=0
    s=suma/ilosc_krokow

    for i in range(ilosc_krokow):
        a += krok
        w = abs(f1(a, n)-f2(a))
        g+=(w-s)**2

    od=np.sqrt(g/ilosc_krokow)
    print("odchylenie: ", od)




n=10
krok = 0.5

print("dla sin:")
zakres(-3*np.pi,0,krok,n,tsin,np.sin)
odchylenie(-3*np.pi,0,krok,n,tsin,np.sin)
print("\n")
zakres(0,np.pi/4,krok,n,tsin,np.sin)
odchylenie(0,np.pi/4,krok,n,tsin,np.sin)
print("\n")
zakres(-3*np.pi,3*np.pi,krok,n,tsin,np.sin)
odchylenie(-3*np.pi,3*np.pi,krok,n,tsin,np.sin)

print("dla cos:")
zakres(-3*np.pi,0,krok,n,tcos,np.cos)
odchylenie(-3*np.pi,0,krok,n,tcos,np.cos)
print("\n")
zakres(0,np.pi/4,krok,n,tcos,np.cos)
odchylenie(0,np.pi/4,krok,n,tcos,np.cos)
print("\n")
zakres(-3*np.pi,3*np.pi,krok,n,tcos,np.cos)
odchylenie(-3*np.pi,3*np.pi,krok,n,tcos,np.cos)

print("dla exp:")
zakres(0,20,krok,n,texp,np.exp)
odchylenie(0,20,krok,n,texp,np.exp)
print("\n")
zakres(-np.pi,2,krok,n,texp,np.exp)
odchylenie(-np.pi,2,krok,n,texp,np.exp)

print("dla tan:")
zakres(-np.pi/2,0,krok,n,ttan,np.tan)
odchylenie(-np.pi/2,0,krok,n,ttan,np.tan)
print("\n")
zakres(0,np.pi/2,krok,n,ttan,np.tan)
odchylenie(0,np.pi/2,krok,n,ttan,np.tan)
print("\n")
zakres(-np.pi/2,np.pi/2,krok,n,ttan,np.tan)
odchylenie(-np.pi/2,np.pi/2,krok,n,ttan,np.tan)



















