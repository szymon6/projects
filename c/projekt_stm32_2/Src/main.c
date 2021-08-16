/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; Copyright (c) 2020 STMicroelectronics.
  * All rights reserved.</center></h2>
  *
  * This software component is licensed by ST under BSD 3-Clause license,
  * the "License"; You may not use this file except in compliance with the
  * License. You may obtain a copy of the License at:
  *                        opensource.org/licenses/BSD-3-Clause
  *
  ******************************************************************************
  */
/* USER CODE END Header */

/* Includes ------------------------------------------------------------------*/
#include "main.h"
#include "tim.h"
#include "usart.h"
#include "gpio.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */

#include "LCD.h"
//#include "symbols.h"

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
/* USER CODE BEGIN PFP */




/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

uint8_t numbers[]="0123456789";
uint8_t symbols[]="-+*/\r";

uint8_t operation=0;

uint8_t curSymbol=0;

uint8_t writeNumber=0;
uint8_t writeSymbol=0;

uint16_t curNumIndex=0;
uint16_t num[2];


int result=0;

uint16_t writeResult=0;



void Reset(){
	LCDclear();

	operation=0;
	curSymbol=0;
	writeNumber=0;
	writeSymbol=0;
    curNumIndex=0;
	result=0;
    writeResult=0;
    num[0]=0;
    num[1]=0;

}


uint8_t CheckAllowed(uint8_t symbol){

		  uint8_t i;
		  for(i=0;i<10;i++){
			  if(symbol==numbers[i])
			  return 1;
		  }

		  for(i=0;i<5;i++){
		    if(symbol==symbols[i])
		     return 2;
		 		  }

		  return 0;

}



void NumberReceived(){

	if(num[curNumIndex]==0&&curSymbol=='0')return;

	num[curNumIndex]*=10;
	num[curNumIndex]+=curSymbol-'0';

	writeNumber=1;
}


void Result(){


if(operation=='+')
	result=num[0]+num[1];

if(operation=='-')
	result=num[0]-num[1];

if(operation=='*')
	result=num[0]*num[1];

if(operation=='/')
	result=num[0]/num[1];


writeResult=1;


}



void SymbolReceived(){

	if(num[curNumIndex]==0)return;

	if(curSymbol=='\r'){

	if(!curNumIndex)return;

	if(curNumIndex){
    Result();
    return;
	}





	}

	operation=curSymbol;
	curNumIndex=1;
	writeSymbol=1;
}







void HAL_UART_RxCpltCallback(UART_HandleTypeDef *huart){




	  uint8_t allowed =  CheckAllowed(curSymbol);

		HAL_GPIO_TogglePin(LD_GPIO_Port, LD_Pin);

	  if(allowed==1)
		  NumberReceived();
	  else if(allowed ==2)
		  SymbolReceived();



	  HAL_UART_Receive_IT(&huart2,&curSymbol,1);
}



/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_TIM1_Init();
  MX_USART2_UART_Init();
  /* USER CODE BEGIN 2 */
  HAL_UART_Receive_IT(&huart2,&curSymbol,1);

  HAL_Delay(100);
  LCDinit();

  //LCDwriteString(symbols);




  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */









  while (1)
  {

	if(writeNumber){
		LCDdata(curSymbol);
		writeNumber=0;


	}

	if(writeSymbol){
		LCDdata(curSymbol);
		writeSymbol=0;

		LCDSetCursorAt(0, 1);


	}





	if(writeResult){
			LCDclear();

			uint8_t str[16];
			sprintf(str,"%d",result);


			LCDwriteString("Wynik:");
			LCDSetCursorAt(0, 1);
			LCDwriteString(str);



			HAL_Delay(2000);

			Reset();

		}





    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSI;
  RCC_OscInitStruct.HSIState = RCC_HSI_ON;
  RCC_OscInitStruct.HSICalibrationValue = RCC_HSICALIBRATION_DEFAULT;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_NONE;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_HSI;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_0) != HAL_OK)
  {
    Error_Handler();
  }
}

/* USER CODE BEGIN 4 */

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */

  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{ 
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     tex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
