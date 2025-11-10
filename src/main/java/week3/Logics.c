/**
 * C program to interweave two strings
 * 
 * @author Bhavya Jain
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *interweave(char *s1, char *s2)
{
    int n = strlen(s1);
    int m = strlen(s2);
    char *result = (char *)malloc((n + m + 1) * sizeof(char));
    int i = 0, j = 0, k = 0;
    while (s1[i] && s2[j])
    {
        result[k++] = s1[i++];
        result[k++] = s2[j++];
    }
    while (s1[i])
        result[k++] = s1[i++];
    while (s2[j])
        result[k++] = s2[j++];
    result[k] = '\0';
    return result;
}

int main()
{
    char s1[100], s2[100];
    printf("Enter first string: ");
    scanf("%99s", s1);
    printf("Enter second string: ");
    scanf("%99s", s2);
    char *result = interweave(s1, s2);
    printf("Interwoven string: %s\n", result);
    free(result);
    return 0;
}