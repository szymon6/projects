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





print("podaj x")
x=float(input())
n=10
print("tsin: ", tsin(x,n))
print("tcos: ", tcos(x,n))
print("texp: ", texp(x,n))
print("ttan: ", ttan(x,n))

















