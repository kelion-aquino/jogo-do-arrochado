from random import randint


def ordenar(numeros):
    em_ordem = []
    media = 0
    
    for i in range(len(numeros)):
        media += numeros[i]

        if i == 0: #pegar o 1º numero exibido sem validar
            em_ordem.append(numeros[i])
        else:
            for j in range(len(em_ordem)): #enquanto estiver no tamanho do novo array ordenado
                if numeros[i] >= em_ordem[len(em_ordem)-1]: #se o número for maior que o último no array ordenado, adiciona e break
                    em_ordem.append(numeros[i])
                    break
                elif numeros[i] < em_ordem[j]: #se for menor que o número já ordenado, insere na posição e break
                    em_ordem.insert(j, numeros[i])
                    break

    # exibir
    print("\n==> Seus números:")  
    for x in em_ordem:
        print(x, end=", ")
    
    media /= len(numeros)    
    
    print(f'\n\n==> A média dos números digitados foi {media:.2f}')
    return em_ordem, media


def estatisticas(ordenados, media):
    mediana = 0
    variancia = 0
    desvio = 0
    
    #mediana
    y = len(ordenados)
    if y % 2 == 0:
        y = int(y/2)
        mediana = (ordenados[y-1] + ordenados[y])/2
    else:
        y += 1
        y = int(y/2)
        mediana = ordenados[y-1]

    # variancia
    for num in ordenados:
        variancia += ((num - media) * (num - media))
    
    if len(ordenados)-1 != 0:
        variancia /= (len(ordenados)-1)
    else:
        variancia /= len(ordenados)
    
    desvio = variancia ** 0.5 #raiz quadrada com o operador **
    
    print(f'==> A mediana dos números digitados foi {mediana:.1f}')
    print(f'==> A variância dos números digitados foi {variancia:.2f}')
    print(f'==> O desvio padrão dos números digitados foi {desvio:.2f}')

    
def arrocha():
    RANDOM = randint(0, 100)
    #print(RANDOM)
    numeros = []

    maior = menor = i = extremidade_menor = 0
    extremidade_maior = 100

    nome = input("Digite seu nome:")

    while True:
        numeros.append(int(input(f'\nDigite seu palpite!\nDICA: O número está no intervalo de {extremidade_menor} ~ {extremidade_maior}:')))

        if numeros[i] > extremidade_maior or numeros[i] < extremidade_menor: # validar se está no range descrito
            print(f"\n=>O número digitado não se encontra no range determinado de {extremidade_menor} ~ {extremidade_maior}\n  Tente novamente.")
            del numeros[i]
        else:
            # pegar igualar o menor valor ao primeiro número digitado válido
            if i == 0:
                menor = numeros[i]        

            # pegar o maior e menor valor
            if numeros[i] > maior:
                maior = numeros[i]
            if numeros[i] < menor:
                menor = numeros[i]

            if numeros[i] == RANDOM:
                break

            if RANDOM > numeros[i]: #para pegar o novo intervalo menor, o número tem que ser maior que o intervalo menor anterior e menor que o número gerado
                extremidade_menor = numeros[i]
            elif RANDOM < numeros[i]: #para pegar o novo intervalo maior, o número tem que ser menor que o intervalo maior anterior e maior que o número gerado
                extremidade_maior = numeros[i]

            print("=>Valor errado, tente novamente!")
            i += 1

    print(f"\n=>Parabéns {nome},\n  Você acertou o número!")

    ordenados, media = ordenar(numeros)
    estatisticas(ordenados, media)    
    
    print(f'==> O maior número digitado foi {maior} e o menor {menor}')
    
arrocha()