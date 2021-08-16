using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class UiManager : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }


    static UiManager _instance;

    public Text gemCountText;
    public Hud hud;

 

    public void UpdateGemCout(int count)
    {
        gemCountText.text = "" + count;
    }

  


  




    public static UiManager Instance
    {


        get
        {

            if(_instance == null)
            {
                Debug.Log("Ui jest null");
            }
            return _instance;

        }


    }

    private void Awake()
    {
        _instance = this;
    }






    // Update is called once per frame
    void Update()
    {
        
    }
}
