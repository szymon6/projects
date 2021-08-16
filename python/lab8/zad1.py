import numpy as np
import matplotlib.pyplot as plt

def f1(x):
    return 6*(x**5)+5*(x**2)-5*x
def f2(x):
    return 1 / ((x - 0.3) ** 2 + 0.001) - 1 / ((x - 0.8) ** 2 + 0.04)
def f3(x):
    return (x**4)-6.4*(x**3)+6.45*(x**2)+20.538*x-31.752


def linerIncremental(fx, xstart, xd,maxincr):
    fstart=fx(xstart)

    for x in np.arange(xstart,maxincr,xd):
        if fstart*fx(x)<0:
            break
    if fstart*fx(x)>0:
        print("nie znaleziono wiecej miejsc zerowych\n")
        return
    else:
        return x-(xd*fx(x))/(fx(x)-fx(x-xd))


def bisectrion(fx,a,b,err):
    while np.absolute(b-a)>err:
        midPoint=(a+b)*0.5
        if fx(midPoint)*fx(a)<0:
            b=midPoint
        midPoint=(a+b)*0.5
        if(midPoint)*fx(b)<0:
            a=midPoint
    return b-(b-a)*fx(b)/(fx(b)-fx(a))




def wszystkie_mz_i(f,min,maxk,krok):
    mzt=[]
    mz=0
    i=0
    while(mz!=None):
        if i ==0:
            m=min
        else:
            m=mzt[i-1]+krok

        mz=linerIncremental(f,m,krok,maxk)

        if mz!=None:
            mzt.append(mz)
        else:
            break


        print(" mjejsce zerowe %f, blad: %f"%(mzt[i],f(mzt[i])))

        i += 1


    return np.asarray(mzt)




x=np.arange(-5,5,0.1)

#f1 i f2 i f3 motodą liniowej inkrementacji

#f1
mz_y1_inkr=wszystkie_mz_i(f1,-5,1000,0.01)
plt.plot(x,f1(x))
plt.plot(mz_y1_inkr,f2(mz_y1_inkr),"o")
plt.show()

#f2
mz_y2_inkr=wszystkie_mz_i(f2,0,1000,0.01)
plt.plot(x,f2(x))
plt.plot(mz_y2_inkr,f2(mz_y2_inkr),"o")
plt.show()

#f3
mz_y3_inkr=wszystkie_mz_i(f3,-5,1000,0.001)
plt.plot(x,f3(x))
plt.plot(mz_y3_inkr,f2(mz_y3_inkr),"o")
plt.show()

#f1 i f2 motodą bisekcji

#f1
plt.plot(x,f1(x))

mz=bisectrion(f1,-2,-1,0.01)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz=bisectrion(f1,-1,-0.01,0.01)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

mz=bisectrion(f1,1,0.1,0.01)
plt.plot(mz,f1(mz), 'or')
print(" mjejsce zerowe %f, blad: %f"%(mz,f1(mz)))

plt.show()


#f2
mz=bisectrion(f2,0,2,0.01)
plt.plot(x,f2(x))
plt.plot(mz,f2(mz), 'o')
plt.show()
print(" \n mjejsce zerowe %f, blad: %f"%(mz,f2(mz)))








