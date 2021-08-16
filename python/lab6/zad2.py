import numpy as np
import matplotlib.pyplot as plt


def suma(x,y,s):

    suma=0

    for i in range(40):
        suma+=y[i]*(x[i]-s)

    return suma



def srednia(x):
    suma=0
    ilosc=0

    for i in x:
        suma +=i
        ilosc +=1
    return suma/ilosc


def bladB(x,xd):
     blad=np.abs(x-xd)
     return  blad

def bladW(x,xd):
    blad=bladB(x,xd)/xd
    return blad




x = np.arange(-20,20) #zakres wartosci parametru funkcji
#model rzeczywisty (idealny + blad)
y = 0.5*x**3+1.5*x**2-2.5*x +69+np.random.normal(scale=200,size=len(x))

#print(x)
#print(y)


sredniaX=srednia(x)
sredniaY=srednia(y)

g=suma(x,y,srednia(x))
d=suma(x,x,srednia(x))

b=g/d
a=srednia(y)-srednia(x)*b


xf=[i for i in np.arange(-20,20)]
yf=[b*i+a for i in np.arange(-20,20)]

bladBw=[]
bladWz=[]



for i in range(20):
    bladBw.append(bladB(yf[i], y[i]))
    bladWz.append(bladW(yf[i],y[i]))


print("Blad bezwzgledny:")
print(bladBw)

print("Blad wzgledny:")
print(bladWz)






















