from scipy.interpolate import interp1d
import numpy as np
import matplotlib.pyplot as plt

xinterp = np.arange(-np.pi, np.pi-0.3, 0.08)
rodzaje=['linear','nearest','zero','slinear','quadratic','cubic']

def funkcja(x,y):

    wielomian=np.polyfit(x,y,10)
    yinterp=np.polyval(wielomian,xinterp)

    # ilustacja
    plt.plot(x, y, '^r', xinterp, yinterp)
    plt.show()







x = np.arange(-np.pi, np.pi, 0.4)
y = [np.sin(1 / x1) for x1 in x]

funkcja(x,y)


x2 = np.arange(-np.pi, np.pi, 0.4)
y2 = [np.exp(np.cos(x1)) for x1 in x2]

funkcja(x2,y2)

