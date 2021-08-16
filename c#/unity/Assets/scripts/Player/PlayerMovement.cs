using System.Collections;
using System.Collections.Generic;
using UnityEngine;
public class PlayerMovement : MonoBehaviour, IDamgeable
{
    private Rigidbody2D rb;
    //SerializedField oznacza możliwość zmiany w inspektorze bez upubliczniania zmniennej
    [SerializeField]
    private float jumpForce = 7.0f;
    [SerializeField]
    private float speed = 2.0f;

    private PlayerAnimation _anim;
    public LayerMask groundLayer;

    public int diamonds;

    public int health;

    bool stopMovement;

    public void Damage()
    {
        Debug.Log("atak na gracza");
        if (stopMovement) return;


        health--;
        UiManager.Instance.hud.DelNext();

        if (health < 1)
        {
            stopMovement = true;
            _anim.Death();
        }
        else
        {
            _anim.Hit();
        }

    }

    void Start()
    {
        // połączenie z komponentem Rigidbody
        rb = GetComponent<Rigidbody2D>();
        _anim =  GetComponent<PlayerAnimation>();
        
    }

    public void AddGems(int amout)
    {
        diamonds += amout;
        UiManager.Instance.UpdateGemCout(diamonds);
    }

    void Update()
    {
        if (stopMovement) return;
        Movement();

        if (Input.GetMouseButtonDown(0) && IsGrounded())
        {
            _anim.Attack();
        }


    }
    void Movement()
    {
        var isGrounded = IsGrounded();
        float move = Input.GetAxisRaw("Horizontal");
        if (Input.GetKeyDown(KeyCode.Space) == true && isGrounded)
        { //dodanie skoku do prędkości ruchu
            rb.velocity = new Vector2(rb.velocity.x, jumpForce);
            _anim.Jump(true);
        } //dodanie prędkości ruchu do komponentu rigidbody
        rb.velocity = new Vector2(move * speed, rb.velocity.y);

        _anim.Move(move);

      
    }




    bool IsGrounded()
    {

        Vector2 position = transform.position;
        Vector2 direction = Vector2.down;
        float distance = 1.0f;

        Debug.DrawRay(transform.position, Vector2.down * 1, Color.green);
        RaycastHit2D hit = Physics2D.Raycast(position, direction, distance, groundLayer);
        if (hit.collider != null)
        {
          //  Debug.Log("HIT: " + hit.collider.name);
            _anim.Jump(false);
            return true;
        }

        return false;



     
    }



}