
import numpy as np

x=np.float32(0.0)
x_d=np.float32(np.arange(0,1.1,0.1))
roznica=1e-8
i=0

while abs(x-1.1)>roznica:
    if(x!=0):
        print("x=%19.17g, abs(x-x_d)=%19.17g, blad %lf" % (x,abs(x-x_d[i]),abs(x-x_d[i])/x*100))
    x=x+0.1
    i=i+1

