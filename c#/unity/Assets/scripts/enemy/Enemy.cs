using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class Enemy : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    public GameObject diamonPrefab;

    [SerializeField]
    protected int health;

    [SerializeField]
    protected int speed;

    [SerializeField]
    protected int gems;

    [SerializeField]
    protected Transform pointA, pointB;



    public virtual void Attack(IDamgeable damgeable)
    {
        damgeable.Damage();
    }

    

    // Update is called once per frame
    void Update()
    {

        
    }


   


}
