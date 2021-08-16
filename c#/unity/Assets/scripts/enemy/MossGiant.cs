using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MossGiant : Enemy, IDamgeable
{
    // Start is called before the first frame update

    private Vector3 _currentTarget;
    private Animator _animMossGiant;
    private SpriteRenderer _mossSprite;

    bool stopMovement;




    public void Damage()
    {
        if (stopMovement) return;



        health--;

        if (health < 1)
        {
            Destroy(this.gameObject, 3);

            stopMovement = true;
            GameObject diamonds = Instantiate(diamonPrefab, transform.position, Quaternion.identity);
            diamonds.GetComponent<Diamond>().gems = 3;
            _animMossGiant.SetTrigger("Death");
        }
        else
        {
            _animMossGiant.SetTrigger("Hit");
        }

    }


    void Start()
    {


        _animMossGiant = GetComponentInChildren<Animator>();
        _mossSprite = GetComponentInChildren<SpriteRenderer>();
        _currentTarget = pointB.position;

    }

    // Update is called once per frame
    void Update()
    {

        if (_animMossGiant.GetCurrentAnimatorStateInfo(0).IsName("Idle") || stopMovement)
        {
            return;
        }


        Movement();
    }

    private void Movement()
    {

        if (_currentTarget == pointB.position) _mossSprite.flipX = false; else _mossSprite.flipX = true;


        if (transform.position == pointA.position)
        {
            _currentTarget = pointB.position;
            _animMossGiant.SetTrigger("Idle");

        }
        else if (transform.position == pointB.position)
        {
            _currentTarget = pointA.position;
            _animMossGiant.SetTrigger("Idle");
        }


        transform.position = Vector3.MoveTowards(transform.position, _currentTarget, speed * Time.deltaTime);

    }

    public override void Attack(IDamgeable damgeable)
    {
        base.Attack(damgeable);
        Debug.Log("Dodatkowy atak");
        _animMossGiant.SetTrigger("Attack");

    }

    private void OnTriggerEnter2D(Collider2D coll)
    {
        if (stopMovement) return;

        if (coll.gameObject.tag == "Player")
        {

            var player = coll.GetComponent<PlayerMovement>();
            Attack(player);
        }
    }

}
