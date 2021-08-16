using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spider : Enemy, IDamgeable
{
    // Start is called before the first frame update

    private Vector3 _currentTarget;
    private Animator _animSpider;
    private SpriteRenderer _mossSprite;

    private bool stopMovement;

    void Start()
    {
     
        _animSpider = GetComponentInChildren<Animator>();
        _mossSprite = GetComponentInChildren<SpriteRenderer>();
        _currentTarget = pointB.position;

    }

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
            _animSpider.SetTrigger("Death");
        }
        else
        {
            _animSpider.SetTrigger("Hit");
        }

    }

    // Update is called once per frame
    void Update()
    {

        if (_animSpider.GetCurrentAnimatorStateInfo(0).IsName("Idle") || stopMovement)
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
            _animSpider.SetTrigger("Idle");

        }
        else if (transform.position == pointB.position)
        {
            _currentTarget = pointA.position;
            _animSpider.SetTrigger("Idle");
        }

        transform.position = Vector3.MoveTowards(transform.position, _currentTarget, speed * Time.deltaTime);

    }

    public override void Attack(IDamgeable damgeable)
    {
        base.Attack(damgeable);
        Debug.Log("Dodatkowy atak");
        _animSpider.SetTrigger("Attack");

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

   

