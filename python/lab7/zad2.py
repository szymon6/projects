import numpy as np
from scipy.optimize import curve_fit
import pylab
import random as r

def f(x,a,b):
    return np.exp(-a*x)-b

x=np.arange(0,4,0.1)
y=[f(i,3,4)+r.random() for i in x]



popt = curve_fit(f,x,y)



yfitted=f(x,popt[0][0], popt[0][1])




pylab.plot(x,y,'.')
pylab.plot(x,yfitted)
pylab.show()










