using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAnimation : MonoBehaviour
{
    // Start is called before the first frame update
  

    // Update is called once per frame
    void Update()
    {
        
    }


    private Animator _anim;
    private SpriteRenderer playerSpriteRenderer;
    private Animator _swordAnim;
    private SpriteRenderer swordSpriteRenderer;

    void Start()
    {
        _anim = GetComponentInChildren<Animator>();
        playerSpriteRenderer = GetComponentInChildren<SpriteRenderer>();
        _swordAnim=transform.GetChild(1).GetComponent<Animator>();
        swordSpriteRenderer = transform.GetChild(1).GetComponent<SpriteRenderer>();
    }

    public void Move(float move) {
        _anim.SetFloat("Move", Mathf.Abs(move));

        if (move > 0)
        {
            Flip(true);
        }
        else if (move < 0)
        {
            Flip(false);
        }

    }

    void Flip(bool faceRight)
    {
        if (faceRight == true)
        {
            playerSpriteRenderer.flipX = false;

            swordSpriteRenderer.flipX = false;
            swordSpriteRenderer.flipY = false;
            Vector3 newPos = swordSpriteRenderer.transform.localPosition;
            newPos.x = 1.01f;
            swordSpriteRenderer.transform.localPosition = newPos;

        }
        else if (faceRight == false)
        {
            playerSpriteRenderer.flipX = true;

            swordSpriteRenderer.flipX = true;
            swordSpriteRenderer.flipY = true;
            Vector3 newPos = swordSpriteRenderer.transform.localPosition;
            newPos.x = -1.01f;
            swordSpriteRenderer.transform.localPosition = newPos;
        }
    }

    public void Jump(bool jumping)
    {
        _anim.SetBool("Jumping", jumping);
    }

    public void Attack()
    {
        _anim.SetTrigger("Attack");
        _swordAnim.SetTrigger("SwordAnimation");
    }

    public void Hit()
    {
        _anim.SetTrigger("Hit");
    }

    public void Death()
    {
        _anim.SetTrigger("Death");
    }

    

}
