import lab7_approx_polynomial as a
import numpy as np
import matplotlib.pyplot as plt
import random as r

x=np.arange(0,20,1)


#y=[6*(i**6)+5*(i**2)+8*i+np.random.random(200) for i in x]
y=[i**r.random() for i in x]



for i in [3,5,7,10]:
    w=a.polyFit(x,y,i)
    a.plotPoly(x,y,w)


