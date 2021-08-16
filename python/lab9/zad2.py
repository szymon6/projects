import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as opt
import timeit

def f1(x):
    return 5*(x**5)+5*(x**2)-5*x
def f2(x):
    return 1 / ((x - 0.3) ** 2 + 0.001) - 1 / ((x - 0.8) ** 2 + 0.04)
def f3(x):
    return (x**4)-6.4*(x**3)+6.45*(x**2)+20.538*x-31.752


def newt(f,x0):
    return opt.newton(f,x0)


def bis(f,a,b):
    return opt.bisect(f,a,b,)


def ridd(f,a,b):
    return opt.ridder(f,a,b)

def bren(f,a,b):
    return opt.brenth(f,a,b)



x=np.arange(-5,5,0.01)




print("f1:")
print(newt(f1,-2),"blad: ",f1(newt(f1,-2)))
print(newt(f1,0),"blad: ",f1(newt(f1,0)))
print(newt(f1,0.5),"blad: ",f1(newt(f1,0.5)))

print("f3:")
print(newt(f3,-2),"blad: ",f3(newt(f3,-2)))
print(newt(f3,0),"blad: ",f3(newt(f3,0)))
print(newt(f3,4),"blad: ",f3(newt(f3,0)))


for f in [bis,ridd,bren]:



    print(f(f1,-2,-0.1),"blad: ",f1(f(f1,-2,-0.1)))
    print(f(f1,-0.1,0.1),"blad: ",f1(f(f1,-2,-0.1)))
    print(f(f1,0.1,1),"blad: ",f1(f(f1,-2,-0.1)))

    print(f(f3,-2,0),"blad: ",f3(f(f3,-2,0)))

    print(f(f3,2,5),"blad: ",f3(f(f3,2,5)))




print(timeit.timeit('newt(f1,-2)',number=100,setup="from __main__ import f1,newt"))
print(timeit.timeit('bis(f1,-2,-0.1)',number=100,setup="from __main__ import f1,bis"))
print(timeit.timeit('ridd(f1,-2,-0.1)',number=100,setup="from __main__ import f1,ridd"))
print(timeit.timeit('bren(f1,-2,-0.1)',number=100,setup="from __main__ import f1,bren"))















