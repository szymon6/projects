using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Diamond : MonoBehaviour
{

    public int gems = 1;



    private void OnTriggerEnter2D(Collider2D coll)
    {
       
        if (coll.tag == "Player")
        {

             PlayerMovement player = coll.GetComponent<PlayerMovement>();

            if(player != null)
            {
                player.AddGems(gems);
                Destroy(this.gameObject);

            }


        }



    }



}
