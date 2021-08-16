import numpy as np



def diag_dom_test(a):
    test = []
    for i in range(len(a)):
        s = sum([np.abs(a[i,j]) for j in range(len(a)) if i!=j])
        if np.abs(a[i,i])>=s:
            test.append(1)
        else:
            test.append(0)
    return test



def Gs(a, b, ile):

    w = np.zeros_like(b)
    n = len(a)
    for i2 in range(0, ile):

        for j in range(0, n):

            d = b[j]

            for i in range(0, n):
                if (j != i):
                    d -= a[j][i] * w[i]

            w[j] = d / a[j][j]

    error = np.dot(a, w) - b
    print("wynik: ",w )
    print("blad: ",error)
    print("test: ", diag_dom_test(a))





def Tablica4x5(t,ilosc):

    a=np.array([[t[0,0],t[1,0],t[2,0],t[3,0]],
                 [t[0,1],t[1,1],t[2,1],t[3,1]],
                 [t[0,2],t[1,2],t[2,2],t[3,2]],
                 [t[0,3],t[1,3],t[2,3],t[3,3]]
                 ]
                )


    b=np.array([t[0,4],t[0,4],t[0,4],t[0,4]])

    Gs(a,b,ilosc)


def Tablica3x4(t,ilosc):

    a=np.array([[t[0,0],t[1,0],t[2,0]],
                 [t[0,1],t[1,1],t[2,1]],
                 [t[0,2],t[1,2],t[2,2]]
                 ]
                )

    b=np.array([t[0,3],t[1,3],t[2,3]])

    Gs(a,b,ilosc)


A=np.array([[3,1,-1,181.05],[1,2,-1,108.35],[1,1,5,142.55]])
B=np.array([[1,-1,2,5],[3,2,1,10],[2,-3,-2,-10]])
D=np.array([[1,3,5,10],[2,5,1,8],[2,3,8,3]])
C=np.array([[5,1,1,1,685],[2,-1,-1,1,165],[3,-1,2,-2,256],[5,-4,3,-2,361]])

print("\ntablica A")
Tablica3x4(A,10)
print("\ntablica B")
Tablica3x4(B,10)
print("\ntablica C")
Tablica4x5(C,10)
print("\ntablica D")
Tablica3x4(D,10)











