import matplotlib.pyplot as plt
import numpy as np

def rysuj(f,xp,xk,k):

    x=[i for i in np.arange(xp,xk,k)]
    y=[i**f for i in np.arange(xp,xk,k)]


    plt.plot(x,y)
    plt.ylabel("f(x)")
    plt.xlabel("x")
    plt.title("f(x_=x^2")
    plt.show()

rysuj(3,-5,10,0.01)