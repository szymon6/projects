import matplotlib.pyplot as plt
import numpy as np

def interplin2p(x,xi,yi,xi1,yi1):

    return yi+(yi1-yi)*(x-x1)/(xi1-xi)

def obliczanie(x1,x2,y1,y2):
    krok=0.1
    ilosc_krokow=(x2-x1)/krok

    ilosc_krokow = int(ilosc_krokow)

    a=x1

    xtab=[]
    ytab=[]

    for i in range(0,ilosc_krokow,1):

        a+=krok

        wynik=interplin2p(a,x1,y1,x2,y2)
        print(wynik)
        xtab.append(a)
        ytab.append(wynik)

    plt.plot(xtab,ytab)
    plt.show()

    print("x:")
    print(xtab)
    print("y:")
    print(ytab)


x1=3
x2=10
y1=np.cos(1/x1)
y2=np.cos(1/x2)

obliczanie(x1,x2,y1,y2)

x1=3
x2=10
y1=np.exp(np.sin(x1))
y2=np.exp(np.sin(x2))

obliczanie(x1,x2,y1,y2)




