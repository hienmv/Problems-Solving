#include <stdio.h>
#define SIZE_CHAR  50
typedef enum { false, true} bool;

/*
a program to copy its input to its output, replacing each string of one or more blanks by a single blank.
*/

/* 
input: a string, size of the string
output: input's string replacing each string of one or more blanks by a single blank.
*/
void convert_Char(char* inputChar, int sizeofInput);

int main()
{
	int c;
	int count = 0;
	char inputChar[SIZE_CHAR + 1];

 	while ((c = getchar()) != EOF)
	{
		inputChar[count++] = c;

		if (c == '.'){
			inputChar[count++] ='\0';
			break;
		}
	}

	printf("Input:\n %s \n", inputChar);
	printf("Output:\n");

	// convert string
	convert_Char(inputChar, count);
	
	printf("%s", inputChar);
	printf("\n");
	return 0;

}

void convert_Char(char* inputChar, int sizeofInput)
{
	bool char_blankFlg = false;
	char outputChar[SIZE_CHAR + 1];
	int count = 0;
	
	for(int i= 0; i < sizeofInput; i++)
	{
		if (char_blankFlg == true && inputChar[i] == ' '){
			continue;
		}else{
			outputChar[count++] = inputChar[i];
			if (char_blankFlg == true)
				char_blankFlg = false;
		}

		if (char_blankFlg == false && inputChar[i] == ' '){
			char_blankFlg = true;
		}

	}
	for (int i= 0; i < count; i++){
		inputChar[i] =  outputChar[i];
	}
	inputChar[count] = '\0';
}