import numpy as np
import matplotlib.pyplot as plt

def f1(x):
    return 6*(x**5)+5*(x**2)-5*x
def f2(x):
    return 1 / ((x - 0.3) ** 2 + 0.001) - 1 / ((x - 0.8) ** 2 + 0.04)
def f3(x):
    return (x**4)-6.4*(x**3)+6.45*(x**2)+20.538*x-31.752



def secant(fx,a,b,err):
    fb=fx(b)

    while np.absolute(fb)>err:
        midPoint = b-(b-a)*fb/(fb-fx(a))
        a=b
        b=midPoint
        fb=fx(b)
    return b

def newton_raphson(fx,x0,err):
    x=x0
    h=0.1e-5
    while np.absolute(fx(x))>err:
        dlfx=fx(x+h/2.0)-fx(x-h/2.0)/h
        x=x-fx(x)/dlfx
    return x








x=np.arange(-5,5,0.1)





#f1 i f2 motodą siecznych

#f1
plt.plot(x,f1(x))

mz=secant(f1,-2,-1,0.0001)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz=secant(f1,-1,-0.01,0.0001)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz=secant(f1,0.5,1,0.0001)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

plt.show()


#f2
mz=secant(f2,0,1,0.0001)
plt.plot(x,f2(x))
plt.plot(mz,f2(mz), 'o')
plt.show()
print(" \n mjejsce zerowe %f, blad: %f"%(mz,f2(mz)))


#f1 i f2 motodą Newtona-Raphsona

plt.plot(x, f1(x))

mz = newton_raphson(f1, -2, 0.01)
plt.plot(mz, f1(mz), 'or')
print("\n mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz = newton_raphson(f1, -0.1, 0.01)
plt.plot(mz, f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz = newton_raphson(f1, 0.5, 0.01)
plt.plot(mz, f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

plt.show()

#f2
plt.plot(x, f2(x))

mz = newton_raphson(f2, 0, 0.01)
plt.plot(mz, f2(mz), 'or')
print("\n mjejsce zerowe %f, blad: %f"%(mz,f2(mz)))
plt.show()








