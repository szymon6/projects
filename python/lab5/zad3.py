from scipy.interpolate import interp1d
import numpy as np
import matplotlib.pyplot as plt

x = np.arange(0,10)
y = np.array([-1,20,11,5,-10,20,8,1,0,8])

def wiel(st):
    wielomian=np.polyfit(x,y,st)

    xinterp=np.arange(0,9,0.1)
    yinterp=np.polyval(wielomian,xinterp)

    plt.plot(x,y,'or',xinterp,yinterp,'.b')
    plt.show()

wiel(6)
wiel(8)
wiel(9)