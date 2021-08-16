import numpy

print("podaj a")
a=float(input())

print("podaj b")
b=float(input())

print("podaj c")
c=float(input())

if a==0 and b==0 and c==0:
    print("rownanie tozsamosciowe")
    exit(0)
if a == 0 and b == 0 and c != 0:
    print("sprzecznosc")
    exit(0)
if (a == 0 and b != 0 and c == 0) or (a != 0 and b == 0 and c == 0):
    print("x=0")
    exit(0)
if a == 0 and b != 0 and c != 0:
    x1 = -c / b;
    print("rownanie liniowe, x=", x1);
    exit(0)


delta = b*b - 4*a*c

if delta > 0:

    p=numpy.sqrt(delta)

    x1=(-b+p) / (2 * a)
    x2=(-b-p) / (2 * a)

    print(" x1 = ", x1)
    print(" x2 = ", x2)

else:
    p = numpy.sqrt(-delta)

    x1Re = -b / (2 * a)
    x1In = -p / (2 * a)

    x2Re = -b / (2 * a)
    x2In = p / (2 * a)

    print(" x1=%f%+fj" % (x1Re, x1In))
    print(" x2=%f%+fj" % (x2Re, x2In))





