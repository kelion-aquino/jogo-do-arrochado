#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define MAX 100

int main(void)
{

    int maior = 0, menor = 0, size = 0, extremidade_menor = 0;
    int extremidade_maior = 100;
    char firstName[20];
    int tries[MAX];
    int input;
    int aux = 0;
    double media = 0.0;
    double mediana = 0.0;
    double variancia = 0.0;
    double desvio = 0.0;

    // gerando um numero randomicamente
    int randomNumber = rand() % 100;

    // nome do usuario
    printf("\nDigite seu nome:");
    gets(firstName);
    
    //jogo do arrochado
    do {
        printf("\n\nDigite seu palpite!\nDICA: O numero esta no intervalo de %d ~ %d:", extremidade_menor, extremidade_maior);
        scanf("%d", &input);

        // validar se esta no range definido
    	if (input > extremidade_maior || input < extremidade_menor)    
            printf("\n=>O numero digitado nao se encontra no range determinado de %d ~ %d\n  Tente novamente.", extremidade_menor, extremidade_maior);
        else {
            
            if (size == 0)
                menor = input;

            // armazena o maior e menor valor
            if (input > maior)
                maior = input;
            else if (input < menor)
                menor = input;

            if (input == randomNumber){
                tries[size] = input;
                size++;
                break;
            }

            if (randomNumber > input)
                extremidade_menor = input;
            else if (randomNumber < input)
                extremidade_maior = input;
            
            tries[size] = input;

            printf("=>Valor errado, tente novamente!\n");
            size++;
        };

    }while(input != randomNumber);

    printf("\n=>Parabens %s,\n  Voce acertou o numero!\n\n", firstName);

    // ordenar
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if(tries[i] < tries[j]){
                aux = tries[i];
                tries[i] = tries[j];
                tries[j] = aux;
            }
        }
    }

    for (int k = 0; k < size; k++)
    {
        media += tries[k];
        printf("[%d]", tries[k]);
    }
    
    // media
    media /= size;
    printf("\n==> A media dos numeros digitados foi %.2f", media);

    // mediana
    aux = size;
    if(aux % 2 == 0) {
        aux = (int) aux/2;
        mediana = (tries[aux - 1] + tries[aux])/2;
    } else {
        aux += 1;
        aux = (int) aux/2;
        mediana = tries[aux-1];
    }

    // variancia
    for (int z = 0; z < size; z++)
    {
        variancia += ((tries[z] - media) * (tries[z] - media));
    }

    if (size-1 != 0)
        variancia /= size - 1;
    else
        variancia /= size;
    
    // desvio
    desvio = sqrt(variancia);

    printf("\n==> O maior numero digitado foi %d e o menor %d", maior, menor);
    printf("\n==> A mediana dos numeros digitados foi %.2f", mediana);
    printf("\n==> A variancia dos numeros digitados foi %.2f", variancia);
    printf("\n==> O desvio padrao dos numeros digitados foi %.2f", desvio);

    return 0;
}

