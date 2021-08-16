from scipy.interpolate import interp1d
import numpy as np
import matplotlib.pyplot as plt

xinterp = np.arange(-np.pi, np.pi-0.3, 0.08)
rodzaje=['linear','nearest','zero','slinear','quadratic','cubic']

def funkcja(x,y, str):

    finterp = interp1d(x, y, kind=str)
    yinterp = finterp(xinterp)

    # ilustacja
    plt.plot(x, y, '^r', xinterp, yinterp, label=str)
    plt.show()


def funkcje(x,y):
    for i in rodzaje:
        funkcja(x,y,i)



x = np.arange(-np.pi, np.pi, 0.4)
y = [np.sin(1 / x1) for x1 in x]

funkcje(x,y)


x2 = np.arange(-np.pi, np.pi, 0.4)
y2 = [np.exp(np.cos(x1)) for x1 in x2]

funkcje(x2,y2)

