using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Hud : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    public Image[] bars;
    int curr = 3;


    public void DelNext()
    {
        if (curr < 0) return;

        bars[curr].enabled = false;
        curr--;
    }
    


    // Update is called once per frame
    void Update()
    {
        
    }
}
