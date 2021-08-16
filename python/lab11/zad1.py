import numpy as np


def rozwiazanie4x5(M):

    M=np.array([M[0]/M[0][0],M[1],M[2],M[3]])
    M = np.array([M[0], M[1]-M[0]*M[1][0], M[2],M[3]])
    M = np.array([M[0], M[1], M[2]-M[0]*M[2][0],M[3]])
    M = np.array([M[0], M[1], M[2] - M[0] * M[2][0], M[3]])
    M = np.array([M[0], M[1], M[2], M[3]-M[0]*M[3][0]])



    M = np.array([M[0], M[1]/M[1][1], M[2],M[3]])
    M = np.array([M[0], M[1], M[2]-M[1]*M[2][1],M[3]])
    M = np.array([M[0], M[1], M[2], M[3]-M[1]*M[3][1]])



    M = np.array([M[0], M[1], M[2]/M[2][2],M[3]])
    M = np.array([M[0], M[1], M[2], M[3] -M[2]*M[3][2]])


    M = np.array([M[0], M[1], M[2], M[3]/M[3][3]])




    x4=M[3][4]
    x3=M[2][4] - M[2][3]*x4
    x2 = M[1][4] -M[1][3]*x4-M[1][2]*x3
    x1 = M[0][4] -M[0][3]*x4-M[0][2]*x3 - M[0][1]*x2





    print("x1:%f,x3:%f,x3:%f,x4:%f "%(x1,x2,x3,x4))




def rozwiazanie3x4(M):

    M=np.array([M[0]/M[0][0],M[1],M[2]])
    M = np.array([M[0], M[1]-M[0]*M[1][0], M[2]])
    M = np.array([M[0], M[1], M[2]-M[0]*M[2][0]])


    M = np.array([M[0], M[1]/M[1][1], M[2]])
    M = np.array([M[0], M[1], M[2]-M[1]*M[2][1]])


    M = np.array([M[0], M[1], M[2]/M[2][2]])


    x3=M[2][3]
    x2=M[1][3]-M[1][2]*x3
    x1=M[0][3]-M[0][2]*x3-M[0][1]*x2



    print("x1:%f,x2:%f,x3:%f "%(x1,x2,x3))







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






