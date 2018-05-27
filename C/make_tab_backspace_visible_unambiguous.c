#include "stdio.h"
#define SIZE_CHAR 50
/* 
	copy its input to its output, replacing each tab by \t, each backspace by \b, and each backslash by \\
	make tabs and backspaces visible in an unambiguous way.
*/
typedef enum { false, true} bool;

/* 
	make tabs and backspaces visible in an unambiguous way.
	input: a tring and size of it.
	output: above string that converted.
*/
void convert_Char(char* inputChar, int sizeofString);
int main()
{
	int c;
	int count = 0;
	char inputChar[SIZE_CHAR + 1];

	while ((c =getchar()) != EOF)
	{
		inputChar[count++] = c;
		if (c == '.'){
			break;
		}

	}

	inputChar[count] = '\0';
	printf("----------------- \n");
	printf("Input: \n %s \n", inputChar);
	
	// convert string
	convert_Char(inputChar, count);

	printf("----------------- \n");
	printf("Output: \n %s \n", inputChar);

	return 0;
}

void convert_Char(char* inputChar, int sizeofString)
{	
	int count = 0;
	char outputChar[SIZE_CHAR + 1];
	printf("%s", inputChar);

	for (int i=0; i < sizeofString; ++i)
	{
		if( inputChar[i] == '\t'){
			outputChar[count++] = '\\';
			outputChar[count++] = 't';
		}
		else if(inputChar[i] == '\b'){
			outputChar[count++] = '\\';
			outputChar[count++] = 'b';
		}
		else if(inputChar[i] == '\\')
			outputChar[count++] = '\\';
		else 
			outputChar[count++] = inputChar[i];
	}

	for (int i=0; i< count; i++){
		inputChar[i] = outputChar[i];
	}
	inputChar[count] = '\0';
}

