using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Attack : MonoBehaviour
{

    bool _canDamage = true;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }



    


    private void OnTriggerEnter2D(Collider2D collision)
    {
    

        IDamgeable hit = collision.GetComponent<IDamgeable>();


        if (_canDamage == true)
        {
            if (hit != null)
            {
                hit.Damage();
                _canDamage = false;
                StartCoroutine(ResetDamage());
            }
        }

        IEnumerator ResetDamage()
        {
            yield return new WaitForSeconds(0.5f);
            _canDamage = true;
        }

    }


}
