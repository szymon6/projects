import numpy as np
xp = 0
xk=2*np.pi

krok=0.1

ilosc=np.abs((xk-xp)/krok)
ilosc=int(np.ceil(ilosc))


for i in range(ilosc):
    xp += krok
    print("i: %d, x:%f sin: %f, cos: %f, tan: %f, ctg: %f" % (i, xp, np.sin(xp),np.cos(xp),np.tan(xp),1.0/np.tan(xp)))
