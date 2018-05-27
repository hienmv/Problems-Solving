#include <stdio.h>
/*
 in Oxy, two rectangles A1B1B1D1 and A2B2C2D2. each of edges is parallel with Ox or Oy
Input:  A1,C1,A2,C2 coordinates
Prerequisite: A1.x <= A2.x && A1.x < C1.x && A2.x < C2.x
Output:  sum of area A1B1C1D1 and A2B2C2D2 with O(1)
*/

int max(int a, int b) {
    return a > b ? a : b;
}

int min(int a, int b) {
    return a > b ? b : a;
}

// caculate substraction mixed-y coordinates
int mixed_y_coordinate_subtraction(int a, int b, int c, int d)
{
    int max_y, min_y, substr;
    max_y = max(max(a, b), max(c, d));
    min_y = min(min(a, b), min(c, d));

    if (a == max_y) {
    	a = 0;
    } else if (b == max_y) {
    	b = 0;
    } else if (c == max_y) {
    	c = 0;
    } else if (d == max_y) {
    	d = 0;
    } 

    if (a == min_y) {
    	a = 0;
    } else if (b == min_y) {   
    	b = 0;
    } else if (c == min_y) {
    	c = 0;
    } else if (d == min_y) {
    	d = 0;
    }

    if (a == 0 && b == 0) {
    	substr = c - d;
    } else if (a == 0 && c == 0) {
    	substr = b - d;
    } else if (a == 0 && d == 0) {
    	substr = b - c;
    } else if (b == 0 && c == 0) {
    	substr = a - d;
    } else if ( b == 0 && d == 0) {
    	substr = a - c;
    } else {
    	substr = a - b;
    }

    return substr;
}

int main()
{
    int x_a1, x_a2, x_c1, x_c2;
    int y_a1, y_a2, y_c1, y_c2;
    int area1, area2, mixed_area;
    int sumOfArea = 0;

    // input
    printf("input A1 coordinate:\n");
    scanf("%d %d", &x_a1, &y_a1);
    printf("input C1 coordinate: \n");
    scanf("%d %d", &x_c1, &y_c1);
    printf("Rectangle 1: A1(%d, %d), C1(%d, %d) \n", x_a1, y_a1, x_c1, y_c1);
    
    printf("input A2 coordinate: \n");
    scanf("%d %d", &x_a2, &y_a2);
    printf("input C2 coorinate: \n");
    scanf("%d %d", &x_c2, &y_c2);
    printf("Rectangle 2: A2(%d, %d), C2(%d, %d) \n", x_a2, y_a2, x_c2, y_c2);

    // caculate area
    // 1. area A1B1C1D1
    area1 = (x_c1 - x_a1) * (y_c1 - y_a1);
    if (area1 < 0) {
        area1 = -area1;
    }
    printf("Area 1 = %d \n", area1);

    // 2. area A2B2C2D2
    area2 = (x_c2 - x_a2) * (y_c2 - y_a2);
    if (area2 < 0) {
        area2 = -area2;
    }
    printf("Area 2 = %d \n", area2);
    
    // 3. mixed_area
    mixed_area = (min(x_c1, x_c2) - x_a2) * mixed_y_coordinate_subtraction(y_a1, y_c1, y_a2, y_c2);
    if (mixed_area < 0) {
        mixed_area = -mixed_area;
    }
    printf("Mixed Area = %d \n", mixed_area);

    // meassure if there is a mixed-area between two areas.
    if (x_a2 <= x_c1
        && ((min(y_a1, y_c1) <= min(y_a2, y_c2) && min(y_a2, y_c2) <= max(y_a1, y_c1))
            || (min(y_a1, y_c1) <= max(y_a2, y_c2) && max(y_a2, y_c2) <= max(y_a1, y_c1))
            || (min(y_a2, y_c2) <= min(y_a1, y_c1) && min(y_a1, y_c1) <= max(y_a2, y_c2))
            || (min(y_a2, y_c2) <= max(y_a1, y_c1) && max(y_a1, y_c1) <= max(y_a2, y_c2))
            )
        )
    {
        if (x_c2 <= x_c1
            && min(y_a1, y_c1) <= min(y_a2, y_c2) && min(y_a2, y_c2) <= max(y_a1, y_c1)
            && min(y_a1, y_c1) <= max(y_a2, y_c2) && max(y_a2, y_c2) <= max(y_a1, y_c1))
        {
            sumOfArea = area1;
        } else {
            sumOfArea = area1 + area2 - mixed_area;
        }
    } else {
        sumOfArea = area1 + area2;
    }

    printf("Sum of Area: %d \n", sumOfArea);

    return 0;
}

