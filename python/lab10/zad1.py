import numpy as np

def det(A):
    return sum([(-1)**i*A[i][0]*det(np.delete(np.delete(A,0,1),i,0)) for i in range(A.shape[0])]) if A.shape!=(1,1) else A[0][0]

def M3x3(A,k0,k1,k2):
    D= np.array([[0 for x in range(3)] for y in range(3)], np.double)

    for i in range(3):
        D[i][0]=A[i][k0]
        D[i][1] = A[i][k1]
        D[i][2] = A[i][k2]
    return D

def M4x4(A,k0,k1,k2,k3):
    D= np.array([[0 for x in range(4)] for y in range(4)], np.double)

    for i in range(4):
        D[i][0]=A[i][k0]
        D[i][1] = A[i][k1]
        D[i][2] = A[i][k2]
        D[i][3] = A[i][k3]
    return D


def rozwiazanie3x4(A):

    D=det(M3x3(A,0,1,2))
    D1=det(M3x3(A,3,1,2))
    D2=det(M3x3(A,0,3,2))
    D3=det(M3x3(A,0,1,3))

    x1=D1/D
    x2=D2/D
    x3=D3/D

    print("x1:%f, x2:%f, x3:%f"%(x1,x2,x3))


def rozwiazanie4x5(A):

    D=det(M4x4(A,0,1,2,3))
    D1=det(M4x4(A,4,1,2,3))
    D2=det(M4x4(A,0,4,2,3))
    D3=det(M4x4(A,0,1,4,3))
    D4=det(M4x4(A,0,1,2,4))


    x1=D1/D
    x2=D2/D
    x3=D3/D
    x4=D4/D

    print("x1:%f, x2:%f, x3:%f,x4:%f"%(x1,x2,x3,x4))

A=np.array([[3,1,-1,181.05],[1,2,-1,108.35],[1,1,5,142.55]])
B=np.array([[1,-1,2,5],[3,2,1,10],[2,-3,-2,-10]])
D=np.array([[1,3,5,10],[2,5,1,8],[2,3,8,3]])
C=np.array([[5,1,1,1,685],[2,-1,-1,1,165],[3,-1,2,-2,256],[5,-4,3,-2,361]])

print("a:")
rozwiazanie3x4(A)
print("b:")
rozwiazanie3x4(B)
print("c:")
rozwiazanie4x5(C)
print("d:")
rozwiazanie3x4(D)


#Sprawdzenie
As=np.array([[3,1,-1],[1,2,-1],[1,1,5]])
Bs=np.array([[1,-1,2],[3,2,1],[2,-3,-2]])
Ds=np.array([[1,3,5],[2,5,1],[2,3,8]])
Cs=np.array([[5,1,1,1],[2,-1,-1,1],[3,-1,2,-2],[5,-4,3,-2]])

Asw=np.array([181.05,108.35,142.55])
Bsw=np.array([5,10,-10])
Csw=np.array([685,165,256,361])
Dsw=np.array([10,8,3])


Asr=np.linalg.solve(As,Asw)
Bsr=np.linalg.solve(Bs,Bsw)
Csr=np.linalg.solve(Cs,Csw)
Dsr=np.linalg.solve(Ds,Dsw)

print(Asr)
print(Bsr)
print(Csr)
print(Dsr)



